# Simple Order Service

## Overview

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

## Implementation:

* Language: Java.
* Database: AWS RDS MySQL used (Or use you own)
* Framework: Spring Framework

## Endpoints Availables: 

1.	Create inventory item
  POST http://localhost:3000/inventories
2.	Read all inventory items
  GET http://localhost:3000/inventories
3.	Read single inventory item
  GET http://localhost:3000/inventories/1
4.	Update inventory item
  PUT http://localhost:3000/inventories/1
5.	Delete inventory item
  DELETE http://localhost:3000/inventories/1
6.	Create order
  POST http://localhost:3000/orders
7.	Read all orders
  GET http://localhost:3000/orders
8.	Read single order
  GET http://localhost:3000/orders/1
9.	Update order
  PUT http://localhost:3000/orders/1
10.	Delete order
  DELETE http://localhost:3000/orders/1
11. Creat Customer
  POST http://localhost:3000/customers
  
## Example JSON Objects:

1.	Create inventory item
  POST 
 ` {
    "name":"RV",
    "description":"Newmar",
    "price":"1000",
    "quantity":"10"	
  }`
6.	Create order
  POST 
 ` {
    "customer":3,
    "date":"2020-09-08",
    "status":"COMFIRMED",
    "items":[2,10,8]	
  }`
11. Creat Customer
  POST
 ` {
    "name":"janie doe",
    "email":"missjanie@company.com"
  }`
  
## Instalation:

### You need Maven to run this project. 
As it is a Spring Boot Aplication, clone the repo, open a command prompt from the directory with pom.xml and run this code:   `mvn spring-boot:run`
