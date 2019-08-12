/*1.고객별로 주문한 제품의 수와 주문한 제품 중 최고항목가격(판매가격)을 추출하시오
결과: 고객번호, 고객이름, 주문제품 수, 주문제품 중 최고항목가격*/
SELECT c.cust_id
    , TRIM(c.cust_name) cust_name
    , (SELECT SUM(oi.quantity)
        FROM orderitems oi, orders o
        WHERE oi.order_num = o.order_num
        AND o.cust_id = c.cust_id
        ) prod_num
    , (SELECT MAX(item_price)
        FROM orderitems oi, orders o
        WHERE oi.order_num = o.order_num
        AND o.cust_id = c.cust_id
        ) highest_price
FROM customers c 
GROUP BY c.cust_id, TRIM(c.cust_name)
ORDER BY c.cust_id;

--(null)값을 가진 cust는 나오지 않음
SELECT c.cust_id
    , TRIM(c.cust_name) cust_name
    , k.sum_quantity
    , k.max_price
FROM customers c
    , (SELECT o.cust_id, MAX(item_price) max_price, SUM(oi.quantity) sum_quantity
        FROM orderitems oi, orders o
        WHERE oi.order_num = o.order_num
        GROUP BY o.cust_id
        ) k
WHERE k.cust_id = c.cust_id;


/*2.주문된 제품 중에 제품의 공급업체가 있는 도시가 ‘Dollsville’이고 주문된 총 제품의 수가 300을 넘는 제품명을 추출하시오*/
SELECT TRIM(p.prod_name) prod_name
FROM products p
WHERE p.vend_id IN (SELECT vend_id
                    FROM vendors
                    WHERE vend_id = p.vend_id
                    AND vend_city = 'Dollsville'
                    )
AND 300 < (SELECT SUM(quantity)
            FROM orderitems 
            WHERE prod_id = p.prod_id
            );
            
--강사님            
SELECT TRIM(p.prod_name) prod_name
FROM products p, vendors v
WHERE p.vend_id = v.vend_id
AND   p.prod_id IN (SELECT prod_id
                    FROM orderitems
                    WHERE prod_id = p.prod_id
                    GROUP BY prod_id
                    HAVING SUM(quantity) > 300
                    )
AND v.vend_city = 'Dollsville';

--테스트
select v.vend_city, TRIM(p.prod_name), SUM(oi.quantity)
FROM vendors v, products p, orderitems oi
WHERE v.vend_id = p.vend_id
AND p.prod_id = oi.prod_id 
AND v.vend_city = 'Dollsville'
group by v.vend_city, p.prod_name;

/*3.고객별로 주문한 상품 중에 가장 비싼 제품명을 추출하시오
결과: 고객번호, 고객이름, 제품번호, 제품이름, 항목가격(판매가격)*/
SELECT c.cust_id
    , TRIM(c.cust_name) cust_name
    , p.prod_id
    , TRIM(p.prod_name) prod_name
    , oi.item_price
FROM customers c, orders o, orderitems oi, products p
WHERE c.cust_id = o.cust_id
AND o.order_num = oi.order_num
AND oi.prod_id = p.prod_id
AND oi.item_price = (SELECT MAX(oii.item_price)
                      FROM orderitems oii, orders oo
                      WHERE oii.order_num = oo.order_num
                      AND oo.cust_id = c.cust_id
                      )
ORDER BY c.cust_id;


--강사님
SELECT c.cust_id
    , TRIM(c.cust_name) cust_name
    , p.prod_id
    , TRIM(p.prod_name) prod_name
    , oi.item_price
FROM customers c, orders o, orderitems oi, products p
    , (SELECT oo.cust_id, MAX(oii.item_price) max_price
       FROM orderitems oii, orders oo
       WHERE oii.order_num = oo.order_num
       GROUP BY oo.cust_id
       ) t
WHERE c.cust_id = o.cust_id
AND o.order_num = oi.order_num
AND oi.prod_id = p.prod_id
AND oi.item_price = t.max_price
AND c.cust_id = t.cust_id;


--아래 테스트
SELECT c.cust_id, MAX(oi.item_price)
FROM orderitems oi, orders o, customers c
WHERE oi.order_num = o.order_num
AND o.cust_id = c.cust_id
GROUP BY c.cust_id;

SELECT c.cust_id, c.cust_name, p.prod_id, p.prod_name, oi.item_price
FROM customers c, orders o, orderitems oi, products p
WHERE c.cust_id = o.cust_id
AND o.order_num = oi.order_num
AND oi.prod_id = p.prod_id
AND p.prod_id IN (SELECT prod_id
                    FROM products pp, customers c, orders o
                    WHERE EXISTS(SELECT MAX(item_price)
                                FROM orderitems
                                WHERE order_num = o.order_num
                                AND o.cust_id = c.cust_id
                                GROUP BY c.cust_id;
                                )
                    )
ORDER BY c.cust_id;


SELECT c.cust_id, prod_id, item_price
FROM products pp, orderitems oi, customers c, orders o
WHERE oi.order_num = o.order_num
AND c.cust_id = o.cust_id
AND oi.prod_id = pp.prod_id
AND EXISTS(SELECT MAX(item_price)
            FROM orderitems
            WHERE order_num = oi.order_num
            AND prod_id = pp.prod_id
            GROUP BY c.cust_id
            );
            
            
            