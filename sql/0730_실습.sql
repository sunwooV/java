/*1. 주문번호가 20005, 20007인 주문된 제품중에 총 주문수량이 100개가 넘은 제품의 제품이름*/
SELECT TRIM(p.prod_name) prod_name, SUM(oi.quantity)
FROM products p, orderitems oi
WHERE p.prod_id = oi.prod_id
AND oi.quantity >= 100
AND oi.order_num IN ('20005', '20007') 
group by TRIM(p.prod_name);

--밑에가 답
SELECT TRIM(p.prod_name) prod_name
FROM products p
WHERE p.prod_id IN (SELECT prod_id
                    FROM orderitems
                    WHERE order_num IN ('20005', '20007')
                    GROUP BY prod_id
                    HAVING SUM(quantity) > 100
                    );

/*2. DDL01나 BRS01 제조업체에서 만든 제품 중 가격이 10불이상 인 제품의 총 판매수량
   결과: 제품명, 총판매수량*/
SELECT TRIM(p.prod_name) prod_name
    , SUM(oi.quantity) quantity_sum
FROM products p, orderitems oi
WHERE p.prod_id = oi.prod_id
AND p.vend_id IN('DDL01', 'BRS01') --(p.vend_id = 'DDL01' OR p.vend_id = 'BRS01')
AND oi.item_price >= 10 
group by p.prod_name;

SELECT TRIM(p.prod_name) prod_name
      , (SELECT SUM(quantity)
         FROM   orderitems
         WHERE  prod_id = p.prod_id
         AND    item_price >= 10       
       ) sum_quantity
FROM   products p
WHERE  vend_id IN ('DDL01', 'BRS01')
AND    EXISTS (SELECT *  --Join 대신 Exists로 풀 수 있으면 Exist로 풀어라./ Exists 안에 SELECT 는 의미 없으므로 *
              FROM   orderitems
              WHERE  prod_id = p.prod_id
              AND    item_price >= 10
             );


/*3. 다음의 조건에 따라 고객의 고객번호, 고객이름, 고객주소를 추출하시오
    조건: 주문한 총 상품의 개수가 2개이상인 고객*/
SELECT c.cust_id, TRIM(c.cust_name) cust_name, TRIM(c.cust_address) cust_address
FROM customers c
WHERE 2 <= (SELECT COUNT(oi.prod_id)
        FROM orders o, orderitems oi
        WHERE c.cust_id = o.cust_id
        AND o.order_num = oi.order_num
        );
        
SELECT cust_id
    , TRIM(cust_name) cust_name
    , TRIM(cust_address) cust_address
FROM   customers c
WHERE  EXISTS(
         SELECT *
         FROM   orderitems oi, orders o
         WHERE  oi.order_num = o.order_num
         AND    o.cust_id = c.cust_id
         GROUP BY c.cust_id
         HAVING COUNT(*) >= 2
       );

/*4. 현재 주문된 상품을 공급한 공급업체의 국가별 공급업체수
    결과: 국가,총공급업체수, 주문된 상품을 공급한 공급업체 수*/
SELECT TRIM(v.vend_country) vend_country
    , (SELECT COUNT(vend_id)
        FROM vendors
        WHERE vend_country = v.vend_country
        ) vend_sum
    , (SELECT COUNT(DISTINCT vv.vend_id)
        FROM vendors vv, products p, orderitems oi, orders o
        WHERE vv.vend_id = p.vend_id
        AND p.prod_id = oi.prod_id
        AND oi.order_num = o.order_num
        AND vv.vend_country = v.vend_country
        ) order_vend_sum
FROM vendors v
group by v.vend_country;

SELECT vend_country, COUNT(vend_id)
FROM vendors
GROUP BY vend_country;

SELECT v.vend_country, COUNT(DISTINCT v.vend_id)
FROM vendors v, products p, orderitems oi, orders o
WHERE v.vend_id = p.vend_id
AND p.prod_id = oi.prod_id
AND oi.order_num = o.order_num
GROUP BY v.vend_country;

SELECT DISTINCT TRIM(v.vend_country)
FROM orderitems oi, products p, vendors v
WHERE oi.prod_id = p.prod_id
AND p.vend_id = v.vend_id;
    
    
SELECT
    TRIM(vend_country), 
    (SELECT COUNT(v.vend_id)
    FROM vendors v
    WHERE v.vend_country = t.vend_country) vend_cnt,
    (SELECT COUNT(DISTINCT v.vend_id)
     FROM   orderitems o, products p, vendors v
     WHERE  o.prod_id = p.prod_id
     AND    p.vend_id = v.vend_id
     AND    v.vend_country = t.vend_country
    ) order_vend_cnt
FROM
(
    SELECT v.vend_country
    FROM   orderitems oi, orders o, products p, vendors v
    WHERE  oi.order_num = o.order_num
    AND    oi.prod_id = p.prod_id
    AND    p.vend_id = v.vend_id
    GROUP BY v.vend_country
) t;
