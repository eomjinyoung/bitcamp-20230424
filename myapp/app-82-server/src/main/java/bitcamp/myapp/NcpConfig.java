package bitcamp.myapp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("ncp.properties")
@ConfigurationProperties("ncp")
public class NcpConfig {
  // ncp.properties 파일의 속성 값을 직접 지정하기
//  @Value("${ncp.end.point}") private String endPoint;
//  @Value("${ncp.region.name}") private String regionName;
//  @Value("${ncp.access.key}") private String accessKey;
//  @Value("${ncp.secret.key}") private String secretKey;

  // ncp.properties 파일의 속성 값을 자동으로 지정하기
  // => @ConfigurationProperties("접두사") 애노테이션을 사용하여 가져올 프로퍼티를 지정한다.
  // => 단 접두사를 제외한 프로퍼티 이름과 자바 객체의 필드 이름이 일치해야 한다.
  private String endPoint;
  private String regionName;
  private String accessKey;
  private String secretKey;

  public NcpConfig() {
    System.out.println("NcpConfig() 호출됨!");
  }

  public String getEndPoint() {
    return endPoint;
  }
  public void setEndPoint(String endPoint) {
    this.endPoint = endPoint;
  }
  public String getRegionName() {
    return regionName;
  }
  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }
  public String getAccessKey() {
    return accessKey;
  }
  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }
  public String getSecretKey() {
    return secretKey;
  }
  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }


}
