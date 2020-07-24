@FeatureFileWithDifferentTests
  Feature: Testing different websites

      @demoAmazon
      Scenario: Test Amazon website
        Given I can navigate to "https:\\amazon.com" website
        When I search for "coffee machine" product in the Search bar
        And I sort the search based on "Avg. Customer Review"
        And I click on the "1" article in the search list
        And I add the item to the cart
        And I check if any similar products are with lower price are available
        And I delete the item with the highest price from cart
