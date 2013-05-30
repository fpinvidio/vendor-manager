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

####TODO
---

1.ClipStorage Integration