insert into brand (name) values ('nike');
insert into product (name) values ('shoes');
INSERT INTO pricing (brand_id, start_date, end_date, price_list, priority, price, currency)
VALUES (1, '2023-07-01T10:00:00', '2023-07-31T23:59:59', 'Summer Sale', 1, 25.99, 'USD');
insert into PRODUCT_PRICING (product_id,pricing_id) values(1,1)