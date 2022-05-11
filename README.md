# s-irk-buyers-club
Сбор данных из ОФД-чеков

## Приступая к работе
Клонировать проект
```
git clone https://github.com/croacker/s-irk-buyers-club
```

## Предварительные условия
* JDK 17

## Сборка
```
mvnw clean package
```

## Сборка Docker-образа
```
./mvnw clean package -P docker
```

## Конфигурирование
* DB_NAME - имя БД, при использовании PostgreSQL
* DB_USERNAME - имя пользователя БД, при использовании PostgreSQL
* DB_PASSWORD - пароль пользователя БД, при использовании PostgreSQL
* TELEGRAM_BOT_TOKEN - токен telegram-бота
* TELEGRAM_BOT_USERNAME - имя пользователя telegram-бота

## Разработка
Для разработки, с H2 в качестве БД 
```--spring.profiles.active=dev``` 

## swagger-ui
```http://localhost:8080/webjars/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config```