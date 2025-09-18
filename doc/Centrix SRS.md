# Software Requirements Specification
## For Centrix

Version 0.1  
Prepared by Aliyah Williams & Mariano Deleva
Centrix 
September 1

Table of Contents
=================
* [Revision History](#revision-history)
* 1 [Introduction](#1-introduction)
  * 1.1 [Document Purpose](#11-document-purpose)
  * 1.2 [Product Scope](#12-product-scope)
  * 1.3 [Definitions, Acronyms and Abbreviations](#13-definitions-acronyms-and-abbreviations)
  * 1.4 [References](#14-references)
  * 1.5 [Document Overview](#15-document-overview)
* 2 [Product Overview](#2-product-overview)
  * 2.1 [Product Functions](#21-product-functions)
  * 2.2 [Product Constraints](#22-product-constraints)
  * 2.3 [User Characteristics](#23-user-characteristics)
  * 2.4 [Assumptions and Dependencies](#24-assumptions-and-dependencies)
* 3 [Requirements](#3-requirements)
  * 3.1 [Functional Requirements](#31-functional-requirements)
    * 3.1.1 [User Interfaces](#311-user-interfaces)
    * 3.1.2 [Hardware Interfaces](#312-hardware-interfaces)
    * 3.1.3 [Software Interfaces](#313-software-interfaces)
  * 3.2 [Non-Functional Requirements](#32-non-functional-requirements)
    * 3.2.1 [Performance](#321-performance)
    * 3.2.2 [Security](#322-security)
    * 3.2.3 [Reliability](#323-reliability)
    * 3.2.4 [Availability](#324-availability)
    * 3.2.5 [Compliance](#325-compliance)
    * 3.2.6 [Cost][def]
    * 3.2.7 [Deadline](#327-deadline)

## Revision History
| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
|Aliyah   9/16   |  Initial SRS        |    1.0    |
|Mariono 9/17   | Updated Srs         |    1.1    |



## 1. Introduction


### 1.1 Document Purpose
The purpose of this Sofware Requirements Document (SRS) is to provide a techincal description of the client-view and developer-view requirements for the Centrix Marketplace application.
Client-view requirements provide a throrough description of the system from the client's perspective. The client side provides a unique perspective of what is required from the system. 
Developer-view requirments provide a thorough description of the system from the software developer's perspective. Functionality, data, and performance are all included in these requirements.

### 1.2 Product Scope
The purpose of the Centrix Marketplace system is to allow for consumers to connect with smaller buisnesses or sell products of thier own; to give sellers and buyers an outlet for their shopping. It serves as an easy to use, simple, and effecient application for anyone to try. It's web based, so any fancy tech is not required which allows for simple use of service to house sucbriptions and communication between sellers and buyers. 

### 1.3 Definitions, Acronyms and Abbreviations  
Java - An object oriented proggramming language for the backend of our Centrix Marketplace system.

HTML - Hypertext Markup Langauge. A markup language that is used to design and create the web application and it's content.

CSS - A cascading style sheet, used for the styling and appearence of the web application. It's used in tandem with html.


JavcaScript -  An object oriented pragramming language used for interactive elements within web applications. Will be used alongside css and html. 
 
API - Application Programming Interface. It will be used to interface the backend and frontend of our application.

VS Code - An intergrated development environment (IDE) for java and html. This is where our system will be created.
                                                                    
### 1.4 References

https://pure-css.github.io/

### 1.5 Document Overview
Section 1 is a general overview of the Document. Section 2 is focused on the products that would be sold. Section 3 focuses on the development proccess and how the application is intended to work.


## 2. Product Overview
Centrix is a marketplace that allows multiple different products to be sold from clothes to shoes to technology. Customers can browse different providers and their products, in which they can also leave reviews and interact with the same providers.The system supports multiple users at once so there is a free flowing community between the providers, customers, and the adminstrators.

### 2.1 Product Functions
 Centrix allows different providers/companies to upload their products and even give out promotions to their subscribers. Subscribing allows customers access to different sales and they hve better acces to provider as well.

### 2.2 Product Constraints
The program will only run on a computer with Java jdk 21 installed. The full scope of the project is hopefully realized however the team has a deadline of  10 weeks that could lead to feature cuts. The program would have a challenge scaling, as the current plan is to use a free version of a Postgresql database to store the information.
  
### 2.3 User Characteristics
Users dont have to have any major web knowlegde. The only thing they have to do it log on and browse their wanted items.

### 2.4 Assumptions and Dependencies
We will be using Java, with our program being dependent on Spring & SpringBoot, and RestAPI to connect to external APIs and developed with VS Code. The application will also use an external  APIs that will help customers with comparing products in their cart as well as keeping track of what is the trending items.
## 3. Requirements

### 3.1 Functional Requirements 

* FR0: the system will allow people to create an account and choose to be a provider or a customer 
      * Every account will have a different identifier assigned to every account.
* FR1: The system will allow the customer to subscribe/favorite different products and providers.
      * A customer may unsubscribe at any time if they are not longer interested in the products or provider.
* FR2: The system shall allow customers to browse through the list of available products.
     * The list ofproduct shall have a search and filter option.
* FR4: Users will be able to modify their own profiles at any time.
* FR5: The system shall allow customers to rate and review the providers and products based on delivery and quality.
* FR6: The system will allow customers to track all their own shipmemnts.
* FR7: The system will allow the customer to add and delete item to their shopping cart.
      * While in the afrt customers can compare items by pricing and item details.
* FR8: The system shall allow providers to upload and remove products at any time.
* FR9 : The system shall allow providers to communicate with customers at any time.
* FR10: The system shall allow the providers to track deliveries and provide updates to the customers.
* FR11: The system shall allow the provider to view reviews left by the customers. 

#### 3.1.1 User interfaces
Web pages using HTML, CSS, and JavaScript.

#### 3.1.2 Hardware interfaces
Devices that have web browser capabilities.

#### 3.1.3 Software interfaces
* Java jdk 21
* PostgreSQL 17
* SpringBoot 3.4.5

### 3.2 Non Functional Requirements 

#### 3.2.1 Performance
* NFR0: The Centrix system will consume less than 100 MB of memory
* NFR1: The novice user will be able to add and manage provider subscriptions in less than 5 minutes.
* NFR2: The expert user will be able to add and manage provider subscriptions in less than 1 minute.

#### 3.2.2 Security
* NFR3:The system will require a username and password to access a specific account.

#### 3.2.3 Reliability
* NFR4: The system will go through vairous stages of testing to help insure that the it runs as smoothly as possible. 
     *  This also means regular updates and status checks in the scheduled downtime.

#### 3.2.4 Availability
* NFR5: The sytem will be available 24/7 to all. It will have regular updates/checkups around 2 am to insure minimal conflict with users.
#### 3.2.5 Compliance
* NFR6: The Centrix software will ensure that all private data and information from both providers and customers will be safe and secure.
* NFR7: Centrix follows all needed regulations and makes sure that the software used is licensed and authorized.

#### 3.2.6 Cost
* NFR8: We expect to spend zero dollars on this project.

#### 3.2.7 Deadline
* NFR9: The final product must be delivered by December 2025.

