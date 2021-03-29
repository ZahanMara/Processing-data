# Processing-data

Consider designing, implementing and testing an application for analyzing the behavior of a person recorded by a set of sensors installed in its house. 
The historical log of the person’s activity is stored as tuples (start_time, end_time, activity_label), where start_time and end_time represent the date and time 
when each activity has started and ended while the activity label represents the type of activity performed by the person: Leaving, Toileting, Showering, Sleeping, Breakfast, Lunch, Dinner, Snack, Spare_Time/TV, Grooming. 
The data is spread over several days as many entries in the log Activities.txt.
This is a program that uses functional programming in Java with lambda expressions and stream processing to perform the tasks described below. 
The results of each task is written in a separate .txt file (each .txt file is named according to the following template task_number.txt, for example Task_1.txt). 
•	TASK_1 
Define a class MonitoredData with 3 fields: start time, end time and activity as string. Read the data from the file Activity.txt using streams and split each line in 3 parts: start_time, end_time and activity_label, and create a list of objects of type MonitoredData. 
•	TASK_2 
Count the distinct days that appear in the monitoring data. 
•	TASK_3 
Count how many times each activity has appeared over the entire monitoring period. Return a structure of type Map representing the mapping of each distinct activity to the number of occurrences in the log; therefore the key of the Map will represent a String object corresponding to the activity name, and the value will represent an Integer object corresponding to the number of times the activity has appeared over the monitoring period. 
•	TASK_4 
Count for how many times each activity has appeared for each day over the monitoring period. Return a structure of type Map> that contains the activity count for each day of the log; therefore the key of the Map will represent an Integer object corresponding to the number of the monitored day, and the value will represent a Map (in this map the key which is a String object corresponds to the name of the activity, and the value which is an Integer object corresponds to the number of times that activity has appeared within the day) 
•	TASK_5 
For each activity compute the entire duration over the monitoring period. Return a structure of type Map in which the key of the Map will represent a String object corresponding to the activity name, and the value will represent a LocalTime object corresponding to the entire duration of the activity over the monitoring period. 
•	TASK_6 
Filter the activities that have more than 90% of the monitoring records with duration less than 5 minutes, collect the results in a List containing only the distinct activity names and return the list.
