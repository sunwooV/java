--���޾�ü�̸��� ����
SELECT vend_id, LENGTH(TRIM(vend_name)) name_length
FROM vendors;

--���޾�ü �ּҸ� �ҹ��� or �빮�ڷ�
SELECT vend_id, TRIM(LOWER(vend_address)) address_lower, TRIM(UPPER(vend_address)) address_upper
FROM vendors;

--���� �ڸ���                           1��°~3��°              4��°~
SELECT vend_id, SUBSTR(vend_address, 1, 3), SUBSTR(vend_address, 4)
FROM vendors;

--���� ��ü�ϱ�                  Street -> Road
SELECT vend_id, REPLACE(vend_address, 'Street', 'Road')
FROM vendors;

--NULL�� �ƴ� ù��° ��
SELECT vend_id, vend_state, vend_city, COALESCE(vend_state, vend_city)
FROM vendors
WHERE vend_id = 'FNG01';

--���޾�ü�ְ� CA�̸� 1, NY�̸� 2 ��� �ƴϸ� 3
SELECT vend_id, vend_state, DECODE(TRIM(vend_state), 'CA', 1, 'NY', 2, 3)
FROM vendors;

--NULL�̸� �ٸ� ������
SELECT nvl(cust_email, 'test@naver.com')
FROM customers;

/*���� �ּ� �߿� �ҹ��ڷ� ��south���� �� �������� ������ Format���� �����Ͻÿ�(��������)
    - Format: ���̸���/�����ּ�, �ҹ��ڷ�*/
SELECT trim(cust_name) || '/' || trim(cust_address) name_address
FROM customers
WHERE LOWER(cust_address) LIKE '%south%';
    
/*�ֹ���ǰ�� �ֹ���ȣ�� ��20005��, ��20007���� �ƴ� ��ǰ�����ڵ带 �����Ͻÿ�
    - ��ǰ�����ڵ�: ��ǰ��ȣ�� ���� 2�ڸ�*/
SELECT SUBSTR(order_num, 1, 2) prod_code
FROM orderitems
WHERE order_num IN ('20005', '20007');

/*���� ���̸��� ���ּ������� �����Ͻÿ�
    - ���ּ�����: ���ּ��߿� Drive�� Car�� ����*/
SELECT cust_name, REPLACE(cust_address, 'Drive', 'Car')
FROM customers;
    
/*���޾�ü�� ���޾�ü��ȣ�� ��br���� ���۵Ǵ� ���޾�ü�̸��� ���޾�ü������ȣ�� ���޾�ü �̸��� ������������ �����Ѵ�
    - ���޾�ü������ȣ: �����ȣ�� �տ� 2�ڸ�*/
SELECT trim(vend_name), SUBSTR(vend_zip, 1, 2) vend_num
FROM vendors
ORDER BY vend_name DESC;
    
/*���޾�ü�� ���޾�ü��ȣ�� ��BRE02���� �ƴ� ���޾�ü�̸��� ���޾�ü���������ȣ�� �����Ѵ�
    - ���޾�ü���������ȣ: ���޾�ü������ȣ�� 44�̸� A, 99�̸� B, 11�̸� C �������� D*/
SELECT trim(vend_name), DECODE(SUBSTR(vend_zip, 1, 2), 44, 'A', 99, 'B', 11, 'C', 'D') place
FROM vendors
WHERE vend_id = 'BRE02';
    
/*���� ����� ���ּ������� �����Ѵ�
    - ���ּ�����: �������ּ� or ���ּ�, �������ּҰ� ������ �������ּҰ� �켱*/
SELECT trim(cust_name), trim(nvl(cust_email, cust_address)) email_or_address
FROM customers;
    
/*��ǰ�� ��ǰ�� ��bear���� ����ִ� ��� ��ǰ�� ���� ��ǰ�̸��� �����ϴµ� bear�� toy�� �����Ͽ� �����Ѵ�*/
SELECT trim(REPLACE(prod_name, 'bear', 'toy'))
FROM products
WHERE prod_name LIKE '%bear%';
