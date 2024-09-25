
<!-- Apache POI for Excel file reading -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.2</version>
    </dependency>

2. Configure MySQL Database
   Add database connection details in application.properties (or application.yml) file:
```
spring.datasource.url=jdbc:mysql://localhost:3306/mydatabase
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

```


7. Test the API
   You can test the API using Postman or any other API testing tool. Send a POST request to http://localhost:8080/api/excel/upload with the Excel file as multipart/form-data.

Sample Excel File
The first row (headers) will be ignored when parsing:

Name	Email	Phone
John Doe	john@example.com	1234567890
Jane Smith	jane@example.com	9876543210
