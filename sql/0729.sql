/*2. ���޾�ü�� ��ǰ�� �����Ͽ� ������ ����� �����Ͻÿ�
���: ���޾�ü��ȣ, ���޾�ü�̸�, ���޾�ü�ּ�, ��ǰ��ȣ, ��ǰ�̸�, ��ǰ����, ��ǰ����
����: ���޾�ü���� �����ǰ���� �̻��� ��ǰ*/
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

/*3. ��ǰ, �ֹ�, �ֹ���ǰ�� �����Ͽ� ������ ����� �����Ͻÿ�
���: �ֹ���ȣ, �ֹ�����, ��ǰ��ȣ, ��ǰ�̸�, ��ǰ����, �׸񰡰�
����: ��ǰ���ݰ� �׸񰡰��� �ٸ� ��ǰ*/
SELECT oi.order_num
    , TO_CHAR(o.order_date, 'yyyy-mm-dd') order_date
    , TRIM(p.prod_name) prod_name
    , p.prod_price
    , oi.item_price
FROM products p, orderitems oi, orders o
WHERE p.prod_id = oi.prod_id
AND oi.order_num = o.order_num
AND p.prod_price <> oi.item_price;

/*4. ���޾�ü, ��ǰ, �ֹ���ǰ�� �����Ͽ� ������ ����� �����Ͻÿ�
���: ���޾�ü��ȣ, ���޾�ü�̸�, ���޾�ü�ּ�, �ֹ���ȣ, ��ǰ��ȣ, ��ǰ�̸�*/
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


/*�ܺ�����*/
-- ���� �ֹ����� ���� ����� �����Ͽ� ��� ����� ����
SELECT  c.cust_id, o.order_num
FROM customers c, orders o
WHERE c.cust_id (+)= o.cust_id; --Main�� �Ǵ� ���̺� (+) ǥ��

--ANSI SQL - �ܺ�����
SELECT c.cust_id, o.order_num
FROM customers c LEFT OUTER JOIN orders o
    ON c.cust_id = o.cust_id;
    
/*�����Լ��� �Բ� ������ ���*/
--�ֹ��� ���� ��ϰ� �� ���� �ֹ��� ������ ����
SELECT c.cust_id, COUNT(o.order_num) num_ord
FROM customers c INNER JOIN orders o 
    ON c.cust_id = o.cust_id
GROUP BY c.cust_id;

--�ֹ��� ���� ���� �����Ͽ� ����
SELECT c.cust_id, COUNT(o.order_num) num_ord
FROM customers c LEFT OUTER JOIN orders o 
    ON c.cust_id = o.cust_id
GROUP BY c.cust_id;


--INNER JOIN : ����� ���� �ִ� ��鸸 ���Խ�Ű�� �׷��� �ʴ� ����� ���� ��ŵ�ϴ�.
--OUTER JOIN : �� ���̺��� ������ ���� LEFT �Ǵ� RIGHT ���� ��� ����� �������� �ݴ��ʿ� ��Ī�Ǵ� ���� ��� �����ִ� JOIN  �Դϴ�.


/*1. ��ǰ�� �ֹ��׸��� �����Ͽ� ������ ����� �����Ͻÿ�
���: ��ǰ��ȣ, ��ǰ�̸�, �ֹ���ȣ, �׸����, �׸񰡰�
����: ��� ��ǰ�� ��ȸ�Ǿ�� �Ѵ�*/
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
--�� �Ʒ� ���� ���
SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , oi.order_num
    , oi.quantity
    , oi.item_price
FROM products p, orderitems oi
WHERE oi.prod_id (+)= p.prod_id;

/*2. ��ǰ�� �ֹ��� �� ���� �ֹ� �� �� �� ���� �����Ͻÿ�
���: �ֹ�����, �� ��*/
SELECT yon, COUNT(yon)
FROM (
    SELECT NVL((SELECT MAX('Y') --MAX()�� MIN() �Լ��� ���Ƿ� �÷��� �����ϰ� �ӽ÷� ������ �÷��� ���� ���ٸ�, 'N'�� ����
                FROM orders
                WHERE cust_id = c.cust_id
                ), 'N') yon
    FROM customers c
    )
GROUP BY yon;

SELECT MAX('P') --MAX()�� MIN() �Լ��� ���Ƿ� �÷��� �����ϰ� �ӽ÷� ������ �÷��� ���� ���ٸ�, 'N'�� ����
FROM orders o, customers c
WHERE o.cust_id = c.cust_id;

SELECT MAX('Y')
FROM orders o, customers c
WHERE o.cust_id = c.cust_id
GROUP BY o.cust_id;

    SELECT NVL((SELECT MIN('Y') --MAX()�� MIN() �Լ��� ���Ƿ� �÷��� �����ϰ� �ӽ÷� ������ �÷��� ���� ���ٸ�, 'N'�� ����
                FROM orders
                WHERE cust_id = c.cust_id
                ), 'N') yon
    FROM customers c;

/*3. ������ �ֹ��� ��ǰ �߿� ���� ���� �ݾ��� ������ ��ǰ�� �����Ͻÿ�
���: ����ȣ, ���̸�, ��ǰ��ȣ, ��ǰ�̸�, �׸񰡰�*/

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



/*4. ���޾�ü���� �����ϴ� ��ǰ�� ���� �ְ���ǰ������ �����Ͻÿ�
���: ���޾�ü��ȣ, ���޾�ü�̸�, ��ǰ ��, �ְ���ǰ����
����: 8$�̻��� ��ǰ�� �����ϴ� ���޾�ü*/


/*5. �ֹ� �߿� ���� ��� ���ð� ��Detroit���̰� �ֹ��� �� ��ǰ�� ���� 500�� �Ѵ� ���� �����Ͻÿ�
���: ����ȣ, ���̸�, ������*/


/*UNION: ������, 2���� SQL�� ���Ͽ� ����
- ���� ������ ������ ȣȯ�Ǿ�� �Ѵ�
- �Ϲ������� UNION�� UNION DISTINCT�� ���Ƽ� �ߺ����� �������ش�.*/
--IL, IN, MI �ֿ� �ִ� ��� ���� ������ Fun4All �̶�� ���� ������ ����
SELECT cust_name, cust_contact, cust_email, cust_state
FROM customers
WHERE cust_state IN ('IL', 'IN', 'MI')
UNION
SELECT cust_name, cust_contact, cust_email, cust_state
FROM customers
WHERE cust_name = 'Fun4All';

--������ ���� ���⵵ ����
SELECT cust_name, cust_contact, cust_email, cust_state
FROM customers
WHERE cust_state IN ('IL', 'IN', 'MI')
OR cust_name = 'Fun4ALl';

/*�ߺ��� ���� x �� ����
--UNION ALL : �ߺ��Ǵ� ���� �������� �ʰ� ��� �����ش�.*/
SELECT cust_name, cust_contact, cust_email, cust_state
FROM customers
WHERE cust_state IN ('IL', 'IN', 'MI')
UNION ALL
SELECT cust_name, cust_contact, cust_email, cust_state
FROM customers
WHERE cust_name = 'Fun4All'
ORDER BY cust_name, cust_contact;

--UNION ��� �ǽ�
/*1. ������ ���ǿ� ���� ���� ����ȣ�� ���̸��� �����Ͻÿ�
����: �ֹ��� ���� ���� �̸����ּҰ� ���� ��*/

/*2. ��� �ֹ��� ���� ������ ����� �����Ͻÿ�
���: �ֹ���ȣ, �ֹ�����(YYYY-MM-DD), ����ȣ, ���̸�, �ֹ���ǰ��ȣ, �ֹ���ǰ�̸�, ���޾�ü��ȣ, ���޾�ü��
����: 3$���� ��ǰ�� �Ǹ��ϴ� ���޾�ü�� ��� �ֹ�*/



select * from products;


/*3. ������ ���ǿ� ���� ���޾�ü�� ���޾�ü��ȣ, ���޾�ü��, ���޾�ü�ּҸ� �����Ͻÿ�
����: �ֹ��� ��ǰ�� 2���̻��̰ų� �����ϴ� ��ǰ�� 2���̻��� ���޾�ü*/


/*4. ������ ���ǿ� ���� ��ǰ�� ��ǰ��ȣ, ��ǰ�̸�, ��ǰ���ݼ���, ��ǰ������ �����Ͻÿ�
��ǰ���ݼ���: ��ǰ������ 3$���̸� �����硯 11%���̸� ����ӡ� �������� �����롯
����: ���޾�ü�� �����ȣ ���� 2�ڸ��� ��44��, ��45��, ��99�� �� ���޾�ü���� ���� �����
�ֹ��� ���ڰ� 5���� �ƴ� ��ǰ*/
SELECT DISTINCT p.prod_id
    , TRIM(p.prod_name) prod_name
    , DECODE(TRUNC(p.prod_price), '3', '����', '11', '���', '����') price_bunrye
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
    , DECODE(TRUNC(p.prod_price), '3', '����', '11', '���', '����') price_nanum
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

--�ȵǴ� �ڵ�
SELECT p.prod_id, p.prod_name, p.prod_desc
    , DECODE(TRUNC(p.prod_price), '3', '����', '11', '���', '����')        
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

/*4_2. ������ ���ǿ� ���� ��ǰ�� ��ǰ��ȣ, ��ǰ�̸�, ��ǰ���ݼ���, ��ǰ������ �����Ͻÿ�
��ǰ���ݼ���: ��ǰ������ 3$���̸� �����硯 11%���̸� ����ӡ� �������� �����롯
����: ���޾�ü�� �����ȣ ���� 2�ڸ��� ��44��, ��45��, ��99�� �� ���޾�ü���� ����ų� (������)
�ֹ��� ���ڰ� 5���� �ƴ� ��ǰ*/   
