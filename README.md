﻿# PatientSchedulerLibrary

This is a shared library used by the client and server applications. I chose to use a shared library in order to make the codebase more maintainable. Both applications use some of the same classes and it is evident that those classes should be maintained in one project. Furthermore, where both applications both implement services or classes which distinctly execute some of the same functionality, eg their database classes, those classes should adhere to the same interface. The ValidServerRequests Enum also acts as a contract for both the server and client to adhere to. This means that the client cannot send an invalid request to the server and also ensures that the server will not have to handle any malformed requests. 

The ORM handles all persitance of objects to the database. Any class which inherits from the ObjectMapper class can call the ObjectMapper's save method. The ORM uses certain annotations to create the insert or update statements for the database to execute.

|Annotation|Use Case|Metadata|
|-----|------|------|
|@TableName|Name of the table this class should use|String name="name of table"|
|@Ignore|Not be persisted|None|
|@PrimaryKey|Object's primary key|None|
|@ForeignKey|Related object's ForeignKey|String cls="The class name", String columnName="The column name", Boolean persistNestedClasses|
