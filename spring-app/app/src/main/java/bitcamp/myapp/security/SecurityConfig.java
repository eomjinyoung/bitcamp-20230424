package bitcamp.myapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
            .authorizeHttpRequests(authorize -> authorize
                    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                    .mvcMatchers("/images/**", "/member/form", "/member/add").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/auth/form")
                    .loginProcessingUrl("/auth/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/", true)
                    //.successForwardUrl("/")
                    //.failureForwardUrl("/auth/form")
                    .permitAll()
            )
//            .logout((logout) ->
//                    logout.deleteCookies("JSESSIONID")
//                            .invalidateHttpSession(true)
//                            .logoutUrl("/auth/logout")
//                            .logoutSuccessUrl("/")
//            )
            //.logout().deleteCookies("JSESSIONID").invalidateHttpSession(true)
            //.logoutUrl("/logout").logoutSuccessUrl("/").and()
            .logout(withDefaults())
            .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
      return new SimplePasswordEncoder();
  }
}
