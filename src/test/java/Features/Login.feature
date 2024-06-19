Feature: User Login

  Scenario: User navigates to Login page
    Given User enters Email id 
    When User enters Password
    When User press Submit button
    Then Page title should contain "nopCommerce demo store"