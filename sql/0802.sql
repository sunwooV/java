/*회원 정보를 저장하는 테이블을 생성하고 추가하는 SQL문*/

--회원 테이블 생성
CREATE TABLE t_member(
    id varchar2(10) primary key,
    pwd varchar2(10),
    name varchar2(50),
    email varchar2(50),
    joinDate date default sysdate
);

--회원 정보 추가
insert into t_member
values('hong', '1212', '홍길동', 'hong@gmail.com', sysdate);

insert into t_member
values('lee', '1212', '이순신', 'lee@test.com', sysdate);

insert into t_member
values('kim', '1212', '김유신', 'kim@web.com', sysdate);

commit;

select * from t_member;


SELECT *
FROM (SELECT c.cust_id, c.cust_name, o.order_num, oi.order_item, p.prod_name
      FROM customers c, orders o, orderitems oi, products p
      WHERE c.cust_id = o.cust_id
      AND o.order_num = oi.order_num
      AND oi.prod_id = p.prod_id
      ) k
WHERE cust_id = '1000000001'
ORDER BY k.cust_id;


SELECT c.cust_id, c.cust_name, o.order_num, oi.order_item, p.prod_name
FROM customers c, orders o, orderitems oi, products p
WHERE c.cust_id = o.cust_id
AND o.order_num = oi.order_num
AND oi.prod_id = p.prod_id
AND c.cust_id = '1000000001';

select c.cust_id, oi.order_num, p.prod_name, oi.quantity, oi.item_price
from products p, orderitems oi, orders o, customers c
where p.prod_id = oi.prod_id
and oi.order_num = o.order_num
and o.cust_id = c.cust_id
and c.cust_id = '1000000001';

ALTER TABLE OrderItems DROP CONSTRAINT FK_OrderItems_Orders;
ALTER TABLE OrderItems DROP CONSTRAINT FK_OrderItems_Products;
ALTER TABLE Orders DROP CONSTRAINT FK_Orders_Customers;
ALTER TABLE Products DROP CONSTRAINT FK_Products_Vendors;

insert into customers (cust_id, cust_name, cust_address)
values('1000000019', '신선우', '서울시 강남구');

commit;


select p.prod_id, p.prod_name, p.prod_price, p.prod_desc, v.vend_name
from products p, vendors v 
where p.vend_id = v.vend_id
AND p.prod_id like '%0%'
AND v.vend_id like '%0%';

select p.prod_id, p.prod_name, p.prod_price, p.prod_desc, v.vend_name, v.vend_id
from products p, vendors v 
where p.vend_id = v.vend_id
AND p.prod_id like '%0%';

insert into products (prod_id, prod_name, prod_price, prod_desc, vend_id) values ('dd','dd',10,'dddd','dfd');




delete from products where prod_id = 'dg';

select * from products;



select * from t_member;



select * from vendors;


insert into vendors (vend_id, vend_name, vend_address, vend_city, vend_state, vend_zip, vend_country)
values('ss', 'Bears R Sunwoo', 'djfdkfj12', 'Seoul', 'CA', 4444, 'USA');

commit;

update vendors
set vend_id = 'dd', vend_name = 'dd', vend_address = 'dd', vend_city = 'dd', vend_state = 'dd', vend_zip = 'dd', vend_country = 'dd'
where vend_id = 'ss';





SELECT C.cust_id, O.order_num
FROM Customers C LEFT OUTER JOIN Orders o
ON Customers.cust_id = Orders.cust_id;



select oi.order_num, oi.order_item, p.prod_id, p.prod_name, oi.quantity, oi.item_price
from orderitems oi, products p
where oi.prod_id = p.prod_id;
and oi.order_num = '20008';

delete from orderitems where order_num = '20008' and prod_id = 'BNBG02';

select *
