---------------------------------------------
-- Sams Teach Yourself SQL in 10 Minutes
-- http://www.forta.com/books/0672325675/
-- Example table creation scripts for Oracle.
---------------------------------------------


-------------------------
-- Create Customers table
-------------------------
CREATE TABLE Customers
(
  cust_id      char(10)  NOT NULL ,
  cust_name    char(50)  NOT NULL ,
  cust_address char(50)  NULL ,
  cust_city    char(50)  NULL ,
  cust_state   char(5)   NULL ,
  cust_zip     char(10)  NULL ,
  cust_country char(50)  NULL ,
  cust_contact char(50)  NULL ,
  cust_email   char(255) NULL 
);

--------------------------
-- Create OrderItems table
--------------------------
CREATE TABLE OrderItems
(
  order_num  int          NOT NULL ,
  order_item int          NOT NULL ,
  prod_id    char(10)     NOT NULL ,
  quantity   int          NOT NULL ,
  item_price decimal(8,2) NOT NULL 
);

----------------------
-- Create Orders table
----------------------
CREATE TABLE Orders
(
  order_num  int      NOT NULL ,
  order_date date     NOT NULL ,
  cust_id    char(10) NOT NULL 
);

------------------------
-- Create Products table
------------------------
CREATE TABLE Products
(
  prod_id    char(10)      NOT NULL ,
  vend_id    char(10)      NOT NULL ,
  prod_name  char(255)     NOT NULL ,
  prod_price decimal(8,2)  NOT NULL ,
  prod_desc  varchar(1000) NULL 
);

-----------------------
-- Create Vendors table
-----------------------
CREATE TABLE Vendors
(
  vend_id      char(10) NOT NULL ,
  vend_name    char(50) NOT NULL ,
  vend_address char(50) NULL ,
  vend_city    char(50) NULL ,
  vend_state   char(5)  NULL ,
  vend_zip     char(10) NULL ,
  vend_country char(50) NULL 
);

----------------------
-- Define primary keys
----------------------
ALTER TABLE Customers ADD CONSTRAINT PK_Customers PRIMARY KEY (cust_id);
ALTER TABLE OrderItems ADD CONSTRAINT PK_OrderItems PRIMARY KEY (order_num, order_item);
ALTER TABLE Orders ADD CONSTRAINT PK_Orders PRIMARY KEY (order_num);
ALTER TABLE Products ADD CONSTRAINT PK_Products PRIMARY KEY (prod_id);
ALTER TABLE Vendors ADD CONSTRAINT PK_Vendors PRIMARY KEY (vend_id);

----------------------
-- Define foreign keys
----------------------
ALTER TABLE OrderItems
ADD CONSTRAINT FK_OrderItems_Orders FOREIGN KEY (order_num) REFERENCES Orders (order_num);
ALTER TABLE OrderItems
ADD CONSTRAINT FK_OrderItems_Products FOREIGN KEY (prod_id) REFERENCES Products (prod_id);
ALTER TABLE Orders
ADD CONSTRAINT FK_Orders_Customers FOREIGN KEY (cust_id) REFERENCES Customers (cust_id);
ALTER TABLE Products
ADD CONSTRAINT FK_Products_Vendors FOREIGN KEY (vend_id) REFERENCES Vendors (vend_id);


-----------------------------------------------
-- Sams Teach Yourself SQL in 10 Minutes
-- http://www.forta.com/books/0672325675/
-- Example table population scripts for Oracle.
-----------------------------------------------


---------------------------
-- Populate Customers table
---------------------------
INSERT INTO Customers(cust_id, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country, cust_contact, cust_email)
VALUES('1000000001', 'Village Toys', '200 Maple Lane', 'Detroit', 'MI', '44444', 'USA', 'John Smith', 'sales@villagetoys.com');
INSERT INTO Customers(cust_id, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country, cust_contact)
VALUES('1000000002', 'Kids Place', '333 South Lake Drive', 'Columbus', 'OH', '43333', 'USA', 'Michelle Green');
INSERT INTO Customers(cust_id, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country, cust_contact, cust_email)
VALUES('1000000003', 'Fun4All', '1 Sunny Place', 'Muncie', 'IN', '42222', 'USA', 'Jim Jones', 'jjones@fun4all.com');
INSERT INTO Customers(cust_id, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country, cust_contact, cust_email)
VALUES('1000000004', 'Fun4All', '829 Riverside Drive', 'Phoenix', 'AZ', '88888', 'USA', 'Denise L. Stephens', 'dstephens@fun4all.com');
INSERT INTO Customers(cust_id, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country, cust_contact)
VALUES('1000000005', 'The Toy Store', '4545 53rd Street', 'Chicago', 'IL', '54545', 'USA', 'Kim Howard');

-------------------------
-- Populate Vendors table
-------------------------
INSERT INTO Vendors(vend_id, vend_name, vend_address, vend_city, vend_state, vend_zip, vend_country)
VALUES('BRS01','Bears R Us','123 Main Street','Bear Town','MI','44444', 'USA');
INSERT INTO Vendors(vend_id, vend_name, vend_address, vend_city, vend_state, vend_zip, vend_country)
VALUES('BRE02','Bear Emporium','500 Park Street','Anytown','OH','44333', 'USA');
INSERT INTO Vendors(vend_id, vend_name, vend_address, vend_city, vend_state, vend_zip, vend_country)
VALUES('DLL01','Doll House Inc.','555 High Street','Dollsville','CA','99999', 'USA');
INSERT INTO Vendors(vend_id, vend_name, vend_address, vend_city, vend_state, vend_zip, vend_country)
VALUES('FRB01','Furball Inc.','1000 5th Avenue','New York','NY','11111', 'USA');
INSERT INTO Vendors(vend_id, vend_name, vend_address, vend_city, vend_state, vend_zip, vend_country)
VALUES('FNG01','Fun and Games','42 Galaxy Road','London', NULL,'N16 6PS', 'England');
INSERT INTO Vendors(vend_id, vend_name, vend_address, vend_city, vend_state, vend_zip, vend_country)
VALUES('JTS01','Jouets et ours','1 Rue Amusement','Paris', NULL,'45678', 'France');

--------------------------
-- Populate Products table
--------------------------
INSERT INTO Products(prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('BR01', 'BRS01', '8 inch teddy bear', 5.99, '8 inch teddy bear, comes with cap and jacket');
INSERT INTO Products(prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('BR02', 'BRS01', '12 inch teddy bear', 8.99, '12 inch teddy bear, comes with cap and jacket');
INSERT INTO Products(prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('BR03', 'BRS01', '18 inch teddy bear', 11.99, '18 inch teddy bear, comes with cap and jacket');
INSERT INTO Products(prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('BNBG01', 'DLL01', 'Fish bean bag toy', 3.49, 'Fish bean bag toy, complete with bean bag worms with which to feed it');
INSERT INTO Products(prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('BNBG02', 'DLL01', 'Bird bean bag toy', 3.49, 'Bird bean bag toy, eggs are not included');
INSERT INTO Products(prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('BNBG03', 'DLL01', 'Rabbit bean bag toy', 3.49, 'Rabbit bean bag toy, comes with bean bag carrots');
INSERT INTO Products(prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('RGAN01', 'DLL01', 'Raggedy Ann', 4.99, '18 inch Raggedy Ann doll');
INSERT INTO Products(prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('RYL01', 'FNG01', 'King doll', 9.49, '12 inch king doll with royal garments and crown');
INSERT INTO Products(prod_id, vend_id, prod_name, prod_price, prod_desc)
VALUES('RYL02', 'FNG01', 'Queen doll', 9.49, '12 inch queen doll with royal garments and crown');

------------------------
-- Populate Orders table
------------------------
INSERT INTO Orders(order_num, order_date, cust_id)
VALUES(20005, TO_DATE('2004-05-01', 'yyyy-mm-dd'), '1000000001');
INSERT INTO Orders(order_num, order_date, cust_id)
VALUES(20006, TO_DATE('2004-01-12', 'yyyy-mm-dd'), '1000000003');
INSERT INTO Orders(order_num, order_date, cust_id)
VALUES(20007, TO_DATE('2004-01-30', 'yyyy-mm-dd'), '1000000004');
INSERT INTO Orders(order_num, order_date, cust_id)
VALUES(20008, TO_DATE('2004-02-03', 'yyyy-mm-dd'), '1000000005');
INSERT INTO Orders(order_num, order_date, cust_id)
VALUES(20009, TO_DATE('2004-02-08', 'yyyy-mm-dd'), '1000000001');

----------------------------
-- Populate OrderItems table
----------------------------
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20005, 1, 'BR01', 100, 5.49);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20005, 2, 'BR03', 100, 10.99);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20006, 1, 'BR01', 20, 5.99);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20006, 2, 'BR02', 10, 8.99);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20006, 3, 'BR03', 10, 11.99);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20007, 1, 'BR03', 50, 11.49);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20007, 2, 'BNBG01', 100, 2.99);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20007, 3, 'BNBG02', 100, 2.99);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20007, 4, 'BNBG03', 100, 2.99);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20007, 5, 'RGAN01', 50, 4.49);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20008, 1, 'RGAN01', 5, 4.99);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20008, 2, 'BR03', 5, 11.99);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20008, 3, 'BNBG01', 10, 3.49);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20008, 4, 'BNBG02', 10, 3.49);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20008, 5, 'BNBG03', 10, 3.49);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20009, 1, 'BNBG01', 250, 2.49);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20009, 2, 'BNBG02', 250, 2.49);
INSERT INTO OrderItems(order_num, order_item, prod_id, quantity, item_price)
VALUES(20009, 3, 'BNBG03', 250, 2.49);

SELECT prod_name FROM Products;
SELECT prod_id,prod_name,prod_price FROM Products;
SELECT * FROM Products;

SELECT vend_name, vend_address, vend_city, vend_state FROM Vendors;

SELECT prod_id, prod_name, prod_price FROM Products;

SELECT cust_name, cust_address, cust_zip, cust_country FROM customers;

SELECT order_num, order_date,cust_id FROM orders;

SELECT prod_name
FROM products
ORDER BY prod_name;

SELECT prod_id, prod_name, prod_price
FROM products
ORDER BY prod_id, prod_name, prod_price;

SELECT prod_id, prod_name, prod_price
FROM products
ORDER BY 2, 3;

SELECT prod_id, prod_name, prod_price
FROM products
ORDER BY prod_price desc;

SELECT prod_id, prod_name, prod_price
FROM products
ORDER BY prod_price DESC, prod_name;

/*���޾�ü(VENDORS)�� �̸�(VEND_NAME), �ּ�(VEND_ADDRESS), ����(VEND_COUNTRY)�� �����ϰ�
�̸��� ��������, ������ ������������ �����Ͻÿ�*/
SELECT vend_name, vend_address, vend_country
FROM vendors
ORDER BY vend_name ASC, vend_country DESC;

/*��ǰ(PRODUCTS)�� ����ID(PROD_ID), ���޾�üID(VEND_ID), ��ǰ�̸�(PROD_NAME),
��ǰ����(PROD_PRICE)�� �����ϰ�, ��ǰ���� ������������ �����Ͻÿ�*/
SELECT prod_id, vend_id, prod_name, prod_price
FROM products
ORDER BY prod_price DESC;

/*��(CUSTOMERS)�� �̸�(CUST_NAME), �ּ�(CUST_ADDRESS), ����(CUST_COUNTRY)�� �����ϰ�
�̸��� ������������ �����Ͻÿ�*/
SELECT cust_name, cust_address, cust_country
FROM customers
ORDER BY cust_name DESC;

/*�ֹ�(ORDERS)�� ������ȣ(ORDER_NUM), �ֹ�����(ORDERR_DATE), �ֹ��� ���� ID(CUST_ID)��
�����ϰ� �ֹ����ڸ� ��������, ��ID�� ������������ �����Ͻÿ�*/
SELECT order_num, order_date, cust_id
FROM orders
ORDER BY order_date DESC, cust_id ASC;

SELECT prod_name
FROM products
WHERE prod_price = 3.49;

SELECT prod_name, prod_price
FROM products
WHERE prod_price < 10;

SELECT vend_id, prod_name
FROM products
WHERE vend_id<> 'DLL01'; -- !=

SELECT prod_name, prod_price
FROM products
WHERE prod_price BETWEEN 5 AND 10;

SELECT prod_name
FROM products
WHERE prod_price IS NULL;

/*��(CUSTOMERS) �� �����ּ�(CUST_EMAIL)�� ���� ����(CUST_NAME)�� 
���� ������������ ����*/
SELECT cust_name
FROM customers
WHERE cust_email IS NULL
ORDER BY cust_name DESC;

/*��(CUSTOMERS) �� ��� �ִ� ����(CUST_CITY)�� ��Chicago�� �� 
����(CUST_NAME),�ּ�(CUST_ADDRESS) ����*/
SELECT cust_name, cust_address
FROM customers
WHERE cust_city = 'Chicago';

/*��ǰ(PRODUCTS)�� ����(PROD_PRICE)�� 5$���� ū ��ǰID(PROD_ID)�� ��ǰ��(PROD_NAME)�� �����
������������ ����*/
SELECT prod_id, prod_name
FROM products
WHERE prod_price > 5
ORDER BY prod_name ASC;

/*���޾�ü(VENDORS)�� ����(VEND_COUNTRY)�� France�� ��ü��(VEND_NAME)�� ��ü�ּ�(VEND_ADDRESS)��
��ü�� ������������ ����*/
SELECT vend_country, vend_name, vend_address
FROM vendors
WHERE vend_country = 'France'
ORDER BY vend_name DESC;

/*�ֹ���ǰ(ORDERITEMS)�߿� ����(ITEM_PRICE)�� 10$���� ���� �ֹ���ȣ(ORDER_NUM)�� ��ǰ��ȣ(PROD_ID)��
����(ITEM_PRICE)�� ���� ������������ ����*/
SELECT order_num, prod_id, item_price
FROM orderitems
ORDER BY item_price ASC;

--DLL01�̶�� ������ü���� ����� ������ 4�������� ��� ��ǰ
SELECT prod_id, prod_price, prod_name
FROM products
WHERE vend_id = 'DLL01'
AND prod_price <= 4;

--DLL01�̳� RRS01�̶�� ������ü���� ���� ��� ��ǰ
SELECT prod_name, prod_price
FROM products
WHERE (vend_id = 'DLL01') OR (vend_id = 'BRS01');

--DDL01�̳� BRS01 ������ü���� ���� ��ǰ �� ������ 10���̻��� ��ǰ�� �˻�
SELECT prod_name, prod_price, vend_id
FROM products
WHERE (vend_id <> 'DLL01' OR vend_id = 'BRS01')
AND prod_price >= 10;

/*�� �߿� ��������̸��� ��Kim Howard�� �̸鼭 �̸��� �ּҰ� ���� ���̸� ����*/
SELECT cust_name
FROM customers
WHERE cust_contact = 'Kim Howard'
AND cust_email IS NULL;

/*���޾�ü ��DLL01���� ��ǰ�߿� ��ǰ������ 5$ ������ ��ǰ�̸��� ��ǰ�̸� ������������ ����*/
SELECT prod_name
FROM products
WHERE vend_id = 'DLL01'
AND prod_price <= 5
ORDER BY prod_name;

/*���޾�ü�� �ִ� ���ð� ��New York���� ���޾�ü�̸��� ���޾�ü�ּ�, ���޾�ü�����ȣ�� 
���޾�ü�̸� ������������ ����*/
SELECT vend_name, vend_address, vend_zip
FROM vendors
WHERE vend_city = 'New York'
ORDER BY vend_name DESC;

/*�ֹ���ǰ �߿� ��ǰ��ȣ�� ��1000000001�� �̰ų� ��1000000004�� �� �ֹ���ȣ�� ����*/
SELECT order_num
FROM orderitems
WHERE (prod_id = 'BNBG01' OR prod_id = 'BR03');

select * from orderitems;

/*�ֹ���ǰ �߿� �׸񰡰��� 5$�̻��̰� 10$�̸��� �ֹ���ȣ�� ������������ ����*/
SELECT order_num
FROM orderitems
WHERE item_price BETWEEN 5 AND 10
ORDER BY order_num ASC;

/*��ǰ �߿� ���޾�ü�� ��DLL01���� �ƴ� ��ǰ�̸��� ������������ ����*/
SELECT prod_name
FROM products
WHERE vend_id != 'DLL01'
ORDER BY prod_name DESC;

--������ü�� DLL01�� BRS01�� ��� ��ǰ�� �����´�
SELECT prod_name, prod_price
FROM products
WHERE vend_id IN ('DLL01', 'BRS01')
ORDER BY prod_name;

--������ü�� DLL01�� BRS01�� �ƴ� ��� ��ǰ�� �����´�
SELECT prod_name, prod_price
FROM products
WHERE vend_id NOT IN ('DLL01', 'BRS01')
ORDER BY prod_name;

/*���̸��� ��Kids Place���ų� ��Village Toys���� ���߿� 
���� ��� ���ð� ��Detroit���� �ƴ� ���̸� ����*/
SELECT cust_name
FROM customers
WHERE cust_name IN ('Kids Place', 'Village Toys')
And cust_city NOT IN ('Detroit');

/*���޾�ü�̸��� ��Bear Emporium�� �ų� ��Jouets et ours���� ���޾�ü �߿� 
���޾�ü �������� ���� ���޾�ü�̸��� ������������ ����*/
SELECT vend_name
FROM vendors
WHERE vend_name IN ('Bear Emporium', 'Jouets et ours')
AND vend_state IS NULL
ORDER BY vend_name DESC;

/*�ֹ���ǰ �߿� ��ǰ��ȣ�� ��BR03�� �ų� ��BNBG01���̰� 
�׸������ 100�� �̻��� �ֹ���ȣ�� ������������ ����*/
SELECT order_num
FROM orderitems
WHERE prod_id IN ('BR03', 'BNBG01')
AND quantity >= 100
ORDER BY 1 ASC;

--Fish��� �ܾ�� �����ϴ� ��� ��ǰ�� �˻�
SELECT prod_name, prod_price
FROM products
WHERE prod_name LIKE 'Fish%';

--bean bag��� ������ �� ��� ��ǰ�� �˻�
SELECT prod_name, prod_price
FROM products
WHERE prod_name LIKE '%bean bag%';

--F�� �����ϰ� y�� ������ ��� ��ǰ�� �˻�
SELECT prod_name, prod_price
FROM products
WHERE prod_name LIKE 'F%y%';
--char�� Ư�� : ���� ���� ���� ������ ä���� F%y�� �ϸ� �ƹ��͵� ��� x

--_ �� ���ϳ��� ���ڿ� ���� �ƹ����ڳ� ����ϴ� �˻�
SELECT prod_id, prod_name
FROM products
WHERE prod_name LIKE '__inch teddy bear%';

/*���̸��� ��Fun���� ���۵ǰ� ��l���� ���۵Ǵ� ���ÿ� ��� 
���̸��� ���ּҸ� ���̸��� ������������ ����*/
SELECT cust_name, cust_address
FROM customers
WHERE cust_name LIKE 'Fun%l%'
ORDER BY cust_name DESC;

/*��ǰ��ȣ�� ��B���� ���۵ǰ� ��1���� ������ ��ǰ�̸��� 
���޾�üID �׸��� ��ǰ������ ��ǰ���� ������������ ����*/
SELECT prod_name, vend_id, prod_price
FROM products
WHERE trim(prod_id) LIKE 'B%1'
ORDER BY prod_price ASC;

/*���޾�üID�� ��D���� ���۵ǰ� ���޾�ü �õ� ��D���� �����ϴ� ���޾�ü��� ���޾�ü�ּҸ�
���޾�ü�� ������������ ����*/
SELECT vend_name, vend_address
FROM vendors
WHERE vend_id LIKE 'D%'
AND vend_city LIKE 'D%'
ORDER BY vend_name ASC;

/*�ֹ���ǰ �߿� ��ǰ��ȣID�� ��BG���� ���Եǰ� �ֹ������� 100���� �ֹ���ȣ�� �ֹ��ܰ��� 
�ֹ��ܰ� ������������ ����*/
SELECT order_num, item_price
FROM orderitems
WHERE prod_id LIKE '%BG%'
AND quantity >= 100
ORDER BY item_price ASC;

--����ʵ� �����

--�ʵ��� ����
SELECT vend_name || '(' || vend_country || ')'
FROM vendors
ORDER BY vend_name;

--���� ����
SELECT vend_name || '(' || RTRIM(vend_country) || ')'
FROM vendors
ORDER BY vend_name;

--��Ī ���
SELECT vend_name || '(' || RTRIM(vend_country) || ')' vend_title
FROM vendors
ORDER BY vend_name;

--������ ��� : +, -, *, /
SELECT prod_id, quantity, item_price, quantity * item_price expanded_price
FROM orderitems
WHERE order_num = 20008;