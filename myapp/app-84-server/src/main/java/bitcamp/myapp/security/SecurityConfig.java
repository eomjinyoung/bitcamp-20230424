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
                    .mvcMatchers("/favicon.ico", "/images/**", "/member/form", "/member/add", "/", "/logout").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(formLoginConfigurer -> formLoginConfigurer
                    .loginPage("/auth/form") // 로그인폼의 URL 지정
                    .loginProcessingUrl("/auth/login") // 로그인 요청을 처리할 URL 지정
                    .usernameParameter("email") // username 값이 들어 있는 요청 파라미터 이름
                    .passwordParameter("password") // password 값이 들어 있는 요청 파라미터 이름
                    //.defaultSuccessUrl("/home", true)
                    .successForwardUrl("/auth/loginSuccess") // 로그인 성공 후 포워딩할 URL
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
