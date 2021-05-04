# project-service
In the interest of time the application is designed with simple get and save logic. It demonstrates the functionality requested in the problem statement. 
It is implemented with following features: 
* It exposes two endpoints as getProjectInfo and updateProject using Spring Boot Rest APIs. 
* It is built using Java 13. 
* It uses in memory H2 DB. 
* Aspect is used to log method start and end loggers. 
* It has Unit Testing, Integration Testing to cover all the Success and Error Test scenarios.  
* Update Api validates the fields for Null/Blank checks and throws appropriate errors.


## Software & Libraries

* Spring Boot, JPA, Java 13, mapstruct, lombok, In memory H2 Database, Git, Maven, Intellij(Eclipse can be used as well). 

    
    
## Plugins

* maven compiler plugin to generate implementation code for mapstruct interface.  

## Building and Deploying Application

* Checkout the project from this location : https://github.com/brijeshparashar/project_services
* Application can be built by using an IDE or by using maven command : mvn clean install.
* Run the application (using the ProjectApplication class) as a spring boot application from the IDE.
* The application on startup will create the DB tables and populate the tables with predefiend scripts using  /resources/schema.sql and /resources/data.sql.   
* Once the application is up it can be tested using the postman collection scripts.

## Testing the application -
* Get Project 
    * URL - http://localhost:8080/project/1
    * Method - GET
    * Request Body : blank
    * Response - 
        `{
		"projectId": 1,
		"projectName": "Project Progress Tracking Tool",
		"checkpoints": [
        {
            "checkpointId": 1001,
            "description": "Checkpoint: RFP Approval",
            "tasks": [
                {
                    "taskId": 101,
                    "taskDescription": "Task : Requirement Gathering",
                    "taskCompleted": true
                },
                {
                    "taskId": 102,
                    "taskDescription": "Task : Detailed Analysis",
                    "taskCompleted": true
                },
                {
                    "taskId": 103,
                    "taskDescription": "Task : Final approval from client",
                    "taskCompleted": false
                }
            ],
            "completionPercentage": 66.67
        },
        {
            "checkpointId": 1002,
            "description": "CheckPoint: Pre-requisites for starting a project",
            "tasks": [
                {
                    "taskId": 104,
                    "taskDescription": "Task : Budget Approval from Management",
                    "taskCompleted": true
                },
                {
                    "taskId": 105,
                    "taskDescription": "Task : Procuring Infrastructure & Hardware ",
                    "taskCompleted": false
                },
                {
                    "taskId": 106,
                    "taskDescription": "Task : Hiring Resources",
                    "taskCompleted": false
                }
            ],
            "completionPercentage": 33.33
        }
    ]
}`
 
* Update Project 
    * Endpoint - http://localhost:8080/project
    * Method - POST
    * Request Body -  
        {
		"projectId": 1,
		"projectName": "Project Progress Tracking Tool",
		"checkpoints": [
        {
            "checkpointId": 1001,
            "description": "Checkpoint: RFP Approval",
            "tasks": [
                {
                    "taskId": 101,
                    "taskDescription": "Task : Requirement Gathering",
                    "taskCompleted": true
                },
                {
                    "taskId": 102,
                    "taskDescription": "Task : Detailed Analysis",
                    "taskCompleted": true
                },
                {
                    "taskId": 103,
                    "taskDescription": "Task : Final approval from client",
                    "taskCompleted": false
                }
            ]
            
        },
        {
            "checkpointId": 1002,
            "description": "CheckPoint: Pre-requisites for starting a project",
            "tasks": [
                {
                    "taskId": 104,
                    "taskDescription": "Task : Budget Approval from Management",
                    "taskCompleted": true
                },
                {
                    "taskId": 105,
                    "taskDescription": "Task : Procuring Infrastructure & Hardware ",
                    "taskCompleted": false
                },
                {
                    "taskId": 106,
                    "taskDescription": "Task : Hiring Resources",
                    "taskCompleted": false
                }
            ]
        }
    ]
}
    * Success Response - 
			Status - 200 OK 
        `
         
         * Error response 
          {
				"errorCode": "SOME_ERROR_CODE",
				"errorMessage": "SOME_ERROR_MESSAGE",
				"errors": null
		  }
		  
		  

## Postman Link for testing.

(If any problems are faced in using this link then import the collection using the JSON attached with the email.) 
