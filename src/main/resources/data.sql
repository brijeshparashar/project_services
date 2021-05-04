/*Insert Project record*/
INSERT INTO Project (projectId, projectName) VALUES (1,'Project Progress Tracking Tool');

/*Insert Checkpoint records*/
INSERT INTO Checkpoint ( checkpointId, description,projectId) VALUES (1001,'Checkpoint: RFP Approval',1);
INSERT INTO Checkpoint ( checkpointId, description,projectId) VALUES (1002,'CheckPoint: Pre-requisites for starting a project',1);

/*Insert Task records*/
INSERT INTO Task (taskId, taskDescription, checkpointId, taskCompleted)  VALUES (101,'Task : Requirement Gathering',1001,1);
INSERT INTO Task (taskId, taskDescription, checkpointId, taskCompleted)  VALUES (102,'Task : Detailed Analysis',1001,1);
INSERT INTO Task (taskId, taskDescription, checkpointId, taskCompleted)  VALUES (103,'Task : Final approval from client',1001,0);
INSERT INTO Task (taskId, taskDescription, checkpointId, taskCompleted)  VALUES (104,'Task : Budget Approval from Management',1002,1);
INSERT INTO Task (taskId, taskDescription, checkpointId, taskCompleted)  VALUES (105,'Task : Procuring Infrastructure & Hardware ',1002,0);
INSERT INTO Task (taskId, taskDescription, checkpointId, taskCompleted)  VALUES (106,'Task : Hiring Resources',1002,0);
