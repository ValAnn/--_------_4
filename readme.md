Swagger UI: \
http://localhost:8080/swagger-ui/index.html

H2 Console: \
http://localhost:8080/h2-console

JDBC URL: jdbc:h2:file:./data \
User Name: sa \
Password: password

Для профиля prod необходимо наличие СУБД PostgreSQL и БД demo-app.

Создать БД можно так: \
createdb -h localhost -U postgres demo-app

Пример запуска приложения с профилем prod и заполнением БД с помощью аргумента --populate:

- nix => bash ./gradlew.bat -Pprod -Pargs='--populate'
- windows =>.\gradlew.bat -Pprod -Pargs='--populate'

Почитать:

- Односторонние и двусторонние связи https://www.baeldung.com/jpa-hibernate-associations
- Getters и Setters для двусторонних связей https://en.wikibooks.org/wiki/Java_Persistence/OneToMany#Getters_and_Setters
- Многие-ко-многим с доп. атрибутами https://thorben-janssen.com/hibernate-tip-many-to-many-association-with-additional-attributes/
- Многие-ко-многим с доп. атрибутами https://www.baeldung.com/jpa-many-to-many
- Каскадное удаление сущностей со связями многие-ко-многим https://www.baeldung.com/jpa-remove-entity-many-to-many
- Выбор типа коллекции для отношений вида ко-многим в JPA https://thorben-janssen.com/association-mappings-bag-list-set/
