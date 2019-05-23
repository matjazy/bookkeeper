# Bookkeeper
This is a project of Spring application that allows users to reach few endpoints.
To run this project go to root directory and follow instructions posted below.

<b>Building</b>
To build the project use `mvn clean install` command

<b>Running</b>
Application will run on port 8080 so make sure that is it not occupied before running it.
To run the application use following command `java -jar target/bookkeeper-0.0.1-SNAPSHOT.jar`

<b>Available endpoints</b>
/api/rating which allows you to access authors of books used in application along with related rating. 
/api/book/{isbn} which allows you to access book by relevant identifier.
/api/category/{category}/books which allows you to access books by case sensitive category.