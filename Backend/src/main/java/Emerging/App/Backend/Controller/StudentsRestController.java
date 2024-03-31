package Emerging.App.Backend.Controller;

import Emerging.App.Backend.Entities.CreatedApplication;
import Emerging.App.Backend.Entities.SentApplication;
import Emerging.App.Backend.Entities.Resume;
import Emerging.App.Backend.Entities.Users;
import Emerging.App.Backend.JSON_Objects.Authentication.ApplicationRequest;
import Emerging.App.Backend.Repository.CreatedApplicationRepository;
import Emerging.App.Backend.Repository.SentApplicationRepository;
import Emerging.App.Backend.Repository.UsersRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentsRestController {

    private CreatedApplicationRepository createdApplicationRepository;

    private SentApplicationRepository sentApplicationRepository;

    private UsersRepository usersRepository;

    @Autowired
    public StudentsRestController(SentApplicationRepository sentApplicationRepository, CreatedApplicationRepository createdApplicationRepository, UsersRepository usersRepository){
        this.usersRepository = usersRepository;
        this.createdApplicationRepository = createdApplicationRepository;
        this.sentApplicationRepository = sentApplicationRepository;
    }

    @PostMapping("/send-application")
    public ResponseEntity<String> sendApplication(@RequestBody ApplicationRequest applicationRequest){
        Optional<Users> senderOptional = usersRepository.findByUsername(applicationRequest.getSender());
        Optional<Users> receiverOptional = usersRepository.findByUsername(applicationRequest.getReceiver());
        if(senderOptional.isEmpty() || receiverOptional.isEmpty()){
            return new ResponseEntity<>("The given sender and receiver doesn't exist.", HttpStatus.NOT_FOUND);
        }

        String message = applicationRequest.getMessage();
        String resumeLink = applicationRequest.getResumeLink();
        int applicationId = applicationRequest.getApplicationId();
        Optional<CreatedApplication> createdApplicationOptional = createdApplicationRepository.findById(applicationId);

        Resume resume = null;
        if(resumeLink != null){
            resume = new Resume();
            resume.setResumeLink(resumeLink);
        }
        if(senderOptional.get() == null){
            return new ResponseEntity<>("Sender not found", HttpStatus.NOT_FOUND);
        } else if (receiverOptional.get() == null) {
            return new ResponseEntity<>("Receiver not found", HttpStatus.NOT_FOUND);
        }

        if(createdApplicationOptional.isEmpty()){
            return new ResponseEntity<>("Application that you were trying to send is not found", HttpStatus.NOT_FOUND);
        }
        SentApplication application = new SentApplication(createdApplicationOptional.get(), senderOptional.get(), receiverOptional.get(), message, resume);
        sentApplicationRepository.save(application);

        return new ResponseEntity<>("Successfully sent", HttpStatus.OK);
    }

    @GetMapping("/applications-list")
    public ResponseEntity<?> applicationsList() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> usersOptional = usersRepository.findByUsername(username);
        if(usersOptional.isEmpty()){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        Users user = usersOptional.get();

        return new ResponseEntity<>(sentApplicationRepository.getStudentApplicationsList(user.getUserId()), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
