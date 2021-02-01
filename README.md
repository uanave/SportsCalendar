# SportsCalendar
Calendar for sport events. Events can be added to the calendar,
and it is possible to categorize the events based on sports.<br/>
Examples:<br/>
• Sat., 18.07.2019, 18:30, Football, Salzburg – Sturm<br/>
• Sun., 23.10.2019, 09:45, Ice Hockey, KAC – Capitals

****Prerequisites:****
- running MySql instance
- a user that can create a database
- configure the environment variables

Run `env SPRING_DATASOURCE_URL="jdbc:mysql://localhost/calendar?useSSL=false&createDatabaseIfNotExist=true" SPRING_DATASOURCE_USERNAME=calendar SPRING_DATASOURCE_PASSWORD=calendar SPRING_SECURITY_USER_NAME=admin SPRING_SECURITY_USER_PASSWORD=admin java -jar SportsCalendar-0.0.1-SNAPSHOT.jar`
