/*1. ��ǰ�ֹ����� �ֹ���ȣ, ��ǰ��ȣ, ��ǰ����������ġ�� �����Ͻÿ�
    - ��ǰ����������ġ: ��ǰ��ȣ�� ������ 2��° 1�ڸ��� ���� 0�̸� ��������,0�̾ƴϸ� ���ؿܡ�
    - �Ѱ���(�׸����*�׸񰡰�)�� 500$�̻��� �ֹ� */
SELECT order_num, prod_id, DECODE(SUBSTR(trim(prod_id), -2, 1), '0', '����', '�ؿ�')
FROM orderitems
WHERE quantity * item_price >= 500;

/*2. �ֹ����� �ֹ���ȣ�� �ֹ����ɳ���(YYYY-MM-DD)�� �����Ͻÿ�
    - �ֹ����ɳ���: ������ 10�ڸ��� 0�̸� 1��,1�̸� 10��, 20�̸� 20��,30�̸� 30�� */
SELECT order_num
    , order_date
    , DECODE(SUBSTR(TO_CHAR(order_date, 'yyyy-mm-dd'), -2, 1), '0', '01', '1', '10', '2', '20', '30') passive_order_date
FROM orders;
    
/*3. ��ǰ�̸��� ��ǰ���� ��king���̶�� �ܾ ���� ��ǰ������ �Ҽ��� 1�ڸ��� 9�� �ƴ� ��ǰ��ȣ�� �����Ͻÿ� */
SELECT prod_id
    , trim(prod_name) prod_name
    , trim(prod_desc) prod_desc
FROM products
WHERE LOWER(prod_name) LIKE '%king%'
    AND LOWER(prod_desc) LIKE '%king%'
    AND SUBSTR(TRUNC(prod_price, 1), -1) <> 9;
--      SUBSTR(TRUNC(prod_price,1), LENGTH(TRUNC(prod_price,1)), 1) <> '9'