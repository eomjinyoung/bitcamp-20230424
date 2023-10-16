package bitcamp.myapp.vo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;

@Data
public class Member implements UserDetails {
  public static final char MALE = 'M';
  public static final char FEMALE = 'W';
  private static final long serialVersionUID = 1L;
  private int no;
  private String name;
  private String email;
  private String password;
  private char gender;
  private Date createdDate;
  private String photo;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority("USER"));
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


}
