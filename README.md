ROBOT APOCALYPSE API
--------------------
[comment]: <> (Minimal [Spring Boot]&#40;http://projects.spring.io/spring-boot/&#41; sample app.)


The year is 2050 and the world as we know it has been taken over by robots. Created as once friendly robots, have now turned against humankind, especially software engineers like yourself. Their mission is to transform everyone into mindless zombies for their entertainment. You as a resistance member (and the last survivor who knows how to code), was designated to develop a system to meet the following requirements

You will develop a REST API (yes, we care about architecture design even in the midst of a robot apocalypse!) which will store information about the survivors, as well as the resources they own.

In order to accomplish this, the API must fulfil the following use cases:

Add survivors to the database
A survivor must have a name, age, gender, ID and last location (latitude, longitude).
A survivor also has an inventory of resources (which you need to declare upon the registration of the survivor). This can include Water, Food, Medication and Ammunition.

Update survivor location
A survivor must have the ability to update their last location, storing the new latitude/longitude pair in the base (no need to track locations, just replacing the previous one is enough).

Flag survivor as infected
In a chaotic situation like this, it's inevitable that a survivor may get transformed into a zombie. When this happens, we need to flag the survivor as infected.
A survivor is marked as infected when at least three other survivors report their contamination.

Connect to the Robot CPU system
Connect to the robot CPU system to get a list of all robots and their known locations. Robots have two categories (Flying robots and land robots). You need to sort this information in a meaningful and intuitive way for humans to understand and process. You can use this link to get the list of robots
https://robotstakeover20210903110417.azurewebsites.net/robotcpu

Reports
The API must also provide the following information:

• Percentage of infected survivors.

• Percentage of non-infected survivors.

• List of infected survivors

• List of non-infected survivors

• List of robots



## Requirements

For building and running the application you need:

- [JDK 11](https://download.oracle.com/otn/java/jdk/11.0.13%2B10/bdde8881e2e3437baa70044f884d2d67/jdk-11.0.13_windows-x64_bin.exe)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.api.robotapocalypse.RobotApocalypseApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

### DOCUMENTATION

To view Documentation on the **ROBOT APOCALYPSE API**. Kindly go to the path below and open **`index.html`** with any browser of your choice.
```shell
target/generated-docs
```


API ACCESS 
--------

#### POST CREATE SURVIVOR

```
POST::http://localhost:8080/api/v1/robot-apocalypse/save-survivor
```
JSON Request:

```json
{
  "survival":{
    "name":"STEVE JOBS",
    "age": 52,
    "gender": "MALE",
    "isInfected": false
  },
  "inventories":[
    {"resource":"COMPUTER"}, 
    {"resource":"DESK"}, 
    {"resource":"KNIFE"}
    ],
  "location": {
    "longitude":30.333,
    "latitude": 32.4442
  }
}

```


JSON Response:

```json
{
  "id": 4,
  "name": "STEVE JOBS",
  "age": 52,
  "gender": "MALE",
  "isInfected": false,
  "noOfFlags": 0
}
```


#### POST UPDATE SURVIVAL LOCATION


```
POST::http://localhost:8080/api/v1/robot-apocalypse/update-survivor-location
```


JSON Request:

```json
{
  "survivalId" : 1,
  "longitude" : "30.449",
  "latitude" : "11.112"
}

```

JSON Response:

```json
{
  "id" : 1,
  "latitude" : "11.112",
  "longitude" : "30.449",
  "survival" : {
    "id" : 1,
    "name" : "Mobile Stock",
    "age" : 20,
    "gender" : "MALE",
    "isInfected" : false,
    "noOfFlags" : 1
  }
}
```

#### GET INDICATED INFECTED SURVIVAL

```
GET::http://localhost:8080/api/v1/robot-apocalypse/flag-infected-survivor/1
```

JSON Response:

```
Survivor Not Infected
```


#### GET PERCENTAGE OF INFECTED SURVIVOR

```
GET::http://localhost:8080/api/v1/robot-apocalypse/percentage-of-infected-survivor
```

JSON Response:

```json
{
  "PercentageOfInfectedSurvivor" : 0.0
}
```


#### GET PERCENTAGE OF NON INFECTED SURVIVOR

```
GET::http://localhost:8080/api/v1/robot-apocalypse/percentage-of-non-infected-survivor
```

JSON Response:

```json
{
  "PercentageOfNonInfectedSurvivor" : 100.0
}
```


#### GET LIST OF INFECTED SURVIVOR

```
GET::http://localhost:8080/api/v1/robot-apocalypse/list-of-infected-survivor
```

JSON Response:

```json
[ ]
```


#### GET LIST OF NON INFECTED SURVIVOR

```
GET::http://localhost:8080/api/v1/robot-apocalypse/list-of-non-infected-survivor
```

JSON Response:

```json
[ {
  "id" : 1,
  "name" : "Mobile Stock",
  "age" : 20,
  "gender" : "MALE",
  "isInfected" : false,
  "noOfFlags" : 1
}, {
  "id" : 2,
  "name" : "Apple Stock",
  "age" : 30,
  "gender" : "FEMALE",
  "isInfected" : false,
  "noOfFlags" : 0
}, {
  "id" : 3,
  "name" : "Samsung Stock",
  "age" : 40,
  "gender" : "MALE",
  "isInfected" : false,
  "noOfFlags" : 0
} ]
```


#### GET ALL ROBOT CPU

```
GET::http://localhost:8080/api/stocks/1
```

JSON Response:

```json
[ {
  "model" : "0J91K",
  "serialNumber" : "8OBG94GB8X58YRN",
  "manufacturedDate" : "2022-03-10T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "3O4B2",
  "serialNumber" : "V3L7R5P3IPYDPPG",
  "manufacturedDate" : "2022-03-03T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "59PFA",
  "serialNumber" : "PRTFSK5FFZGCB1R",
  "manufacturedDate" : "2022-03-21T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "5CCRV",
  "serialNumber" : "4WK28V6XN49JXY6",
  "manufacturedDate" : "2022-04-06T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "60QB2",
  "serialNumber" : "VKMXPV7B7ZUBYKX",
  "manufacturedDate" : "2022-03-29T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "6GFZX",
  "serialNumber" : "TP8X3ET6ZUB16QO",
  "manufacturedDate" : "2022-03-23T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "7Y2UU",
  "serialNumber" : "4QPAPR8DWBCZC3N",
  "manufacturedDate" : "2022-04-04T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "8625Z",
  "serialNumber" : "AAMHYOGYFTIJIR2",
  "manufacturedDate" : "2022-04-02T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "9UHKG",
  "serialNumber" : "P1APRKQ05QQPSEZ",
  "manufacturedDate" : "2022-04-15T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "A9CGV",
  "serialNumber" : "MFEK5PP7IWJ2JL6",
  "manufacturedDate" : "2022-04-08T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "AWZ30",
  "serialNumber" : "N6QZEQCWU39LLI8",
  "manufacturedDate" : "2022-03-31T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "AYV56",
  "serialNumber" : "COI7SXFGRJRNXHD",
  "manufacturedDate" : "2022-03-09T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "B4SQY",
  "serialNumber" : "RQZHYER7N5ON6U9",
  "manufacturedDate" : "2022-04-17T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "B7AXU",
  "serialNumber" : "AAIXF4K9EHROB1X",
  "manufacturedDate" : "2022-03-07T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "B8NQX",
  "serialNumber" : "04C6C0YFJRZEWBU",
  "manufacturedDate" : "2022-03-16T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "BB30I",
  "serialNumber" : "0ZOXQ39A5D0Z3V7",
  "manufacturedDate" : "2022-03-11T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "BUGKZ",
  "serialNumber" : "GGRZDABTFJ3WCWO",
  "manufacturedDate" : "2022-04-16T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "EY5T4",
  "serialNumber" : "A7IA0NNMEABG8E3",
  "manufacturedDate" : "2022-03-06T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "F0K0F",
  "serialNumber" : "E4IU6X50HX3MZFG",
  "manufacturedDate" : "2022-04-21T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "FJUWY",
  "serialNumber" : "R2UE33W4J7C4A08",
  "manufacturedDate" : "2022-03-05T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "G4ITZ",
  "serialNumber" : "VEAT5P43C1J5E62",
  "manufacturedDate" : "2022-03-22T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "GFN01",
  "serialNumber" : "57DR0R9XRRPH6WH",
  "manufacturedDate" : "2022-03-25T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "I1MQM",
  "serialNumber" : "8AAJ9BEUTX0HVOO",
  "manufacturedDate" : "2022-04-01T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "J3L8S",
  "serialNumber" : "6TFJXN0F3YADXCI",
  "manufacturedDate" : "2022-04-13T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "JAO0G",
  "serialNumber" : "5FG46GFDUZY95C1",
  "manufacturedDate" : "2022-03-13T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "JKLMR",
  "serialNumber" : "J6UVW3N5ZXGCZWI",
  "manufacturedDate" : "2022-04-18T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "JLJ6I",
  "serialNumber" : "N0LH8PVDM9GUYH0",
  "manufacturedDate" : "2022-03-20T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "K6N7I",
  "serialNumber" : "NVVENZUG171QE2B",
  "manufacturedDate" : "2022-03-15T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "L1U3K",
  "serialNumber" : "3RFQOW4RUHYLZDL",
  "manufacturedDate" : "2022-03-04T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "LQ9JC",
  "serialNumber" : "QCDNQL7F8U9YT5F",
  "manufacturedDate" : "2022-03-24T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "MEJ51",
  "serialNumber" : "DKLMA0ZCQNB9YRO",
  "manufacturedDate" : "2022-04-11T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "N3N34",
  "serialNumber" : "358887GUXEUACD6",
  "manufacturedDate" : "2022-04-14T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "N5H0C",
  "serialNumber" : "LSC7C2063AL9Q1M",
  "manufacturedDate" : "2022-04-03T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "N7OF2",
  "serialNumber" : "4NS9GCX7BY0S6D1",
  "manufacturedDate" : "2022-03-30T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "NXTP8",
  "serialNumber" : "TAA4PELEW6VTDG4",
  "manufacturedDate" : "2022-04-09T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "OLBW3",
  "serialNumber" : "SZR7TG64ESOD2E4",
  "manufacturedDate" : "2022-04-12T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "R976M",
  "serialNumber" : "K8MILL7OPYR17IG",
  "manufacturedDate" : "2022-03-28T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "RL8A5",
  "serialNumber" : "6FPREGIMTCASA2X",
  "manufacturedDate" : "2022-03-19T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "RMVHS",
  "serialNumber" : "GO7FUNHALIFDJSH",
  "manufacturedDate" : "2022-03-26T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "S7PE6",
  "serialNumber" : "XK4FHJ2RE5YCWFH",
  "manufacturedDate" : "2022-04-05T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "STZCX",
  "serialNumber" : "BP7KHX3DHS36828",
  "manufacturedDate" : "2022-03-18T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "T9VX8",
  "serialNumber" : "LKXRE217YX3UFHU",
  "manufacturedDate" : "2022-03-27T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "TICPY",
  "serialNumber" : "YITE6H3SZ1MT6KR",
  "manufacturedDate" : "2022-03-17T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "TMY57",
  "serialNumber" : "UEY9IWVWXYMPWIC",
  "manufacturedDate" : "2022-04-10T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "TTTNP",
  "serialNumber" : "QJ0I555577HXI93",
  "manufacturedDate" : "2022-03-08T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "UVK2G",
  "serialNumber" : "YQ80M9C6WSP57TC",
  "manufacturedDate" : "2022-04-07T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "V6EU4",
  "serialNumber" : "1VI0YMXJMJ3AKN8",
  "manufacturedDate" : "2022-04-20T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "WRUJ7",
  "serialNumber" : "E3G3NJPKBXX8L02",
  "manufacturedDate" : "2022-03-14T01:28:41.464+00:00",
  "category" : "Land"
}, {
  "model" : "XMB7X",
  "serialNumber" : "QDI4OSYF3CZQJTT",
  "manufacturedDate" : "2022-03-12T01:28:41.464+00:00",
  "category" : "Flying"
}, {
  "model" : "ZQKA8",
  "serialNumber" : "SDFK5QZSXF1SG9P",
  "manufacturedDate" : "2022-04-19T01:28:41.464+00:00",
  "category" : "Land"
} ]
```

### License

Apache License, Version 2.0
