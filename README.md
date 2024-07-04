# top-movies

Api for getting top rated movies by genre that are refreshed weekly.

### Prerequisites
* Java development kit 21
* Gradle 8.2 or later
* IDE
* Git
* Postman (for api testing)
* [Top movies front end repository](https://github.com/arnaskv/top-movies-fe)
### Getting Started
* Setup mysql database
* Set environment variables for mysql connection:
  * MYSQL_HOST
  * MYSQL_PORT
  * MYSQL_DB
  * MYSQL_USER
  * MYSQL_PASSWORD
* Set environment variable for tmdb api:
  * TMDB_ACCESS_TOKEN
### Build And Run
```shell
./gradlew build
./gradlew bootRun
```
