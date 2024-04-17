package Emerging.App.Backend.Controller;

import Emerging.App.Backend.Entities.Authorities;
import Emerging.App.Backend.Entities.MyUserDetails;
import Emerging.App.Backend.Entities.Users;
import Emerging.App.Backend.JSON_Objects.Authentication.*;
import Emerging.App.Backend.JWT.JWTutil;
import Emerging.App.Backend.Repository.AuthoritiesRepository;
import Emerging.App.Backend.Repository.UserDetailsRepository;
import Emerging.App.Backend.Repository.UsersRepository;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("")
public class LoginRestController {

    private String requestingDomain = "localhost";
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
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest request){
        SignUpResponse response = new SignUpResponse();
        String email = request.getEmail();
        String atAddress = email.substring(email.indexOf("@") + 1);
        if(!atAddress.toUpperCase().equals("WARHAWKS.ULM.EDU") && !atAddress.toUpperCase().equals("ULM.EDU")){
            response.setMessage("It must be a *@warhawks.ulm.edu or *@ulm.edu email");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        String department = request.getDepartment();
        String cwid = request.getCwid();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String password = passwordEncoder.encode(request.getPassword());

        int authorityId = request.getAuthorityId();
        Optional<Authorities> authorityOptional = authoritiesRepository.findById(authorityId);
        Authorities authority;

        if (email == null || department == null || cwid == null || firstName == null || lastName == null) {
            response.setMessage("All parameters must be non-null");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if(!email.contains("@")){
            response.setMessage("Please enter a valid email");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        String username = email.substring(0, email.indexOf('@'));

        if(authorityOptional.isPresent()){
            authority = authorityOptional.get();
            Users user = new Users(username, password);
            MyUserDetails userDetails = new MyUserDetails(authority, email, department, cwid, firstName, lastName);
            userDetails.setUser(user);
            userDetailsRepository.save(userDetails);
        } else {
            response.setMessage("Authority not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.setMessage("Successfully made an account");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request){
//        AuthResponse response = new AuthResponse();
//        response.setJwt(null);
//        String email = request.getemail();
//        if(!email.contains("@")){
//            response.setMessage("The email should contain @");
//            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//        }
//        String username = email.substring(0, email.indexOf("@"));
//
//        Optional<Users> usersOptional = usersRepository.findByUsername(username);
//        if(usersOptional.isEmpty()){
//            response.setMessage("User not found");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//        Users user = usersOptional.get();
//        if(!user.getUserDetails().getEmail().trim().equals(email)){
//            response.setMessage("User email doesn't match with system");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//        try{
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
//        } catch (BadCredentialsException e){
//            response.setMessage("Password doesn't match");
//            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//        }
//        JWTutil instance = new JWTutil();
//        String jwt = instance.generateJWT(username, request.getPassword());
//        response.setJwt(jwt);
//        response.setMessage("Successfully Authenticated");
//        response.setAuthority(user.getUserDetails().getAuthority().getAuthorityName());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request, HttpServletResponse response){
        AuthResponse authResponse = new AuthResponse();

        String email = request.getemail();

        if(email == null){
            authResponse.setMessage("The email should not be null");
            return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
        }

        if(!email.contains("@")){
            return new ResponseEntity<>("The email should contain @", HttpStatus.UNAUTHORIZED);
        }
        String username = email.substring(0, email.indexOf("@"));

        Optional<Users> usersOptional = usersRepository.findByUsername(username);
        if(usersOptional.isEmpty()){
            authResponse.setMessage("User not found");
            return new ResponseEntity<>(authResponse, HttpStatus.NOT_FOUND);
        }
        Users user = usersOptional.get();
        if(!user.getUserDetails().getEmail().trim().equals(email)){
            authResponse.setMessage("User email doesn't match with system");
            return new ResponseEntity<>(authResponse, HttpStatus.NOT_FOUND);
        }
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
        } catch (BadCredentialsException e){
            authResponse.setMessage("Password doesn't match");
            return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
        }
        JWTutil instance = new JWTutil();
        String jwt = instance.generateJWT(username, request.getPassword());
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setMaxAge(3600);
        cookie.setDomain(requestingDomain);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        authResponse.setMessage("SUCCESS ");
        authResponse.setAuthority(user.getUserDetails().getAuthority().getAuthorityName());
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
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

    @PostMapping("/remove-authentication")
    public void logout(HttpServletResponse response){
        Cookie jwt = new Cookie("jwt", "");
        jwt.setMaxAge(0);
        response.addCookie(jwt);
    }
}
