#language:en

Feature: Add products to cart and delete them
  Scenario: : Add products to cart
    When go to main page
    And add random product to cart
    And go to main page

  Scenario: Delete product from cart
    When go to cart
    And delete random product from cart