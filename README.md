# JPA inheritance
This project demonstrates use of inheritance for mapping common columns that are
used in many entities and to bypass boilerplate code. In this demo mapped supper class technique is used as one 
type of inheritance strategy. Mapped superclass inheritance allows inheritance to be used in the object model, when
it does not exist in the data model.

## Common pitfalls
The main disadvantage of mapped superclasses is that they cannot be queried or persisted. 
You also cannot have a relationship to a mapped superclass. If you require any of these then you must use another 
inheritance model, such as table per class, which is virtually identical to a mapped superclass except it (may) 
not have these limitations. Another alternative is to change your model such that your classes do not have 
relationships to the superclass, such as changing the relationship to a subclass, or removing the relationship 
and instead querying for its value by querying each possible subclass and collecting the results in Java.

## Running the project
1. Create database with name **inheritance**
2. Open terminal and navigate to your project
3. Type command `mvn install -DskipTests`
4. Run project

## Running tests
1. Open terminal and navigate to your project
2. Type command `mvn test`

## Author
Heril Muratović  
Software Engineer  
<br>
**Mobile**: +38269657962  
**E-mail**: hedzaprog@gmail.com  
**Skype**: hedza06  
**Twitter**: hedzakirk  
**LinkedIn**: https://www.linkedin.com/in/heril-muratovi%C4%87-021097132/  
**StackOverflow**: https://stackoverflow.com/users/4078505/heril-muratovic