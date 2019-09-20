/*1. ��ǰ�ֹ����� �ֹ���ȣ, ��ǰ��ȣ, ��ǰ����������ġ�� �����Ͻÿ�
   - ��ǰ����������ġ: ��ǰ��ȣ�� ������ 2�ڸ��� 01�̸� ��������, �ƴϸ� ���ؿܡ�
   - ����: �Ѱ���(�׸����*�׸񰡰�)�� 500$�̻��� �ֹ�*/
   
SELECT order_num, prod_id, DECODE(SUBSTR(TRIM(prod_id), -2), '01', '����', '�ؿ�') prod_location
FROM orderitems oi
WHERE order_num IN (SELECT order_num
                    FROM orderitems
                    GROUP BY order_num
                    HAVING SUM(quantity * item_price) >= 500
                    );

/*2. �ֹ��� �������� �����Ͽ� ������ ����� �����Ͻÿ�
   - ���: �ֹ���ȣ, �ֹ�����, ��ǰ��ȣ, ����ȣ, ���̸�, ���ּ�, ����, �������ּ�
   - ����: ��ü�ֹ����� ���� ���� ��ǰ������ �Ǹ��� ���޾�ü���� ���� ��ǰ����ȸ*/

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

--������ �ֹ��� ��ǰ�� count���� �ʾ���. -> ��ü ��ǰ count
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



--�����
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

--�Ʒ��� ����
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

/*3. �ֹ� �߿� ���� ��� ���ð� ��Detroit���̰� �ֹ��� �� ��ǰ�� ���� 500�� �Ѵ� ���� �����Ͻÿ�
   - ���: ����ȣ, ���̸�, ������*/
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


/*4. ��� �ֹ��� ���� ������ ����� �����Ͻÿ�
   - ���: �ֹ���ȣ, �ֹ�����(YYYY-MM-DD), ����ȣ, ���̸�, �ֹ���ǰ��ȣ, �ֹ���ǰ�̸�, ���޾�ü��ȣ, ���޾�ü��
   - ����: 3$���� ��ǰ�� �Ǹ��ϴ� ���޾�ü�� ��� �ֹ�*/

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


--�Ʒ� ������ 5��
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

/*5. �ֹ����ڰ� 2004�⵵�� �߻��� �ֹ� �߿� �ֹ���ǰ�� �׸񰡰��� 9$�� �Ѵ� �׸񰡰ݿ� ���� 10%���� DC�Ͽ� �����Ͻÿ�*/
UPDATE orderitems
SET item_price = item_price * 0.9
WHERE item_price > 9
AND order_num IN (SELECT order_num
                  FROM orders
                  WHERE TO_CHAR(order_date, 'yyyy') = '2004'
                  );
                  
