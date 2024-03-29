package Emerging.App.Backend.Controller;

import Emerging.App.Backend.Entities.CreatedApplication;
import Emerging.App.Backend.Entities.Users;
import Emerging.App.Backend.JSON_Objects.CreateApplicationRequest;
import Emerging.App.Backend.Repository.CreatedApplicationRepository;
import Emerging.App.Backend.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faculty")
public class FacultyRestController {

    private CreatedApplicationRepository createdApplicationRepository;
    private UsersRepository usersRepository;

    @Autowired
    public FacultyRestController(CreatedApplicationRepository createdApplicationRepository, UsersRepository usersRepository){
        this.createdApplicationRepository = createdApplicationRepository;
        this.usersRepository = usersRepository;
    }

    @PostMapping("/create-application")
    public String createApplication(@RequestBody CreateApplicationRequest request){
        String username = request.getUsername();
        String projectName = request.getProjectName();
        String projectDescription = request.getProjectDescription();
        Users user = usersRepository.findByUsername(username);

        CreatedApplication application = new CreatedApplication(user, projectName, projectDescription);
        createdApplicationRepository.save(application);

        return "Success";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
