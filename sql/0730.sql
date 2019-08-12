/*4. ������ ���ǿ� ���� ��ǰ�� ��ǰ��ȣ, ��ǰ�̸�, ��ǰ���ݼ���, ��ǰ������ �����Ͻÿ�
��ǰ���ݼ���: ��ǰ������ 3$���̸� �����硯 11%���̸� ����ӡ� �������� �����롯
����: ���޾�ü�� �����ȣ ���� 2�ڸ��� ��44��, ��45��, ��99�� �� ���޾�ü���� �����
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
SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , DECODE(TRUNC(p.prod_price), '3', '����', '11', '���', '����') price_nanum
    , p.prod_desc
FROM products p, vendors v
WHERE SUBSTR(v.vend_zip, 1, 2) IN ('44', '45', '99')
AND p.vend_id = v.vend_id
UNION
SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , DECODE(TRUNC(p.prod_price), '3', '����', '11', '���', '����') price_nanum
    , p.prod_desc
FROM products p
WHERE EXISTS (SELECT *
            FROM orders o, orderitems oi
            WHERE o.order_num = oi.order_num
            AND oi.prod_id = p.prod_id
            AND TO_CHAR(o.order_date, 'mm') <> '05'
            );
            
            
SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , DECODE(TRUNC(p.prod_price), '3', '����', '11', '���', '����') price_nanum
    , p.prod_desc
FROM products p, vendors v
WHERE SUBSTR(v.vend_zip, 1, 2) IN ('44', '45', '99')
AND p.vend_id = v.vend_id
UNION
SELECT p.prod_id
    , TRIM(p.prod_name) prod_name
    , DECODE(TRUNC(p.prod_price), '3', '����', '11', '���', '����') price_nanum
    , p.prod_desc
FROM products p
WHERE NOT EXISTS (SELECT * --�ֹ� ���� ��ǰ�� �Բ� ���´�. -> NOT EXISTS �ȿ� ������ ���Ƽ� �Ȱɷ����� �� ����(�Ƹ���)
            FROM orders o, orderitems oi
            WHERE o.order_num = oi.order_num
            AND oi.prod_id = p.prod_id
            AND TO_CHAR(o.order_date, 'mm') = '05' -- �����󿡼��� ������ ǥ���� ���� �Ἥ �ȿ� �������� ���� NOT EXISTS�� ���� ���̴�.
            );
            


SELECT DISTINCT *
FROM orders o, orderitems oi, products p
WHERE o.order_num = oi.order_num
AND oi.prod_id = p.prod_id
AND TO_CHAR(o.order_date, 'mm') <> '05';

/*CASE WHEN*/
--�����ȣ�� ���� type�� ����
SELECT vend_id,
    CASE WHEN SUBSTR(vend_zip, 1, 2) = '44' THEN 'A'
         WHEN SUBSTR(vend_zip, 1, 2) = '99' THEN 'B'
         ELSE 'C'
    END AS resion_type
FROM vendors; 

--�⺻ ��ǰ���� �ֹ� �� ���� �ܰ��� �ֹ��Ǵ� ��ǰ �߿� ������ 5$�� �������� ��/����/��ġ�� ����
SELECT order_num, prod_id,
    CASE WHEN item_price > 5 THEN '��'
         WHEN item_price < 5 THEN '����'
         ELSE '��ġ'
    END AS price_std
FROM orderitems p
WHERE item_price >= (SELECT MIN(prod_price)
                    FROM products
                    WHERE prod_id = p.prod_id
                    );
                    
-- ���޾�ü�� �ֺ� ���� �÷����� �и��Ͽ� ����
SELECT SUM(CASE WHEN vend_state = 'MI' THEN 1 END) MI_CNT,
       SUM(CASE WHEN vend_state = 'OH' THEN 1 END) OH_CNT,
       SUM(CASE WHEN vend_state = 'CA' THEN 1 END) CA_CNT,
       SUM(CASE WHEN vend_state = 'NY' THEN 1 END) NY_CNT
FROM vendors;


/*1. �ֹ��� �ֹ���ȣ, ����ȣ, �ֹ��ñ⸦ �����Ͻÿ�
�ֹ��ñ�: ���� 1~15�� ���̸� �����ϡ�, 16~31�� ���̸� �����ϡ�*/
SELECT order_num
    , cust_id
    , CASE WHEN TO_CHAR(order_date, 'dd') BETWEEN '01' AND '15' THEN '����'
           WHEN TO_CHAR(order_date, 'dd') BETWEEN '16' AND '31' THEN '����'
           ELSE '����'
      END AS order_sig
FROM orders;

   SELECT order_num, cust_id,
          CASE WHEN TO_CHAR(order_date,'DD') >= '01' AND TO_CHAR(order_date,'DD') <= '15' THEN '����'
               WHEN TO_CHAR(order_date,'DD') >= '16' AND TO_CHAR(order_date,'DD') <= '31' THEN '����'
          END order_period
   FROM   orders
   ;


/*2. �ֹ����� �ֹ���ȣ�� �ֹ����ɳ��ڸ� �����Ͻÿ�
�ֹ����ɳ���: �ֹ����ڰ� 1~15���̸� ���糯��, 16~31���̸� ������ 1��*/
SELECT order_num
    , TO_CHAR(order_date, 'yyyy-mm-dd') order_date
    , CASE WHEN TO_CHAR(order_date, 'dd') BETWEEN '01' AND '15' THEN TO_CHAR(order_date, 'yyyy-mm-dd')
           WHEN TO_CHAR(order_date, 'dd') BETWEEN '16' AND '31' THEN TO_CHAR(TRUNC(ADD_MONTHS(order_date, 1), 'mm'), 'yyyy-mm-dd')
           ELSE '����'
      END AS order_pos
FROM orders;

   SELECT  order_num,
           CASE WHEN TO_CHAR(order_date,'DD') >= '01' AND TO_CHAR(order_date,'DD') <= '15' THEN TO_CHAR(SYSDATE,'YYYY-MM-DD')
                WHEN TO_CHAR(order_date,'DD') >= '16' AND TO_CHAR(order_date,'DD') <= '31' THEN TO_CHAR(SYSDATE+1, 'YYYY-MM-DD')
          END orderCanDt
   FROM    orders
   ;

/*3. �ֹ����� �ֹ���ȣ�� �ֹ����ɳ���(YYYY-MM-DD)�� �����Ͻÿ�
- �ֹ����ɳ���: ������ 10�ڸ��� 0�̸� 1��, 1�̸� 10��, 20�̸� 20��, 30�̸� 30��*/
SELECT order_num
    , TO_CHAR(order_date, 'yyyy-mm-dd') order_date
    , CASE WHEN SUBSTR(TO_CHAR(order_date, 'dd'), 1, 1) = '0' THEN TO_CHAR(order_date, 'yyyy-mm-') || '01'
           WHEN SUBSTR(TO_CHAR(order_date, 'dd'), 1, 1) = '1' THEN TO_CHAR(order_date, 'yyyy-mm-') || '10'
           WHEN SUBSTR(TO_CHAR(order_date, 'dd'), 1, 1) = '2' THEN TO_CHAR(order_date, 'yyyy-mm-') || '20'
           WHEN SUBSTR(TO_CHAR(order_date, 'dd'), 1, 1) = '3' THEN TO_CHAR(order_date, 'yyyy-mm-') || '30'
           ELSE '����'
      END AS order_pos
FROM orders;


SELECT order_num,
        TO_CHAR(
            CASE WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '0' THEN TO_DATE(TO_CHAR(order_date,'YYYYMM')||'01')
                 WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '1' THEN TO_DATE(TO_CHAR(order_date,'YYYYMM')||'10')
                 WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '2' THEN TO_DATE(TO_CHAR(order_date,'YYYYMM')||'20')
                WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '3' THEN TO_DATE(TO_CHAR(order_date,'YYYYMM')||'30')
             END, 'YYYY-MM-DD') orderCanDt     
FROM   orders
;


/*������ �Է�*/
--CUSTOMERS�� ��� �÷��� ���� DATA �߰�
INSERT INTO customers
VALUES('1000000016', 'Toy Land', '123 Any Street', 'New York', 'NY', '11111', 'USA', NULL, NULL);

--Customers�� Ư�� �÷��� ���� Data �߰�
INSERT INTO customers(cust_id, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country, cust_contact)
VALUES('1000000017', 'Toy Land', '123 Any Street', 'New York', 'NY', '11111', 'USA', NULL);

/*�ʱⰪ�� �����Ͽ� ���̺� ����*/
CREATE TABLE custnew AS
SELECT * FROM customers;

select * from custnew;

DELETE FROM custnew;

/*SELECT ������ ����� ������ �Է�*/
-- �� �÷� �� ������ Ÿ���� ��ġ�ؾ� �Ѵ�
INSERT INTO custnew(cust_id, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country)
SELECT cust_id, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country
FROM customers;

/*���� ����*/
INSERT INTO customers(cust_id, cust_name, cust_address, cust_city, cust_zip, cust_country, cust_contact, cust_email)
VALUES('1000000006', 'ȫ�浿', '����� ���ʱ� ������� 459', 'Seoul', '51243', 'KOR', 'Park', 'aaa@naver.com');
INSERT INTO customers(cust_id, cust_name, cust_address, cust_city, cust_zip, cust_country, cust_contact, cust_email)
VALUES('1000000007', '�ڱ浿', '����� ���ʱ� ������� 459', 'Seoul', '51472', 'KOR', 'Kim', 'bbb@daum.net');
INSERT INTO customers(cust_id, cust_name, cust_address, cust_city, cust_zip, cust_country, cust_contact, cust_email)
VALUES('1000000008', '�ż���', '����� ���ʱ� ������� 459', 'Seoul', '53782', 'KOR', 'Kim', 'ccc@google.co.kr');

DELETE customers WHERE cust_id = '1000000008';

INSERT INTO products (prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('KR01', 'BRS01', '�Ƿη�����', '10', 'TV�����׷����� ������ �Ƿηθ� �������� ����');
INSERT INTO products (prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('KR02', 'BRE02', '������', '15', '���� �����ϴ� ������� ����');
INSERT INTO products (prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('KR03', 'DLL01', '�±�V�κ�Ʈ', '100', '����� �ڱ��ϴ� ��� ���� �±�V�κ�Ʈ');

select * from products;

INSERT INTO orders
VALUES('20010', '2019-02-01', '1000000006');
INSERT INTO orders
VALUES('20011', '2019-05-04', '1000000007');

INSERT INTO orderitems
VALUES('20010', '1', 'KR01', '2', '9');
INSERT INTO orderitems
VALUES('20011', '1', 'KR02', '2', '16');
INSERT INTO orderitems
VALUES('20010', '2', 'KR03', '3', '95');

/*UPDATE : ������ ������Ʈ*/
--ID�� 1000000005�� ���� ���ڸ����ּҸ� ����
UPDATE customers
SET cust_email = 'kim@naver.com'
    , cust_contact = 'Sam Roverts'
WHERE cust_id = '1000000005';

/*1. �����̺��� ����ȣ�� ��1000000006���� ���� ����ڸ� ��Jin������ �����Ͻÿ�*/
UPDATE customers
SET cust_contact = 'Jin'
WHERE cust_id = '1000000006';

/*2. �ֹ����̺��� �������� ��KOR���� ���� �ֹ��� �׸������ +1���� ���Ͻÿ�*/
UPDATE orderitems oi
SET quantity = quantity + 1
WHERE EXISTS (SELECT *
              FROM orders o, customers c
              WHERE c.cust_id = o.cust_id
              AND o.order_num = oi.order_num
              AND c.cust_country = 'KOR'
             );
             
SELECT * FROM orderitems;

/*3. �ֹ����ڰ� 2019�⵵�� �߻��� �ֹ� �߿� �ֹ���ǰ�� �׸񰡰��� 90$�� �Ѵ� �׸񰡰ݿ� ���� 10%���� DC�Ͽ� �����Ͻÿ�*/
UPDATE orderitems oi
SET item_price = item_price * 0.9
WHERE EXISTS (SELECT *
              FROM orders o
              WHERE o.order_num = oi.order_num
              AND TO_CHAR(o.order_date, 'yyyy') = '2019'
              AND oi.item_price > 90
              );
              
              
UPDATE orderitems k
SET    item_price = (item_price - item_price*0.1)
WHERE  order_num IN (
          SELECT order_num
          FROM   orders
          WHERE  TO_CHAR(order_date, 'YYYY') = '2019'
       )
AND    item_price > 90       
;
SELECT oi.item_price, p.prod_price
FROM orderitems oi, products p
WHERE oi.prod_id = p.prod_id;

/*4. �ֹ���ǰ�߿� ��ǰ�� ��ǰ���ݺ��� �ֹ���ǰ�� �׸񰡰��� ��� �׸񰡰��� ��ǰ�� ��ǰ�������� �����Ͻÿ�*/
UPDATE orderitems oi
SET item_price = (SELECT prod_price
                    FROM products
                    WHERE prod_id = oi.prod_id
                    )
WHERE item_price > (SELECT prod_price
                    FROM products
                    WHERE prod_id = oi.prod_id
                    );
                    
UPDATE orderitems k
SET    item_price = (
                      SELECT prod_price
                      FROM   products
                      WHERE  prod_id = k.prod_id
                     )
WHERE  prod_id IN (
         SELECT prod_id
         FROM   products b
         WHERE  k.item_price > b.prod_price
       )
;

select p.prod_price, oi.item_price from orderitems oi , products p
WHERE p.prod_id = oi.prod_id;

/*1. ��ǰ��ȣ�� KR03�� ��ǰ�� ��ǰ���̺��� �����ϰ� �ֹ���ǰ���̺����� �����Ͻÿ�*/
DELETE FROM orderitems
WHERE prod_id = 'KR03';

DELETE FROM products
WHERE prod_id = 'KR03';


/*2. ���޾�ü�� ��BRE02���� ���޾�ü�� ������ ��ǰ�� ���������� ��ǰ�� �ֹ��� �ֹ������� �����Ͻÿ�*/
DELETE FROM orderitems
WHERE prod_id IN (SELECT prod_id
                    FROM products
                    WHERE vend_id = 'BRE02'
                    AND prod_name LIKE '%������%'
                    )
;

DELETE FROM orders o
WHERE NOT EXISTS(SELECT order_num
                FROM orderitems
                WHERE order_num = o.order_num
                )
;                

/*3. �� ��1000000006���� �ֹ��� �ֹ������� �����Ͻÿ�*/
DELETE FROM orderitems
WHERE order_num IN (SELECT order_num
                    FROM orders
                    WHERE cust_id = '1000000006'
                    )
;

DELETE FROM orders o
WHERE NOT EXISTS(SELECT order_num
                FROM orderitems
                WHERE order_num = o.order_num
                )
;                

