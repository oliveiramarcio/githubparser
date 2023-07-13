# Github Parser App for Trustly's Hiring Challenge

*Parses Github repository URL and returns repository files with total line numbers and bytes grouped by file extension in JSON format through Github repository page scraping.*

**[Trustly's](https://www.trustly.net) hiring challenge developed as [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) Rest API in [Visual Studio Code](https://code.visualstudio.com/docs/languages/java) with [Spark Framework](http://sparkjava.com), [Jetty](https://www.eclipse.org/jetty), [Maven](https://maven.apache.org), [Swagger](https://swagger.io), [Swagger UI](https://swagger.io/tools/swagger-ui) and [JUnit](https://junit.org).**

The app was deployed at [Heroku](http://githubparser.herokuapp.com) using [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli).

As I am finishing my move from Colombia to Brazil, I didn't have much time to work on this application.

And as I currently don't have a solid Java experience, I did my best in one day to get a working version.

I used only native classes to download and analyze the github html.

I believe it could be much easier with a minimal html parser, but I respected the statement of the challenge and made the parser with regular expressions, which I believe has penalized performance a little.

Repositories with many files or large files end up being timeout on Heroku, but work on localhost with delays.

The project could have used dependency injection, a number of other design patterns and even better approaches to analyzing html, but within my available time it was what I managed to do.

As I used Swagger and Swagger UI for documentation, the API method is self-explanatory.

I created the API method as a `GET` for the sake of simplicity, but I think it could be a `POST`, since there is data processing for return in a specific format.

Also for the sake of time, I didn't create data persistence.

I refactored the code a lot while working on it, but it is far from ideal and more time is needed to research optimized approaches.

The Javadoc of the project classes was also pending.

For simplicity and time saving, I created unit tests only for the model class an two integration tests for `GithubParserControllerTest` class, but I know that the entire app logic should have unit tests.