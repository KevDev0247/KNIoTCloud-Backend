# Jitlantis-Backend

## Jitlantis Team
Roles:
- Feng Yuan: Cofounder, Product Manager
- Yonggang Su: Cofounder, System Architect, Frontend Developer, Mobile Developer
- Kevin Zhijun Wang: Cofounder, Web Full stack developer, Mobile Developer
- Ying Quan: UI & UX designer

Contributions:
- Product Management: Feng Yuan
- Marketing: Feng Yuan
- Requirement Analysis: Feng Yuan, Yongggang Su, Kevin Zhijun Wang
- Business Logic Design: Feng Yuan
- System Design: Yonggang Su
- Database Design: Yonggang Su, Kevin Zhijun Wang
- Web Frontend Development: Yonggang Su, Kevin Zhijun Wang
- Web Backend Development: Kevin Zhijun Wang
- QA and Testing: Yonggang Su
- UI & UX design: Ying Quan

## System Design
![System Design](https://user-images.githubusercontent.com/58012125/90317906-8d2dca00-defa-11ea-8d09-652bf7375634.png)

## Architecture
![Architecture](https://user-images.githubusercontent.com/58012125/90317935-c108ef80-defa-11ea-9a93-7bc2473e5289.png)


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