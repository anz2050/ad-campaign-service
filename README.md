# ad-campaign-service
Ad Campaign RESTful Service - simple HTTP-based ad server

## Project Technology Description

  - Spring Boot in-built Tomcat Server
  - Java 1.8
  - Apache Maven
  - Spring Boot RESTful API
  - Spring Data JPA
  - In-memory database (H2)
  - jUnit, Mockito and Spring MockMvc (unit testing)

## Download, Build & Run this Application

* **Download:**
```
  $> git clone https://github.com/anz2050/ad-campaign-service.git
```

* **Build:**
```
  $> cd ad-campaign-service
  
  $> mvn clean package
```  

* **Run Application:**
```
  $> java -jar target/ad-campaign-service-0.0.1-SNAPSHOT.jar
```

  Use any REST client (e.g. Chrome Postman, Mozilla RestClient, SOAPUI) to test the service

## Requirement
* **Create Ad Campaign via HTTP POST:**

  A user should be able to create an ad campaign by sending a POST request to the ad server at `http://<host>:8080/ad`.  The body of the POST  request must be a JSON object containing the following data:

```
{
"partnerId": "unique_string_representing_partner',
"duration": "int_representing_campaign_duration_in_seconds_from_now"
"adContent": "string_of_content_to_display_as_ad"
}
```

e.g.
```
{
  "partnerId": "Apple",
  "duration": 70,
  "adContent": "iPhone 7 plus - Best Technology"
}
```

    The server should enforce the following invariant upon receiving a request to create a new campaign

    * Only one active campaign can exist for a given partner.

    If an error is encountered, the ad server must return an appropriate response and indicate the problem to the user.  The response format is left up to the discretion of the implementer.

* **Fetch Ad Campaign for a Partner:**

   A partner should be able to get their ad data by sending a GET request to the ad server at `http://<host>:8080/ad/<partner_id>`
   
   Response can be delivered as a JSON object representing the active ad

   If the current time is greater than a campaign's creation time + duration, then the server's response should be an error indicating that no active ad campaigns exist for the specified partner.


* **Fetch list of all Ad Campaigns:**

  Use a GET request to the ad server at `http://<host>:8080/`
