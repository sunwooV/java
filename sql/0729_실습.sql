/*1. 고객에서 고객번호, 고객이름, 고객주소, 주문한 총 금액,  주문한 제품중에 가장비싼금액의 상품명을 추출하시오
    - 주문한 총 금액: 주문상품에서 주문한 상품의 항목가격의 SUM
    - 주문한 제품중에 가장비싼 금액의 상품명: 주문상품에서 해당고객이 주문한 상품중 가장비싼 상품의 상품명*/
SELECT TRIM(c.cust_id)
    , TRIM(c.cust_name)
    , TRIM(c.cust_address)
    ,(SELECT SUM(item_price)
        FROM orderitems oi, orders o
        WHERE oi.order_num = o.order_num
        AND o.cust_id = c.cust_id
        ) sum_price
    , (SELECT TRIM(MAX(prod_name))
        FROM products p, orderitems oi, orders o
        WHERE p.prod_id = oi.prod_id
        AND oi.order_num = o.order_num
        AND o.cust_id = c.cust_id
       ) max_price_name
FROM customers c;

SELECT cust_id, cust_name, cust_address,
       (SELECT SUM(item_price)
        FROM   orderitems a, orders b
        WHERE  a.order_num = b.order_num
        AND    b.cust_id = k.cust_id
       ) order_tot_price,
       (SELECT MAX(c.prod_name)
        FROM   orderitems a, orders b, products c
        WHERE  a.order_num = b.order_num
        AND    a.prod_id = c.prod_id
        AND    b.cust_id = k.cust_id
        AND    a.item_price = (
                                SELECT MAX(item_price)
                                FROM   orderitems aa, orders bb
                                WHERE  aa.order_num = bb.order_num
                                AND    bb.cust_id = k.cust_id
                              )
       ) max_prod_name
FROM   customers k
;

/*2. 상품을주문한 고객중에 고객국가, 고객주별 주문수를 추출하시오
    - 결과: 고객국가,고객주, 주문수*/
SELECT TRIM(cust_country) cust_country
    , TRIM(cust_state) cust_state
    , (
        SELECT COUNT(*)
        FROM orders o
        WHERE c.cust_id = o.cust_id
        ) order_num
FROM customers c;


/*3. 주문상품에서 고객주별 가장비싼 제품을 산 고객정보를 추출하시오
   - 결과: 고객주, 비싼 제품의 가격*/
SELECT c.cust_state
    , MAX(oi.item_price)
FROM orderitems oi, orders o, customers c
WHERE oi.order_num = o.order_num
AND o.cust_id = c.cust_id
GROUP BY c.cust_state;


