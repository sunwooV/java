/*1. 상품주문에서 주문번호, 제품번호, 제품제조국가위치를 추출하시오
    - 제품제조국가위치: 제품번호의 마지막 2번째 1자리의 값이 0이면 ‘국내’,0이아니면 ‘해외’
    - 총가격(항목수량*항목가격)이 500$이상인 주문 */
SELECT order_num, prod_id, DECODE(SUBSTR(trim(prod_id), -2, 1), '0', '국내', '해외')
FROM orderitems
WHERE quantity * item_price >= 500;

/*2. 주문에서 주문번호와 주문가능날자(YYYY-MM-DD)를 추출하시오
    - 주문가능날자: 일자의 10자리가 0이면 1일,1이면 10일, 20이면 20일,30이면 30일 */
SELECT order_num
    , order_date
    , DECODE(SUBSTR(TO_CHAR(order_date, 'yyyy-mm-dd'), -2, 1), '0', '01', '1', '10', '2', '20', '30') passive_order_date
FROM orders;
    
/*3. 제품이름과 제품설명에 ‘king’이라는 단어가 들어가고 제품가격의 소수점 1자리가 9가 아닌 제품번호를 추출하시오 */
SELECT prod_id
    , trim(prod_name) prod_name
    , trim(prod_desc) prod_desc
FROM products
WHERE LOWER(prod_name) LIKE '%king%'
    AND LOWER(prod_desc) LIKE '%king%'
    AND SUBSTR(TRUNC(prod_price, 1), -1) <> 9;
--      SUBSTR(TRUNC(prod_price,1), LENGTH(TRUNC(prod_price,1)), 1) <> '9'