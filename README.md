# BusinessRuleEngine

Language : Java  version : Java 8   

This code will have a centralised Business rules Table which will have actions for each entity which will act as rules for that entity when it passes the application.

BUSINESS RULES ->                                                                                                



ENTITY                             ->                                                      ACTIONS                                                                           

book	                            ->                              shipping,royalty department                           

create membership	  ->             activateCreate,sendMail                                                           

product	                ->         shipping                                                       

upgrade membership	    ->         activateUpdate,sendMail                                                     

video	                   ->        video                                                  

As seen above Business Rules table.  This will allow the components to be mutually exclusive than being tightly coupled.
Each Action can be added to any other entity for code reusability.
Also going forward any action can also be removed from the table for an entity to restrict the action to be taken.
BUsiness rules can be added/modified/removed dynamically using this One BusinessRuleEngine .

Used Spring-Jdbc to connect to database.

Database : Mysql 8



CODE :

BusinessRules.java --> for implementing the processing logic for the rules.

Order.java ,Users.java , PackingSlip.java  --> beans used in the applications for Order information , user Informations and PackingSlip for each order and the message respectively.

OrdersDao , UsersDao , PackingSlipDao  --> respective Dao classes for database interactions.

Bean-Context.xml  --> for configuringt he Beans.

Mail.java --> used to Invoke the MailSender. Configurations used to support Gmail Server.

OrderTests.java --> Created Single Junit test class to do quick TDD from scratch.


	
