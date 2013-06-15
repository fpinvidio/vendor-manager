vendor-manager
==============

---
###VendorManager module for UMFlix

---
**Importing VendorManager - Dependency**

1. git pull https://github.com/fpinvidio/vendor-manager.git to target directory
2. cd target directory
3. mvn clean install
4. cd current project
5. add dependency to pom.xml
<br />
	`<dependency>`<br />
	    &nbsp;&nbsp;&nbsp;&nbsp;`<groupId>vendor-manager</groupId>`<br />
        &nbsp;&nbsp;&nbsp;&nbsp;`<artifactId>vendor-manager</artifactId>`<br />
        &nbsp;&nbsp;&nbsp;&nbsp;`<version>1.0-SNAPSHOT</version>`<br />
    `</dependency>`
6. mvn install current project

**Deploying VendorManager - Release v1.0**

1. download https://github.com/fpinvidio/vendor-manager/archive/v1.0.tar.gz and extract
2. cd extract directory
3. mvn clean install
4. cd target directory
5. copy .war to webapps folder
6. for viewing wsdl go to http://127.0.0.1:8080/vendor-manager-1.0-SNAPSHOT/webservices/VendorManager?wsdl
7. if you are unable to access wsdl run shutdown.sh and startup.sh scripts in tomee and try again