/*1.고객에서 고객번호, 고객이름, 고객주소, 주문한 총 금액,  주문한 제품 중에 가장 최근에 주문한 상품명을 추출하시오
    - 주문한 총 금액: 해당고객이 주문상품에서 주문한 상품의 항목가격의 SUM
    - 주문한 제품 중에 가장 최근에 주문한 상품명:주문상품에서 해당고객이 주문한 상품중 가장 최근에 주문한 상품의 상품명 
    (상품이 여러개일 경우 가나다순으로 가장 큰 상품명을 출력)*/
SELECT c.cust_id
    , TRIM(c.cust_name) cust_name
    , TRIM(c.cust_address) cust_address
    , (SELECT SUM(oi.item_price) sum_price
       FROM orderitems oi, orders o
       WHERE oi.order_num = o.order_num
       AND o.cust_id = c.cust_id
     ) price_sum
     , (SELECT MAX(p.prod_name) prod_name
        FROM products p, orderitems oi, orders o
        WHERE p.prod_id = oi.prod_id
        AND oi.order_num = o.order_num
        AND o.order_date = (SELECT MAX(order_date)
                            FROM orders
                            WHERE cust_id = c.cust_id
                            )
        ) prod_name
FROM customers c;



--아래는 연습 

SELECT c.cust_id, SUM(oi.item_price) sum_price
FROM orderitems oi, orders o, customers c
WHERE oi.order_num = o.order_num
AND o.cust_id = c.cust_id
GROUP BY c.cust_id
ORDER BY c.cust_id;

SELECT c.cust_id, SUM(oi.item_price) sum_price
       FROM orderitems oi, orders o, customers c
       WHERE oi.order_num = o.order_num
       AND o.cust_id = c.cust_id
       GROUP BY c.cust_id;

SELECT c.cust_id, TRIM(p.prod_name), MAX(o.order_date) order_date
FROM products p, orderitems oi, orders o, customers c
WHERE p.prod_id = oi.prod_id
AND oi.order_num = o.order_num
AND o.cust_id = c.cust_id
AND EXISTS(SELECT MAX(oo.order_date)
          FROM orders oo
          WHERE o.cust_id = oo.cust_id
          )
GROUP BY c.cust_id, TRIM(p.prod_name)
ORDER BY c.cust_id;

SELECT MAX(o.order_date) order_date, c.cust_id, p.prod_name, ROW_NUMBER() OVER(PARTITION BY c.cust_id ORDER BY p.prod_name ASC) rn
FROM products p, orderitems oi, orders o, customers c
WHERE p.prod_id = oi.prod_id
AND oi.order_num = o.order_num
AND o.cust_id = c.cust_id
GROUP BY c.cust_id, p.prod_name
ORDER BY c.cust_id, p.prod_name ASC;


/*2. 상품을주문한 고객 중에 고객국가,고객담당자 별 주문수를 추출하시오
    - 결과: 고객국가,고객담당자, 주문수
    - 조건: 주문수가 2건이상인 경우만 조회 */
SELECT c.cust_country, c.cust_contact, COUNT(o.order_num) order_sum
FROM customers c, orders o
WHERE c.cust_id = o.cust_id
GROUP BY c.cust_country, c.cust_contact
HAVING count(o.order_num) >= 2;
   

/*3. 다음의 조건에 따라 제품의 제품번호, 제품이름, 제품가격수준, 제품설명을 추출하시오
   - 제품가격수준: 제품가격이 3$대이면 ‘적당’ 11$대이면 ‘비쌈’나머지는 ‘보통’
   - 조건: 공급업체의 우편번호 앞의 2자리가 ‘44’,‘45’, ’99’ 인공급업체에서 만들거나 주문된 날자가 5월이 아닌 제품 */
SELECT k.prod_id
    , TRIM(k.prod_name) prod_name
    , DECODE(TRUNC(k.item_price), 3, '적당', 11, '비쌈', '보통') price_level
    , TRIM(k.prod_desc) prod_desc
FROM (SELECT p.prod_id, p.prod_name, oi.item_price, p.prod_desc
      FROM vendors v, products p, orderitems oi, orders o
      WHERE v.vend_id = p.vend_id
      AND p.prod_id = oi.prod_id
      AND oi.order_num = o.order_num
      AND (SUBSTR(v.vend_zip, 1, 2) IN ('44', '45', '99') OR TO_CHAR(o.order_date, 'mm') != '05')
      ) k;
      
SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , DECODE(TRUNC(p.prod_price), 3, '적당', 11, '비쌈', '보통') price_level
    , TRIM(p.prod_desc) prod_desc
FROM products p
WHERE p.vend_id IN (SELECT vend_id
                  FROM vendors v
                  WHERE v.vend_id = p.vend_id
                  AND SUBSTR(v.vend_zip, 1, 2) IN ('44', '45', '99')
                  )
OR p.prod_id IN (SELECT oi.prod_id
                FROM orderitems oi, orders o
                WHERE oi.order_num = o.order_num
                AND TO_CHAR(o.order_date, 'mm') != '05'
                );




/*4. 주문상품중에 제품의 제품가격보다 주문상품의 항목가격이 비싼 항목가격을 제품의제품가격으로 변경하시오 */
UPDATE orderitems oi
SET item_price = (SELECT prod_price
                  FROM products
                  WHERE prod_id = oi.prod_id
                  )
WHERE prod_id IN (SELECT p.prod_id
                  FROM products p, orderitems oii
                  WHERE p.prod_id = oii.prod_id
                  AND p.prod_price < oii.item_price
                  );



