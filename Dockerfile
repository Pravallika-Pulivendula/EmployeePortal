FROM openjdk:11
ADD target/EmployeePortal-0.0.1-SNAPSHOT.jar employee-portal-app.jar
ENTRYPOINT ["java","-jar","employee-portal-app.jar"]