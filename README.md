SIGL MEPA 2017
====

Continuous Integration
---------------
[![Build Status](https://travis-ci.org/sigl-mepa-2017/mepa.svg?branch=master)](https://travis-ci.org/sigl-mepa-2017/mepa)

Staging environment
---------------
https://mepa.herokuapp.com/

Members
---------------
* BEAUFILS Damien
* MARIN Florian

Requirements
---------------
* JDK 8
* Maven >= 3.2

How to run it
---------------
* Compile the whole project:
```
mvn clean install
```
* Run Tomcat server:
```
mvn tomcat7:run -pl mepa-front
```
* Open `http://localhost:8080/` in your favorite web browser

Learn how to use Git
---------------
https://pcottle.github.io/learnGitBranching/
