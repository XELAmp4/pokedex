package fr.iut.ab.pkdxapi.configurations;

import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import fr.iut.ab.pkdxapi.repositories.UserRepository;
import fr.iut.ab.pkdxapi.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

   

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
        // Accès pour tous
        .requestMatchers("/users/register").permitAll()
        .requestMatchers("/users/login").permitAll()
        // Accès si ADMIN
        .requestMatchers(HttpMethod.DELETE,"/pkmn/**").hasAuthority("ROLE_ADMIN")
        .requestMatchers(HttpMethod.PATCH, "/users/update/**").hasAuthority("ROLE_ADMIN")

        // Accès si user connecté
        .requestMatchers("/pkmn").authenticated()
        .requestMatchers("/pkmn/**").authenticated()
        
        
    )
    .httpBasic(Customizer.withDefaults()).csrf(csrf->csrf.disable()) ;
    return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService(userRepository);
    } 
    private UserRepository userRepository;
    public SecurityConfiguration(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
