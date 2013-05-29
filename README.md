vendor-manager
==============

VendorManager module for UMFlix

Importing VendorManager - Dependency
 ------------------------------------------

1. git pull $url target</li>
2. cd target</li>
3. mvn clean install</li>
4. cd current project</li>
5. add dependency to pom.xml</li>
		<dependency>
            <groupId>org.apache.openejb</groupId>
            <artifactId>javaee-api</artifactId>
            <version>6.0-4</version>
            <scope>provided</scope>
        </dependency>
6. mvn install current project