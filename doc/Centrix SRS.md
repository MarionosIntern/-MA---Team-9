# Software Requirements Specification
## For <project name>

Version 0.1  
Prepared by <author>  
<organization>  
<date created> 

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
    * 3.2.6 [Cost](#326-cost)
    * 3.2.7 [Deadline](#327-deadline)

## Revision History
| Name | Date    | Reason For Changes  | Version   |
| ---- | ------- | ------------------- | --------- |
|      |         |                     |           |
|Mariono| 9/17   | Updated Srs         |    1.1    |
|      |         |                     |           |

## 1. Introduction

### 1.1 Document Purpose
The purpose of this Sofware Requirements Document (SRD) is to provide a techincal description of the client-view and developer-view requirements for the Centrix Marketplace application.
Client-view requirements provide a throrough description of the system from the client's perspective. The client side provides a unique perspective of what is required from the system. 
Developer-view requirments provide a thorough description of the system from the software developer's perspective. Functionality, data, and performance are all included in these requirements.

# 1.2 Product Scope
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
Section 1 is a general overview of our web application . It's an introdution, intended for readers interetsed in the system. In section 2, it contains information about the product and it's features. Providing insight for our customers and buisnesses. In section 3, the documant displays our system constraints and requirements throughout the development process.

# 2. Product Overview
Centrix Market is a web based platform to give consumers freedom with their choices and money in a simple way. Buyers can browse our product list, interact with their favorite sellers through subscriptions, leave reviews on products, and track their own shipment. Providers upload products, manage their relationship with the buyers, view buyer statistics, and track shipments. The system allows for freedom and transperency across all roles,
 for an opn marketplace.
# 2.1 Product Functions
-Centrix Market allows the providers to upload and manage their product listing. Along with interacting with their buyer to conncet them and track their shipment to ensure a smooth delivery. 
-Centric Market allows the customers to browse and purchase anything on our product listing. They can subscribe to buyers to interact and keep up with their listings. Along with tracking their shipments, so they are carefree and know where thier item is at. 

### 2.2 Product Constraints
The program will currently run only on a computer with Java jdk 21 installed. Since this is for a class project, there is a deadline of about 10 weeks, which will inhibit the application's full potential. 
  
### 2.3 User Characteristics
This application doesn't require or expect any expertise from the buyers or sellers, in reagrd to computer and software engineering. As long as the user can navigate a web browser, they are more than qualified to use our application. 

### 2.4 Assumptions and Dependencies
We wil be using Java for our backend and html for our frontend. Our program will be dependent on PureCss and Bootstrap, along with RestAPI to communicate with external API's. Our program will be developed in VS Code. 

## 3. Requirements

### 3.1 Functional Requirements 
* FR8: The system will allow the buyer to upload products
    * Their product listing will go through a verification process.

* FR9: The system will allow the buyer to actively reply to buyer reviews

* FR10: The system will allow the buyer to access customer statistics

* FR11: The system will allow the buyer to track the buye's shipment to ensure a successful delivery

#### 3.1.1 User interfaces
Define the software components for which a user interface is needed. Describe the logical characteristics of each interface between the software product and the users. This may include sample screen images, any GUI standards or product family style guides that are to be followed, screen layout constraints, standard buttons and functions (e.g., help) that will appear on every screen, keyboard shortcuts, error message display standards, and so on. Details of the user interface design should be documented in a separate user interface specification.

Could be further divided into Usability and Convenience requirements.

#### 3.1.2 Hardware interfaces
Describe the logical and physical characteristics of each interface between the software product and the hardware components of the system. This may include the supported device types, the nature of the data and control interactions between the software and the hardware, and communication protocols to be used.

#### 3.1.3 Software interfaces
Describe the connections between this product and other specific software components (name and version), including databases, operating systems, tools, libraries, and integrated commercial components. Identify the data items or messages coming into the system and going out and describe the purpose of each. Describe the services needed and the nature of communications. Refer to documents that describe detailed application programming interface protocols. Identify data that will be shared across software components. If the data sharing mechanism must be implemented in a specific way (for example, use of a global data area in a multitasking operating system), specify this as an implementation constraint.

### 3.2 Non Functional Requirements 

#### 3.2.1 Performance
If there are performance requirements for the product under various circumstances, state them here and explain their rationale, to help the developers understand the intent and make suitable design choices. Specify the timing relationships for real time systems. Make such requirements as specific as possible. You may need to state performance requirements for individual functional requirements or features.

#### 3.2.2 Security
Specify any requirements regarding security or privacy issues surrounding use of the product or protection of the data used or created by the product. Define any user identity authentication requirements. Refer to any external policies or regulations containing security issues that affect the product. Define any security or privacy certifications that must be satisfied.

#### 3.2.3 Reliability
Specify the factors required to establish the required reliability of the software system at time of delivery.

#### 3.2.4 Availability
Specify the factors required to guarantee a defined availability level for the entire system such as checkpoint, recovery, and restart.

#### 3.2.5 Compliance
Specify the requirements derived from existing standards or regulations

#### 3.2.6 Cost
Specify monetary cost of the software product.

#### 3.2.7 Deadline
Specify schedule for delivery of the software product.
