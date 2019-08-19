#Author: your.email@your.domain.com

@tag
Feature: Title of your feature
	Shopping in website http://automationpractice.com

@tag1
Scenario Outline: Login in to website 
Given I click on sign in page
And   Login page is displayed
When  I enter <username> 
And <password> and sign in 
Then  Verify <logged> into account

Examples:
    |username                  | password |        logged    |
    |seleniumtraining@gmail.com| planit123|  planit training |

    
   