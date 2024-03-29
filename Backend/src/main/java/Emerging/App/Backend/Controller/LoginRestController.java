package Emerging.App.Backend.Controller;

import Emerging.App.Backend.Entities.Authorities;
import Emerging.App.Backend.Entities.MyUserDetails;
import Emerging.App.Backend.Entities.Users;
import Emerging.App.Backend.JSON_Objects.AuthRequest;
import Emerging.App.Backend.JSON_Objects.AuthResponse;
import Emerging.App.Backend.JSON_Objects.ChangePasswordAuthRequest;
import Emerging.App.Backend.JSON_Objects.SignUpRequest;
import Emerging.App.Backend.JWT.JWTutil;
import Emerging.App.Backend.Repository.AuthoritiesRepository;
import Emerging.App.Backend.Repository.UserDetailsRepository;
import Emerging.App.Backend.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("")
public class LoginRestController {

    private AuthenticationManager authenticationManager;
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;
    private JavaMailSender javaMailSender;
    private AuthoritiesRepository authoritiesRepository;
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    @Lazy
    public LoginRestController(UserDetailsRepository userDetailsRepository, AuthoritiesRepository authoritiesRepository, AuthenticationManager authenticationManager, UsersRepository usersRepository, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender){
        this.authenticationManager = authenticationManager;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.authoritiesRepository = authoritiesRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request){
        String email = request.getEmail();
        String department = request.getDepartment();
        String cwid = request.getCwid();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String password = passwordEncoder.encode(request.getPassword());

        int authorityId = request.getAuthorityId();
        Optional<Authorities> authorityOptional = authoritiesRepository.findById(authorityId);
        Authorities authority;

        if (email == null || department == null || cwid == null || firstName == null || lastName == null) {
            return new ResponseEntity<>("All parameters must be non-null", HttpStatus.BAD_REQUEST);
        }

        if(!email.contains("@")){
            return new ResponseEntity<>("Please enter a valid email", HttpStatus.BAD_REQUEST);
        }
        String username = email.substring(0, email.indexOf('@'));

        if(authorityOptional.isPresent()){
            authority = authorityOptional.get();
            Users user = new Users(username, password);
            MyUserDetails userDetails = new MyUserDetails(authority, email, department, cwid, firstName, lastName);
            userDetails.setUser(user);
            userDetailsRepository.save(userDetails);
        } else {
            return new ResponseEntity<>("Authority not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Successfully made an account", HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        JWTutil instance = new JWTutil();
        String jwt = instance.generateJWT(request.getUsername(), request.getPassword());
        AuthResponse response = new AuthResponse(jwt);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/change-authentication")
    public ResponseEntity<String> changeAuthentication(@RequestBody ChangePasswordAuthRequest request){
        Optional<Users> userOptional = usersRepository.findById(request.getUsername());
        if(userOptional.isPresent()){
            Users user = userOptional.get();
            if(passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                usersRepository.save(user);
            } else{
                return new ResponseEntity<>("Old password is incorrect", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("No " + request.getUsername() + " users found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Successfully Changed", HttpStatus.OK);
    }

//    @PostMapping("/new-password")
//    public String newPassword(@RequestParam String id){
//        return "Hi";
//    }

//    @GetMapping("/forgot-authentication")
//    public String forgotAuthentication(){
//        sendForgotPasswordMail();
//        return "Emailed the link to change password";
//    }
//
//    public void sendForgotPasswordMail(){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("noreply@baeldung.com");
//        message.setTo("khawasgs@warhawks.ulm.edu");
//        message.setSubject("hii");
//        message.setText("text");
//        javaMailSender.send(message);
//    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
