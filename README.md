# order-management-system
Order Management System using two microservices

## Implemented an order management system that has two microservices:
* Order Service
  * The Order Service is responsible for creating new orders, retrieving existing order info.
* Order Item Service.
  * Order Item Service is responsible for creating order items, retrieving order items.
  
## Architecture Includes:
  * Feign client for inter-service communication
  * Used H2 for in-memory data store
  * Implemented Global Exception Handlers for graceful handling or exceptions
  * Both services have input validations in place
  * Both services have REST endpoints exposed 
  * Added Lombok for reduced BPC and increased readability
  
## Both services have swagger ui and h2 console is exposed.
Service | Swagger URL | H2 URL
------------ | ------------- | -------------
Order Service | http://localhost:8082/swagger-ui.html | http://localhost:8082/h2-console
Order Item Service | http://localhost:8080/swagger-ui.html | http://localhost:8080/h2-console

### use jdbc url: jdbc:h2:mem:testdb for connecting to H2 and username is 'sa', no password needed
  
## Order Item:
  * Product Code
  * Product Name
  * Quantity

## Order:
  * Customer Name
  * Order Date (yyyy-MM-dd) - cannot be in past
  * Shipping Address
  * Order Items
  * Total ($)
  
## Sample Request:
{
  "customerName": "Hari",
  "orderDate": "2020-12-31",
  "orderItems": [
    {
      "productCode": "001",
      "productName": "Laptop",
      "quantity": 2
    }
  ],
  "shippingAddress": "14, OMR, Chennai - 96",
  "total": 100
}
  
