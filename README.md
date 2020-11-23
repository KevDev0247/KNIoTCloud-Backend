# Jitlantis-Backend

## Jitlantis Team
- Feng Yuan: Cofounder, Product Manager, Marketing Director
- Yonggang Su: Cofounder, System Architect, Full stack developer, Mobile Developer
- Kevin Zhijun Wang: Cofounder, Full stack developer
- Ying Quan: UI & UX designer

## System Design
![System Design](https://user-images.githubusercontent.com/58012125/90318570-5f974f80-deff-11ea-908b-413f72bf750d.png)

## Architecture
![Architecture](https://user-images.githubusercontent.com/58012125/90318700-47740000-df00-11ea-98ee-29f74af087fe.png)

## Gallery
![Dashboard](https://user-images.githubusercontent.com/58012125/99997466-32263180-2d8b-11eb-8bf2-ba7d9067836b.png)

## Contributions:
- Product Management: Feng Yuan
- Marketing: Feng Yuan
- Requirement Analysis: Feng Yuan, Yongggang Su, Kevin Zhijun Wang
- Business Logic Design: Feng Yuan
- System Design: Yonggang Su
- Database Design: Yonggang Su, Kevin Zhijun Wang
- Web Frontend Development: Yonggang Su, Kevin Zhijun Wang
- Web Backend Development: Kevin Zhijun Wang, Yonggang Su
- QA and Testing: Yonggang Su
- UI & UX design: Ying Quan

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