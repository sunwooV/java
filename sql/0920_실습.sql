/*
1. ������ ���ǿ� ���� ��ǰ�� ��ǰ��ȣ, ��ǰ�̸�, ��ǰ���ݼ���, ��ǰ������ �����Ͻÿ�
  - ��ǰ���ݼ���: ��ǰ������ 3$���̸� �����硯 11$���̸� ����ӡ��������� �����롯
  - ����: ���޾�ü�� �����ȣ ���� 2�ڸ��� ��44��,��45��, ��99�� �� ���޾�ü���� ����ų�
              �ֹ��� ���ڰ� 5���� �ƴ� ��ǰ
*/
SELECT k.prod_id, k.prod_name, k.prod_desc
     , DECODE(TRUNC(k.prod_price), '3', '����', '11', '���', '����') price_level
FROM vendors v
    , (SELECT p.prod_id, p.prod_name, p.prod_desc, p.vend_id, p.prod_price
      FROM products p, orderitems oi
      WHERE p.prod_id = oi.prod_id
      AND oi.order_num IN (SELECT order_num
                           FROM orders
                           WHERE TO_CHAR(order_date, 'MM') <> '05'
                           )
    )k
WHERE k.vend_id = v.vend_id
AND SUBSTR(v.vend_zip,0,2) IN ('44','45','99');


/*
2. �ֹ���ǰ�߿� ��ǰ�� ��ǰ���ݺ��� �ֹ���ǰ�� �׸񰡰��� ��� �׸񰡰��� ��ǰ����ǰ�������� �����Ͻÿ�
*/
UPDATE orderitems oi
SET oi.item_price = (SELECT prod_price
                     FROM products
                     WHERE oi.prod_id = prod_id
                     )
WHERE oi.prod_id IN (SELECT prod_id
                     FROM products
                     WHERE oi.prod_id = prod_id
                     AND oi.item_price > prod_price);



/*
3. ������ ����ȣ, ���̸�, ���ּ�, �ֹ��� �� �ݾ�, �ֹ�����ǰ�߿� �����ѱݾ��� ��ǰ���� �����Ͻÿ�
  - �ֹ��� �� �ݾ�: �ش���� �ֹ���ǰ���� �ֹ��� ��ǰ�� �׸񰡰��� SUM
  - �ֹ�����ǰ�߿� ������ �ݾ��� ��ǰ��: �ֹ���ǰ���� �ش���� �ֹ��� ��ǰ�� ������ ��ǰ�� ��ǰ��
*/
SELECT c.cust_id, c.cust_name, c.cust_address, k.sum_price, k.max_price
FROM customers c
     , (SELECT o.cust_id, SUM(oi.item_price) sum_price, MAX(oi.item_price) max_price
        FROM orderitems oi, orders o
        WHERE o.cust_id IN (SELECT cust_id
                            FROM customers
                            WHERE cust_id = o.cust_id
                            )
        GROUP BY o.cust_id)k
WHERE k.cust_id = c.cust_id
ORDER BY c.cust_id;


/*
4. ��ǰ�̸��� ��ǰ���� ��king���̶�� �ܾ ���� ��ǰ������ �Ҽ��� 1�ڸ��� 9�� �ƴ� ��ǰ��ȣ�� �����Ͻÿ�
*/
SELECT prod_id
FROM products
WHERE prod_name LIKE ('%King%')
AND prod_desc LIKE ('%king%')
AND SUBSTR(TRUNC(prod_price, 1),-1) <> '9';


/*
5. �ֹ����� �ֹ���ȣ�� �ֹ����ɳ���(YYYY-MM-DD)�� �����Ͻÿ�
  �ֹ����ɳ���: ������ 10�ڸ��� 0�̸� 1��,1�̸� 10��, 20�̸� 20��,30�̸� 30��
*/
SELECT order_num
    , (CASE WHEN SUBSTR(TO_CHAR(order_date), -2, 1) = '0'
                 THEN TO_CHAR(order_date, 'yyyy-mm-')||'01'
            WHEN SUBSTR(TO_CHAR(order_date), -2, 1) = '1'
                 THEN TO_CHAR(order_date, 'yyyy-mm-')||'10'
            WHEN SUBSTR(TO_CHAR(order_date), -2, 1) = '2'
                 THEN TO_CHAR(order_date, 'yyyy-mm-')||'20'
            WHEN SUBSTR(TO_CHAR(order_date), -2, 1) = '3'
                 THEN TO_CHAR(order_date, 'yyyy-mm-')||'30'
        END) possible_date
FROM orders;

--�����
   SELECT order_num,
          TO_CHAR(
              CASE WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '0' THEN
                             TO_DATE(TO_CHAR(order_date,'YYYYMM')||'01')
                   WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '1' THEN
                             TO_DATE(TO_CHAR(order_date,'YYYYMM')||'10')
                   WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '2' THEN
                             TO_DATE(TO_CHAR(order_date,'YYYYMM')||'20')
                   WHEN SUBSTR(TO_CHAR(order_date,'DD'),1,1) = '3' THEN
                             TO_DATE(TO_CHAR(order_date,'YYYYMM')||'30')
              END, 'YYYY-MM-DD') orderCanDt     
   FROM   orders
   ;
