@ui @healthcheck
Feature: E-commerce Project Web Site Health Check

	Background: Navigation to Application BASE URL
 	Given User navigated to the home application url

	@SearchProd1
  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
  #  Given User opened browser
  # Given User navigated to the home application url
    When User Search for product "Mobiles"
    Then Search Result page is displayed with title contain "Mobiles"
  #  And Browser is closed
    
    @SearchProd2
     Scenario: User is able to Open the browser, navigate to the URL and Search for Product
   # Given User opened browser
   # Given User navigated to the home application url
    When User Search for product "Keyboards"
    Then Search Result page is displayed with title contain "Keyboards"
  #  And Browser is closed
    
    @ProdDetails
     Scenario: User is click on the Product and check the Product Details
   # Given User opened browser
   # Given User navigated to the home application url
    And User Search for product "Monitors"
    When User click on any product
    Then Product Description is displayed in new tab
   #  And Browser is closed
   
   @MultiSearch
   Scenario Outline: User is able to search multiple products
   	 When User Search for product "<product_name>"
   	Then Search Result page is displayed with title contain "<product_result>"
   	Examples:
   	|product_name  |product_result|
   	|mouse         |mouse           |
   	|earphone      |earphone        |
   	|computer      |computer        |