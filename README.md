# splash
Heroku button template for a simple contact management app. </br></br>

[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## API Specification

###Login and get a User Token back (like v1)
- Request: http://localhost:8080/logiclineportal/user/login
- Method Type: POST
- Header Values: Content-Type: application/json;
Example: 
- Request Data: {"username":"user1","password":"user1"} 
- Response Data: {
    "firstName": "Max",
    "lastName": "Bauer",
    "role": "ROLE_CUSTOMER",
    "userId": "2",
    "token": "332bc0ddba3c4bc792c4829ff3834ca9"
}

###Login with CAPTCHA and get a User Token back (like v1)
- Request: http://localhost:8080/logiclineportal/user/login
- Method Type: POST
- Header Values: Content-Type: application/json;
Example: 
- Request Data: {"username":"user1","password":"user1", "recaptcha_challenge_field":"xxxx", "recaptcha_response_field":"yyyy"}
- Response Data: 
ResponseCode: 200
Response Data: {
    "firstName": "Max",
    "lastName": "Bauer",
    "role": "ROLE_CUSTOMER",
    "userId": "2",
    "token": "332bc0ddba3c4bc792c4829ff3834ca9"
}
ResponseCode: 412 (captcha failed)
null
ResponseCode: 401 (login failed)
null			

###Create New User (Request and Response Data changed)
- Request: http://localhost:8080/logiclineportal/user/create
- Method Type: POST
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad

Example: 
- Request Data:
{  
    "email": "max@gmx.de",
    "mainName": "Max",
  	"mainSurname": "Bauerfeld", 
   	"mainCity": "Sindelfingen",
  	"mainCountry": "Deutschland",
   	"mainZipcode": "71063",
    "mainStreet": "Sindelfingen",
  	"mainPhone": "071122872" 
}

- Response Data: 
	password of the new generated user

###Create new Password (Response Data changed, Method Type Changed)
- Request: http://localhost:8080/logiclineportal/user/edit/password/:userIdFk
- Method Type: POST 
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example:  
- Request Data: 
		 http://localhost:8080/logiclineportal/user/edit/password/2			
- Response Data:
		/!o8COfz8h
		

###Contact Object (Response Data changed)
- Request: http://localhost:8080/logiclineportal/user/edit/:userIdFk
- Method Type: GET
- Header Values: Content-Type: application/json; token: 332bc0ddba3c4bc792c4829ff3834ca9
Updated URL to retrieve UserInfo by User ID field 
Example: 
- Request URL: http://localhost:8080/logiclineportal/user/edit/2
- Request Data:
- Response Data: 
{
    "email": "max@gmx.de",
    "mainName": "Max",
    "mainSurname": "Bauerfeld",
    "mainCity": "Hockenheim",
    "mainCountry": "Deutschland",
    "mainZipcode": "71063",
    "mainStreet": "Sindelfingen",
    "mainPhone": "071122872"
}


###Get all Contacts (Response Data changed)
- Request: http://localhost:8080/logiclineportal/user/search
- Method Type: GET
- Header Values: Content-Type: application/json; token: xyz
Example: 
- Request Data: 
- Response Data:
{
    "2": "Bauerfeld",
    "3": "Metzger"
} 

### Search Contacts by last name - or a part of it (Response Data changed)
- Request: http://localhost:8080/logiclineportal/user/search/:customerId
- Method Type: GET
- Header Values: Content-Type: application/json; token: xyz
Example: 
- Request Data: http://localhost:8080/logiclineportal/user/search/Bauerf
- Response Data:
{
    "2": "Bauerfeld"
} 


###Update Contact Object (Request Data changed)
- Request: http://localhost:8080/logiclineportal/user/edit/:userIdFk
- Method Type: PUT
- Header Values: Content-Type: application/json; token: 332bc0ddba3c4bc792c4829ff3834ca9
Updated URL to update UserInfo by User ID field 
Example: 
- Request URL: http://localhost:8080/logiclineportal/user/edit/2
- Request Data: 
{
    "email": "max@gmx.de",
    "mainName": "Max",
    "mainSurname": "Bauerfeld",
    "mainCity": "Hockenheim",
    "mainCountry": "Deutschland",
    "mainZipcode": "71063",
    "mainStreet": "Sindelfingen",
    "mainPhone": "071122872"
}
- Response Data:




## API Specification v1
###Login and get a User Token back
- Request: http://localhost:8080/logiclineportal/user/login
- Method Type: POST
- Header Values: Content-Type: application/json;
Example: 
- Request Data: {"username":"user1","password":"user1"} 
- Response Data: {
    "firstName": "Max",
    "lastName": "Bauer",
    "role": "ROLE_CUSTOMER",
    "userId": "2",
    "token": "332bc0ddba3c4bc792c4829ff3834ca9"
}

###Login with CAPTCHA and get a User Token back
- Request: http://localhost:8080/logiclineportal/user/login
- Method Type: POST
- Header Values: Content-Type: application/json;
Example: 
- Request Data: {"username":"user1","password":"user1", "recaptcha_challenge_field":"xxxx", "recaptcha_response_field":"yyyy"}
- Response Data: 
ResponseCode: 200
Response Data: {
    "firstName": "Max",
    "lastName": "Bauer",
    "role": "ROLE_CUSTOMER",
    "userId": "2",
    "token": "332bc0ddba3c4bc792c4829ff3834ca9"
}
ResponseCode: 412 (captcha failed)
null
ResponseCode: 401 (login failed)
null			

###Create New User
- Request: http://localhost:8080/logiclineportal/user/create
- Method Type: POST
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad

Example: 
- Request Data:
{
    "customerId": "5121-9992-09",
    "typ": null,
    "identityNr": null,
    "orgNr": null,
    "company": "nothing",
    "invoiceLab": null,
    "facilityNr": null,
    "contactPersNr": null,
    "email": "franz@gmx.de",
    "mainPoPox": "",
    "mainStreet": "Tueringerstr. 10",
    "mainZipcode": "71083",
    "mainCity": "Mannheim",
    "mainName": "Fraz",
    "mainSurname": "Zappa"
}

- Response Data: 
	id of the new generated user

###Create new Password
- Request: http://localhost:8080/logiclineportal/user/edit/password/:userIdFk
- Method Type: PUT
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example:  
- Request Data: 
		 http://localhost:8080/logiclineportal/user/edit/password/2			
- Response Data:
		/!o8COfz8h

### DEPRECATED Get Contact Object (for current User)
- Request: http://localhost:8080/logiclineportal/user/edit
- Method Type: GET
- Header Values: Content-Type: application/json; token: 332bc0ddba3c4bc792c4829ff3834ca9
Example: 
- Request Data:
- Response Data: {
    "userInfoId": 1,
    "userIdFk": 2,
    "customerId": "5121-9992-01",
    "typ": null,
    "identityNr": null,
    "orgNr": null,
    "company": "nothing",
    "invoiceLab": null,
    "facilityNr": null,
    "contactPersNr": null,
    "email": "max@gmx.de",
    "mainPoPox": "",
    "mainStreet": "Planiestr. 10",
    "mainZipcode": "71063",
    "mainCity": "Sindelfingen",
    "mainName": "Max",
    "mainSurname": "Bauer"
}

###Contact Object 
- Request: http://localhost:8080/logiclineportal/user/edit/:userIdFk
- Method Type: GET
- Header Values: Content-Type: application/json; token: 332bc0ddba3c4bc792c4829ff3834ca9
Updated URL to retrieve UserInfo by User ID field 
Example: 
- Request URL: http://localhost:8080/logiclineportal/user/edit/2
- Request Data:
- Response Data: 
{
    "userInfoId": 1,
    "userIdFk": 2,
    "customerId": "5121-9992-01",
    "typ": null,
    "identityNr": null,
    "orgNr": null,
    "company": "nothing",
    "invoiceLab": null,
    "facilityNr": null,
    "contactPersNr": null,
    "email": "max@gmx.de",
    "mainPoPox": "",
    "mainStreet": "Planiestr. 10",
    "mainZipcode": "71063",
    "mainCity": "Sindelfingen",
    "mainName": "Max",
    "mainSurname": "Bauer"
}


###Get all Contacts 
- Request: http://localhost:8080/logiclineportal/user/search
- Method Type: GET
- Header Values: Content-Type: application/json; token: xyz
Example: 
- Request Data: 
- Response Data:
{
    "2": "5121-9992-01",
    "3": "5121-9992-02"
} 

### Serch Contacts by customerId (or a part of it)
- Request: http://localhost:8080/logiclineportal/user/search/:customerId
- Method Type: GET
- Header Values: Content-Type: application/json; token: xyz
Example: 
- Request Data: http://localhost:8080/logiclineportal/user/search/999
- Response Data:
{
    "2": "5121-9992-01",
    "3": "5121-9992-02"
} 

### DEPRECATED Update Contact Object (for current User)
- Request: http://localhost:8080/logiclineportal/user/edit
- Method Type: PUT
- Header Values: Content-Type: application/json; token: 332bc0ddba3c4bc792c4829ff3834ca9
Example: 
- Request Data: 
{
    "userInfoId": 1,
    "userIdFk": 2,
    "customerId": "5121-9992-01",
    "typ": null,
    "identityNr": null,
    "orgNr": null,
    "company": "nothing",
    "invoiceLab": null,
    "facilityNr": null,
    "contactPersNr": null,
    "email": "max@gmx.de",
    "mainPoPox": "",
    "mainStreet": "Planiestr. 10",
    "mainZipcode": "71063",
    "mainCity": "Sindelfingen",
    "mainName": "Max",
    "mainSurname": "Bauerfeld"
}
- Response Data:

###Update Contact Object
- Request: http://localhost:8080/logiclineportal/user/edit/:userIdFk
- Method Type: PUT
- Header Values: Content-Type: application/json; token: 332bc0ddba3c4bc792c4829ff3834ca9
Updated URL to update UserInfo by User ID field 
Example: 
- Request URL: http://localhost:8080/logiclineportal/user/edit/2
- Request Data: 
{
    "userInfoId": 1,
    "userIdFk": 2,
    "customerId": "5121-9992-01",
    "typ": null,
    "identityNr": null,
    "orgNr": null,
    "company": "nothing",
    "invoiceLab": null,
    "facilityNr": null,
    "contactPersNr": null,
    "email": "max@gmx.de",
    "mainPoPox": "",
    "mainStreet": "Planiestr. 10",
    "mainZipcode": "71063",
    "mainCity": "Sindelfingen",
    "mainName": "Max",
    "mainSurname": "Bauerfeld"
}
- Response Data:
