/*AVG():NULL 값이 있는 경우 계산에서 제외된다*/
--Products 테이블에 있는 모든 제품의 가격평균
SELECT AVG(prod_price) avg_price
FROM products;

--DLL01의 공급업체에 대한 제품 평균
SELECT AVG(prod_price) avg_price
FROM products
WHERE vend_id = 'DLL01';

/*COUNT()
- COUNT(*): 테이블의 모든 행의 개수, NULL값 포함
- COUNT(컬럼): 컬럼의 NULL값을 제외한 행의 개수*/
--Customers 테이블에 있는 모든 고객의 수
SELECT COUNT(*) num_cust
FROM customers;

--Cust_email 열에 값이 있는 고객의 수만 계산
SELECT COUNT(cust_email) as num_cust
FROM customers;

/*MAX(): 지정한 열에서 가장 큰 값을 반환
- TEXT도 사용가능, NULL값은 무시된다*/
--Products 테이블에서 가격이 가장 비싼 제품의 가격
SELECT MAX(prod_price) max_price
FROM products;

/*MIN(): 지정한 열에서 가장 낮은 값을 반환
- TEXT도 사용가능, NULL값은 무시된다*/
--Products 테이블에서 가격이 가장 저렴한 제품의 가격
SELECT MIN(prod_price) min_price
FROM products;

/*SUM(): 지정한 열에서 모든 값을 더한 합계
- NULL값은 무시된다*/
--모든 주문한 물품의 수량의 합계
SELECT SUM(quantity) items_ordered
FROM orderitems;

--각 주문에 대한 총 금액을 반환
SELECT SUM(item_price * quantity) total_price
FROM orderitems
WHERE order_num = 20005;

/*고유값의 집계: SUM, MAX, MIN에서 사용*/
--가격이 같은 물품이 있는 경우 한번만 계산에 포함
SELECT AVG(DISTINCT prod_price) avg_price
FROM products
WHERE vend_id = 'DLL01';

/*집계함수 결합*/
SELECT COUNT(*) num_items
    , MIN(prod_price) price_min
    , MAX(prod_price) price_max
    , ROUND(AVG(prod_price), 2) price_avg
FROM products;

/*1. 고객주문에서 주문날자가 가장 최근인 날자와 가장 최초인 날자를 추출하시오*/
SELECT TO_CHAR(MAX(order_date), 'yyyy-mm-dd') date_current
    , TO_CHAR(MIN(order_date), 'yyyy-mm-dd') date_oldest
FROM orders;

/*2. 주문상품에서 제품번호가 BN으로 시작하는 각 주문의 총금액(항목수량*항목가격)이 가장큰 금액을 추출하시오*/
SELECT MAX(quantity + item_price) high_price
FROM orderitems
WHERE UPPER(prod_id) LIKE 'BN%';

/*3. 주문상품에서 제품번호가 BR01와 BR03인 제품의 주문된 항목수량의 평균을 추출하시오*/
SELECT ROUND(AVG(quantity), 2) quantity
FROM orderitems
WHERE prod_id IN ('BR01', 'BR03');

/*그룹만들기
- 중첩된 그룹이 있을 경우 데이터는 마지막 지정된 그룹을 기준으로 요약된다
- GROUP BY로 그룹화 되면 해당 컬럼은 SELECT절에 나타나야 한다
- NULL값도 그룹으로 분류된다*/
--공급업체별 상품 수
SELECT vend_id, COUNT(*) num_prods
FROM products
GROUP BY vend_id;

/*필터링 그룹: 그룹을 만든다음 그 그룹결과에서 그룹함수를 통해 필터링을 수행
- WHERE: 그룹화 하기 전에 필터링 수행
- HAVING: 그룹화 한 후에 필터링 수행*/
SELECT cust_id, COUNT(*) orders
FROM orders
GROUP BY cust_id
HAVING COUNT(*) >= 2;

--가격이 4 이상인 제품을 두 개 이상 가진 공급업체를 추출
SELECT vend_id, COUNT(*) num_prods
FROM products
WHERE prod_price >= 4
GROUP BY vend_id
HAVING COUNT(*) >= 2;

--ORDER BY 와 함께 사용
SELECT order_num, COUNT(*) items
FROM orderitems
GROUP BY order_num
HAVING COUNT(*) >=3
ORDER BY items, order_num;

--주문된 제품의 종류수
SELECT COUNT(DISTINCT prod_id)
FROM orderitems;

--주문된 제품별 총 주문수량
SELECT prod_id, SUM(quantity)
FROM orderitems
GROUP BY prod_id;

--주문된 제품의 총 주문수량이 100개가 넘은 제품의 제품번호
SELECT prod_id, SUM(quantity)
FROM orderitems
GROUP BY prod_id
HAVING SUM(quantity) > 100;

--주문번호가 20005, 20007인 주문된 제품중에서 총 주문수량이 100개가 넘은 제품의 제품번호
SELECT prod_id, SUM(quantity)
FROM orderitems
WHERE order_num IN (20005, 20007)
GROUP BY prod_id
HAVING SUM(quantity) > 100;

/*1. 주문제품의 주문번호와 주문번호별 주문제품의 수를 추출하시오
결과: 주문번호, 주문제품의수*/
SELECT order_num, COUNT(prod_id) num_prod
FROM orderitems
GROUP BY order_num;

/*2. 주문에서 주문날자별 주문한 고객의 수를 추출하시오
결과: 주문날자(YYYY-MM-DD), 고객의 수*/
SELECT TO_CHAR(order_date, 'yyyy-mm-dd') order_date
    , COUNT(DISTINCT cust_id) --똑같은 고객이 여러번 주문할 수 있기 때문에 DISTINCT
FROM orders
group by TO_CHAR(order_date, 'yyyy-mm-dd');

/*3. 제품에서 공급업체번호별 제품의 수를 추출하시오
결과: 공급업체번호, 제품의수*/
SELECT vend_id
    , COUNT(prod_id) prod_num
FROM products
GROUP BY vend_id;

/*4. 고객 중에 우편번호가 4로 시작되는 고객의 수를 추출하시오
결과: 고객의 수*/
SELECT COUNT(cust_id) cust_num
FROM customers
WHERE cust_zip LIKE '4%';

/*5. 고객 중에 이메일 주소가 없는 고객의 수를 추출하시오
결과: 고객의 수*/
SELECT COUNT(cust_id) cust_num
FROM customers
WHERE cust_email IS NULL;

/*6. 제품중에 공급업체별 제품가격의 평균을 추출하시오
결과: 공급업체번호, 평균가격*/
SELECT vend_id, ROUND(AVG(prod_price), 2) avg_prod_price
FROM products
GROUP BY vend_id;

/*7. 고객에서 주별 고객의 수를 추출하시오
결과: 고객주, 고객의수*/
SELECT cust_state, COUNT(cust_id)
FROM customers
GROUP BY cust_state;

/*8. 제품에서 공급업체가 BRS01, DLL01인 제품중에 가장비싼 제품의 가격이 5$이상인 제품의 공급업체번호를 추출하시오
결과: 공급업체번호*/
SELECT vend_id
FROM products
WHERE vend_id IN ('BRS01', 'DLL01')
group by vend_id
HAVING MAX(prod_price) >= 5; 

/*9. 주문에서 1월중 주문한 주문중에 가장늦게 주문한 고객번호를 추출
결과: 주문일자(YYYY-MM-DD), 고객번호*/
SELECT cust_id
    , TO_CHAR(MAX(order_date), 'yyyy-mm-dd') lastest_order_date
FROM orders
WHERE TO_CHAR(order_date, 'mm') = '01' 
GROUP BY cust_id;

/*IN절안에 하위쿼리 사용하기
- 하위쿼리를 먼저 실행하여 결과를 추출하고 그 결과를 바탕으로 메인 쿼리를 실행한다*/
--상품번호가 RGAN01인 상품을 주문한 고객id추출
SELECT cust_id
FROM orders
WHERE order_num IN (SELECT order_num
                    FROM orderitems
                    WHERE prod_id = 'RGAN01');
                    
--하위쿼리 안에 하위쿼리 사용
--상품번호가 RGAN01인 상품을 주문한 고객의 이름과 연락처 추출
SELECT cust_name, cust_contact
FROM customers
WHERE cust_id IN (SELECT cust_id
                  FROM orders
                  WHERE order_num IN (SELECT order_num
                                      FROM orderitems
                                      WHERE prod_id = 'RGAN01'));
                                      
/*하위쿼리를 계산필드로 사용
- 중복된 컬럼명을 구분하기 위해 테이블명으로 구분*/
SELECT cust_name, cust_state,
       (SELECT COUNT(*)
        FROM orders
        WHERE orders.cust_id = customers.cust_id) orders
FROM customers
ORDER BY cust_name;

--FROM절에 SQL 사용하기
SELECT order_num, order_item, type
FROM(
    SELECT order_num, order_item, SUBSTR(prod_id, 1, 2) type
    FROM orderitems
)A
WHERE type = 'BR';

--EXISTS, NOT EXISTS
SELECT cust_id, cust_name, cust_address
FROM customers a
WHERE EXISTS( --EXISTS안에 SELECT에는 무엇이 와도 상관 없다.
        SELECT *
        FROM orders
        WHERE cust_id = a.cust_id
        );
        
/*1. 제품의 고유ID와 제품이름, 제품가격, 제품설명을 추출하시오
조건: 공급업체의 국가가 ‘USA’인 공급업체의 제품*/
SELECT prod_id
    , trim(prod_name) prod_name
    , trim(prod_price) prod_price
    , prod_desc
FROM products
WHERE vend_id IN (SELECT vend_id
                    FROM vendors
                    WHERE vend_country = 'USA'
                    );

/*2. 고객의 고객번호, 고객이름, 고객주소, 고객메일주소를 추출하시오
조건: 고객의 메일주소가 있고 주문을 한 건이라도 한 고객*/
SELECT cust_id, trim(cust_name), trim(cust_address), trim(cust_email)
FROM customers a
WHERE cust_email IS NOT NULL
AND EXISTS (
             SELECT cust_id
             FROM orders
             WHERE a.cust_id = cust_id
            );

/*3. 공급업체의 공급업체번호, 공급업체이름, 공급업체주소, 공급업체별 제품 수를 추출하시오
조건: 공급업체별 제품수가 2개 이상인 공급업체*/
SELECT vend_id, trim(vend_name), trim(vend_address),
    (SELECT COUNT(*)
    FROM products
    WHERE a.vend_id = vend_id
    ) prod_num
FROM vendors a
WHERE EXISTS(
        SELECT *
        FROM products
        WHERE a.vend_id = vend_id
        GROUP BY vend_id
        HAVING COUNT(prod_id) >= 2
        );

/*4. 주문제품 중에 제품별로 항목가격이 가장 낮은 제품번호를 추출하시오
결과: 제품번호, 항목가격(제품별 가장 낮은 항목가격)*/
SELECT prod_id, MIN(item_price)
FROM orderitems
GROUP BY prod_id;


/*조인: 테이블 간에 동일한 컬럼들을 연결시켜서 통합된 정보를 추출하는 방법
- 조인을 많이 할 수록 성능을 떨어진다*/
--상품에 대한 공급업체 정보를 함께 추출
SELECT vend_name, prod_name, prod_price
FROM vendors, products
WHERE vendors.vend_id = products.vend_id;

--조인 시에 조건절에서 테이블간의 관계를 설정하지 않으면 cartesian product
SELECT vend_name, prod_name, prod_price
FROM vendors, products;

/*ANSI SQL - 내부 조인*/
SELECT vend_name, prod_name, prod_price
FROM vendors INNER JOIN products
    ON vendors.vend_id = products.vend_id;
    
/*여러 테이블 조인*/
--상품에 대한 공급업체 정보와 주문수량까지 추출
SELECT vend_name, prod_name, prod_price, quantity
FROM orderitems, vendors, products
WHERE products.vend_id = vendors.vend_id
AND orderitems.prod_id = products.prod_id
AND order_num = 20007;

--상품번호가 RGAN01인 주문자이름과 연락처 추출
SELECT cust_name, cust_contact
FROM customers, orders, orderitems
WHERE customers.cust_id = orders.cust_id
AND orderitems.order_num = orders.order_num
AND prod_id = 'RGAN01';

/*테이블 별칭사용*/
--상품번호가 RGAN01인 주문자이름과 연락처 추출
SELECT cust_name, cust_contact
FROM customers c, orders o, orderitems oi
WHERE c.cust_id = o.cust_id
AND oi.order_num = o.order_num
AND prod_id = 'RGAN01';

/*동일 테이블 조인*/
--Jim Jones라는 사람이 일하는 회사의 모든 고객 담당자의 메일주소 추출
SELECT cust_id, cust_name, cust_contact
FROM customers
WHERE cust_name = (SELECT cust_name
                    FROM customers
                    WHERE cust_contact = 'Jim Jones');
                    
--같은 쿼리를 조인으로 추출
SELECT c1.cust_id, c1.cust_name, c1.cust_contact
FROM customers c1, customers c2
WHERE c1.cust_name = c2.cust_name
AND c2.cust_contact = 'Jim Jones';

/*조인 시 전체 컬럼 조회 처리*/
SELECT c.*, o.order_num, o.order_date, oi.prod_id
FROM customers c, orders o, orderitems oi
WHERE c.cust_id = o.cust_id
AND oi.order_num = o.order_num
AND prod_id = 'RGAN01';

/*1. 주문과 고객정보를 조인하여 다음의 결과를 추출하시오
결과: 주문번호, 주문날자, 고객번호, 고객이름, 고객주소, 고객시, 고객메일주소
조건: 고객별로 마지막 주문날자의 주문*/
SELECT o.order_num
    , TO_CHAR(o.order_date, 'yyyy-mm-dd') lastest_order_date
    , c.cust_id, trim(c.cust_name)
    , trim(c.cust_address)
    , trim(c.cust_city)
    , trim(c.cust_email)
FROM orders o, customers c
WHERE o.cust_id = c.cust_id
AND o.order_date IN (SELECT MAX(order_date)
                    FROM orders o2
                    WHERE cust_id = c.cust_id
                    GROUP BY cust_id
                    );
                    
SELECT a.order_num, a.order_date, a.cust_id, b.cust_name, b.cust_city, b.cust_email
FROM   orders a, customers b
WHERE  a.cust_id = b.cust_id
AND    a.order_date = (
                      SELECT MAX(order_date)
                      FROM   orders
                      WHERE  cust_id = b.cust_id
                     );

                    
SELECT MAX(o.order_date)
FROM   orders o, customers b
WHERE  o.cust_id = b.cust_id;

select * from orders;
SELECT TO_CHAR(MAX(order_date), 'yyyy-mm-dd')
FROM orders o2, customers c
WHERE o2.cust_id = c.cust_id
GROUP BY o2.cust_id;


