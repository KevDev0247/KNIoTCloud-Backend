# Jitlantis-Frontend

## About us
This project aims to modernize JIT manufacturing process with a focus on Total Productive Maintenance (TPM). 
We aim to contribute to Industry 4.0 by integrating Maintenance workflow into an SaaS web platform to bring convenience to both manufacturers and their customers. 
COVID-19 pandemic has had a profound impact on manufacturers all over the world, and we hope our project can make a difference in manufacturing sector. 


## System Design
![jitlantis_system_design](https://user-images.githubusercontent.com/58012125/101298503-468f1300-37fc-11eb-8174-fc6e0791b9ee.png)

## Architecture
![jitlantis_architecture](https://user-images.githubusercontent.com/58012125/101298466-19dafb80-37fc-11eb-82e1-5138935afdf2.png)

## Gallery
![Dashboard](https://user-images.githubusercontent.com/58012125/99997466-32263180-2d8b-11eb-8bf2-ba7d9067836b.png)


## SpringBootUsage

1.create database

mysql -uroot

create database db_example

2.create jar

mvn clean package

3.execute jar

cd target

java -jar spring-boot-api-0.1.0.jar

4.notices

//create tables in first time 

spring.jpa.hibernate.ddl-auto=none

//create tables in every time

spring.jpa.hibernate.ddl-auto=create 

5.api url
http://localhost:8080/swagger-ui.html
