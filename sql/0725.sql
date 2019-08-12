--공급업체이름의 길이
SELECT vend_id, LENGTH(TRIM(vend_name)) name_length
FROM vendors;

--공급업체 주소를 소문자 or 대문자로
SELECT vend_id, TRIM(LOWER(vend_address)) address_lower, TRIM(UPPER(vend_address)) address_upper
FROM vendors;

--문자 자르기                           1번째~3번째              4번째~
SELECT vend_id, SUBSTR(vend_address, 1, 3), SUBSTR(vend_address, 4)
FROM vendors;

--문자 대체하기                  Street -> Road
SELECT vend_id, REPLACE(vend_address, 'Street', 'Road')
FROM vendors;

--NULL이 아닌 첫번째 값
SELECT vend_id, vend_state, vend_city, COALESCE(vend_state, vend_city)
FROM vendors
WHERE vend_id = 'FNG01';

--공급업체주가 CA이면 1, NY이면 2 모두 아니면 3
SELECT vend_id, vend_state, DECODE(TRIM(vend_state), 'CA', 1, 'NY', 2, 3)
FROM vendors;

--NULL이면 다른 값으로
SELECT nvl(cust_email, 'test@naver.com')
FROM customers;

/*고객의 주소 중에 소문자로 ‘south’가 들어간 고객정보를 다음의 Format으로 추출하시오(공백제거)
    - Format: 고객이름’/’고객주소, 소문자로*/
SELECT trim(cust_name) || '/' || trim(cust_address) name_address
FROM customers
WHERE LOWER(cust_address) LIKE '%south%';
    
/*주문상품의 주문번호가 ‘20005’, ‘20007’가 아닌 제품유형코드를 추출하시오
    - 제품유형코드: 제품번호의 앞의 2자리*/
SELECT SUBSTR(order_num, 1, 2) prod_code
FROM orderitems
WHERE order_num IN ('20005', '20007');

/*고객의 고객이름과 고객주소정보를 추출하시오
    - 고객주소정보: 고객주소중에 Drive는 Car로 변경*/
SELECT cust_name, REPLACE(cust_address, 'Drive', 'Car')
FROM customers;
    
/*공급업체의 공급업체번호가 ‘br’로 시작되는 공급업체이름과 공급업체지역번호를 공급업체 이름을 내림차순으로 추출한다
    - 공급업체지역번호: 우편번호의 앞에 2자리*/
SELECT trim(vend_name), SUBSTR(vend_zip, 1, 2) vend_num
FROM vendors
ORDER BY vend_name DESC;
    
/*공급업체의 공급업체번호가 ‘BRE02’이 아닌 공급업체이름과 공급업체사업구역번호를 추출한다
    - 공급업체사업구역번호: 공급업체지역번호가 44이면 A, 99이면 B, 11이면 C 나머지는 D*/
SELECT trim(vend_name), DECODE(SUBSTR(vend_zip, 1, 2), 44, 'A', 99, 'B', 11, 'C', 'D') place
FROM vendors
WHERE vend_id = 'BRE02';
    
/*고객의 고객명과 고객주소정보를 추출한다
    - 고객주소정보: 고객메일주소 or 고객주소, 고객메일주소가 있으면 고객메일주소가 우선*/
SELECT trim(cust_name), trim(nvl(cust_email, cust_address)) email_or_address
FROM customers;
    
/*제품의 제품명에 ‘bear’이 들어있는 모든 제품에 대해 제품이름을 추출하는데 bear를 toy로 변경하여 추출한다*/
SELECT trim(REPLACE(prod_name, 'bear', 'toy'))
FROM products
WHERE prod_name LIKE '%bear%';
