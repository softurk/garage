# Garage Project

### Used Technologies

<ul style="list-style-type:disc">
  <li> <b> Java 1.8 </b> </li>
  <li> <b> Gradle </b> </li>
  <li> <b> Spring Boot </b> </li>
  <ul>
    <li> Spring Boot 2.5.0 </li>
    <li> Spring Boot Web </li>
    <li> Spring Boot Jpa </li>
    <li> Lombok </li>
    <li> ModelMapper</li>
    <li> H2 Database </li>
  </ul>
</ul>


### 📖 Information

<ul style="list-style-type:disc">
  <li><b>Garage System</b> that issues tickets according to the license plate, color and type of the vehicle.</li>
  </li>
</ul>

### 🔨 Run the App

<b>1 )</b> Open Terminal under <b> vodafone-garage </b> folder to run <b>Application</b>
```
    gradle bootRun
```

<b>2 )</b> To <b>produce</b> ticket, use a <b>POST</b> request url with the request body
```
    http://localhost:8080/garage/parking

    park 34-AB-12 Black Car
```

```
    http://localhost:8080/garage/parking

    park 34-AB-123 Red Truck
```

```
    http://localhost:8080/garage/parking

    park 34-AB-1234 Blue Jeep
```

<b>3 )</b> To <b>leave</b> the garage, use a <b>POST</b> request url with the request body
```
    http://localhost:8080/garage/parking

    leave 1
```

<b>4 )</b> To <b>list</b> the vehicles in the garage, use a <b>POST</b> request url with the request body
```
    http://localhost:8080/garage/parking

    status
```

### Screenshots

<details>
<summary>Click here to show the screenshots of project</summary>
    <p> Postman Car Request </p>
    <img src ="screenshots\garage1.png">
    <p> Postman Jeep Request </p>
    <img src ="screenshots\garage2.png">
    <p> Postman Truck Request </p>
    <img src ="screenshots\garage3.png">
    <p> h2 Database </p>
    <img src ="screenshots\garage4.png">
</details>
