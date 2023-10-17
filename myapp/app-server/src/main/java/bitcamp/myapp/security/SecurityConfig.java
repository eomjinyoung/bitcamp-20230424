package bitcamp.myapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.DispatcherType;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable().cors().disable()
            .authorizeHttpRequests(registry -> registry
                    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                    .mvcMatchers("/images/**", "/member/form", "/member/add").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(formLoginConfigurer -> formLoginConfigurer
                    .loginPage("/auth/form")
                    .loginProcessingUrl("/auth/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/")
                    .permitAll()
            )
            .logout(withDefaults())
            .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    //return new SimplePasswordEncoder();
    return new BCryptPasswordEncoder();
  }

  public static void main(String[] args) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    System.out.println(passwordEncoder.encode("1111"));
  }
}
