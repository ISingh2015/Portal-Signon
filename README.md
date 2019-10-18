# Portal-Signon

Portal Signon is a Single Sign on spring backend module for authenticating and authorizing Portal Users. This is Spring Secured and does not require any security configuration accept the one included in this module. It supports Backend user authentication using ORACLE database tables. It supports backend user ROLE Authorisation using Oracle database tables. 

Clone the project and execute the following maven script to build and run the module(see below scripts). The dependencies will be automatically resolved from the parent project. The module can also be built from the  **PORTAL-BUILD** directory, and can be executed using SPRING-BOOT as a jar file (see below script).

```
mvn clean test install
mvn spring-boot: run 
```

