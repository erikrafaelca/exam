The assignment was designed in the following layers:

1- Domain: here you can find the User.java entity
2- Repository: here the DAO layer allow persist the 
User entity through the UserRepository class
3- Service: here the following class are available:
	3.1: MessageByLocalService: allows to access the message.properties data
	3.2: UserDetailsImpl: class used to pass User entity data to security model
	3.3: UserService: Interface of the Business Logic with services provided to User
	3.4: UserServiceImpl: Implementation of User services
4- Controller: UserController exposes the endpoints to manage Users.
Here we also defined a class to handle Exception thrown by controllers
through ExceptionControllerAdvice.java
5- Exception: a class to wrap exceptions and expose to user can be found here
6- Config: classes to config security and second level cache can be found here


With respect to the implementation:
1- From all items required just the 4th was not implemented.
2- Hazelcast was initiated but not completed
3- It was used Eclipse, Spring-boot 1.5.3, and embedded Tomcat
4- The login/password to access and manage users is admin/admin
5- login/logout is available on the app
