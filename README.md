# Springboot Zara Demo

## Overview
The following spring boot application contains rest end point to get pricing of product based on product-id, brand-id and a date

## Guidelines
Run the example of connecting to OpenFin and creating applications

1. Start the application

2. Data is auto initiated and inserted into H2 db on application start-up

3. Hit the rest api '/pricing' with HTTP GET method. passing params : product-id, brand-id and a date (date in ISO format YYYY-MM-DDTHH:mm:SS)

4. Internal logic is to get the matching record from db.

```java
   List<Pricing> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate );
```

5. If output from db is multiple records there we compare the priority. And the product with highest priority (least number) will be selected.

6. Priority 0 - highest , 5 - lowest.

7. Hence the final output of api is pricing based on all above conditions

## Test scenarios

1. Test case for file PricingController is written in PricingControllerTest class where we have covered below scenrios

a. request at 10:00 on day 14 of product 35455 for brand.
b. request at 4:00 p.m. on day 14 of product 35455 for brand 1.
c. request at 9:00 p.m. on day 14 of product 35455 for brand 1.
d. request at 10:00 on day 15 of product 35455 for brand 1.
e. request at 9:00 p.m. on the 16th of product 35455 for brand 1.

2. Test case for repository class is written in PricingRepositoryTest class. Where we are inserting data in h2 db and retrieving it using our query.


