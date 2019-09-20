                  
CREATE TABLE member
(
  member_id   varchar2(50)  NOT NULL ,
  name       varchar2(50)  NOT NULL ,
  password   varchar2(10)  NOT NULL ,
  answer     varchar2(50)  NOT NULL ,
  regdate    timestamp  NOT NULL
);

ALTER TABLE member ADD CONSTRAINT PK_member PRIMARY KEY (member_id);

select * from member;

drop table member;

select count(*) as cnt
from member
where member_id = 'hong'
and password='1212';

CREATE TABLE guestbook_message
(
  message_id   int  NOT NULL ,
  guest_name   varchar2(50)  NOT NULL ,
  password     varchar2(10)  NOT NULL ,
  message      varchar2(4000)  NOT NULL
);

drop table guestbook_message;

select * from guestbook_message order by message_id asc;

select * from (select row_number() over(order by message_id) rnum, a.* from guestbook_message a order by message_id asc) where rnum >= 1 and rnum <= 3 order by rnum asc;

select rownum rnum, a.* from guestbook_message a order by message_id asc;

select row_number() over(order by message_id) rnum, a.* from guestbook_message a order by message_id asc;

CREATE TABLE member
(
  member_id   varchar2(50)  NOT NULL ,
  name       varchar2(50)  NOT NULL ,
  password   varchar2(10)  NOT NULL ,
  regdate    timestamp  NOT NULL
);

CREATE TABLE article
(
  article_no int  NOT NULL ,
  writer_id   varchar2(50)  NOT NULL ,
  writer_name varchar2(50)  NOT NULL ,
  title      varchar2(255)  NOT NULL ,
  regdate    timestamp  NOT NULL,
  moddate    timestamp  NOT NULL,
  read_cnt   int  NULL
);

drop table article;



CREATE TABLE article_content
(
  article_no int  NOT NULL ,
  content    varchar2(4000)  NULL
);

ALTER TABLE guestbook_message ADD CONSTRAINT PK_guestbook_message PRIMARY KEY (message_id);
ALTER TABLE member ADD CONSTRAINT PK_member PRIMARY KEY (member_id);
ALTER TABLE article ADD CONSTRAINT PK_article PRIMARY KEY (article_no);
ALTER TABLE article_content ADD CONSTRAINT PK_article_content PRIMARY KEY (article_no);

create sequence message_id_seq start with 1 increment BY 1 maxvalue 10000;
create sequence memberid_seq start with 1 increment BY 1 maxvalue 10000;
create sequence article_no_seq start with 1 increment BY 1 maxvalue 10000;

alter sequence article_no_seq increment by 1;


insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä2', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä3', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä4', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä5', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä6', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä7', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä8', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä9', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä10', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä11', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä12', sysdate, sysdate, 0);

insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä13', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä14', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä15', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä16', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä17', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä18', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä19', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä20', sysdate, sysdate, 0);
insert into article values(article_no_seq.nextval, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä23', sysdate, sysdate, 0);
insert into article values(1, 'lee', 'ÀÌ', '¾È³çÇÏ¼¼¿ä21', sysdate, sysdate, 0);
commit;
insert into ARTICLE_CONTENT (select article_no, 'dd' content 
                            from article 
                            where article_no = '1');
                            
select * from article_content;

select * from article;

select * from member;

commit;

select article_no, writer_id, writer_name, title, regdate, moddate from article;

select a.*,
(select content from article_content ac where ac.article_no = a.article_no) content
from article a 
where a.article_no = '1';

update article set read_cnt = read_cnt + 1 where article_no = '9';


select * from t_member;

select * from customers;


select trim(cust_id) cust_id, trim(cust_address) cust_address, trim(cust_state) cust_state, trim(cust_zip) cust_zip,trim(cust_country) cust_country, trim(cust_contact) cust_contact, trim(cust_email) cust_eamil from customers where cust_id = '1000000001';

select * from customers order by cust_id;

select *
from(
select cust_id, cust_name, LAG(cust_id) OVER(ORDER BY cust_id) before, LEAD(cust_id) OVER(ORDER BY cust_id) from customers)
where cust_id = '12';

select cust_state from customers where cust_state not in('mi');