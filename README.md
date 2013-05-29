vendor-manager
==============

---
###VendorManager module for UMFlix

---
**Importing VendorManager - Dependency**

1. git pull $url target
2. cd target
3. mvn clean install
4. cd current project
5. add dependency to pom.xml
<br />
	`<dependency>`<br />
	    &nbsp;&nbsp;&nbsp;&nbsp;`<groupId>org.apache.openejb</groupId>`<br />
        &nbsp;&nbsp;&nbsp;&nbsp;`<artifactId>javaee-api</artifactId>`<br />
        &nbsp;&nbsp;&nbsp;&nbsp;`<version>6.0-4</version>`<br />
        &nbsp;&nbsp;&nbsp;&nbsp;`<scope>provided</scope>`<br />
    `</dependency>`
6. mvn install current project