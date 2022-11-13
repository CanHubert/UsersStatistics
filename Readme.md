1. Run docker-compose db service to start database. (from terminal -  docker-compose up db, or right click on service from intellj)
2. Run com.example.demo.DemoApplication to start application
3. Use Postman or similar application to send http request.
4. Example requests:
* http://localhost:8080/save method POST
* with body:
* {
  "date": "2022-11-13",
  "ip": "124.12.12.12"
  }
* to save user visit
http://localhost:8080/statistics method GET
* to get user statistics