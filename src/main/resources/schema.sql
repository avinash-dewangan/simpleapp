DROP TABLE IF EXISTS CITY;
CREATE TABLE CITY (
                      city_code INT AUTO_INCREMENT  PRIMARY KEY,
                      city_name VARCHAR(50) NOT NULL,
                      city_pincode INT NOT NULL
);
