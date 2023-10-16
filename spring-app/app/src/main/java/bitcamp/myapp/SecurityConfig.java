package bitcamp.myapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .authorizeRequests((authorize) -> authorize
                    .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                    .loginPage("/auth/form")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .loginProcessingUrl("/auth/login")
                    .successForwardUrl("/")
                    .failureForwardUrl("/auth/form")
                    .permitAll()
            ).build();
  }
}
