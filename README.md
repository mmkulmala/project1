# project1
Project 1 for Cybersec MOOC 2017. This project is very simple Spring-boot-hibernate app which holds a SQL injection and ways to fix it.

### Things to do before app works
1. Install mysql (any mean you can and know how.. but just do base install with default passwd)
2. After install do this:
  * mysql --user=root --password (press enter)
  * create database messagedb; (enter)

And now you can run the app!

## Running the app
This can be done from console by going to root (where's the pom.xml file is..) and typing: mvn spring-boot:run or from your fav ide (run option).

## Add data
To add something so you can fetch it do the following:
Go to mysql shell and type:
* insert into messages(content) values ('test content');
Or use REST endpoint (curl or postman - your choice):
* curl -d '{"content":"some content"}' -H "Content-Type: application/json" -X POST http://localhost:8096/user/message
* curl -d '{"content":"more content"}' -H "Content-Type: application/json" -X POST http://localhost:8096/user/message

## Testing injection and fix
* curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8096/user/message_old?id=' or '1'='1

Fixed call should be:
* curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8096/user/message/1

