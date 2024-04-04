package Emerging.App.Backend.Controller;

import Emerging.App.Backend.Entities.CreatedApplication;
import Emerging.App.Backend.Entities.MyUserDetails;
import Emerging.App.Backend.Entities.SentApplication;
import Emerging.App.Backend.Entities.Users;
import Emerging.App.Backend.JSON_Objects.Faculty.CreateProjectRequest;
import Emerging.App.Backend.JSON_Objects.Faculty.FacultyApplicationResponse;
import Emerging.App.Backend.JSON_Objects.Faculty.FacultyProjectResponse;
import Emerging.App.Backend.JSON_Objects.Faculty.FacultyReceivedApplicationsResponse;
import Emerging.App.Backend.Repository.CreatedApplicationRepository;
import Emerging.App.Backend.Repository.SentApplicationRepository;
import Emerging.App.Backend.Repository.UsersRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/faculty")
public class FacultyRestController {

    private CreatedApplicationRepository createdApplicationRepository;
    private UsersRepository usersRepository;
    private SentApplicationRepository sentApplicationRepository;

    @Autowired
    public FacultyRestController(SentApplicationRepository sentApplicationRepository, CreatedApplicationRepository createdApplicationRepository, UsersRepository usersRepository){
        this.createdApplicationRepository = createdApplicationRepository;
        this.usersRepository = usersRepository;
        this.sentApplicationRepository = sentApplicationRepository;
    }

    @GetMapping("/project")
    public ResponseEntity<?> getProject(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> usersOptional = usersRepository.findByUsername(username);
        FacultyProjectResponse facultyProjectResponse = new FacultyProjectResponse();
        if(usersOptional.isEmpty()){
            facultyProjectResponse.setStatusMessage("Given user doesn't exist");
            return new ResponseEntity<>(facultyProjectResponse, HttpStatus.NOT_FOUND);
        }
        Users user = usersOptional.get();
        int id = user.getUserId();
        Optional<CreatedApplication> createdApplicationOptional = createdApplicationRepository.findByCreatorUserId(id);

        if(createdApplicationOptional.isEmpty()){
            facultyProjectResponse.setStatusMessage("No applications were found.");
            return new ResponseEntity<>(facultyProjectResponse, HttpStatus.NOT_FOUND);
        }

        CreatedApplication createdApplication = createdApplicationOptional.get();
        facultyProjectResponse.setName(createdApplication.getName());
        facultyProjectResponse.setDescription(createdApplication.getDescription());
        facultyProjectResponse.setUsername(createdApplication.getUser().getUsername());
        facultyProjectResponse.setApplicationId(createdApplication.getApplicationId());
        facultyProjectResponse.setStatusMessage("Success");
        return new ResponseEntity<>(facultyProjectResponse, HttpStatus.OK);
    }

    @GetMapping("/received-applications")
    public ResponseEntity<?> getReceivedApplications(@RequestParam(name="search-query", required = false) String searchQuery){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> usersOptional = usersRepository.findByUsername(username);
        FacultyReceivedApplicationsResponse facultyReceivedApplicationsResponse = new FacultyReceivedApplicationsResponse();
        if(usersOptional.isEmpty()){
            facultyReceivedApplicationsResponse.setStatusMessage("Given user doesn't exist");
            return new ResponseEntity<>(facultyReceivedApplicationsResponse, HttpStatus.NOT_FOUND);
        }
        Users user =usersOptional.get();
        int id = user.getUserId();

        if(searchQuery != null){
            List<SentApplication> list = sentApplicationRepository.getSearchQueryResults(id, "%" + searchQuery + "%");
            Iterator<SentApplication> iterator = list.listIterator();
            List<FacultyReceivedApplicationsResponse> responseList = new ArrayList<>();
            while(iterator.hasNext()){
                SentApplication current = iterator.next();
                int sentApplicationId = current.getSentApplicationId();
                String firstName = current.getSender().getUserDetails().getFirstName();
                String lastName = current.getSender().getUserDetails().getLastName();
                String decision = current.getDecision();
                responseList.add(new FacultyReceivedApplicationsResponse(sentApplicationId, firstName, lastName, decision));
            }
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } else{
            List<SentApplication> senderList = sentApplicationRepository.getReceivedApplicationsList(id);
            Iterator<SentApplication> iterator = senderList.listIterator();
            List<FacultyReceivedApplicationsResponse> responseList = new ArrayList<>();
            while(iterator.hasNext()){
                SentApplication current = iterator.next();
                int sentApplicationId = current.getSentApplicationId();
                String firstName = current.getSender().getUserDetails().getFirstName();
                String lastName = current.getSender().getUserDetails().getLastName();
                String decision = current.getDecision();
                responseList.add(new FacultyReceivedApplicationsResponse(sentApplicationId, firstName, lastName, decision));
            }
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }
    }

    @GetMapping("/application")
    public ResponseEntity<FacultyApplicationResponse> getApplication(@RequestParam(name = "sent-application-id", required = true) int sentApplicationId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> usersOptional = usersRepository.findByUsername(username);
        FacultyApplicationResponse response = new FacultyApplicationResponse();
        if(usersOptional.isEmpty()){
            response.setStatusMessage("Given user doesn't exist");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Users user = usersOptional.get();
        int id = user.getUserId();

        Optional<SentApplication> sentApplicationOptional = sentApplicationRepository.findById(sentApplicationId);
        if(sentApplicationOptional.isEmpty()){
            response.setStatusMessage("The provided sent application id doesn't exist");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if(id != sentApplicationOptional.get().getReceiver().getUserId()){
            response.setStatusMessage("The given sent application id is not accessible by the current user.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        SentApplication sentApplication = sentApplicationOptional.get();
        MyUserDetails senderDetails = sentApplication.getSender().getUserDetails();

        response.setFirstName(senderDetails.getFirstName());
        response.setLastName(senderDetails.getLastName());
        response.setCwid(senderDetails.getCwid());
        response.setClassification(senderDetails.getClassification());
        response.setDepartment(senderDetails.getDepartment());
        response.setResumeLink(sentApplication.getResume().getResumeLink());
        response.setMessage(sentApplication.getMessage());
        response.setGpa(senderDetails.getGpa());
        response.setDecision(sentApplication.getDecision());
        response.setStatusMessage("Success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/project")
    public ResponseEntity<String> createApplication(@RequestBody CreateProjectRequest request){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String projectName = request.getProjectName();
        String projectDescription = request.getProjectDescription();
        Optional<Users> usersOptional = usersRepository.findByUsername(username);
        if(usersOptional.isEmpty()){
            return new ResponseEntity<>("Given user doesn't exist", HttpStatus.NOT_FOUND);
        }
        Users user = usersOptional.get();

        CreatedApplication application = new CreatedApplication(user, projectName, projectDescription);
        createdApplicationRepository.save(application);

        return new ResponseEntity<>("Successfully retrieved the project", HttpStatus.OK);
    }

    @PutMapping("/project")
    public ResponseEntity<?> editApplication(@RequestBody CreateProjectRequest request){
        String updatedProjectName = request.getProjectName();
        String updatedProjectDescription = request.getProjectDescription();

        if(updatedProjectName == null || updatedProjectDescription == null){
            return new ResponseEntity<>("projectName and projectDescription must be in the header and not be null", HttpStatus.NOT_FOUND);
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> usersOptional = usersRepository.findByUsername(username);
        if(usersOptional.isEmpty()){
            return new ResponseEntity<>("Given user doesn't exist", HttpStatus.NOT_FOUND);
        }
        Users ourUser = usersOptional.get();

        Optional<CreatedApplication> createdApplicationOptional = createdApplicationRepository.findByCreatorUserId(ourUser.getUserId());
        if(createdApplicationOptional.isEmpty()){
            return new ResponseEntity<>("Nothing to edit", HttpStatus.NOT_FOUND);
        }

        createdApplicationOptional.get().setName(updatedProjectName);
        createdApplicationOptional.get().setDescription(updatedProjectDescription);
        createdApplicationRepository.flush();

        return new ResponseEntity<>("Successfully Edited", HttpStatus.OK);
    }

    @DeleteMapping("/project")
    public ResponseEntity<?> deleteApplication(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> usersOptional = usersRepository.findByUsername(username);
        if(usersOptional.isEmpty()){
            return new ResponseEntity<>("Given user doesn't exist", HttpStatus.NOT_FOUND);
        }
        Users user = usersOptional.get();
        int id = user.getUserId();

        Optional<CreatedApplication> appIdOptional = createdApplicationRepository.findByCreatorUserId(id);

        if(appIdOptional.isEmpty()){
            return new ResponseEntity<>("App ID Not Found", HttpStatus.OK);
        }
        createdApplicationRepository.deleteById(appIdOptional.get().getApplicationId());
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/decision")
    public ResponseEntity<?> updateDecision(@RequestParam(name = "sent-application-id", required = true) int sentApplicationId,
                                                 @RequestParam(name = "decision", required = true) String decision){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> usersOptional = usersRepository.findByUsername(username);
        if(usersOptional.isEmpty()){
            return new ResponseEntity<>("Given user doesn't exist", HttpStatus.NOT_FOUND);
        }
        Users user = usersOptional.get();
        int userId = user.getUserId();

        if(!decision.toUpperCase().equals("ACCEPT")  && !decision.toUpperCase().equals("DECLINE") ){
            return new ResponseEntity<>("The decision has to be either 'accept' or 'decline'", HttpStatus.BAD_REQUEST);
        }

        Optional<SentApplication> sentApplicationOptional = sentApplicationRepository.findById(sentApplicationId);
        if(sentApplicationOptional.isEmpty()){
            return new ResponseEntity<>("The sent applications with the given id was not found", HttpStatus.NOT_FOUND);
        }
        if(sentApplicationOptional.get().getReceiver().getUserId() != userId){
            return new ResponseEntity<>("You don't have the permission to change this application's decision", HttpStatus.UNAUTHORIZED);
        }
        sentApplicationOptional.get().setDecision(decision.toUpperCase());
        sentApplicationRepository.flush();

        return new ResponseEntity<>("Successfully edited", HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
