# order-management-system
Order Management System using two microservices

Implemented an order management system that has two microservices:
* Order Service
  * The Order Service is responsible for creating new orders, retrieving existing order info.
* Order Item Service.
  * Order Item Service is responsible for creating order items, retrieving order items.
  
Order Item:
  * Product Code
  * Product Name
  * Quantity

Order:
  * Customer Name
  * Order Date (yyyy-MM-dd)
  * Shipping Address
  * Order Items
  * Total ($)
  
Architecture Includes:
  * Feign client for inter-service communication
  * Used H2 for in-memory data store
  * Implemented Global Exception Handlers for graceful handling or exceptions
  * Both services have input validations in place
  * Both services have REST endpoints exposed 
  
