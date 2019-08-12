/*AVG():NULL ���� �ִ� ��� ��꿡�� ���ܵȴ�*/
--Products ���̺� �ִ� ��� ��ǰ�� �������
SELECT AVG(prod_price) avg_price
FROM products;

--DLL01�� ���޾�ü�� ���� ��ǰ ���
SELECT AVG(prod_price) avg_price
FROM products
WHERE vend_id = 'DLL01';

/*COUNT()
- COUNT(*): ���̺��� ��� ���� ����, NULL�� ����
- COUNT(�÷�): �÷��� NULL���� ������ ���� ����*/
--Customers ���̺� �ִ� ��� ���� ��
SELECT COUNT(*) num_cust
FROM customers;

--Cust_email ���� ���� �ִ� ���� ���� ���
SELECT COUNT(cust_email) as num_cust
FROM customers;

/*MAX(): ������ ������ ���� ū ���� ��ȯ
- TEXT�� ��밡��, NULL���� ���õȴ�*/
--Products ���̺��� ������ ���� ��� ��ǰ�� ����
SELECT MAX(prod_price) max_price
FROM products;

/*MIN(): ������ ������ ���� ���� ���� ��ȯ
- TEXT�� ��밡��, NULL���� ���õȴ�*/
--Products ���̺��� ������ ���� ������ ��ǰ�� ����
SELECT MIN(prod_price) min_price
FROM products;

/*SUM(): ������ ������ ��� ���� ���� �հ�
- NULL���� ���õȴ�*/
--��� �ֹ��� ��ǰ�� ������ �հ�
SELECT SUM(quantity) items_ordered
FROM orderitems;

--�� �ֹ��� ���� �� �ݾ��� ��ȯ
SELECT SUM(item_price * quantity) total_price
FROM orderitems
WHERE order_num = 20005;

/*�������� ����: SUM, MAX, MIN���� ���*/
--������ ���� ��ǰ�� �ִ� ��� �ѹ��� ��꿡 ����
SELECT AVG(DISTINCT prod_price) avg_price
FROM products
WHERE vend_id = 'DLL01';

/*�����Լ� ����*/
SELECT COUNT(*) num_items
    , MIN(prod_price) price_min
    , MAX(prod_price) price_max
    , ROUND(AVG(prod_price), 2) price_avg
FROM products;

/*1. ���ֹ����� �ֹ����ڰ� ���� �ֱ��� ���ڿ� ���� ������ ���ڸ� �����Ͻÿ�*/
SELECT TO_CHAR(MAX(order_date), 'yyyy-mm-dd') date_current
    , TO_CHAR(MIN(order_date), 'yyyy-mm-dd') date_oldest
FROM orders;

/*2. �ֹ���ǰ���� ��ǰ��ȣ�� BN���� �����ϴ� �� �ֹ��� �ѱݾ�(�׸����*�׸񰡰�)�� ����ū �ݾ��� �����Ͻÿ�*/
SELECT MAX(quantity + item_price) high_price
FROM orderitems
WHERE UPPER(prod_id) LIKE 'BN%';

/*3. �ֹ���ǰ���� ��ǰ��ȣ�� BR01�� BR03�� ��ǰ�� �ֹ��� �׸������ ����� �����Ͻÿ�*/
SELECT ROUND(AVG(quantity), 2) quantity
FROM orderitems
WHERE prod_id IN ('BR01', 'BR03');

/*�׷츸���
- ��ø�� �׷��� ���� ��� �����ʹ� ������ ������ �׷��� �������� ���ȴ�
- GROUP BY�� �׷�ȭ �Ǹ� �ش� �÷��� SELECT���� ��Ÿ���� �Ѵ�
- NULL���� �׷����� �з��ȴ�*/
--���޾�ü�� ��ǰ ��
SELECT vend_id, COUNT(*) num_prods
FROM products
GROUP BY vend_id;

/*���͸� �׷�: �׷��� ������� �� �׷������� �׷��Լ��� ���� ���͸��� ����
- WHERE: �׷�ȭ �ϱ� ���� ���͸� ����
- HAVING: �׷�ȭ �� �Ŀ� ���͸� ����*/
SELECT cust_id, COUNT(*) orders
FROM orders
GROUP BY cust_id
HAVING COUNT(*) >= 2;

--������ 4 �̻��� ��ǰ�� �� �� �̻� ���� ���޾�ü�� ����
SELECT vend_id, COUNT(*) num_prods
FROM products
WHERE prod_price >= 4
GROUP BY vend_id
HAVING COUNT(*) >= 2;

--ORDER BY �� �Բ� ���
SELECT order_num, COUNT(*) items
FROM orderitems
GROUP BY order_num
HAVING COUNT(*) >=3
ORDER BY items, order_num;

--�ֹ��� ��ǰ�� ������
SELECT COUNT(DISTINCT prod_id)
FROM orderitems;

--�ֹ��� ��ǰ�� �� �ֹ�����
SELECT prod_id, SUM(quantity)
FROM orderitems
GROUP BY prod_id;

--�ֹ��� ��ǰ�� �� �ֹ������� 100���� ���� ��ǰ�� ��ǰ��ȣ
SELECT prod_id, SUM(quantity)
FROM orderitems
GROUP BY prod_id
HAVING SUM(quantity) > 100;

--�ֹ���ȣ�� 20005, 20007�� �ֹ��� ��ǰ�߿��� �� �ֹ������� 100���� ���� ��ǰ�� ��ǰ��ȣ
SELECT prod_id, SUM(quantity)
FROM orderitems
WHERE order_num IN (20005, 20007)
GROUP BY prod_id
HAVING SUM(quantity) > 100;

/*1. �ֹ���ǰ�� �ֹ���ȣ�� �ֹ���ȣ�� �ֹ���ǰ�� ���� �����Ͻÿ�
���: �ֹ���ȣ, �ֹ���ǰ�Ǽ�*/
SELECT order_num, COUNT(prod_id) num_prod
FROM orderitems
GROUP BY order_num;

/*2. �ֹ����� �ֹ����ں� �ֹ��� ���� ���� �����Ͻÿ�
���: �ֹ�����(YYYY-MM-DD), ���� ��*/
SELECT TO_CHAR(order_date, 'yyyy-mm-dd') order_date
    , COUNT(DISTINCT cust_id) --�Ȱ��� ���� ������ �ֹ��� �� �ֱ� ������ DISTINCT
FROM orders
group by TO_CHAR(order_date, 'yyyy-mm-dd');

/*3. ��ǰ���� ���޾�ü��ȣ�� ��ǰ�� ���� �����Ͻÿ�
���: ���޾�ü��ȣ, ��ǰ�Ǽ�*/
SELECT vend_id
    , COUNT(prod_id) prod_num
FROM products
GROUP BY vend_id;

/*4. �� �߿� �����ȣ�� 4�� ���۵Ǵ� ���� ���� �����Ͻÿ�
���: ���� ��*/
SELECT COUNT(cust_id) cust_num
FROM customers
WHERE cust_zip LIKE '4%';

/*5. �� �߿� �̸��� �ּҰ� ���� ���� ���� �����Ͻÿ�
���: ���� ��*/
SELECT COUNT(cust_id) cust_num
FROM customers
WHERE cust_email IS NULL;

/*6. ��ǰ�߿� ���޾�ü�� ��ǰ������ ����� �����Ͻÿ�
���: ���޾�ü��ȣ, ��հ���*/
SELECT vend_id, ROUND(AVG(prod_price), 2) avg_prod_price
FROM products
GROUP BY vend_id;

/*7. ������ �ֺ� ���� ���� �����Ͻÿ�
���: ����, ���Ǽ�*/
SELECT cust_state, COUNT(cust_id)
FROM customers
GROUP BY cust_state;

/*8. ��ǰ���� ���޾�ü�� BRS01, DLL01�� ��ǰ�߿� ������ ��ǰ�� ������ 5$�̻��� ��ǰ�� ���޾�ü��ȣ�� �����Ͻÿ�
���: ���޾�ü��ȣ*/
SELECT vend_id
FROM products
WHERE vend_id IN ('BRS01', 'DLL01')
group by vend_id
HAVING MAX(prod_price) >= 5; 

/*9. �ֹ����� 1���� �ֹ��� �ֹ��߿� ����ʰ� �ֹ��� ����ȣ�� ����
���: �ֹ�����(YYYY-MM-DD), ����ȣ*/
SELECT cust_id
    , TO_CHAR(MAX(order_date), 'yyyy-mm-dd') lastest_order_date
FROM orders
WHERE TO_CHAR(order_date, 'mm') = '01' 
GROUP BY cust_id;

/*IN���ȿ� �������� ����ϱ�
- ���������� ���� �����Ͽ� ����� �����ϰ� �� ����� �������� ���� ������ �����Ѵ�*/
--��ǰ��ȣ�� RGAN01�� ��ǰ�� �ֹ��� ��id����
SELECT cust_id
FROM orders
WHERE order_num IN (SELECT order_num
                    FROM orderitems
                    WHERE prod_id = 'RGAN01');
                    
--�������� �ȿ� �������� ���
--��ǰ��ȣ�� RGAN01�� ��ǰ�� �ֹ��� ���� �̸��� ����ó ����
SELECT cust_name, cust_contact
FROM customers
WHERE cust_id IN (SELECT cust_id
                  FROM orders
                  WHERE order_num IN (SELECT order_num
                                      FROM orderitems
                                      WHERE prod_id = 'RGAN01'));
                                      
/*���������� ����ʵ�� ���
- �ߺ��� �÷����� �����ϱ� ���� ���̺������ ����*/
SELECT cust_name, cust_state,
       (SELECT COUNT(*)
        FROM orders
        WHERE orders.cust_id = customers.cust_id) orders
FROM customers
ORDER BY cust_name;

--FROM���� SQL ����ϱ�
SELECT order_num, order_item, type
FROM(
    SELECT order_num, order_item, SUBSTR(prod_id, 1, 2) type
    FROM orderitems
)A
WHERE type = 'BR';

--EXISTS, NOT EXISTS
SELECT cust_id, cust_name, cust_address
FROM customers a
WHERE EXISTS( --EXISTS�ȿ� SELECT���� ������ �͵� ��� ����.
        SELECT *
        FROM orders
        WHERE cust_id = a.cust_id
        );
        
/*1. ��ǰ�� ����ID�� ��ǰ�̸�, ��ǰ����, ��ǰ������ �����Ͻÿ�
����: ���޾�ü�� ������ ��USA���� ���޾�ü�� ��ǰ*/
SELECT prod_id
    , trim(prod_name) prod_name
    , trim(prod_price) prod_price
    , prod_desc
FROM products
WHERE vend_id IN (SELECT vend_id
                    FROM vendors
                    WHERE vend_country = 'USA'
                    );

/*2. ���� ����ȣ, ���̸�, ���ּ�, �������ּҸ� �����Ͻÿ�
����: ���� �����ּҰ� �ְ� �ֹ��� �� ���̶� �� ��*/
SELECT cust_id, trim(cust_name), trim(cust_address), trim(cust_email)
FROM customers a
WHERE cust_email IS NOT NULL
AND EXISTS (
             SELECT cust_id
             FROM orders
             WHERE a.cust_id = cust_id
            );

/*3. ���޾�ü�� ���޾�ü��ȣ, ���޾�ü�̸�, ���޾�ü�ּ�, ���޾�ü�� ��ǰ ���� �����Ͻÿ�
����: ���޾�ü�� ��ǰ���� 2�� �̻��� ���޾�ü*/
SELECT vend_id, trim(vend_name), trim(vend_address),
    (SELECT COUNT(*)
    FROM products
    WHERE a.vend_id = vend_id
    ) prod_num
FROM vendors a
WHERE EXISTS(
        SELECT *
        FROM products
        WHERE a.vend_id = vend_id
        GROUP BY vend_id
        HAVING COUNT(prod_id) >= 2
        );

/*4. �ֹ���ǰ �߿� ��ǰ���� �׸񰡰��� ���� ���� ��ǰ��ȣ�� �����Ͻÿ�
���: ��ǰ��ȣ, �׸񰡰�(��ǰ�� ���� ���� �׸񰡰�)*/
SELECT prod_id, MIN(item_price)
FROM orderitems
GROUP BY prod_id;


/*����: ���̺� ���� ������ �÷����� ������Ѽ� ���յ� ������ �����ϴ� ���
- ������ ���� �� ���� ������ ��������*/
--��ǰ�� ���� ���޾�ü ������ �Բ� ����
SELECT vend_name, prod_name, prod_price
FROM vendors, products
WHERE vendors.vend_id = products.vend_id;

--���� �ÿ� ���������� ���̺��� ���踦 �������� ������ cartesian product
SELECT vend_name, prod_name, prod_price
FROM vendors, products;

/*ANSI SQL - ���� ����*/
SELECT vend_name, prod_name, prod_price
FROM vendors INNER JOIN products
    ON vendors.vend_id = products.vend_id;
    
/*���� ���̺� ����*/
--��ǰ�� ���� ���޾�ü ������ �ֹ��������� ����
SELECT vend_name, prod_name, prod_price, quantity
FROM orderitems, vendors, products
WHERE products.vend_id = vendors.vend_id
AND orderitems.prod_id = products.prod_id
AND order_num = 20007;

--��ǰ��ȣ�� RGAN01�� �ֹ����̸��� ����ó ����
SELECT cust_name, cust_contact
FROM customers, orders, orderitems
WHERE customers.cust_id = orders.cust_id
AND orderitems.order_num = orders.order_num
AND prod_id = 'RGAN01';

/*���̺� ��Ī���*/
--��ǰ��ȣ�� RGAN01�� �ֹ����̸��� ����ó ����
SELECT cust_name, cust_contact
FROM customers c, orders o, orderitems oi
WHERE c.cust_id = o.cust_id
AND oi.order_num = o.order_num
AND prod_id = 'RGAN01';

/*���� ���̺� ����*/
--Jim Jones��� ����� ���ϴ� ȸ���� ��� �� ������� �����ּ� ����
SELECT cust_id, cust_name, cust_contact
FROM customers
WHERE cust_name = (SELECT cust_name
                    FROM customers
                    WHERE cust_contact = 'Jim Jones');
                    
--���� ������ �������� ����
SELECT c1.cust_id, c1.cust_name, c1.cust_contact
FROM customers c1, customers c2
WHERE c1.cust_name = c2.cust_name
AND c2.cust_contact = 'Jim Jones';

/*���� �� ��ü �÷� ��ȸ ó��*/
SELECT c.*, o.order_num, o.order_date, oi.prod_id
FROM customers c, orders o, orderitems oi
WHERE c.cust_id = o.cust_id
AND oi.order_num = o.order_num
AND prod_id = 'RGAN01';

/*1. �ֹ��� �������� �����Ͽ� ������ ����� �����Ͻÿ�
���: �ֹ���ȣ, �ֹ�����, ����ȣ, ���̸�, ���ּ�, ����, �������ּ�
����: ������ ������ �ֹ������� �ֹ�*/
SELECT o.order_num
    , TO_CHAR(o.order_date, 'yyyy-mm-dd') lastest_order_date
    , c.cust_id, trim(c.cust_name)
    , trim(c.cust_address)
    , trim(c.cust_city)
    , trim(c.cust_email)
FROM orders o, customers c
WHERE o.cust_id = c.cust_id
AND o.order_date IN (SELECT MAX(order_date)
                    FROM orders o2
                    WHERE cust_id = c.cust_id
                    GROUP BY cust_id
                    );
                    
SELECT a.order_num, a.order_date, a.cust_id, b.cust_name, b.cust_city, b.cust_email
FROM   orders a, customers b
WHERE  a.cust_id = b.cust_id
AND    a.order_date = (
                      SELECT MAX(order_date)
                      FROM   orders
                      WHERE  cust_id = b.cust_id
                     );

                    
SELECT MAX(o.order_date)
FROM   orders o, customers b
WHERE  o.cust_id = b.cust_id;

select * from orders;
SELECT TO_CHAR(MAX(order_date), 'yyyy-mm-dd')
FROM orders o2, customers c
WHERE o2.cust_id = c.cust_id
GROUP BY o2.cust_id;


