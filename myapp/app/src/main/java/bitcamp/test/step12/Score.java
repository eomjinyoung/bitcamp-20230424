package bitcamp.test.step12;

class Score {
  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float aver;

  Score(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
    this.compute();
  }

  void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = this.sum / 3f;
  }
}