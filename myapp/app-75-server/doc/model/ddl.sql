-- 수강신청
DROP TABLE IF EXISTS edu_application RESTRICT;

-- 강의
DROP TABLE IF EXISTS edu_lect RESTRICT;

-- 학생
DROP TABLE IF EXISTS edu_student RESTRICT;

-- 강의실
DROP TABLE IF EXISTS edu_classroom RESTRICT;

-- 교육센터
DROP TABLE IF EXISTS edu_center RESTRICT;

-- 학력
DROP TABLE IF EXISTS edu_degree RESTRICT;

-- 은행
DROP TABLE IF EXISTS edu_bank RESTRICT;

-- 강의실사진
DROP TABLE IF EXISTS edu_classroom_photo RESTRICT;

-- 강사
DROP TABLE IF EXISTS edu_teacher RESTRICT;

-- 매니저
DROP TABLE IF EXISTS edu_manager RESTRICT;

-- 강의배정
DROP TABLE IF EXISTS edu_lect_teacher RESTRICT;

-- 회원
DROP TABLE IF EXISTS edu_member RESTRICT;

-- 고용형태
DROP TABLE IF EXISTS edu_employ_type RESTRICT;

-- 수강신청
CREATE TABLE edu_application (
  ano   INTEGER      NOT NULL COMMENT '수강신청번호', -- 수강신청번호
  lno   INTEGER      NOT NULL COMMENT '강의번호', -- 강의번호
  cdt   DATE         NOT NULL DEFAULT (current_date()) COMMENT '신청일', -- 신청일
  state VARCHAR(10)  NOT NULL COMMENT '상태', -- 상태
  note  VARCHAR(255) NULL     COMMENT '비고', -- 비고
  sno   INTEGER      NULL     COMMENT '학생번호' -- 학생번호
)
COMMENT '수강신청';

-- 수강신청
ALTER TABLE edu_application
  ADD CONSTRAINT PK_edu_application -- 수강신청 기본키
  PRIMARY KEY (
  ano -- 수강신청번호
  );

ALTER TABLE edu_application
  MODIFY COLUMN ano INTEGER NOT NULL AUTO_INCREMENT COMMENT '수강신청번호';

-- 강의
CREATE TABLE edu_lect (
  lno     INTEGER      NOT NULL COMMENT '강의번호', -- 강의번호
  title   VARCHAR(255) NOT NULL COMMENT '강의명', -- 강의명
  content MEDIUMTEXT   NOT NULL COMMENT '강의설명', -- 강의설명
  sdt     DATE         NOT NULL COMMENT '시작일', -- 시작일
  edt     DATE         NOT NULL COMMENT '종료일', -- 종료일
  price   INTEGER      NOT NULL COMMENT '수강료', -- 수강료
  crno    INTEGER      NULL     COMMENT '강의실번호', -- 강의실번호
  mrno    INTEGER      NULL     COMMENT '매니저번호' -- 매니저번호
)
COMMENT '강의';

-- 강의
ALTER TABLE edu_lect
  ADD CONSTRAINT PK_edu_lect -- 강의 기본키
  PRIMARY KEY (
  lno -- 강의번호
  );

-- 강의 인덱스
CREATE INDEX IX_edu_lect
  ON edu_lect( -- 강의
    title ASC -- 강의명
  );

ALTER TABLE edu_lect
  MODIFY COLUMN lno INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의번호';

-- 학생
CREATE TABLE edu_student (
  sno      INTEGER      NOT NULL COMMENT '학생번호', -- 학생번호
  post_no  VARCHAR(10)  NOT NULL COMMENT '우편번호', -- 우편번호
  bas_addr VARCHAR(255) NOT NULL COMMENT '기본주소', -- 기본주소
  det_addr VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
  dno      INTEGER      NOT NULL COMMENT '학력번호', -- 학력번호
  school   VARCHAR(60)  NULL     COMMENT '최종학교', -- 최종학교
  major    VARCHAR(60)  NULL     COMMENT '전공', -- 전공
  bno      INTEGER      NOT NULL COMMENT '은행번호', -- 은행번호
  acc_no   VARCHAR(20)  NOT NULL COMMENT '계좌번호', -- 계좌번호
  work     BOOLEAN      NOT NULL COMMENT '재직여부' -- 재직여부
)
COMMENT '학생';

-- 학생
ALTER TABLE edu_student
  ADD CONSTRAINT PK_edu_student -- 학생 기본키
  PRIMARY KEY (
  sno -- 학생번호
  );

-- 강의실
CREATE TABLE edu_classroom (
  crno     INTEGER     NOT NULL COMMENT '강의실번호', -- 강의실번호
  cno      INTEGER     NOT NULL COMMENT '교육센터번호', -- 교육센터번호
  name     VARCHAR(60) NOT NULL COMMENT '강의실', -- 강의실
  capacity INTEGER     NOT NULL COMMENT '수용인원' -- 수용인원
)
COMMENT '강의실';

-- 강의실
ALTER TABLE edu_classroom
  ADD CONSTRAINT PK_edu_classroom -- 강의실 기본키
  PRIMARY KEY (
  crno -- 강의실번호
  );

-- 강의실 유니크 인덱스
CREATE UNIQUE INDEX UIX_edu_classroom
  ON edu_classroom ( -- 강의실
    cno ASC,  -- 교육센터번호
    name ASC  -- 강의실
  );

ALTER TABLE edu_classroom
  MODIFY COLUMN crno INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의실번호';

-- 교육센터
CREATE TABLE edu_center (
  cno      INTEGER      NOT NULL COMMENT '교육센터번호', -- 교육센터번호
  name     VARCHAR(60)  NOT NULL COMMENT '교육센터', -- 교육센터
  post_no  VARCHAR(10)  NOT NULL COMMENT '우편번호', -- 우편번호
  bas_addr VARCHAR(255) NOT NULL COMMENT '기본주소', -- 기본주소
  det_addr VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
  tel      VARCHAR(30)  NOT NULL COMMENT '대표번호', -- 대표번호
  fax      VARCHAR(30)  NULL     COMMENT '대표팩스' -- 대표팩스
)
COMMENT '교육센터';

-- 교육센터
ALTER TABLE edu_center
  ADD CONSTRAINT PK_edu_center -- 교육센터 기본키
  PRIMARY KEY (
  cno -- 교육센터번호
  );

-- 교육센터 유니크 인덱스
CREATE UNIQUE INDEX UIX_edu_center
  ON edu_center ( -- 교육센터
    name ASC -- 교육센터
  );

ALTER TABLE edu_center
  MODIFY COLUMN cno INTEGER NOT NULL AUTO_INCREMENT COMMENT '교육센터번호';

-- 학력
CREATE TABLE edu_degree (
  dno   INTEGER     NOT NULL COMMENT '학력번호', -- 학력번호
  title VARCHAR(60) NOT NULL COMMENT '학력명' -- 학력명
)
COMMENT '학력';

-- 학력
ALTER TABLE edu_degree
  ADD CONSTRAINT PK_edu_degree -- 학력 기본키
  PRIMARY KEY (
  dno -- 학력번호
  );

-- 학력 유니크 인덱스
CREATE UNIQUE INDEX UIX_edu_degree
  ON edu_degree ( -- 학력
    title ASC -- 학력명
  );

ALTER TABLE edu_degree
  MODIFY COLUMN dno INTEGER NOT NULL AUTO_INCREMENT COMMENT '학력번호';

-- 은행
CREATE TABLE edu_bank (
  bno   INTEGER     NOT NULL COMMENT '은행번호', -- 은행번호
  title VARCHAR(60) NOT NULL COMMENT '은행명' -- 은행명
)
COMMENT '은행';

-- 은행
ALTER TABLE edu_bank
  ADD CONSTRAINT PK_edu_bank -- 은행 기본키
  PRIMARY KEY (
  bno -- 은행번호
  );

-- 은행 유니크 인덱스
CREATE UNIQUE INDEX UIX_edu_bank
  ON edu_bank ( -- 은행
    title ASC -- 은행명
  );

ALTER TABLE edu_bank
  MODIFY COLUMN bno INTEGER NOT NULL AUTO_INCREMENT COMMENT '은행번호';

-- 강의실사진
CREATE TABLE edu_classroom_photo (
  cpno     INTEGER      NOT NULL COMMENT '강의실사진번호', -- 강의실사진번호
  crno     INTEGER      NOT NULL COMMENT '강의실번호', -- 강의실번호
  filename VARCHAR(255) NOT NULL COMMENT '사진파일명' -- 사진파일명
)
COMMENT '강의실사진';

-- 강의실사진
ALTER TABLE edu_classroom_photo
  ADD CONSTRAINT PK_edu_classroom_photo -- 강의실사진 기본키
  PRIMARY KEY (
  cpno -- 강의실사진번호
  );

ALTER TABLE edu_classroom_photo
  MODIFY COLUMN cpno INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의실사진번호';

-- 강사
CREATE TABLE edu_teacher (
  tno    INTEGER     NOT NULL COMMENT '강사번호', -- 강사번호
  dno    INTEGER     NOT NULL COMMENT '학력번호', -- 학력번호
  school VARCHAR(60) NOT NULL COMMENT '최종학교', -- 최종학교
  major  VARCHAR(60) NOT NULL COMMENT '전공', -- 전공
  etno   INTEGER     NULL     COMMENT '고용형태번호', -- 고용형태번호
  hr_pay INTEGER     NULL     COMMENT '시강료' -- 시강료
)
COMMENT '강사';

-- 강사
ALTER TABLE edu_teacher
  ADD CONSTRAINT PK_edu_teacher -- 강사 기본키
  PRIMARY KEY (
  tno -- 강사번호
  );

-- 매니저
CREATE TABLE edu_manager (
  mrno INTEGER     NOT NULL COMMENT '매니저번호', -- 매니저번호
  dept VARCHAR(60) NULL     COMMENT '부서', -- 부서
  posi VARCHAR(60) NULL     COMMENT '직위' -- 직위
)
COMMENT '매니저';

-- 매니저
ALTER TABLE edu_manager
  ADD CONSTRAINT PK_edu_manager -- 매니저 기본키
  PRIMARY KEY (
  mrno -- 매니저번호
  );

-- 강의배정
CREATE TABLE edu_lect_teacher (
  lno INTEGER NOT NULL COMMENT '강의번호', -- 강의번호
  tno INTEGER NOT NULL COMMENT '강사번호' -- 강사번호
)
COMMENT '강의배정';

-- 강의배정
ALTER TABLE edu_lect_teacher
  ADD CONSTRAINT PK_edu_lect_teacher -- 강의배정 기본키
  PRIMARY KEY (
  lno, -- 강의번호
  tno  -- 강사번호
  );

-- 회원
CREATE TABLE edu_member (
  mno   INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
  name  VARCHAR(60) NOT NULL COMMENT '이름', -- 이름
  tel   VARCHAR(30) NOT NULL COMMENT '전화', -- 전화
  email VARCHAR(40) NOT NULL COMMENT '이메일' -- 이메일
)
COMMENT '회원';

-- 회원
ALTER TABLE edu_member
  ADD CONSTRAINT PK_edu_member -- 회원 기본키
  PRIMARY KEY (
  mno -- 회원번호
  );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_edu_member
  ON edu_member ( -- 회원
    email ASC -- 이메일
  );

-- 회원 인덱스
CREATE INDEX IX_edu_member
  ON edu_member( -- 회원
    name ASC -- 이름
  );

-- 회원 인덱스2
CREATE INDEX IX_edu_member2
  ON edu_member( -- 회원
    tel ASC -- 전화
  );

ALTER TABLE edu_member
  MODIFY COLUMN mno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 고용형태
CREATE TABLE edu_employ_type (
  etno  INTEGER     NOT NULL COMMENT '고용형태번호', -- 고용형태번호
  title VARCHAR(60) NOT NULL COMMENT '고용형태명' -- 고용형태명
)
COMMENT '고용형태';

-- 고용형태
ALTER TABLE edu_employ_type
  ADD CONSTRAINT PK_edu_employ_type -- 고용형태 기본키
  PRIMARY KEY (
  etno -- 고용형태번호
  );

ALTER TABLE edu_employ_type
  MODIFY COLUMN etno INTEGER NOT NULL AUTO_INCREMENT COMMENT '고용형태번호';

-- 수강신청
ALTER TABLE edu_application
  ADD CONSTRAINT FK_edu_lect_TO_edu_application -- 강의 -> 수강신청
  FOREIGN KEY (
  lno -- 강의번호
  )
  REFERENCES edu_lect ( -- 강의
  lno -- 강의번호
  );

-- 수강신청
ALTER TABLE edu_application
  ADD CONSTRAINT FK_edu_student_TO_edu_application -- 학생 -> 수강신청
  FOREIGN KEY (
  sno -- 학생번호
  )
  REFERENCES edu_student ( -- 학생
  sno -- 학생번호
  );

-- 강의
ALTER TABLE edu_lect
  ADD CONSTRAINT FK_edu_classroom_TO_edu_lect -- 강의실 -> 강의
  FOREIGN KEY (
  crno -- 강의실번호
  )
  REFERENCES edu_classroom ( -- 강의실
  crno -- 강의실번호
  );

-- 강의
ALTER TABLE edu_lect
  ADD CONSTRAINT FK_edu_manager_TO_edu_lect -- 매니저 -> 강의
  FOREIGN KEY (
  mrno -- 매니저번호
  )
  REFERENCES edu_manager ( -- 매니저
  mrno -- 매니저번호
  );

-- 학생
ALTER TABLE edu_student
  ADD CONSTRAINT FK_edu_degree_TO_edu_student -- 학력 -> 학생
  FOREIGN KEY (
  dno -- 학력번호
  )
  REFERENCES edu_degree ( -- 학력
  dno -- 학력번호
  );

-- 학생
ALTER TABLE edu_student
  ADD CONSTRAINT FK_edu_bank_TO_edu_student -- 은행 -> 학생
  FOREIGN KEY (
  bno -- 은행번호
  )
  REFERENCES edu_bank ( -- 은행
  bno -- 은행번호
  );

-- 학생
ALTER TABLE edu_student
  ADD CONSTRAINT FK_edu_member_TO_edu_student -- 회원 -> 학생
  FOREIGN KEY (
  sno -- 학생번호
  )
  REFERENCES edu_member ( -- 회원
  mno -- 회원번호
  );

-- 강의실
ALTER TABLE edu_classroom
  ADD CONSTRAINT FK_edu_center_TO_edu_classroom -- 교육센터 -> 강의실
  FOREIGN KEY (
  cno -- 교육센터번호
  )
  REFERENCES edu_center ( -- 교육센터
  cno -- 교육센터번호
  );

-- 강의실사진
ALTER TABLE edu_classroom_photo
  ADD CONSTRAINT FK_edu_classroom_TO_edu_classroom_photo -- 강의실 -> 강의실사진
  FOREIGN KEY (
  crno -- 강의실번호
  )
  REFERENCES edu_classroom ( -- 강의실
  crno -- 강의실번호
  );

-- 강사
ALTER TABLE edu_teacher
  ADD CONSTRAINT FK_edu_degree_TO_edu_teacher -- 학력 -> 강사
  FOREIGN KEY (
  dno -- 학력번호
  )
  REFERENCES edu_degree ( -- 학력
  dno -- 학력번호
  );

-- 강사
ALTER TABLE edu_teacher
  ADD CONSTRAINT FK_edu_member_TO_edu_teacher -- 회원 -> 강사
  FOREIGN KEY (
  tno -- 강사번호
  )
  REFERENCES edu_member ( -- 회원
  mno -- 회원번호
  );

-- 강사
ALTER TABLE edu_teacher
  ADD CONSTRAINT FK_edu_employ_type_TO_edu_teacher -- 고용형태 -> 강사
  FOREIGN KEY (
  etno -- 고용형태번호
  )
  REFERENCES edu_employ_type ( -- 고용형태
  etno -- 고용형태번호
  );

-- 매니저
ALTER TABLE edu_manager
  ADD CONSTRAINT FK_edu_member_TO_edu_manager -- 회원 -> 매니저
  FOREIGN KEY (
  mrno -- 매니저번호
  )
  REFERENCES edu_member ( -- 회원
  mno -- 회원번호
  );

-- 강의배정
ALTER TABLE edu_lect_teacher
  ADD CONSTRAINT FK_edu_lect_TO_edu_lect_teacher -- 강의 -> 강의배정
  FOREIGN KEY (
  lno -- 강의번호
  )
  REFERENCES edu_lect ( -- 강의
  lno -- 강의번호
  );

-- 강의배정
ALTER TABLE edu_lect_teacher
  ADD CONSTRAINT FK_edu_teacher_TO_edu_lect_teacher -- 강사 -> 강의배정
  FOREIGN KEY (
  tno -- 강사번호
  )
  REFERENCES edu_teacher ( -- 강사
  tno -- 강사번호
  );