/*4. 다음의 조건에 따라 제품의 제품번호, 제품이름, 제품가격수준, 제품설명을 추출하시오
제품가격수준: 제품가격이 3$대이면 ‘적당’ 11%대이면 ‘비쌈’ 나머지는 ‘보통’
조건: 공급업체의 우편번호 앞의 2자리가 ‘44’, ‘45’, ’99’ 인 공급업체에서 만들고
주문된 날자가 5월이 아닌 제품*/    
SELECT DISTINCT p.prod_id
    , TRIM(p.prod_name) prod_name
    , DECODE(TRUNC(p.prod_price), '3', '적당', '11', '비쌈', '보통') price_bunrye
    , p.prod_desc
FROM products p, vendors v, orderitems oi, orders o
WHERE SUBSTR(v.vend_zip, 1, 2) IN ('44', '45', '99')
AND p.vend_id = v.vend_id
AND p.prod_id = oi.prod_id
AND oi.order_num = o.order_num
AND TO_CHAR(o.order_date, 'mm') <> '05'
ORDER BY p.prod_id;

SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , DECODE(TRUNC(p.prod_price), '3', '적당', '11', '비쌈', '보통') price_nanum
    , p.prod_desc
FROM products p, vendors v
WHERE SUBSTR(v.vend_zip, 1, 2) IN ('44', '45', '99')
AND p.vend_id = v.vend_id
AND EXISTS (SELECT *
            FROM orders o, orderitems oi
            WHERE o.order_num = oi.order_num
            AND oi.prod_id = p.prod_id
            AND TO_CHAR(o.order_date, 'mm') <> '05'
            )
ORDER BY p.prod_id;

--안되는 코드
SELECT p.prod_id, p.prod_name, p.prod_desc
    , DECODE(TRUNC(p.prod_price), '3', '적당', '11', '비쌈', '보통')        
FROM products p, vendors v
WHERE SUBSTR(v.vend_zip, 1, 2) IN ('44', '45', '99')
AND p.vend_id = v.vend_id
AND (SELECT TO_CHAR(o.order_date, 'mm')
    FROM orders o, orderitems oi
    WHERE o.order_num = oi.order_num
    AND oi.prod_id = p.prod_id
    ) <> '05';
            
SELECT TO_CHAR(o.order_date, 'mm')
FROM orders o;

SELECT TO_CHAR(o.order_date, 'mm')
FROM orders o, orderitems oi, products p
WHERE o.order_num = oi.order_num
AND oi.prod_id = p.prod_id;

/*4_2. 다음의 조건에 따라 제품의 제품번호, 제품이름, 제품가격수준, 제품설명을 추출하시오
제품가격수준: 제품가격이 3$대이면 ‘적당’ 11%대이면 ‘비쌈’ 나머지는 ‘보통’
조건: 공급업체의 우편번호 앞의 2자리가 ‘44’, ‘45’, ’99’ 인 공급업체에서 만들거나 (합집합)
주문된 날자가 5월이 아닌 제품*/   
SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , DECODE(TRUNC(p.prod_price), '3', '적당', '11', '비쌈', '보통') price_nanum
    , p.prod_desc
FROM products p, vendors v
WHERE SUBSTR(v.vend_zip, 1, 2) IN ('44', '45', '99')
AND p.vend_id = v.vend_id
UNION
SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , DECODE(TRUNC(p.prod_price), '3', '적당', '11', '비쌈', '보통') price_nanum
    , p.prod_desc
FROM products p
WHERE EXISTS (SELECT *
            FROM orders o, orderitems oi
            WHERE o.order_num = oi.order_num
            AND oi.prod_id = p.prod_id
            AND TO_CHAR(o.order_date, 'mm') <> '05'
            );
            
            
SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , DECODE(TRUNC(p.prod_price), '3', '적당', '11', '비쌈', '보통') price_nanum
    , p.prod_desc
FROM products p, vendors v
WHERE SUBSTR(v.vend_zip, 1, 2) IN ('44', '45', '99')
AND p.vend_id = v.vend_id
UNION
SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , DECODE(TRUNC(p.prod_price), '3', '적당', '11', '비쌈', '보통') price_nanum
    , p.prod_desc
FROM products p
WHERE NOT EXISTS (SELECT * --주문 안한 제품도 함께 나온다. -> NOT EXISTS 안에 조건이 많아서 안걸러지는 것 같다(아마도)
            FROM orders o, orderitems oi
            WHERE o.order_num = oi.order_num
            AND oi.prod_id = p.prod_id
            AND TO_CHAR(o.order_date, 'mm') = '05' -- 업무상에서는 긍정의 표현을 많이 써서 안에 긍정으로 쓰고 NOT EXISTS를 쓰는 편이다.
            );
            


SELECT DISTINCT *
FROM orders o, orderitems oi, products p
WHERE o.order_num = oi.order_num
AND oi.prod_id = p.prod_id
AND TO_CHAR(o.order_date, 'mm') <> '05';

/*CASE WHEN*/
--우편번호로 지역 type을 구분
SELECT vend_id,
    CASE WHEN SUBSTR(vend_zip, 1, 2) = '44' THEN 'A'
         WHEN SUBSTR(vend_zip, 1, 2) = '99' THEN 'B'
         ELSE 'C'
    END AS resion_type
FROM vendors; 

--기본 제품보다 주문 시 낮은 단가로 주문되는 상품 중에 가격을 5$를 기준으로 고가/저가/일치로 구분
SELECT order_num, prod_id,
    CASE WHEN item_price > 5 THEN '고가'
         WHEN item_price < 5 THEN '저가'
         ELSE '일치'
    END AS price_std
FROM orderitems p
WHERE item_price >= (SELECT MIN(prod_price)
                    FROM products
                    WHERE prod_id = p.prod_id
                    );
                    
-- 공급업체의 주별 수를 컬럼으로 분리하여 추출
SELECT SUM(CASE WHEN vend_state = 'MI' THEN 1 END) MI_CNT,
       SUM(CASE WHEN vend_state = 'OH' THEN 1 END) OH_CNT,
       SUM(CASE WHEN vend_state = 'CA' THEN 1 END) CA_CNT,
       SUM(CASE WHEN vend_state = 'NY' THEN 1 END) NY_CNT
FROM vendors;


/*1. 주문의 주문번호, 고객번호, 주문시기를 추출하시오
주문시기: 일이 1~15일 사이면 ‘상일’, 16~31일 사이면 ‘하일’*/
SELECT order_num
    , cust_id
    , CASE WHEN TO_CHAR(order_date, 'dd') BETWEEN '01' AND '15' THEN '상일'
           WHEN TO_CHAR(order_date, 'dd') BETWEEN '16' AND '31' THEN '하일'
           ELSE '오류'
      END AS order_sig
FROM orders;

   SELECT order_num, cust_id,
          CASE WHEN TO_CHAR(order_date,'DD') >= '01' AND TO_CHAR(order_date,'DD') <= '15' THEN '상일'
               WHEN TO_CHAR(order_date,'DD') >= '16' AND TO_CHAR(order_date,'DD') <= '31' THEN '하일'
          END order_period
   FROM   orders
   ;


/*2. 주문에서 주문번호와 주문가능날자를 추출하시오
주문가능날자: 주문날자가 1~15일이면 현재날자, 16~31일이면 다음달 1일*/
SELECT order_num
    , TO_CHAR(order_date, 'yyyy-mm-dd') order_date
    , CASE WHEN TO_CHAR(order_date, 'dd') BETWEEN '01' AND '15' THEN TO_CHAR(order_date, 'yyyy-mm-dd')
           WHEN TO_CHAR(order_date, 'dd') BETWEEN '16' AND '31' THEN TO_CHAR(TRUNC(ADD_MONTHS(order_date, 1), 'mm'), 'yyyy-mm-dd')
           ELSE '오류'
      END AS order_pos
FROM orders;

   SELECT  order_num,
           CASE WHEN TO_CHAR(order_date,'DD') >= '01' AND TO_CHAR(order_date,'DD') <= '15' THEN TO_CHAR(SYSDATE,'YYYY-MM-DD')
                WHEN TO_CHAR(order_date,'DD') >= '16' AND TO_CHAR(order_date,'DD') <= '31' THEN TO_CHAR(SYSDATE+1, 'YYYY-MM-DD')
          END orderCanDt
   FROM    orders
   ;

/*3. 주문에서 주문번호와 주문가능날자(YYYY-MM-DD)를 추출하시오
- 주문가능날자: 일자의 10자리가 0이면 1일, 1이면 10일, 20이면 20일, 30이면 30일*/
SELECT order_num
    , TO_CHAR(order_date, 'yyyy-mm-dd') order_date
    , CASE WHEN SUBSTR(TO_CHAR(order_date, 'dd'), 1, 1) = '0' THEN TO_CHAR(order_date, 'yyyy-mm-') || '01'
           WHEN SUBSTR(TO_CHAR(order_date, 'dd'), 1, 1) = '1' THEN TO_CHAR(order_date, 'yyyy-mm-') || '10'
           WHEN SUBSTR(TO_CHAR(order_date, 'dd'), 1, 1) = '2' THEN TO_CHAR(order_date, 'yyyy-mm-') || '20'
           WHEN SUBSTR(TO_CHAR(order_date, 'dd'), 1, 1) = '3' THEN TO_CHAR(order_date, 'yyyy-mm-') || '30'
           ELSE '오류'
      END AS order_pos
FROM orders;


SELECT order_num,
        TO_CHAR(
            CASE WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '0' THEN TO_DATE(TO_CHAR(order_date,'YYYYMM')||'01')
                 WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '1' THEN TO_DATE(TO_CHAR(order_date,'YYYYMM')||'10')
                 WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '2' THEN TO_DATE(TO_CHAR(order_date,'YYYYMM')||'20')
                WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '3' THEN TO_DATE(TO_CHAR(order_date,'YYYYMM')||'30')
             END, 'YYYY-MM-DD') orderCanDt     
FROM   orders
;


/*데이터 입력*/
--CUSTOMERS에 모든 컬럼에 대해 DATA 추가
INSERT INTO customers
VALUES('1000000016', 'Toy Land', '123 Any Street', 'New York', 'NY', '11111', 'USA', NULL, NULL);

--Customers에 특정 컬럼에 대해 Data 추가
INSERT INTO customers(cust_id, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country, cust_contact)
VALUES('1000000017', 'Toy Land', '123 Any Street', 'New York', 'NY', '11111', 'USA', NULL);

/*초기값을 포함하여 테이블 생성*/
CREATE TABLE custnew AS
SELECT * FROM customers;

select * from custnew;

DELETE FROM custnew;

/*SELECT 쿼리의 결과로 데이터 입력*/
-- 각 컬럼 간 데이터 타입이 일치해야 한다
INSERT INTO custnew(cust_id, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country)
SELECT cust_id, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country
FROM customers;

/*연습 문제*/
INSERT INTO customers(cust_id, cust_name, cust_address, cust_city, cust_zip, cust_country, cust_contact, cust_email)
VALUES('1000000006', '홍길동', '서울시 서초구 강남대로 459', 'Seoul', '51243', 'KOR', 'Park', 'aaa@naver.com');
INSERT INTO customers(cust_id, cust_name, cust_address, cust_city, cust_zip, cust_country, cust_contact, cust_email)
VALUES('1000000007', '박길동', '서울시 서초구 강남대로 459', 'Seoul', '51472', 'KOR', 'Kim', 'bbb@daum.net');
INSERT INTO customers(cust_id, cust_name, cust_address, cust_city, cust_zip, cust_country, cust_contact, cust_email)
VALUES('1000000008', '신선우', '서울시 서초구 강남대로 459', 'Seoul', '53782', 'KOR', 'Kim', 'ccc@google.co.kr');

DELETE customers WHERE cust_id = '1000000008';

INSERT INTO products (prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('KR01', 'BRS01', '뽀로로인형', '10', 'TV프르그램으로 유명한 뽀로로를 인형으로 만듬');
INSERT INTO products (prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('KR02', 'BRE02', '곰인형', '15', '요즘 유행하는 곰모양의 인형');
INSERT INTO products (prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('KR03', 'DLL01', '태권V로보트', '100', '향수를 자극하는 어른을 위한 태권V로보트');

select * from products;

INSERT INTO orders
VALUES('20010', '2019-02-01', '1000000006');
INSERT INTO orders
VALUES('20011', '2019-05-04', '1000000007');

INSERT INTO orderitems
VALUES('20010', '1', 'KR01', '2', '9');
INSERT INTO orderitems
VALUES('20011', '1', 'KR02', '2', '16');
INSERT INTO orderitems
VALUES('20010', '2', 'KR03', '3', '95');

/*UPDATE : 데이터 업데이트*/
--ID가 1000000005인 고객의 전자메일주소를 변경
UPDATE customers
SET cust_email = 'kim@naver.com'
    , cust_contact = 'Sam Roverts'
WHERE cust_id = '1000000005';

/*1. 고객테이블에서 고객번호가 ‘1000000006’인 고객의 담당자를 ‘Jin’으로 변경하시오*/
UPDATE customers
SET cust_contact = 'Jin'
WHERE cust_id = '1000000006';

/*2. 주문테이블에서 고객국가가 ‘KOR’인 고객이 주문한 항목수량에 +1씩을 더하시오*/
UPDATE orderitems oi
SET quantity = quantity + 1
WHERE EXISTS (SELECT *
              FROM orders o, customers c
              WHERE c.cust_id = o.cust_id
              AND o.order_num = oi.order_num
              AND c.cust_country = 'KOR'
             );
             
SELECT * FROM orderitems;

/*3. 주문날자가 2019년도에 발생한 주문 중에 주문상품의 항목가격이 90$를 넘는 항목가격에 대해 10%씩을 DC하여 변경하시오*/
UPDATE orderitems oi
SET item_price = item_price * 0.9
WHERE EXISTS (SELECT *
              FROM orders o
              WHERE o.order_num = oi.order_num
              AND TO_CHAR(o.order_date, 'yyyy') = '2019'
              AND oi.item_price > 90
              );
              
              
UPDATE orderitems k
SET    item_price = (item_price - item_price*0.1)
WHERE  order_num IN (
          SELECT order_num
          FROM   orders
          WHERE  TO_CHAR(order_date, 'YYYY') = '2019'
       )
AND    item_price > 90       
;
SELECT oi.item_price, p.prod_price
FROM orderitems oi, products p
WHERE oi.prod_id = p.prod_id;

/*4. 주문상품중에 제품의 제품가격보다 주문상품의 항목가격이 비싼 항목가격을 제품의 제품가격으로 변경하시오*/
UPDATE orderitems oi
SET item_price = (SELECT prod_price
                    FROM products
                    WHERE prod_id = oi.prod_id
                    )
WHERE item_price > (SELECT prod_price
                    FROM products
                    WHERE prod_id = oi.prod_id
                    );
                    
UPDATE orderitems k
SET    item_price = (
                      SELECT prod_price
                      FROM   products
                      WHERE  prod_id = k.prod_id
                     )
WHERE  prod_id IN (
         SELECT prod_id
         FROM   products b
         WHERE  k.item_price > b.prod_price
       )
;

select p.prod_price, oi.item_price from orderitems oi , products p
WHERE p.prod_id = oi.prod_id;

/*1. 제품번호가 KR03인 제품을 제품테이블에서 삭제하고 주문상품테이블에서도 삭제하시오*/
DELETE FROM orderitems
WHERE prod_id = 'KR03';

DELETE FROM products
WHERE prod_id = 'KR03';


/*2. 공급업체가 ‘BRE02’인 공급업체가 생산한 제품중 ‘곰인형’ 제품을 주문한 주문정보를 삭제하시오*/
DELETE FROM orderitems
WHERE prod_id IN (SELECT prod_id
                    FROM products
                    WHERE vend_id = 'BRE02'
                    AND prod_name LIKE '%곰인형%'
                    )
;

DELETE FROM orders o
WHERE NOT EXISTS(SELECT order_num
                FROM orderitems
                WHERE order_num = o.order_num
                )
;                

/*3. 고객 ‘1000000006’가 주문한 주문정보를 삭제하시오*/
DELETE FROM orderitems
WHERE order_num IN (SELECT order_num
                    FROM orders
                    WHERE cust_id = '1000000006'
                    )
;

DELETE FROM orders o
WHERE NOT EXISTS(SELECT order_num
                FROM orderitems
                WHERE order_num = o.order_num
                )
;                

