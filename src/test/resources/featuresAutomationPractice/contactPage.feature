Feature: ContactPage feature 
	ContactPage in website http://automationpractice.com/index.php?

@test1 
Scenario: navigate to Contact page 
	Given I am on the main page
	When I click Contact us button
	Then I am taken to the Contact page
	And driver is closed 
	
@test2
Scenario: send contact message
	Given I navigate to the contact page
	And I fill out the form
	When I click the send button on the contact page
	Then the message is sent
	And driver is closed