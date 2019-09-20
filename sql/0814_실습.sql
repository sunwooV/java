/*1. 상품주문에서 주문번호, 제품번호, 제품제조국가위치를 추출하시오
   - 제품제조국가위치: 제품번호의 마지막 2자리가 01이면 ‘국내’, 아니면 ‘해외’
   - 조건: 총가격(항목수량*항목가격)이 500$이상인 주문*/
   
SELECT order_num, prod_id, DECODE(SUBSTR(TRIM(prod_id), -2), '01', '국내', '해외') prod_location
FROM orderitems oi
WHERE order_num IN (SELECT order_num
                    FROM orderitems
                    GROUP BY order_num
                    HAVING SUM(quantity * item_price) >= 500
                    );

/*2. 주문과 고객정보를 조인하여 다음의 결과를 추출하시오
   - 결과: 주문번호, 주문날자, 제품번호, 고객번호, 고객이름, 고객주소, 고객시, 고객메일주소
   - 조건: 전체주문에서 가장 많은 상품종류를 판매한 공급업체에서 만든 제품만조회*/

SELECT o.order_num, TO_CHAR(o.order_date, 'yyyy-mm-dd') order_date, c.cust_id, k.prod_id, c.cust_name, c.cust_address, c.cust_city, c.cust_email
FROM orders o, customers c
    , (SELECT oi.prod_id, p.vend_id, oi.order_num
       FROM orderitems oi, products p
       WHERE oi.prod_id = p.prod_id
    ) k
WHERE o.cust_id = c.cust_id
AND k.order_num = o.order_num
AND k.vend_id = (SELECT v.vend_id
                FROM vendors v, products pp
                WHERE v.vend_id = pp.vend_id
                GROUP BY v.vend_id
                HAVING COUNT(pp.prod_id) = (SELECT MAX(COUNT(pp.prod_id))
                                             FROM products pp
                                             GROUP BY pp.vend_id
                                             )
            )
ORDER BY c.cust_id, prod_id;

SELECT o.order_num, TO_CHAR(o.order_date, 'yyyy-mm-dd') order_date, c.cust_id, k.prod_id, c.cust_name, c.cust_address, c.cust_city, c.cust_email
FROM orders o, customers c
    , (SELECT oi.prod_id, oi.order_num
       FROM orderitems oi, products p
       WHERE oi.prod_id = p.prod_id
       AND p.vend_id = (SELECT v.vend_id
                        FROM vendors v, products pp
                        WHERE v.vend_id = pp.vend_id
                        GROUP BY v.vend_id
                        HAVING COUNT(pp.prod_id) = (SELECT MAX(COUNT(pp.prod_id))
                                                     FROM products pp
                                                     GROUP BY pp.vend_id
                                                     )
                    )
    ) k
WHERE o.cust_id = c.cust_id
AND k.order_num = o.order_num
ORDER BY c.cust_id, prod_id;

--위에는 주문된 상품을 count하지 않았음. -> 전체 상품 count
SELECT o.order_num, TO_CHAR(o.order_date, 'yyyy-mm-dd') order_date, c.cust_id, k.prod_id, c.cust_name, c.cust_address, c.cust_city, c.cust_email
FROM orders o, customers c
    , (SELECT oi.prod_id, oi.order_num
       FROM orderitems oi, products p
       WHERE oi.prod_id = p.prod_id
       AND p.vend_id = (SELECT pp.vend_id
                        FROM products pp, orderitems oii
                        WHERE pp.prod_id = oii.prod_id
                        GROUP BY pp.vend_id
                        HAVING COUNT(oii.prod_id) = (SELECT MAX(COUNT(oi.prod_id))
                                                     FROM orderitems oi, products p
                                                     WHERE oi.prod_id = p.prod_id
                                                     GROUP BY p.vend_id
                                                     )
                    )
    ) k
WHERE o.cust_id = c.cust_id
AND k.order_num = o.order_num
ORDER BY c.cust_id, prod_id;



--강사님
SELECT a.order_num, a.order_date, b.prod_id, a.cust_id, c.cust_name, c.cust_address, c.cust_city, c.cust_email
FROM   orders a, orderitems b, customers c
WHERE  a.order_num = b.order_num
AND    a.cust_id = c.cust_id
AND    b.prod_id IN (
         SELECT prod_id
         FROM   products
         WHERE  vend_id IN (
                SELECT vend_id
                FROM
                (
                    SELECT b.vend_id, COUNT(a.prod_id) AS prod_cnt
                    FROM   orderitems a, products b
                    WHERE  a.prod_id = b.prod_id
                    GROUP BY b.vend_id
                ) m
                WHERE prod_cnt = (
                    SELECT MAX(COUNT(a.prod_id)) AS prod_cnt
                    FROM   orderitems a, products b
                    WHERE  a.prod_id = b.prod_id
                    GROUP BY b.vend_id
                )         
              )
       )
;    

--아래는 연습
SELECT o.order_num, TO_CHAR(o.order_date, 'yyyyMMdd') order_date, c.cust_id, c.cust_name, c.cust_address, c.cust_city, c.cust_email
FROM orders o, customers c
    , (SELECT SUM(oi.prod_id), p.vend_id, oi.order_num
       FROM orderitems oi, products p
       WHERE oi.prod_id = p.prod_id 
       GROUP BY p.vend_id, oi.order_num
        ) k
WHERE o.cust_id = c.cust_id
AND o.order_num = k.order_num;

SELECT o.order_num, TO_CHAR(o.order_date, 'yyyy-mm-dd') order_date, c.cust_id, c.cust_name, c.cust_address, c.cust_city, c.cust_email
    , (SELECT oi.prod_id, p.vend_id
       FROM orderitems oi, products p
       WHERE oi.order_num = o.order_num
       AND oi.prod_id = p.prod_id
       AND p.vend_id IN (SELECT v.vend_id
                         FROM vendors v, products pp
                         WHERE pp.prod_id = p.prod_id
                         GROUP BY v.vend_id
                         HAVING COUNT(pp.prod_id) = (SELECT MAX(COUNT(pp.prod_id))
                                                     FROM products p
                                                     GROUP BY p.vend_id
                                                     )
                        )
    ) k
FROM orders o, customers c
WHERE o.cust_id = c.cust_id;



SELECT v.vend_id
FROM vendors v, products pp
WHERE v.vend_id = pp.vend_id
GROUP BY v.vend_id
HAVING COUNT(pp.prod_id) = (SELECT MAX(COUNT(pp.prod_id))
                             FROM products pp
                             GROUP BY pp.vend_id
                             );
                             
SELECT v.vend_id
FROM vendors v, products pp, orderitems oii
WHERE v.vend_id = pp.vend_id
AND pp.prod_id = oii.prod_id
GROUP BY v.vend_id
HAVING COUNT(oii.prod_id) = (SELECT MAX(COUNT(oi.prod_id))
                             FROM orderitems oi, products p
                             WHERE oi.prod_id = p.prod_id
                             GROUP BY p.vend_id
                             );
                             
SELECT v.vend_id
FROM vendors v, products pp, orderitems oii
WHERE v.vend_id = pp.vend_id
AND pp.prod_id = oii.prod_id

GROUP BY v.vend_id
HAVING COUNT(oii.prod_id) = (SELECT MAX(COUNT(oi.prod_id))
                             FROM orderitems oi, products p
                             WHERE oi.prod_id = p.prod_id
                             GROUP BY p.vend_id
                             );

/*3. 주문 중에 고객이 사는 도시가 ‘Detroit’이고 주문한 총 제품의 수가 500을 넘는 고객을 추출하시오
   - 결과: 고객번호, 고객이름, 고객도시*/
SELECT c.cust_id, c.cust_name, c.cust_city
FROM customers c, orders o
WHERE c.cust_id = o.cust_id
AND c.cust_city = 'Detroit'
AND EXISTS (SELECT *
            FROM orderitems oi
            WHERE oi.order_num = o.order_num
            GROUP BY c.cust_id
            HAVING SUM(quantity) > 500
            );


/*4. 모든 주문에 대해 다음의 결과를 추출하시오
   - 결과: 주문번호, 주문날자(YYYY-MM-DD), 고객번호, 고객이름, 주문제품번호, 주문제품이름, 공급업체번호, 공급업체명
   - 조건: 3$대의 제품을 판매하는 공급업체의 모든 주문*/

SELECT o.order_num
    , TO_CHAR(o.order_date, 'yyyy-mm-dd') order_num
    , c.cust_id
    , TRIM(c.cust_name) cust_name
    , k.prod_id
    , TRIM(k.prod_name) prod_name
    , k.vend_id
    , TRIM(k.vend_name) vend_name
FROM customers c, orders o
    , (SELECT oi.order_num, p.prod_id, p.prod_name, v.vend_id, v.vend_name, p.prod_price
       FROM orderitems oi, products p, vendors v
       WHERE oi.prod_id = p.prod_id
       AND p.vend_id = v.vend_id
       )k
WHERE c.cust_id = o.cust_id
AND k.order_num = o.order_num
AND TRUNC(k.prod_price) = 3;


--아래 조인이 5개
SELECT o.order_num
    , TO_CHAR(o.order_date, 'yyyy-mm-dd') order_num
    , c.cust_id
    , TRIM(c.cust_name) cust_name
    , oi.prod_id
    , TRIM(p.prod_name) prod_name
    , v.vend_id
    , TRIM(v.vend_name) vend_name
FROM customers c, orders o, orderitems oi, products p, vendors v
WHERE c.cust_id = o.cust_id
AND o.order_num = oi.order_num
AND oi.prod_id = p.prod_id
AND p.vend_id = v.vend_id
AND TRUNC(p.prod_price) = 3;

/*5. 주문날자가 2004년도에 발생한 주문 중에 주문상품의 항목가격이 9$를 넘는 항목가격에 대해 10%씩을 DC하여 변경하시오*/
UPDATE orderitems
SET item_price = item_price * 0.9
WHERE item_price > 9
AND order_num IN (SELECT order_num
                  FROM orders
                  WHERE TO_CHAR(order_date, 'yyyy') = '2004'
                  );
                  
