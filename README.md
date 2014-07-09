dropwizard-jdbi-template
========================

Project template for a REST service exposed through dropwizard and backed by an embedded Derby accessed through JDBI.

This contains,

- Dropwizard application exposing REST server
- UserDAO which uses JDBI to interact with database
- Embedded Derby integration
- Sample test clients

which works together out-of-the-box.

Usage
=====

To start the server,

- create the distributable by running "mvn clean install"
- move to target folder and run "java -jar dropwizard-jdbi-template-1.0-SNAPSHOT.jar server ../myapp.yaml"

The server will start at port 8080. You can use UserResourceClient to interact with it. You can also get into admin interface by going to http://localhost:8081



