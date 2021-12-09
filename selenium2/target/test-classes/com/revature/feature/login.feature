Feature: Login

# " " double quotes are used as inline parameters for the actual gluecode methods. It will be passed in as arguments

Scenario: Invalid password, valid username (negative test)
	Given I am at the login page
	When I type in a username of "gamal" 
	But I type in a password of "jlkjljsdf"
	And I click the login button
	Then I should see a message of "Incorrect username and/or password"
	
	Scenario:  password, invalid username (negative test)
	Given I am at the login page
	When I type in a username of "xxxx" 
	But I type in a password of "gamal"
	And I click the login button
	Then I should see a message of "Incorrect username and/or password"
	
Scenario:  invalidpassword, invalid username (negative test)
	Given I am at the login page
	When I type in a username of "xxxx" 
	But I type in a password of "xxx"
	And I click the login button
	Then I should see a message of "Incorrect username and/or password"
	
	Scenario:  validpassword, valid username (negative test)
	Given I am at the login page
	When I type in a username of "gamal" 
	But I type in a password of "gamal"
	And I click the login button
	Then I should directed to the regular homepage