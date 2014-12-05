-- -- TODAYS METRICS

	-- Number of Completed Transactions for Today 
    -- @param : Rest_ID ; ID of the restaurant
		SELECT IFNULL(COUNT(ID),0) FROM ORDERS WHERE DATE(ENDED) = DATE(SYSDATE()) AND REST_ID = 1;

	-- Total Sales for today
	-- @param : Rest_ID ; ID of the restaurant
		SELECT IFNULL(SUM(TOTAL),0) FROM ORDERS WHERE DATE(ENDED) = DATE(SYSDATE()) AND REST_ID =1;

	-- Percentage increase from Yesterday	
	-- @param : Rest_ID ; ID of the restaurant
    -- Percentage equation is simplied (A-B)/B*100 = (A/B - 1)*100
		SELECT
			IFNULL((((
				(SELECT IFNULL(SUM(TOTAL),0) FROM ORDERS WHERE DATE(ENDED) = DATE(SYSDATE()) AND REST_ID =1)
				/ 
				(SELECT IFNULL(SUM(TOTAL),0) FROM ORDERS WHERE DATE(ENDED) = DATE(SUBDATE(SYSDATE(),1)) AND REST_ID =1)
			) -1 ) *100 ) ,0)
			AS PERCENTAGE
		FROM DUAL 

-- -- TO DATE's METRICS

	-- Number of Transactions till this year
	-- @param : Rest_ID ; ID of the restaurant
		SELECT IFNULL(COUNT(ID),0) FROM ORDERS WHERE YEAR(ENDED) = YEAR(SYSDATE()) AND REST_ID =1;

	-- Total Sales this year
	-- @param : Rest_ID ; ID of the restaurant
		SELECT IFNULL(SUM(TOTAL),0) FROM ORDERS WHERE YEAR(ENDED) = YEAR(SYSDATE()) AND REST_ID =1;

	-- Percentage increase from last week 
	-- @param : Rest_ID ; ID of the restaurant
    -- Percentage equation is simplied (A-B)/B*100 = (A/B - 1)*100
        SELECT
			IFNULL((((
				(SELECT IFNULL(SUM(TOTAL),0) FROM ORDERS WHERE YEAR(ENDED) = YEAR(SYSDATE()) AND REST_ID = 1)
				/ 
				(SELECT IFNULL(SUM(TOTAL),0) FROM ORDERS WHERE (YEAR(ENDED) = YEAR(SYSDATE())) 
														AND (DATE(ENDED) < DATE(SUBDATE(SYSDATE(),7))) 
														AND REST_ID = 1)
			) -1 ) *100 ) ,0)
			AS PERCENTAGE
		FROM DUAL 

	-- Percentage increase from 6 months ago or beginning of this year (whichever is greater)
	-- @param : Rest_ID ; ID of the restaurant
    -- Percentage equation is simplied (A-B)/B*100 = (A/B - 1)*100
		SELECT
			IFNULL((((
				(SELECT IFNULL(SUM(TOTAL),0) FROM ORDERS WHERE YEAR(ENDED) = YEAR(SYSDATE()) AND REST_ID = 1)
				/ 
				(SELECT IFNULL(SUM(TOTAL),0) FROM ORDERS WHERE (YEAR(ENDED) = YEAR(SYSDATE())) AND (
						DATE(ENDED) < GREATEST(DATE_FORMAT(NOW() ,'%Y-01-01'), DATE_SUB(SYSDATE(), INTERVAL 6 MONTH))
                        ) AND
                        REST_ID = 1)
			) -1 ) *100 ) ,0)
			AS PERCENTAGE
		FROM DUAL 


-- -- CUSTOMER METRICS

	-- Total number of customers in the last 30 days
    -- @param : Rest_ID ; ID of the restaurant
		SELECT COUNT(C.ID) FROM 
        CUSTOMERS C
			INNER JOIN ORDERS O ON O.CUSTOMER_ID = C.ID
            WHERE O.REST_ID = 1
				AND O.ENDED > SUBDATE(SYSDATE(), 30)
     
	