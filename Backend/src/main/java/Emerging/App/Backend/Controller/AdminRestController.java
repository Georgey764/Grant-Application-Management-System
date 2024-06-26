package Emerging.App.Backend.Controller;

import Emerging.App.Backend.Entities.Semester;
import Emerging.App.Backend.Repository.CreatedApplicationRepository;
import Emerging.App.Backend.Repository.SemesterRepository;
import Emerging.App.Backend.Repository.SentApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private SemesterRepository semesterRepository;
    private CreatedApplicationRepository createdApplicationRepository;
    private SentApplicationRepository sentApplicationRepository;

    @Autowired
    public AdminRestController(SemesterRepository semesterRepository, CreatedApplicationRepository createdApplicationRepository, SentApplicationRepository sentApplicationRepository){
        this.semesterRepository = semesterRepository;
        this.createdApplicationRepository = createdApplicationRepository;
        this.sentApplicationRepository = sentApplicationRepository;
    }

    @PutMapping("/semester")
    public ResponseEntity<?> updateSemester(@RequestParam("name") String semesterName){
        Semester semester = semesterRepository.findById(1).get();
        semester.setSemesterName(semesterName);
        semesterRepository.flush();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/reset-semester")
    public ResponseEntity<?> resetSemester(){
        sentApplicationRepository.deleteAll();
        createdApplicationRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
