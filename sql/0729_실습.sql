/*1. ������ ����ȣ, ���̸�, ���ּ�, �ֹ��� �� �ݾ�,  �ֹ��� ��ǰ�߿� �����ѱݾ��� ��ǰ���� �����Ͻÿ�
    - �ֹ��� �� �ݾ�: �ֹ���ǰ���� �ֹ��� ��ǰ�� �׸񰡰��� SUM
    - �ֹ��� ��ǰ�߿� ������ �ݾ��� ��ǰ��: �ֹ���ǰ���� �ش���� �ֹ��� ��ǰ�� ������ ��ǰ�� ��ǰ��*/
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

/*2. ��ǰ���ֹ��� ���߿� ������, ���ֺ� �ֹ����� �����Ͻÿ�
    - ���: ������,����, �ֹ���*/
SELECT TRIM(cust_country) cust_country
    , TRIM(cust_state) cust_state
    , (
        SELECT COUNT(*)
        FROM orders o
        WHERE c.cust_id = o.cust_id
        ) order_num
FROM customers c;


/*3. �ֹ���ǰ���� ���ֺ� ������ ��ǰ�� �� �������� �����Ͻÿ�
   - ���: ����, ��� ��ǰ�� ����*/
SELECT c.cust_state
    , MAX(oi.item_price)
FROM orderitems oi, orders o, customers c
WHERE oi.order_num = o.order_num
AND o.cust_id = c.cust_id
GROUP BY c.cust_state;


