package Emerging.App.Backend.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfig{

    private MyFilter myFilter;

    private MyUserDetailService myUserDetailService;

    @Autowired
    public SecurityConfig(MyFilter myFilter, MyUserDetailService myUserDetailService){
        this.myFilter = myFilter;
        this.myUserDetailService = myUserDetailService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(myWebsiteConfigurationSource()))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers( "/authenticate", "/sign-up", "/redirect").permitAll()
                                .requestMatchers("/students/**").hasRole("STUDENT")
                                .requestMatchers("/faculty/**").hasRole("FACULTY")
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    CorsConfigurationSource myWebsiteConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(System.getenv("ALLOWED_ORIGIN")));
        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "DELETE", "POST", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(myUserDetailService);

        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}