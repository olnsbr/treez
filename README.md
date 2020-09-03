Simple Order Service

Overview

Treez is building a service that allows customers to place orders via a REST API. We would like to be able to model the current inventory, deducting from inventory as orders are created, and adding inventory back if orders are canceled.

Inventory records should include the following attributes:

* Name
* Description
* Price
* Quantity available

Order records should include the following attributes:

* Customer email address
* Date order placed
* Order status
* List of products selected from Inventory

The service include the following:

1.	Support CRUD operations for inventory and orders
a.	When creating, updating, or canceling an order, inventory should be adjusted accordingly.
b.	When a request to create an order is made, it will include a list of inventories to include in the order
c.	If inventory levels are insufficient, a request to create an order should be denied.

The implementation:

* Language: Java.
* Database: AWS RDS MySQL used (Personal RDS Endpoind is intentionally exposed in this project for evaluation) 
* Framework: Spring Framework

Endpoints Availables: 

1.	Create inventory item
a.	POST http://localhost:3000/inventories
2.	Read all inventory items
a.	GET http://localhost:3000/inventories
3.	Read single inventory item
a.	GET http://localhost:3000/inventories/1
4.	Update inventory item
a.	PUT http://localhost:3000/inventories/1
5.	Delete inventory item
a.	DELETE http://localhost:3000/inventories/1
6.	Create order
a.	POST http://localhost:3000/orders
7.	Read all orders
a.	GET http://localhost:3000/orders
8.	Read single order
a.	GET http://localhost:3000/orders/1
9.	Update order
a.	PUT http://localhost:3000/orders/1
10.	Delete order
a.	DELETE http://localhost:3000/orders/1
11. Creat Customer
a.  POST http://localhost:3000/customers
