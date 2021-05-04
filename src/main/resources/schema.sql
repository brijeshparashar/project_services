DROP TABLE IF EXISTS Task;
DROP TABLE IF EXISTS Checkpoint;
DROP TABLE IF EXISTS Project;


CREATE TABLE Project
(
    projectId   INT AUTO_INCREMENT PRIMARY KEY,
    projectName VARCHAR(100)

);
CREATE TABLE Checkpoint
(
    checkpointId INT AUTO_INCREMENT PRIMARY KEY,
    description  VARCHAR(100),
    projectId    INT,
    constraint FK_Project_Id foreign key (projectId) references Project (projectId)
);

CREATE TABLE Task
(
    taskId          INT AUTO_INCREMENT PRIMARY KEY,
    taskDescription VARCHAR(100),
    checkpointId    INT,
    taskCompleted   BIT,
    constraint FK_Checkpoint_Id foreign key (checkpointId) references Checkpoint (checkpointId)
);

