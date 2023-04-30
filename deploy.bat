@REM .\gradlew clean build &
@REM docker build --tag=guitar-store:latest .
.\gradlew clean build & docker-compose build & docker-compose up