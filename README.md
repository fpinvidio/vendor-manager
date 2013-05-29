vendor-manager
==============

VendorManager module for UMFlix

/** Importing VendorManager - Dependency **/
 ------------------------------------------

1 - git pull $url target
2 - cd target
3 - mvn clean install
4 - cd current project
5 - add dependency to pom.xml
        <dependency>
            <groupId>org.apache.openejb</groupId>
            <artifactId>javaee-api</artifactId>
            <version>6.0-4</version>
            <scope>provided</scope>
        </dependency>
6 - mvn install current project