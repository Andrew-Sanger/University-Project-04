# University-Project-04
## University project 4 - Programming 2 - Library Management System

The first project in the Programming 2 course. This Java project required the use of a lot more complicated ideas such as UML, objects, abstraction, interfaces, facades etc.

***NOTE: Some of the code containted within this assignment such as the test harness was written by the Professor, and is labelled as such at the top of the code. Anything else is my own creation.***

## Background information (from the course notes)

The objective of this assignment is to introduce you to real-life development practices, and also extend your understanding of core OO design and development processes utilising basic UML and Java programming language. As such, the emphasis is on adhering to a specification, which was designed to test various aspects of object-orientation, rather than developing a complete commercial grade product with realistic business rules.

Specifically, your task is to implement a simple “Library Management System (LMS)”, detailed below, according to a class diagram that will be outlined during our first online chat session. A final class diagram will be made available on Blackboard for your reference. You will be able to use this diagram to whatever extent you consider appropriate. Additionally, your solution will need to adhere to a purposively built test harness (available on Blackboard).

The system will consist of a collection of interacting classes/interfaces to store information about: 

1. The **library** and its **library collection**
2. The **holdings** contained in the library collection
3. The **member** who can borrow/return available holdings
4. The **borrowing history** of the library member.

The system will also include a unified “front end”, through the provision of an LMSModel façade interface (provided on Blackboard) that will offer a single point of entry for all high-level operations. This front end will support the testing of your program, independently of a graphical user interface, using the test harness. The façade is one of the fundamental OO design patterns which is intended to hide the system implementation specifics from outside clients. You can find a brief description of this pattern at http://en.wikipedia.org/wiki/Facade_pattern.

## 1. LMS Scenario

The system will need to be modelled using a number of OO classes/interfaces. The main entities involved in the system, and their behaviours, are detailed below.

### ***Library***
The *library* should capture information about the library collection and the library member. Note that a typical library will contain multiple collections and members, but for simplicity reasons the decision was made to restrict the library to one collection and one member only. 

### ***LibraryCollection***
The *library collection* has a collection code (e.g. “LIB_IT”), a name (e.g. “Information Technology”), and a collection of holdings. The library collection is dynamic and unbounded i.e. you can add any number of holdings to a library collection. 

### ***Holding***
Each *holding* has:
- a seven-digit numeric code (e.g. 0000001);
- a title (e.g. “Programming 2”);
- a predefined standard loan fee ($ value);
- a maximum loan period (defined in terms of the number of days);
- a late penalty fee ($ value) calculated on a per late day basis.

Also, the system needs to cater for two distinct types of Holdings, *Books* and *Videos*:
- Books have a fixed (i.e. constant) standard loan fee of $10, and a fixed maximum loan period of 28 days. 
- Videos have a variable standard loan fee of either $4 or $6, and a fixed maximum loan period of 7 days. 
- The formula for calculating late penalty fee is different for both Holding types as follows:
  - Books: late fee = number of late days x fixed daily rate of $2. E.g. if a given book was returned 3 days late, the late fee will be $6 (3 days x $2).
  - Videos: late fee = number of late days x 50% of the standard loan fee. E.g. if a given video with a standard loan fee of $6 was returned 3 days late, the late fee will be $9 (3 days x 50% of $6).

### ***Member***
The *member* has:
- a member ID (e.g. “m00001”)
- a full name (e.g. “Joe Bloggs”)
- a maximum/initial borrowing credit ($ value), which is predefined according to the specific membership type, see below
- a collection of currently borrowed holdings
- a borrowing history comprised of the borrowing records.

Also, the system needs to cater for two distinct types of Members, *Standard* and *Premium*:
- *Standard members* have a fixed maximum (or initial) borrowing credit of $30, whereas *Premium members* have a fixed maximum borrowing credit of $45. The holding’s standard loan fee should be deducted from member’s borrowing credit when he/she borrows this holding.
- Also, the holding return procedure will differ based on a specific member type as described in Section 3 below.

### ***Borrowing History***
Each borrowing record in the borrowing history should capture information about a previously returned holding and a corresponding fee payed for this holding (including late fees if applicable).

## 2. Base Functionality
At a minimum, the LMS should provide the following functionality (exposed via LMSModel façade interface available on Blackboard):

- Create a new library, library collection, and library member
- Add/delete holding to/from a library collection
- Get a list (collection) of all the holdings in the library collection
- Count the number of holdings (books and videos) in the library collection
- Allow a member to borrow a holding
- Allow a member to return a holding
- Get a list (collection) of all currently borrowed holdings for the member
- Calculate the late fee for a holding (when applicable)
- Create and add a borrowing record to a borrowing history
- Get a specific borrowing record from a borrowing history
- Get a list of all the borrowing records (i.e. a complete borrowing history)
- Calculate the remaining credit ($ value) for a member
- Refresh member’s credit (i.e. restore the credit to the initial maximum value)
- Calculate the total late fees ($ value) accumulated by a member for all the holdings stored in the borrowing history
- Check for *borrowing eligibility* (see Section 3 below)

## 3. Additional Implementation Details and Constraints

- Your primary goal is to implement the provided LMSModel interface, in a class called LMSFacade in order to provide the behaviour specified as comments in the LMSModel source file and tested by the TestHarness.java (available on Blackboard). Note that in Assignment 2 you will be asked to write a graphical user interface to more effectively utilise LMSModel.
- You have freedom in how you choose to implement your solution; however, you must implement it in such a way that the TestHarness is NOT modified. You should use inheritance, polymorphism, abstract classes and interfaces effectively, as taught in this course. 
- Even though the LMSModel interface returns array types, you should use data structures from the Java collection API (e.g. Map, Set, List etc.) in your implementation and convert them for return as necessary. You should choose structures that are appropriate to the task, for example if lookups are frequently performed a Map would be a suitable data structure.
- The provided source code includes DateUtil class which stores the "current" system date, and also allows you to get the total loan period (in number of days) for a holding i.e. returnDate - borrowDate. This can be useful when calculating late fees for a given holding. Specifically, the following calls should be used to find (1) the current date; and (2) the total loan period in number of days:
  - (1) String borrowDate = DateUtil.getInstance().getDate()); // call this when borrowing holdings
  - (2) int numOfDays = DateUtil.getInstance().getElapsedDays(borrowDate); // call this when returning holdings
  - Note that we need to artificially set the "current" date from within the TestHarness in order to test the borrow/return functions.
- You must provide appropriate constructors and methods as required by the TestHarness in order to ensure that your solution can be complied and tested without modifying the TestHarness.
  - example 1. LibraryCollection class must implement the Holding[] getAllHoldings() method as used in the TestHarness tests.
  - example 2. StandardMember and PremiumMember, which represent the concrete Memberclasses that you need to implement according to TestHarness, must have the following constructors: 
    - public StandardMember (String memberId, String memberName); 
    - public PremiumMember (String memberId, String memberName);
  - The rest of the requirements can be determined from the provided TestHarness.java source file.

### ***Library Collection Rules:***
The library collection is dynamic and unbounded. That is, you can have an unlimited number of holdings added to the collection. When calling addHolding(…) and removeHolding(…) methodsto build up the library collection, the following rules must be maintained: 
- Only one copy of a given holding can be added to the collection;
- You cannot remove a holding form the collection if it is currently on loan.

Boolean values should be used to indicate the successful completion of the add/removeHolding() functions based on the above rules.

### ***Holding Borrow/Return Rules:*** 
The member can borrow any number of holdings as long as the following rules are followed:
- The member must have a sufficient credit available to borrow a given holding.
- The member can borrow each holding only once.

The holding return procedure differs based on a specific member type:
- Standard members are not allowed to return a (late) holding if their current balance will become negative after paying the late penalty fee. In such cases, the current credit of a member needs to be restored to the initial maximum value first.
- Premium members can return a holding even if this will result in a negative current balance. However, they won’t be able to borrow any new holdings until their current credit is restored to the initial maximum value.

Exceptions must be thrown when the holding borrow/return rules are violated. The exceptions must extend the LMSException class (to be provided). Note: at the minimum you should provide and use three exception sub-classes: 
- InsufficientCreditException, MultipleBorrowingException, OverdrawnCreditException.

## 4. String Representations
Certain classes must provide specific string representations by overriding a public String toString() method which conforms to the format described below. This is required to enable correct operation of the TestHarness and is used as an alternative to data access methods, thereby allowing greater flexibility in how you implement your solution. Examine the TestHarness and the specification below to see how they are used.

### ***Library Collection***
collection_code:collection_name:holdingCodes
e.g. LIB_IT:Information Technology:1000001,2000002
Note:
1. holdingCodes are optional since a collection can be empty.
e.g. LIB_IT:Information Technology would indicate an empty collection.
2. multiple holdingCodes should be separated with comas (no space in between).
   
***Member***

member_id:full_name:remaining_credit:type

e.g. m00001:Joe Bloggs:25:PREMIUM

Note: type must be either “STANDARD” or “PREMIUM”.

***Holding***

holding_code:holding_title:standard_loan_fee:max_loan_period:holding_type

e.g. 0000001:Introduction to Java Programming:10:28:BOOK

Note: type must be either “BOOK” or “VIDEO”.

***History Record***

holding_code:payed_fee

e.g. 0000001:15

LibraryCollection, Member, Holding and HistoryRecord codes/ids and names/titles are assumed to be unique and case insensitive, and must not contain any colons (:) or commas (,) that would otherwise lead to invalidly formatted string representations.
