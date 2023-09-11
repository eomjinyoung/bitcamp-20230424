package bitcamp.myapp;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentSelector2 {
  public static void main(String[] args) throws Exception {
    String[] names = {
        "곽나현", "김동영", "김묘경", "김선래", "김선준", "김성주", "김세연", "김종원",
        "김현덕", "김호일", "박동우", "박승현", "박진혁", "방현석", "봉세환", "서재운",
        "손병진", "신준식", "신현우", "양소율", "오동현", "이승혁", "이정훈", "이진석",
        "전준호", "정연수", "주현욱", "최근우", "최기현", "한태영"
    };

    ArrayList<String> nameList = new ArrayList<>(Arrays.asList(names));

    int count = 0;
    while (nameList.size() > 0) {
      System.out.printf("%s ", nameList.remove((int)(Math.random() * nameList.size())));
      Thread.sleep(2000);
      if (++count % 6 == 0) {
        System.out.println();
        count = 0;
        Thread.sleep(10000);
      }
    }
  }
}
