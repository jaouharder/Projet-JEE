# Projet-JEE


---

## GET THIS APP USING GIT

### Requirements

+ An IDE to execute Spring Boot apps
+ Local development server (XAMPP/WAMP) must includes MySql.

### How to install

1. Download or clone this repository
2. Import ReservationSystem to your IDE as Spring Project
3. Import ***Reservationdb.sql***  to your local MySql databases
4. In your IDE navigate to ```src/main/resources/application.properties``` and configure your mail (by default we're using smtp protocol you can use your own or just configure your account to it), then configure your adress and port to Mysql server. 
5. Build that project ***Maven BUILD***
6. Finally run it as ***SPRING BOOT APP***

Navigate to ```Front PROJECT/index.html``` and that's it 

---

## GET THIS APP USING DOCKER

1. pull the application image from docker hub with the command :``` docker pull xenophobe1999/spring-reservation_final  ```
2. Use MySQL Image published by Docker Hub (https://hub.docker.com/_/mysql/) Command to run the mysql container:
```docker run --name db_container -p 3307:3307 -d mysqlimg where “mysqlimg” is the name of mysql image```
3. create mysql database user with username ***xenophobe1*** and password ***xeno1*** and grant all privileges to him.Command to do that :
```CREATE USER 'xenophobe1'@'%' IDENTIFIED BY 'xeno1';```
```GRANT ALL PRIVILEGES ON *.* TO 'xenophobe1'@'%'; ```
4. create the database ***reservationdb*** .Use our script ***Reservationdb.sql*** to create the database tables automatically 
5. Run the application image in a container.use the following command to do that:
```docker run -p 8080:8080 --name final-app --link db_container:mysql -d app_image```
where “final_app” is the name of your  containerand “app_image ” is the name of the spring boot application image.
6. download our front end project from our repository github and launch the application by clicking to ***index.html*** file 
7. Have a good test and enjoy making reservation with our application

