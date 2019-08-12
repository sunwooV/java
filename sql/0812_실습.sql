/*1.������ ����ȣ, ���̸�, ���ּ�, �ֹ��� �� �ݾ�,  �ֹ��� ��ǰ �߿� ���� �ֱٿ� �ֹ��� ��ǰ���� �����Ͻÿ�
    - �ֹ��� �� �ݾ�: �ش���� �ֹ���ǰ���� �ֹ��� ��ǰ�� �׸񰡰��� SUM
    - �ֹ��� ��ǰ �߿� ���� �ֱٿ� �ֹ��� ��ǰ��:�ֹ���ǰ���� �ش���� �ֹ��� ��ǰ�� ���� �ֱٿ� �ֹ��� ��ǰ�� ��ǰ�� 
    (��ǰ�� �������� ��� �����ټ����� ���� ū ��ǰ���� ���)*/
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



--�Ʒ��� ���� 

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


/*2. ��ǰ���ֹ��� �� �߿� ������,������� �� �ֹ����� �����Ͻÿ�
    - ���: ������,�������, �ֹ���
    - ����: �ֹ����� 2���̻��� ��츸 ��ȸ */
SELECT c.cust_country, c.cust_contact, COUNT(o.order_num) order_sum
FROM customers c, orders o
WHERE c.cust_id = o.cust_id
GROUP BY c.cust_country, c.cust_contact
HAVING count(o.order_num) >= 2;
   

/*3. ������ ���ǿ� ���� ��ǰ�� ��ǰ��ȣ, ��ǰ�̸�, ��ǰ���ݼ���, ��ǰ������ �����Ͻÿ�
   - ��ǰ���ݼ���: ��ǰ������ 3$���̸� �����硯 11$���̸� ����ӡ��������� �����롯
   - ����: ���޾�ü�� �����ȣ ���� 2�ڸ��� ��44��,��45��, ��99�� �ΰ��޾�ü���� ����ų� �ֹ��� ���ڰ� 5���� �ƴ� ��ǰ */
SELECT k.prod_id
    , TRIM(k.prod_name) prod_name
    , DECODE(TRUNC(k.item_price), 3, '����', 11, '���', '����') price_level
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
    , DECODE(TRUNC(p.prod_price), 3, '����', 11, '���', '����') price_level
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




/*4. �ֹ���ǰ�߿� ��ǰ�� ��ǰ���ݺ��� �ֹ���ǰ�� �׸񰡰��� ��� �׸񰡰��� ��ǰ����ǰ�������� �����Ͻÿ� */
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



