/*2. 공급업체와 제품을 조인하여 다음의 결과를 추출하시오
결과: 공급업체번호, 공급업체이름, 공급업체주소, 제품번호, 제품이름, 제품가격, 제품설명
조건: 공급업체별로 평균제품가격 이상인 제품*/
SELECT v.vend_id
    , TRIM(v.vend_name)
    , TRIM(v.vend_address)
    , p.prod_id
    , TRIM(p.prod_name)
    , p.prod_price
    , TRIM(p.prod_desc)
FROM vendors v, products p
WHERE v.vend_id = p.vend_id
AND p.prod_price >= (SELECT AVG(prod_price)
                    FROM products
                    WHERE vend_id = v.vend_id
                    );

/*3. 제품, 주문, 주문제품을 조인하여 다음의 결과를 추출하시오
결과: 주문번호, 주문일자, 제품번호, 제품이름, 제품가격, 항목가격
조건: 제품가격과 항목가격이 다른 제품*/
SELECT oi.order_num
    , TO_CHAR(o.order_date, 'yyyy-mm-dd') order_date
    , TRIM(p.prod_name) prod_name
    , p.prod_price
    , oi.item_price
FROM products p, orderitems oi, orders o
WHERE p.prod_id = oi.prod_id
AND oi.order_num = o.order_num
AND p.prod_price <> oi.item_price;

/*4. 공급업체, 제품, 주문제품을 조인하여 다음의 결과를 추출하시오
결과: 공급업체번호, 공급업체이름, 공급업체주소, 주문번호, 제품번호, 제품이름*/
SELECT v.vend_id
    , TRIM(v.vend_name) vend_name
    , TRIM(v.vend_address) vend_address
    , oi.order_num
    , p.prod_id
    , TRIM(p.prod_name) prod_name
FROM vendors v, products p, orderitems oi
WHERE oi.prod_id = p.prod_id
AND p.vend_id = v.vend_id;

SELECT a.vend_id, a.vend_name, a.vend_address, d.order_num, d.prod_id, c.prod_name
FROM   vendors a, orders b, products c, orderitems d
WHERE  d.order_num = b.order_num
AND    d.prod_id = c.prod_id
AND    c.vend_id = a.vend_id
;


/*외부조인*/
-- 아직 주문하지 않은 사람을 포함하여 모든 고객목록 추출
SELECT  c.cust_id, o.order_num
FROM customers c, orders o
WHERE c.cust_id (+)= o.cust_id; --Main이 되는 테이블에 (+) 표시

--ANSI SQL - 외부조인
SELECT c.cust_id, o.order_num
FROM customers c LEFT OUTER JOIN orders o
    ON c.cust_id = o.cust_id;
    
/*집계함수와 함께 조인을 사용*/
--주문한 고객의 목록과 각 고객이 주문한 수량을 추출
SELECT c.cust_id, COUNT(o.order_num) num_ord
FROM customers c INNER JOIN orders o 
    ON c.cust_id = o.cust_id
GROUP BY c.cust_id;

--주문을 안한 고객도 포함하여 추출
SELECT c.cust_id, COUNT(o.order_num) num_ord
FROM customers c LEFT OUTER JOIN orders o 
    ON c.cust_id = o.cust_id
GROUP BY c.cust_id;


--INNER JOIN : 모두의 값에 있는 행들만 포함시키고 그렇지 않는 행들은 제외 시킵니다.
--OUTER JOIN : 두 테이블에서 지정된 쪽인 LEFT 또는 RIGHT 쪽의 모든 결과를 보여준후 반대쪽에 매칭되는 값이 없어도 보여주는 JOIN  입니다.


/*1. 제품과 주문항목을 조인하여 다음의 결과를 추출하시오
결과: 제품번호, 제품이름, 주문번호, 항목수량, 항목가격
조건: 모든 제품이 조회되어야 한다*/
SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , oi.order_num
    , oi.quantity
    , oi.item_price
FROM products p LEFT OUTER JOIN orderitems oi
    ON oi.prod_id = p.prod_id;
    
SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , oi.order_num
    , oi.quantity
    , oi.item_price
FROM orderitems oi RIGHT OUTER JOIN products p
    ON oi.prod_id = p.prod_id;
--위 아래 같은 결과
SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , oi.order_num
    , oi.quantity
    , oi.item_price
FROM products p, orderitems oi
WHERE oi.prod_id (+)= p.prod_id;

/*2. 제품을 주문한 고객 수와 주문 안 한 고객 수를 추출하시오
결과: 주문여부, 고객 수*/
SELECT yon, COUNT(yon)
FROM (
    SELECT NVL((SELECT MAX('Y') --MAX()와 MIN() 함수는 임의로 컬럼을 생성하고 임시로 생성된 컬럼에 값이 없다면, 'N'로 변경
                FROM orders
                WHERE cust_id = c.cust_id
                ), 'N') yon
    FROM customers c
    )
GROUP BY yon;

SELECT MAX('P') --MAX()와 MIN() 함수는 임의로 컬럼을 생성하고 임시로 생성된 컬럼에 값이 없다면, 'N'로 변경
FROM orders o, customers c
WHERE o.cust_id = c.cust_id;

SELECT MAX('Y')
FROM orders o, customers c
WHERE o.cust_id = c.cust_id
GROUP BY o.cust_id;

    SELECT NVL((SELECT MIN('Y') --MAX()와 MIN() 함수는 임의로 컬럼을 생성하고 임시로 생성된 컬럼에 값이 없다면, 'N'로 변경
                FROM orders
                WHERE cust_id = c.cust_id
                ), 'N') yon
    FROM customers c;

/*3. 고객별로 주문한 상품 중에 가장 높은 금액을 가지는 상품을 추출하시오
결과: 고객번호, 고객이름, 제품번호, 제품이름, 항목가격*/

SELECT c.cust_id, c.cust_name, oi.prod_id
    , oi.item_price
    , (SELECT p.prod_name
        FROM products p
        WHERE p.prod_id = oi.prod_id
        )
FROM customers c, orders o, orderitems oi
WHERE c.cust_id = o.cust_id
AND o.order_num = oi.order_num;

SELECT c.cust_id, c.cust_name, MAX(oi.item_price)
FROM customers c, orders o, orderitems oi
WHERE c.cust_id = o.cust_id 
AND o.order_num = oi.order_num
group by c.cust_id, c.cust_name;

SELECT c.cust_id, c.cust_name, MAX(oi.item_price), p.prod_id, p.prod_name
FROM customers c, orders o, orderitems oi, products p
WHERE c.cust_id = o.cust_id 
AND o.order_num = oi.order_num
AND oi.prod_id = p.prod_id
group by c.cust_id, c.cust_name, p.prod_id, p.prod_name
HAVING oi.item_price = (SELECT MAX(oi2.item_price)
                    FROM orderitems oi2
                    WHERE oi2.prod_id = p.prod_id
                    AND oi2.order_num = o.order_num
                    AND o.cust_id = c.cust_id
                    GROUP BY c.cust_id
                    );


SELECT MAX(oi2.item_price)
FROM orderitems oi2,customers c, orders o, products p
                    WHERE oi2.prod_id = p.prod_id
                    AND oi2.order_num = o.order_num
                   AND o.cust_id = c.cust_id
                    GROUP BY c.cust_id;

SELECT c.cust_id
    , c.cust_name
    , p.prod_id
    , p.prod_name
    , MAX(oi.item_price)
FROM customers c, orders o, orderitems oi, products p
WHERE p.prod_id = oi.prod_id
AND oi.order_num = o.order_num
AND o.cust_id = c.cust_id
GROUP BY c.cust_id;



/*4. 공급업체별로 생산하는 제품의 수와 최고제품가격을 추출하시오
결과: 공급업체번호, 공급업체이름, 제품 수, 최고제품가격
조건: 8$이상의 제품을 생산하는 공급업체*/


/*5. 주문 중에 고객이 사는 도시가 ‘Detroit’이고 주문한 총 제품의 수가 500을 넘는 고객을 추출하시오
결과: 고객번호, 고객이름, 고객도시*/


/*UNION: 합집합, 2개의 SQL을 합하여 추출
- 열의 데이터 형식은 호환되어야 한다
- 일반적으로 UNION은 UNION DISTINCT와 같아서 중복열을 삭제해준다.*/
--IL, IN, MI 주에 있는 모든 고객과 고객명이 Fun4All 이라는 고객의 고객정보 추출
SELECT cust_name, cust_contact, cust_email, cust_state
FROM customers
WHERE cust_state IN ('IL', 'IN', 'MI')
UNION
SELECT cust_name, cust_contact, cust_email, cust_state
FROM customers
WHERE cust_name = 'Fun4All';

--다음과 같이 추출도 가능
SELECT cust_name, cust_contact, cust_email, cust_state
FROM customers
WHERE cust_state IN ('IL', 'IN', 'MI')
OR cust_name = 'Fun4ALl';

/*중복열 제거 x 및 정렬
--UNION ALL : 중복되는 열을 제거하지 않고 모두 보여준다.*/
SELECT cust_name, cust_contact, cust_email, cust_state
FROM customers
WHERE cust_state IN ('IL', 'IN', 'MI')
UNION ALL
SELECT cust_name, cust_contact, cust_email, cust_state
FROM customers
WHERE cust_name = 'Fun4All'
ORDER BY cust_name, cust_contact;

--UNION 사용 실습
/*1. 다음의 조건에 따라 고객의 고객번호와 고객이름을 추출하시오
조건: 주문을 안한 고객과 이메일주소가 없는 고객*/

/*2. 모든 주문에 대해 다음의 결과를 추출하시오
결과: 주문번호, 주문날자(YYYY-MM-DD), 고객번호, 고객이름, 주문제품번호, 주문제품이름, 공급업체번호, 공급업체명
조건: 3$대의 제품을 판매하는 공급업체의 모든 주문*/



select * from products;


/*3. 다음의 조건에 따라 공급업체의 공급업체번호, 공급업체명, 공급업체주소를 추출하시오
조건: 주문된 상품이 2건이상이거나 제조하는 제품이 2개이상인 공급업체*/


/*4. 다음의 조건에 따라 제품의 제품번호, 제품이름, 제품가격수준, 제품설명을 추출하시오
제품가격수준: 제품가격이 3$대이면 ‘적당’ 11%대이면 ‘비쌈’ 나머지는 ‘보통’
조건: 공급업체의 우편번호 앞의 2자리가 ‘44’, ‘45’, ’99’ 인 공급업체에서 만든 만들고
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
