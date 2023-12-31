# Springboot Zara Demo

## Overview
The following spring boot repository contains 2 solutions with different strategies zarademo and zarademoavanzado

## zarademo
Run the example of connecting to OpenFin and creating applications

1. Start the application

2. Data is auto initiated and inserted into H2 db on application start-up

3. Hit the rest api '/pricing' with HTTP POST method. passing params : product-id, brand-id and a date (date in ISO format YYYY-MM-DDTHH:mm:SS)

4. Internal logic is to get the matching record from db.

```java
   List<Pricing> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long productId, Long brandId, LocalDateTime startDate, LocalDateTime endDate );
```

5. If output from db is multiple records there we compare the priority. And the product with highest priority (least number) will be selected.

6. Priority 0 - highest , 5 - lowest.

7. Hence the final output of api is pricing based on all above conditions

## example calls
Find Pricing [GET]
```
http://localhost:8080/pricing?applicationDate=2020-06-14 00.00.00&productId=35455&brandId=1
```
Get all Pricing [GET]
```
http://localhost:8080/pricing
```
## Test scenarios

1. Test case for file PricingController is written in PricingControllerTest class where we have covered below scenrios

a. request at 10:00 on day 14 of product 35455 for brand.
b. request at 4:00 p.m. on day 14 of product 35455 for brand 1.
c. request at 9:00 p.m. on day 14 of product 35455 for brand 1.
d. request at 10:00 on day 15 of product 35455 for brand 1.
e. request at 9:00 p.m. on the 16th of product 35455 for brand 1.

2. Test case for repository class is written in PricingRepositoryTest class. Where we are inserting data in h2 db and retrieving it using our query.


## zarademoavanzado
Run the example of connecting to OpenFin and creating applications

1. Start the application

2. Data is auto initiated and inserted into H2 db on application start-up

3. Hit the rest api '/pricing' with HTTP POST method. passing params : product-id, brand-id and a date (date in ISO format YYYY-MM-DDTHH:mm:SS)

4. Internal logic is to get the matching record from db.

```java
   public List<Pricing> getPriceListBaseOnDate(Long productId, Long brandId, LocalDateTime inputDate);
```

5. If output from db is multiple records there we compare the priority. And the product with highest priority (least number) will be selected.

6. Priority 0 - highest , 5 - lowest.

7. Hence the final output of api is pricing based on all above conditions
8. Desing of the objects Brand, Product, Pricing by JPA
9. Posibility to create Brand, Product, Pring
   
10. Posibility to update Brand, Product, Pring
11. Posibility to delete Brand, Product, Pring
12. Handeled exceptions

## start the app by postman
This methods shoul be call by POST
[Create Brand]
```
http://localhost:8080/brands
```
[Headers]
```
Content-Type : application/json
```
[Json :body:raw]
```
{
  
  "name": "Zara"
}
```
[Create product]
```
http://localhost:8080/product
```
[Headers]
```
Content-Type : application/json
```
[Json :body:raw]
```
{
  
  "name": "Belt"
}
```

[Create Pricing]
```
http://localhost:8080/pricing
```
[Headers]
```
Content-Type : application/json
```
[Json :body:raw]
```
{
  "startDate": "2023-07-01T10:00:00",
  "endDate": "2023-07-31T23:59:59",
  "priceList": "Summer Sale Man",
  "priority": 1,
  "price": 17.99,
  "currency": "USD",
  "brand": {
    "id": 2,
    "name": "Zara"
  },
  "products": [
    {
      "id": 2,
      "name": "belt"
    }
  ]
}
```
## Mandatory functios
Get all Pricing [GET]
```
http://localhost:8080/pricing
```
Find pricing [POST]
```
http://localhost:8080/pricing/list?productId=1&brandId=1&applicationDate=2023-07-14T10:00:00
```
## Extra functios
## Get
Get all Products [GET]
```
http://localhost:8080/products
```
Get all Brand [GET]
```
http://localhost:8080/brands
```

## Get by ID
Get Product by id [GET]
```
http://localhost:8080/products/1
```
Get Brand by id [GET]
```
http://localhost:8080/brands/1
```
Get Pricing by id [GET]
```
http://localhost:8080/pricing/1
```

## update by ID
This methods shoul be call by PUT
Update Brand [PUT]
```
http://localhost:8080/brands/1
```
[Headers]
```
Content-Type : application/json
```
[Update :body:raw]
```
{
  
  "name": "Zara"
}
```
Update product [PUT]
```
http://localhost:8080/products
```
[Headers]
```
Content-Type : application/json
```
[Json :body:raw]
```
{
  
  "name": "Belt"
}
```

Update Pricing [PUT]
```
http://localhost:8080/pricing/1
```
[Headers]
```
Content-Type : application/json
```
[Json :body:raw]
```
{
  "startDate": "2023-07-01T10:00:00",
  "endDate": "2023-07-31T23:59:59",
  "priceList": "Summer Sale Man",
  "priority": 1,
  "price": 17.99,
  "currency": "USD",
  "brand": {
    "id": 2,
    "name": "Zara"
  },
  "products": [
    {
      "id": 2,
      "name": "belt"
    }
  ]
}
```

## Delete by ID
Delete  Product [DELETE]
```
http://localhost:8080/products/1
```
Delete all Brand [DELETE]
```
http://localhost:8080/brands/1
```
Delete all Pricing [DELETE]
```
http://localhost:8080/pricing/1
```

## Test scenarios

1. Test case for file PricingController is written in PricingControllerTest class where we have covered below scenrios

a. request at 10:00 on day 14 of product 35455 for brand.
b. request at 4:00 p.m. on day 14 of product 35455 for brand 1.
c. request at 9:00 p.m. on day 14 of product 35455 for brand 1.
d. request at 10:00 on day 15 of product 35455 for brand 1.
e. request at 9:00 p.m. on the 16th of product 35455 for brand 1.

2. Test case for repository class is written in PricingRepositoryTest class. Where we are inserting data in h2 db and retrieving it using our query.

3. Tests cases for the get, delete, update, create operations


