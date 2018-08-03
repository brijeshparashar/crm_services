# crm_services


Instructions to run the CRM project 

OPTION 1 
1  Checkout the project from git URL : https://github.com/brijeshparashar/crm_services 
2  Import /customer-service-v1-api project as existing maven project in Eclipse
3  Build as maven project
4  Open CustomerApplication.java and run as 'Java Application'


5  Test Create Customer via Postman/RestClient
  	(a) Use postman or restclient to test. 
	    Request parameters are as follows -
		 Request method - POST
		 Content-Type - application/json
		 Body - {"customerId":null,
  "firstName":"Name",
  "lastName":"Last",
  "emailId": "emailId@emailId.com",
  "alternateEmailId":"alternate@alternateEmailId.com",
  "addresses": [{
  	"addressId" : null,
    "unitNumber": "22",
    "streetName": "George Street",
    "suburb": "Sydney",
    "postCode": "2000",
    "country": "Australia",
    "addressType":"OFFICE"
  },{
  	"addressId" : null,
    "unitNumber": "99",
    "streetName": "Campbell Street",
    "suburb": "Paramatta",
    "postCode": "2150",
    "country": "Australia",
    "addressType":"HOME"
  }]
}
		 URL - http://localhost:8080/customer
        (b) To check Database entries Use url: http://localhost:8080/console
	    Driver - org.h2.Driver
	    JDBC url - jdbc:h2:mem:testdb
	    username - sa
	    password - 
        (c) Run -> SELECT * FROM CUSTOMER;
	         SELECT * FROM ADDRESS; 

6 Test Update Customer via Postman/RestClient
         (a) Use postman or restclient to test. 
	    Request parameters are as follows -
		 Request method - PUT
		 Content-Type - application/json
		 Body - {"customerId":1,
  "firstName":"Name",
  "lastName":"Last",
  "emailId": "emailId@emailId.com",
  "alternateEmailId":"alternate@alternateEmailId.com",
  "addresses": [{
  	"addressId" : 1,
    "unitNumber": "22",
    "streetName": "George Street",
    "suburb": "Sydney",
    "postCode": "2000",
    "country": "Australia",
    "addressType":"OFFICE"
  },{
  	"addressId" : 2,
    "unitNumber": "99",
    "streetName": "Campbell Street",
    "suburb": "Paramatta",
    "postCode": "2150",
    "country": "Australia",
    "addressType":"HOME"
  }]
}
		 URL - http://localhost:8080/customer
	 (b) Add the CustomerId and AddressIds (can be retrieved from database or taken from response of Create Customer) as Retrieve Customer is not part of this project. 
         (c) After the request is successful check the changes in Database following the steps 5(b) and 5(c). 

7 Test Delete Customer via Postman/RestClient
	 (a) Use postman or restclient to test. 
	     Request parameters are as follows -
		 Request method - DELETE
		 Path Parameter - 1
		 URL - http://localhost:8080/customer/1
		 "1" at the end of URL above is the customerId of the customer to be deleted.

Alternatively 

OPTION 2 
1  Checkout the project from git URL :  https://github.com/brijeshparashar/crm_services 
2  Use command prompt to reach /customer-service-v1-api
3  Build the project using mvn clean install
4  Execute mvn spring-boot:run
5  Follow steps 5-7 to test the application. 


Important URLs : 
Database: http://localhost:8080/console
Application Base URL : http://localhost:8080/customer

To cater for Basic request and response Swagger2 is implemented. The URL for reference is as below.  
Swagger : http://localhost:8080/swagger-ui.html
