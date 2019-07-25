/*���������� ���̸��� ��The���� ���۵ǰ� �������� ��USA���� ���� �ּ������� ������ Format���� �����Ͻÿ�    
   (������� ������ �ʰ� ó���Ѵ�)    
   Format = ���ּҡ� ������/���� �����֡� ���������ȣ��(����������)��*/
SELECT trim(cust_address) cust_address
    , trim(cust_city) cust_city
    , trim(cust_state) cust_state
    , trim(cust_zip) || '(' || trim(cust_country) || ')' zip_country
FROM customers
WHERE cust_name LIKE 'The%'
AND cust_country = 'USA';


/*�ֹ���ǰ�׸񿡼� �ֹ���ȣ�� ��20005��, ��20007�� �� �ֹ��� ���� �ֹ���ȣ, ��ǰ��ȣ�� �ֹ��ѱݾ�(�׸����*�׸񰡰�) �� 
   �����Ͻÿ�*/
SELECT order_num, prod_id, quantity * item_price total
FROM orderitems
WHERE order_num IN ('20005', '20007');

   

/*��ǰ�������� ���޾�ü��ȣ�� ��BRS01��, ��DLL01���� �ƴ� ��ǰ�̸��� ��ǰ������ ������ Format���� �����Ͻÿ�
   Format = ��ǰ�̸���[����ǰ���ݡ�]��*/
SELECT trim(prod_name) || '[' || prod_price || ']' name_price
FROM products
WHERE vend_id NOT IN ('BRS01', 'DLL01');
