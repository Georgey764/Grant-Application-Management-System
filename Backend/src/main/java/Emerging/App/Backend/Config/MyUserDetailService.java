package Emerging.App.Backend.Config;

import Emerging.App.Backend.Entities.MyUserDetails;
import Emerging.App.Backend.Entities.Users;
import Emerging.App.Backend.Repository.UserDetailsRepository;
import Emerging.App.Backend.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private UsersRepository usersRepository;
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    public MyUserDetailService(UsersRepository usersRepository, UserDetailsRepository userDetailsRepository){
        this.usersRepository = usersRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usersOptional = usersRepository.findByUsername(username);
        if(usersOptional.isEmpty()){
            throw new UsernameNotFoundException("Username was not found");
        }
        Users user = usersOptional.get();

        int userId = user.getUserId();
        Optional<MyUserDetails> userDetailsOptional = userDetailsRepository.findById(userId);
        if (user == null || userDetailsOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userDetailsOptional.get().getAuthorities().getAuthorityName()));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
