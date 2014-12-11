-- ORDER LIST
SELECT  O.ID,
		O.ORDER_NUMBER ORDER_NUMBER, 
		T.TABLE_NUMBER TABLE_NUMBER,
        C.NAME CUSTOMER_NAME,
        O.STARTED,
        O.ENDED,
        O.NO_OF_GUEST,
		O.TOTAL,
        O.TIP,
        (O.DISCOUNT*O.TOTAL)/100 DISCOUNT,
        O.PAID_AMOUNT
FROM ORDERS O
	LEFT OUTER JOIN TABLES T 
		ON T.ID = O.TABLE_NUMBER
	LEFT OUTER JOIN CUSTOMERS C
		ON C.ID = O.CUSTOMER_ID
	LEFT OUTER JOIN RESTAURANTS R
		ON R.ID = O.REST_ID
	WHERE O.ACTIVE = 0
    AND O.ENDED BETWEEN '2014-01-01' AND '2014-12-12'
    AND R.ID = 1

-- Order Detail List
SELECT 
		M.NAME, 
		OD.QUANTITY, 
		OD.KITCHEN_NOTE, 
		OD.PRICE, 
		OD.VOID, 
        OD.VOID_REASON 
	FROM ORDER_DETAILS OD
	LEFT OUTER JOIN MENU M 
		ON M.ID = OD.MENU_ID
WHERE ORDER_ID = 1


-- Void Items
SELECT 
		M.NAME, 
		OD.VOID_REASON, 
		O.ORDER_NUMBER, 
		O.STARTED, 
        O.ENDED
	FROM ORDER_DETAILS OD
	LEFT OUTER JOIN ORDERS O
		ON O.ID = OD.ORDER_ID
	LEFT OUTER JOIN MENU M 
		ON M.ID = OD.MENU_ID
WHERE OD.VOID = 1
	AND O.ACTIVE = 0
    AND O.ENDED BETWEEN '2014-01-01' AND '2014-12-12'
    AND O.REST_ID = 2
    
    
    