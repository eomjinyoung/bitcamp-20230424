package bitcamp.myapp;

import org.springframework.context.annotation.Configuration;

@Configuration
public class NcpConfig {
  private String endPoint = "https://kr.object.ncloudstorage.com";
  private String regionName = "kr-standard";
//  private String accessKey = System.getProperty("accessKey");
//  private String secretKey = System.getProperty("secretKey");
  private String accessKey = "8T84wn31xUrjULtuj3iM";
  private String secretKey = "maGYlwIY3VQMzcXHwec4JC0DHP4gB7GJmcHSwqMT";
  // -DaccessKey=8T84wn31xUrjULtuj3iM -DsecretKey=maGYlwIY3VQMzcXHwec4JC0DHP4gB7GJmcHSwqMT

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
