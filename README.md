# java-spring-security-anywr
Solution for Anywr spring security test

-------

Usage
-------
The project runs with embedded tomcat in a spring boot jar and uses h2 database, so there's no additional configuration needed.

APIs CURLs example:
* Sign Up:
`curl --location --request POST 'http://localhost:8080/signUp' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "username":"cchigne",
  "name": "Cesar",
  "lastname": "Chigne",
  "email": "cesar.chigne@gmail.com",
  "password": "123456"
  }'`


* Sign In: `curl --location --request POST 'http://localhost:8080/signIn' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "username":"cchigne",
  "password": "123456"
  }'`

  
* Get logged user information: `curl --location --request GET 'http://localhost:8080/user' \
  --header 'Authorization: Bearer <retrieved token on Sign In API>'`


Resources
-------
* https://www.adictosaltrabajo.com/2020/05/21/introduccion-a-spring-security/
* https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
