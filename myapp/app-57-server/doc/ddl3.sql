-- 회원
DROP TABLE IF EXISTS myapp_member RESTRICT;

-- 게시글
DROP TABLE IF EXISTS myapp_board RESTRICT;

-- 게시판유형
DROP TABLE IF EXISTS myapp_board_category RESTRICT;

-- 게시글첨부파일
DROP TABLE IF EXISTS myapp_board_file RESTRICT;

-- 회원
CREATE TABLE myapp_member (
  member_no    INTEGER      NOT NULL COMMENT '번호', -- 번호
  name         VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  email        VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  password     VARCHAR(100) NOT NULL COMMENT '암호', -- 암호
  gender       CHAR(1)      NOT NULL COMMENT '성별', -- 성별
  created_date DATE         NOT NULL DEFAULT (current_date()) COMMENT '등록일' -- 등록일
)
COMMENT '회원';

-- 회원
ALTER TABLE myapp_member
  ADD CONSTRAINT PK_myapp_member -- 회원 기본키
  PRIMARY KEY (
  member_no -- 번호
  );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_myapp_member
  ON myapp_member ( -- 회원
    email ASC -- 이메일
  );

ALTER TABLE myapp_member
  MODIFY COLUMN member_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '번호';

-- 게시글
CREATE TABLE myapp_board (
  board_no     INTEGER      NOT NULL COMMENT '번호', -- 번호
  title        VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content      MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  view_count   INTEGER      NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
  created_date DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  writer       INTEGER      NOT NULL COMMENT '작성자', -- 작성자
  category     INTEGER      NOT NULL COMMENT '게시판' -- 게시판
)
COMMENT '게시글';

-- 게시글
ALTER TABLE myapp_board
  ADD CONSTRAINT PK_myapp_board -- 게시글 기본키
  PRIMARY KEY (
  board_no -- 번호
  );

ALTER TABLE myapp_board
  MODIFY COLUMN board_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '번호';

-- 게시판유형
CREATE TABLE myapp_board_category (
  board_category_no INTEGER     NOT NULL COMMENT '번호', -- 번호
  name              VARCHAR(50) NOT NULL COMMENT '게시판이름' -- 게시판이름
)
COMMENT '게시판유형';

-- 게시판유형
ALTER TABLE myapp_board_category
  ADD CONSTRAINT PK_myapp_board_category -- 게시판유형 기본키
  PRIMARY KEY (
  board_category_no -- 번호
  );

-- 게시판유형 유니크 인덱스
CREATE UNIQUE INDEX UIX_myapp_board_category
  ON myapp_board_category ( -- 게시판유형
    name ASC -- 게시판이름
  );

ALTER TABLE myapp_board_category
  MODIFY COLUMN board_category_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '번호';

-- 게시글첨부파일
CREATE TABLE myapp_board_file (
  board_file_no INTEGER      NOT NULL COMMENT '번호', -- 번호
  filepath      VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  board_no      INTEGER      NOT NULL COMMENT '게시글번호' -- 게시글번호
)
COMMENT '게시글첨부파일';

-- 게시글첨부파일
ALTER TABLE myapp_board_file
  ADD CONSTRAINT PK_myapp_board_file -- 게시글첨부파일 기본키
  PRIMARY KEY (
  board_file_no -- 번호
  );

ALTER TABLE myapp_board_file
  MODIFY COLUMN board_file_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '번호';

-- 게시글
ALTER TABLE myapp_board
  ADD CONSTRAINT FK_myapp_member_TO_myapp_board -- 회원 -> 게시글
  FOREIGN KEY (
  writer -- 작성자
  )
  REFERENCES myapp_member ( -- 회원
  member_no -- 번호
  );

-- 게시글
ALTER TABLE myapp_board
  ADD CONSTRAINT FK_myapp_board_category_TO_myapp_board -- 게시판유형 -> 게시글
  FOREIGN KEY (
  category -- 게시판
  )
  REFERENCES myapp_board_category ( -- 게시판유형
  board_category_no -- 번호
  );

-- 게시글첨부파일
ALTER TABLE myapp_board_file
  ADD CONSTRAINT FK_myapp_board_TO_myapp_board_file -- 게시글 -> 게시글첨부파일
  FOREIGN KEY (
  board_no -- 게시글번호
  )
  REFERENCES myapp_board ( -- 게시글
  board_no -- 번호
  );