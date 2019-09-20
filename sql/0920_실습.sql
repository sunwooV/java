/*
1. 다음의 조건에 따라 제품의 제품번호, 제품이름, 제품가격수준, 제품설명을 추출하시오
  - 제품가격수준: 제품가격이 3$대이면 ‘적당’ 11$대이면 ‘비쌈’나머지는 ‘보통’
  - 조건: 공급업체의 우편번호 앞의 2자리가 ‘44’,‘45’, ’99’ 인 공급업체에서 만들거나
              주문된 날자가 5월이 아닌 제품
*/
SELECT k.prod_id, k.prod_name, k.prod_desc
     , DECODE(TRUNC(k.prod_price), '3', '적당', '11', '비쌈', '보통') price_level
FROM vendors v
    , (SELECT p.prod_id, p.prod_name, p.prod_desc, p.vend_id, p.prod_price
      FROM products p, orderitems oi
      WHERE p.prod_id = oi.prod_id
      AND oi.order_num IN (SELECT order_num
                           FROM orders
                           WHERE TO_CHAR(order_date, 'MM') <> '05'
                           )
    )k
WHERE k.vend_id = v.vend_id
AND SUBSTR(v.vend_zip,0,2) IN ('44','45','99');


/*
2. 주문상품중에 제품의 제품가격보다 주문상품의 항목가격이 비싼 항목가격을 제품의제품가격으로 변경하시오
*/
UPDATE orderitems oi
SET oi.item_price = (SELECT prod_price
                     FROM products
                     WHERE oi.prod_id = prod_id
                     )
WHERE oi.prod_id IN (SELECT prod_id
                     FROM products
                     WHERE oi.prod_id = prod_id
                     AND oi.item_price > prod_price);



/*
3. 고객에서 고객번호, 고객이름, 고객주소, 주문한 총 금액, 주문한제품중에 가장비싼금액의 상품명을 추출하시오
  - 주문한 총 금액: 해당고객이 주문상품에서 주문한 상품의 항목가격의 SUM
  - 주문한제품중에 가장비싼 금액의 상품명: 주문상품에서 해당고객이 주문한 상품중 가장비싼 상품의 상품명
*/
SELECT c.cust_id, c.cust_name, c.cust_address, k.sum_price, k.max_price
FROM customers c
     , (SELECT o.cust_id, SUM(oi.item_price) sum_price, MAX(oi.item_price) max_price
        FROM orderitems oi, orders o
        WHERE o.cust_id IN (SELECT cust_id
                            FROM customers
                            WHERE cust_id = o.cust_id
                            )
        GROUP BY o.cust_id)k
WHERE k.cust_id = c.cust_id
ORDER BY c.cust_id;


/*
4. 제품이름과 제품설명에 ‘king’이라는 단어가 들어가고 제품가격의 소수점 1자리가 9가 아닌 제품번호를 추출하시오
*/
SELECT prod_id
FROM products
WHERE prod_name LIKE ('%King%')
AND prod_desc LIKE ('%king%')
AND SUBSTR(TRUNC(prod_price, 1),-1) <> '9';


/*
5. 주문에서 주문번호와 주문가능날자(YYYY-MM-DD)를 추출하시오
  주문가능날자: 일자의 10자리가 0이면 1일,1이면 10일, 20이면 20일,30이면 30일
*/
SELECT order_num
    , (CASE WHEN SUBSTR(TO_CHAR(order_date), -2, 1) = '0'
                 THEN TO_CHAR(order_date, 'yyyy-mm-')||'01'
            WHEN SUBSTR(TO_CHAR(order_date), -2, 1) = '1'
                 THEN TO_CHAR(order_date, 'yyyy-mm-')||'10'
            WHEN SUBSTR(TO_CHAR(order_date), -2, 1) = '2'
                 THEN TO_CHAR(order_date, 'yyyy-mm-')||'20'
            WHEN SUBSTR(TO_CHAR(order_date), -2, 1) = '3'
                 THEN TO_CHAR(order_date, 'yyyy-mm-')||'30'
        END) possible_date
FROM orders;

--강사님
   SELECT order_num,
          TO_CHAR(
              CASE WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '0' THEN
                             TO_DATE(TO_CHAR(order_date,'YYYYMM')||'01')
                   WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '1' THEN
                             TO_DATE(TO_CHAR(order_date,'YYYYMM')||'10')
                   WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '2' THEN
                             TO_DATE(TO_CHAR(order_date,'YYYYMM')||'20')
                   WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '3' THEN
                             TO_DATE(TO_CHAR(order_date,'YYYYMM')||'30')
              END, 'YYYY-MM-DD') orderCanDt     
   FROM   orders
   ;
