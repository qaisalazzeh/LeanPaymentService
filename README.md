# Lean Payments
Lean Payment is a Java-Spring boot based back-end API that used to list merchants(Customers) Information, Accounts and their Transactions, theoretically this API deals with per-generated .csv files contains all of these information being cached (application boot up) into redis server as In memory caching mechanism that supports the performance for API.

## Installation-Prerequisites
In order to have this back end service up and running on you machine (server), you will need following as prerequisites:
* Download and install [Java 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html), make sure to [setup JAVA_HOME](https://www.baeldung.com/java-home-on-windows-7-8-10-mac-os-x-linux) environment variable.
* Download and install [Maven](https://maven.apache.org/install.html), make sure to [setup MAVEN_HOME](https://mkyong.com/maven/how-to-install-maven-in-windows/) for windows OS.
* Download and install [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) code version control tool.
* Install [Redis](https://www.digitalocean.com/community/tutorials/how-to-install-and-secure-redis-on-ubuntu-18-04) in your machine.
* Install [Tomcat 9](https://tomcat.apache.org/download-90.cgi) as application server that will be used to deploy the service.

## Installation-Steps
* Clone API source code into your machine :
```bash
git clone https://github.com/qaisalazzeh/LeanPaymentService.git
```
* Navigate to LeanPayment Repository and make sure you are on pom.xml file:
```bash
mvn clean install
```
* Make sure that redis server is up and running on localhost:6379 if you are deploying the service on local machine otherwise you need to change redis configuration in source code, [Redis Manual](https://www.digitalocean.com/community/tutorials/how-to-install-and-secure-redis-on-ubuntu-18-04) : 
```bash
sudo systemctl status redis
```

#### Run the Service Using IDE
* Import the source code as maven project into IDE ([Intellij](https://www.jetbrains.com/idea/) , [Eclips STS](https://spring.io/tools)) workspace
* Add Downloaded Tomcat server to your workspace then right click on leanpayment project RunAs --> Run on server.
 
#### Run the Service Using Application server
* Copy the generate .war file under target folder from your repository (make sure the war file is generated correctly) to /webapps folder under tomcat directory source code.
* Navigate Tomcat and deploy the application using tomcat shell scrips [Tomcat Maual](https://tomcat.apache.org/tomcat-9.0-doc/deployer-howto.html#Deploying_on_a_running_Tomcat_server)
  

## API Usage
Now the service is up and running on your machine : 
* You can use [Postman](https://www.postman.com/) to start test the API, any other rest client.
* You can use curl utility if you are mac or Linux OS from your terminal:
```bash
curl -XGET http://localhost:8080/leanpayment/customer/1
```
```bash
curl -XGET http://localhost:8080/leanpayment/account/1
```
```bash
curl -XGET http://localhost:8080/leanpayment/account/1/transactions
```

## Notes
* API returns the response as json response with content type 'application/json'.
* API consumes only GET http request , POST is not supported.
* You will see Encryptable object return; just to show that the API is doing simple encryption/decryption for sensitive information(iban,email....) - not standard but to show is an example.
* API Response contains null values for all FK under any entity you search for - i am using the fake entity that loaded only by id as this will support the performance of the system no need to load whole account as your search for transaction for given account id by example.
* You are not allowed to use this API response returns if you are not using one of the white listed keys defined by lean team.
* API is doing clean for the client's request for cross site scripting attack.
* API caches the file at first boot-up for the service; will re-cache again if some flushed the redis keys.
* Tests included under test source folder to cover the service implementation.
* If you face and issue with Lombok please visit this [manual](https://stackoverflow.com/questions/11803948/lombok-is-not-generating-getter-and-setter) to support Lombok for your IDE.
* I used the java 8 streaming to support my solution and for less code.

## Questions
* #### Why i am using redis as in memory database solution ? 
  Redis is highly compatable with Spring boot and Spring data frameworks as you can use the spring redis dependency to mange the messages and key for your redis service
  [ReadMore](https://docs.spring.io/spring-data/data-redis/docs/current/reference/html/#reference)
* #### Why i am using Tomcat ?
  Tomcat is apache lightweight application server solution that is compatible with java and spring solution, you can use another application server but may it require more efforts to setup the server and check dependency tree conflicts that may occurred.


##### Author : Qais Azzeh, Software Engineer, [Email me](qais.azzeh@gmail.com)
