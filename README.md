
<div>

## Дипломная работа "Разработка интернет-магазина на Java с использованием технологий Spring"
</div>

---
### Разработчик
- [Муслимов Владислав](https://github.com/MuVlad)

---
## Описание проекта и его функциональность

Бекэнд реализован в виде Spring Boot REST API, используется БД PostgreSQL в контейнере Docker.
Для миграций используется Liquibase.
Документация к API генерируется с помощью Swagger. Для просмотра документации открыть адрес:

http://localhost:8080/swagger-ui/index.html

### Реализованы следующие функции:

- Авторизация и аутентификация пользователей;
- CRUD-операции для управления продуктами;
- Контроллер для управления корзиной;
- Контроллер для управления пользователями;

---
## Запуск приложения
* Для запуска приложения Вам потребуется выполнить несколько шагов:
    - Клонировать проект и открыть его в среде разработки (например, *IntelliJ IDEA* или *VSCode*);
    - В файле **application.yml** указать путь к Вашей базе данных;
    - Запустить метод **main** в файле **SpringOnlineStoreApplication.java**.

---
## Стэк технологий
* **Backend**:
    - Java 17
    - Maven
    - Spring Boot
    - Spring Web
    - Spring Data JPA
    - Spring Security
    - Stream API
    - REST
    - GIT
    - Swagger
    - Lombok
    - Mapstruck
* **SQL**:
    - PostgreSQL
    - Liquibase
* **Test**:
    - Junit
    - Mockito
