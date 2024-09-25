
* import.sql is Hibernate-specific and runs as part of the JPA bootstrap process.
```
# Tell Hibernate to handle schema creation and then import data using import.sql
spring.jpa.hibernate.ddl-auto=create

# Enable logging for SQL statements (optional, for debugging purposes)
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

```

* data.sql runs at the Spring Boot level and can be used for any database, regardless of whether you are using Hibernate.

```
spring.datasource.initialization-mode=always
spring.datasource.data=classpath:data.sql


```


* schema.sql If you're using a relational database (like MySQL, PostgreSQL, etc.), the schema.sql file will be executed automatically when the application starts.
```
spring.datasource.initialization-mode=always
spring.datasource.schema=classpath:schema.sql
spring.jpa.hibernate.ddl-auto=none

```
