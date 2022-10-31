# 이력관리 프로그램

### 사용DB 변경 후 권한부여
```sql
CREATE USER 'green'@'%' IDENTIFIED BY 'green1234';
CREATE DATABASE jobsite;
GRANT ALL PRIVILEGES ON jobsite.* TO 'green'@'%';
```

### 테이블 생성
```sql
create table employee(
    employee_id int primary KEY AUTO_INCREMENT,
    employee_name VARCHAR(24) NOT null,
    employee_birth VARCHAR(24) NOT NULL,
    employee_sex VARCHAR(8) NOT null,
    employee_username VARCHAR(24) NOT null,
    employee_password VARCHAR(24) NOT null,
    employee_email VARCHAR(64) NOT null,
    employee_tel VARCHAR(24) NOT NULL ,
    employee_location VARCHAR(128) NOT null
);

create table resume(
    resume_id int primary KEY auto_increment,
    resume_title VARCHAR(24) NOT null,
    employee_id INT NOT null,
    resume_image_id INT,
    highschool_name VARCHAR(24) ,
    highschool_startdate VARCHAR(24),
    highschool_enddate VARCHAR(24),
    highschool_major VARCHAR(24),
    univ_name VARCHAR(24),
    univ_startdate VARCHAR(24),
    univ_enddate VARCHAR(24),
    univ_major VARCHAR(24),
    univ_grades VARCHAR(8),
    prev_co VARCHAR(40),
    career_period VARCHAR(40),
    career_position VARCHAR(24),
    career_department VARCHAR(24),
    career_task varchar(128),
    job_id INT NOT null,
    is_main BOOLEAN NOT null,
    created_at TIMESTAMP NOT null
);

create table company(
    company_id int primary KEY auto_increment,
    company_number INT NOT null,
    company_name VARCHAR(24) NOT null,
    company_email VARCHAR(64) NOT null,
    company_tel VARCHAR(24) NOT null,
    company_location VARCHAR(256) NOT null,
    company_username VARCHAR(24) NOT null,
    company_password VARCHAR(24) NOT null
);

create table intro(
    intro_id int primary KEY auto_increment,
    company_id INT,
    intro_coname VARCHAR(24) NOT null,
    intro_birth VARCHAR(24) NOT null,
    intro_task VARCHAR(256) NOT null,
    intro_sal VARCHAR(24),
    intro_wellfare LONGTEXT,
    intro_content LONGTEXT,
    intro_location VARCHAR(256),
    job_id INT,
    intro_image_id INT
);

create TABLE notice(
    notice_id int primary KEY auto_increment,
    company_id INT,
    notice_title VARCHAR(40),
    notice_period VARCHAR(24),
    notice_dept VARCHAR(24),
    notice_position VARCHAR(24),
    notice_task VARCHAR(256),
    notice_sal VARCHAR(24),
    notice_qual VARCHAR(24),
    notice_career VARCHAR(24),
    notice_wellfare LONGTEXT,
    job_id int
);

create table job(
    job_id int primary KEY auto_increment,
    job_name VARCHAR(24) NOT NULL,
    job_code INT NOT null
);

create table subscribe(
    subscribe_id int primary KEY auto_increment,
    employee_id INT NOT null,
    company_id INT NOT NULL
);

create table application(
    application_id int primary KEY auto_increment,
    resume_id INT NOT null,
    notice_id INT NOT null,
    created_at TIMESTAMP NOT null
);

create table resume_image(
    resume_image_id int primary KEY auto_increment,
    origin_image_name VARCHAR(4000) NOT null,
    new_image_name VARCHAR(4000) NOT null,
    image_path VARCHAR(4000) NOT null,
    created_at TIMESTAMP NOT null
);

create table intro_image(
    intro_image_id int primary KEY auto_increment,
    company_id int not null,
    origin_image_name VARCHAR(4000) NOT null,
    new_image_name VARCHAR(4000) NOT null,
    image_path VARCHAR(4000) NOT null,
    created_at TIMESTAMP NOT null
);

create table emp_check(
    emp_check_id int primary KEY auto_increment,
    employee_id INT NOT null,
    job_id INT NOT null
);

create table co_check(
    co_check_id int primary KEY auto_increment,
    company_id INT NOT null,
    job_id INT NOT null
);

```

### 더미데이터 추가
```sql
insert into employee(employee_name, employee_birth, employee_sex, employee_username, employee_password, employee_email, employee_tel, employee_location) 
VALUES('이성진', '1993-08-18', '남', 'jinsa', '1234', 'jinsa004@naver.com', '010-7164-9311', '김해');
insert into employee(employee_name, employee_birth, employee_sex, employee_username, employee_password, employee_email, employee_tel, employee_location) 
VALUES('전영재', '1993-08-05', '남', 'wjsdudwo', '1234', 'wjsdudwox@naver.com', '010-1111-2222', '부산');
insert into employee(employee_name, employee_birth, employee_sex, employee_username, employee_password, employee_email, employee_tel, employee_location) 
VALUES('정회지', '1999-11-22', '여', 'hj12', '1234', 'hj12@naver.com', '010-2222-3333', '부산');
insert into resume(resume_title, employee_id, resume_image_id, highschool_name, highschool_startdate, highschool_enddate, highschool_major, univ_name, univ_startdate, univ_enddate, univ_major, univ_grades, prev_co, career_period, career_position, career_department, career_task, job_id, is_main, created_at) 
VALUES('완성하겠습니다.', 1, NULL, "영운고", '2009-03-01', '2012-02-01', "문과", NULL, NULL, NULL, NULL, NULL, "김해여객", "1년", "주임", "영업관리부", "사무업무", 1, 0, NOW());
insert into resume(resume_title, employee_id, resume_image_id, highschool_name, highschool_startdate, highschool_enddate, highschool_major, univ_name, univ_startdate, univ_enddate, univ_major, univ_grades, prev_co, career_period, career_position, career_department, career_task, job_id, is_main, created_at) 
VALUES('최선을 다하겠습니다..', 2, NULL, NULL, NULL, NULL, NULL, "서면대", '2012-03-01', '2018-02-01', "영어영문학과", 3.3, "보성엔진", "1년", "사원", "해외영업", "무역관리", 2, 0, NOW());
INSERT INTO company
(company_number, company_name, company_email, company_tel, company_location, company_username, company_password)
VALUES(621070, '삼성전자', 'aabb@samsung.com', '02-1234-1234', '부산시 부산진구 어디어디',  'samsungman1234', 'q1w2e3r4');
INSERT INTO company
(company_number, company_name, company_email, company_tel, company_location, company_username, company_password)
VALUES(110152, '보성엔지니어링', 'ebz2@bosung.com', '051-621-0864', '부산시 부산진구 초량동 어디어디', 'bosung1234', 'q1w2e3r4!!');
INSERT INTO company
(company_number, company_name, company_email, company_tel, company_location, company_username, company_password)
VALUES(117242, 'LG전자', 'veda@lgelectronic.com', '02-5522-1854', '부산시 부산진구 어디어디', 'LGman1234', 'q1w2e3r4!@');
INSERT INTO intro
(company_id, intro_coname, intro_birth, intro_task, intro_sal, intro_wellfare, intro_content, intro_location, job_id, intro_image_id)
VALUES(1, '회사소개입니다', '1998-02-24', 'flutter 신규 앱 개발', '2600만원', '야근거의없음, 월1회 회식', '우리회사는 어쩌고저쩌고 이렇습니다', '부산시 부산진구 어디어디', 3, NULL);
INSERT INTO intro
(company_id, intro_coname, intro_birth, intro_task, intro_sal, intro_wellfare, intro_content, intro_location, job_id, intro_image_id)
VALUES(2, '회사소개입니당', '2007-07-01', 'DB 관리', '2400만원', '야근거의없음, 분기당 1회 회식', '우리회사는 어쩌고저쩌고 저렇습니다', '부산시 부산진구 어디어디', 2, NULL);
INSERT INTO intro
(company_id, intro_coname, intro_birth, intro_task, intro_sal, intro_wellfare, intro_content, intro_location, job_id, intro_image_id)
VALUES(3, '회사소개요', '1995-11-20', '웹디자인', '2800만원', '전자레인지 있음, 주5회 회식', '우리회사는 어쩌고저쩌고 그렇다', '부산시 부산진구 어디어디', 1, NULL);
INSERT INTO notice(company_id, notice_title, notice_period, notice_dept, notice_position, notice_task, notice_sal, notice_qual, notice_career, notice_wellfare, job_id)
VALUES(1, '백엔드 개발자 모집중', '2022-10-30', '백엔드개발', '사원', 'java 코딩', '회사 내규에 따름', '대졸', '신입', '전자레인지 있음, 커피 제공', 2);
INSERT INTO notice(company_id, notice_title, notice_period, notice_dept, notice_position, notice_task, notice_sal, notice_qual, notice_career, notice_wellfare, job_id)
VALUES(2, '프론트엔드 개발자 모집합니다.', '2022-10-30', '프론트엔드개발', '사원', 'html 코딩', '회사 내규에 따름', '대졸', '신입', '전자레인지 있음, 커피 제공', 1);
INSERT INTO notice(company_id, notice_title, notice_period, notice_dept, notice_position, notice_task, notice_sal, notice_qual, notice_career, notice_wellfare, job_id)
VALUES(3,'flutter 풀스택 개발자 모집합니다.', '2022-10-30', '풀스택개발', '사원', 'flutter 코딩', '3600만원', '고졸', '3년', '경조사비 제공, 분기별 보너스 지급', 3);
insert into job(job_name, job_code) 
VALUES("프론트엔드", 1);
insert into job(job_name, job_code) 
VALUES("백엔드", 2);
insert into job(job_name, job_code) 
VALUES("풀스택", 3);
insert into job(job_name, job_code) 
VALUES("안드로이드", 4);
insert into job(job_name, job_code) 
VALUES("IOS", 5);
insert into subscribe(employee_id, company_id) 
VALUES(1,1);
insert into subscribe(employee_id, company_id) 
VALUES(1,2);
insert into subscribe(employee_id, company_id) 
VALUES(2,1);
insert into application(resume_id, notice_id, created_at) 
VALUES(1,1,NOW());
insert into application(resume_id, notice_id, created_at) 
VALUES(1,2,NOW());
insert into application(resume_id, notice_id, created_at) 
VALUES(2,1,NOW());
insert into resume_image(origin_image_name, new_image_name, image_path, created_at) 
VALUES("100100101", "10101110", "D:/user/resume/image", NOW());
insert into resume_image(origin_image_name, new_image_name, image_path, created_at) 
VALUES("100100111", "10101111", "D:/user/resume/image", NOW());
insert into resume_image(origin_image_name, new_image_name, image_path, created_at)
VALUES("100101101", "10111110", "D:/user/resume/image", NOW());
insert into intro_image(company_id, origin_image_name, new_image_name, image_path, created_at) 
VALUES(1,"110101101", "11111110", "D:/user/notice/image", NOW());
insert into intro_image(company_id, origin_image_name, new_image_name, image_path, created_at) 
VALUES(2,"101101101", "10111111", "D:/user/notice/image", NOW());
insert into intro_image(company_id, origin_image_name, new_image_name, image_path, created_at) 
VALUES(3,"11101101", "11001110", "D:/user/notice/image", NOW());
insert into emp_check(employee_id, job_id) 
VALUES(1, 1);
insert into emp_check(employee_id, job_id) 
VALUES(2, 2);
insert into emp_check(employee_id, job_id) 
VALUES(3, 1);
insert into co_check(company_id, job_id) 
VALUES(1, 1);
insert into co_check(company_id, job_id) 
VALUES(2, 2);
insert into co_check(company_id, job_id) 
VALUES(3, 2);
```