<h1 align="center"> Atm location service </h1> <br>

## Table of Contents

- [Introduction](#introduction)
- [Requirements](#requirements)
- [Quick Start](#quick-start)
- [Testing](#testing)

## Introduction

This service will give information about atm location information along with address etc.

## Requirements
The application can be run locally, the requirements for each setup listed below.

### Local
* [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/download.cgi)
* [GIT](https://git-scm.com/downloads)
* [PostMan](https://www.postman.com/downloads/)

After installing the above prerequisites please add bin folder location to path variable

###Steps to set up the project

1) Open command prompt or git bash & clone the repository by using below command
```bash
git clone https://github.com/charanteja01/atm-service
```

2) Go to 
```bash
cd <Location>/atm-service
```
3) Run below command to build the project
```bash
mvn clean install
```

## Quick Start

### Run the service in local by using any one of the below commands
```bash
$ mvn spring-boot:run
```
       Or
```
java -jar target/atm-service-0.0.1-SNAPSHOT.jar
```
Application will run on port `9090`

## Testing
Please Make use of postman collection placed in below location
```
Go to <Location>/atm-service/postmancollection
```

Open postman and navigate to following menu options to import collection into post man
```
File -> Import -> Select post man collection in <Location>/atm-service/postmancollection 
```