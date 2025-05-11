
Бановская система состоит из следующих модулей:

- **banking** - родительский модуль
- **dependency_bom** - pom управления зависимостями
- **user-service** - сервис пользователя
- **payment-service** - сервис переводов
- **config-service** - сервис конфигураций
- **discovery-service** -eureka
- **gateway-service** - api gateway


## Склонировать репозиторий

- git clone git@gitlab.com:megagrebec1999/banking-system.git
- https://github.com/ofuron72/banking.git

## Функционал:
- Взаимодействие микросервисов реализовано через Kafka
- Реализован функционал перевода денег с одного счета на другой
- Реализовано api поиска пользователей по параметрам

## Стек технологий

- **Java 21**
- **Spring Boot**
- **PostgreSQL**
- **Hibernate**
- **Liquibase**
- **Kafka**
- **MapStruct**
- **Docker**
- **Swagger**
- **JUnit5**



