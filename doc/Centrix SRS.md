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
|Aliyah   9/16 |   |   Initial SRS        1.0 |           |
|      |         |                     |           |
|      |         |                     |           |

## 1. Introduction

### 1.1 Document Purpose

### 1.2 Product Scope


### 1.3 Definitions, Acronyms and Abbreviations                                                                                                                                                                          |

### 1.4 References


### 1.5 Document Overview


## 2. Product Overview


### 2.1 Product Functions
 

### 2.2 Product Constraints

  
### 2.3 User Characteristics


### 2.4 Assumptions and Dependencies

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

