SIGL MEPA 2017
====

Continuous Integration
---------------
[![Build Status](https://travis-ci.org/sigl-mepa-2017/mepa.svg?branch=master)](https://travis-ci.org/sigl-mepa-2017/mepa)
https://travis-ci.org/sigl-mepa-2017/mepa

Staging environment
---------------
[![Heroku](http://heroku-badge.herokuapp.com/?app=mepa&style=flat&svg=1)](https://mepa.herokuapp.com/)
https://mepa.herokuapp.com/

Members
---------------
* CHEMOULA Jacky
* BEAUFILS Damien
* MARIN Florian
* WAUQUIEZ Simon
* SAYAGH Tahar
* DE CHENERILLES Gregoire
* MACE Simon
* GOASDOUE Jordan
* ZHENG Valentin
* CLAEBOTS Charles
* LEBEL Gildas
* ASSAADI Ayoub
* CAPES Hugo
* GHIRIGHINI Steven
* GIRALDO Alonso
* DENIS Antoine
* LASNE Guillaume

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
* Run webapp:
```
java -jar mepa-front/target/dependency/webapp-runner.jar --port 8080 mepa-front/target/*.war
```
* Open `http://localhost:8080/` in your favorite web browser

Learn how to use Git
---------------
https://pcottle.github.io/learnGitBranching/
