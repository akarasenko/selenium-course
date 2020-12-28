#language:en

Feature: Add products to cart and delete them
  Scenario: : Add products to cart
    Given go to main page
    When add "3" random products to cart

  Scenario: Remove all products from cart
    Given go to cart
    When delete all products from cart
    Then cart is empty