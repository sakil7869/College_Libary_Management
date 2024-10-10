package College_Library_Management.CollegeLibraryManagement.Configuration;

import College_Library_Management.CollegeLibraryManagement.Service.LibrarianService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final LibrarianService librarianService;

    @Bean
    SecurityFilterChain securityFilterChai(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/books/**","borrowing/**").hasAnyRole("LIBRARIAN")
                        .anyRequest().authenticated())
                .csrf(csrfConfigure -> csrfConfigure.disable())
                .sessionManagement(sessionConfig -> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }
}
