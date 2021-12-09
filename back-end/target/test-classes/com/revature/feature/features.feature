Feature: Authentication

Scenario: authenticate username and password (negative test)
					Given i am at login page
					When i type username into username input
					And i click login-btn
					Then Message displayed incorrect username and/or password