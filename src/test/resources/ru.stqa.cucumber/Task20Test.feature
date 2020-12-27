#language:en

Feature: Add products to cart and delete them
  Scenario: : Add products to cart and delete them
    Given go to main page
    When add "3" random products to cart
    Then go to cart
    And delete all products from cart