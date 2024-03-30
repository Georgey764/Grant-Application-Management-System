package Emerging.App.Backend.Controller;

import Emerging.App.Backend.Entities.CreatedApplication;
import Emerging.App.Backend.Entities.MyUserDetails;
import Emerging.App.Backend.Entities.Users;
import Emerging.App.Backend.JSON_Objects.Faculty.CreateApplicationRequest;
import Emerging.App.Backend.JSON_Objects.Faculty.FacultyProjectResponse;
import Emerging.App.Backend.JSON_Objects.Faculty.FacultyReceivedApplicationsResponse;
import Emerging.App.Backend.Repository.CreatedApplicationRepository;
import Emerging.App.Backend.Repository.SentApplicationRepository;
import Emerging.App.Backend.Repository.UsersRepository;
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
    public ResponseEntity<FacultyProjectResponse> getProject(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int id = usersRepository.findByUsername(username).getUserId();
        Optional<CreatedApplication> createdApplicationOptional = createdApplicationRepository.findByCreatorUserId(id);

        if(createdApplicationOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CreatedApplication createdApplication = createdApplicationOptional.get();
        FacultyProjectResponse facultyProjectResponse = new FacultyProjectResponse(createdApplication.getApplicationId(), createdApplication.getUser().getUsername(), createdApplication.getName(), createdApplication.getDescription());
        return new ResponseEntity<>(facultyProjectResponse, HttpStatus.OK);
    }

    @GetMapping("/received-applications")
    public List<FacultyReceivedApplicationsResponse> getReceivedApplications(@RequestParam(name="search-query", required = false) String searchQuery){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int id = usersRepository.findByUsername(username).getUserId();

        if(searchQuery != null){
            List<MyUserDetails> list = sentApplicationRepository.getSearchQueryResults(id, "%" + searchQuery + "%");
            Iterator<MyUserDetails> iterator = list.listIterator();
            List<FacultyReceivedApplicationsResponse> responseList = new ArrayList<>();
            while(iterator.hasNext()){
                MyUserDetails current = iterator.next();
                int senderUserId = current.getUser().getUserId();
                String firstName = current.getFirstName();
                String lastName = current.getLastName();
                responseList.add(new FacultyReceivedApplicationsResponse(senderUserId, firstName, lastName));
            }
            return responseList;
        } else{
            List<Users> senderList = sentApplicationRepository.getReceivedApplicationsList(id);
            Iterator<Users> iterator = senderList.listIterator();
            List<FacultyReceivedApplicationsResponse> responseList = new ArrayList<>();
            while(iterator.hasNext()){
                Users current = iterator.next();
                int senderUserId = current.getUserId();
                String firstName = current.getUserDetails().getFirstName();
                String lastName = current.getUserDetails().getLastName();
                responseList.add(new FacultyReceivedApplicationsResponse(senderUserId, firstName, lastName));
            }
            return responseList;
        }
    }

    @PostMapping("/create-application")
    public String createApplication(@RequestBody CreateApplicationRequest request){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String projectName = request.getProjectName();
        String projectDescription = request.getProjectDescription();
        Users user = usersRepository.findByUsername(username);

        CreatedApplication application = new CreatedApplication(user, projectName, projectDescription);
        createdApplicationRepository.save(application);

        return "Success";
    }

    @PostMapping("/edit-application")
    public ResponseEntity<String> editApplication(@RequestBody CreateApplicationRequest request){
        String updatedProjectName = request.getProjectName();
        String updatedProjectDescription = request.getProjectDescription();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users ourUser = usersRepository.findByUsername(username);

        Optional<CreatedApplication> createdApplicationOptional = createdApplicationRepository.findByCreatorUserId(ourUser.getUserId());
        if(createdApplicationOptional.isEmpty()){
            return new ResponseEntity<>("Nothing to edit", HttpStatus.NOT_FOUND);
        }

        createdApplicationOptional.get().setName(updatedProjectName);
        createdApplicationOptional.get().setDescription(updatedProjectDescription);
        createdApplicationRepository.flush();

        return new ResponseEntity<>("Successfully Edited", HttpStatus.OK);
    }

    @PostMapping("/delete-application")
    public ResponseEntity<String> deleteApplication(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int id = usersRepository.findByUsername(username).getUserId();
        Optional<CreatedApplication> appIdOptional = createdApplicationRepository.findByCreatorUserId(id);

        if(appIdOptional.isEmpty()){
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
        createdApplicationRepository.deleteById(appIdOptional.get().getApplicationId());
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
