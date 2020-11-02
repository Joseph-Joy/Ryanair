#Author: Joseph Joy

@Chrome @Regression
Feature: Flight Booking
  This feature will enable user to book flight from the application

  Background: Login into application
    Given Login with gmail "joseph250395@gmail.com" and "pallenjoykurian"

  @Sanity
  Scenario: Verify decilned message
    Given I search filghts from "Dublin" to "London Luton" on "2020-11-08" for 2 adults and 1 child
    And I select the flight
    And I enter the adults information
      | Mr  | First Name One | Sur Name One |
      | Mrs | First Name Two | Sur Name Two |
    And I enter the child information
    | Kids | Joy |
    And I proceed to continue and checkout
    And I provide phoneNumber as "0899434539"
    And I enter CardPayment details as "5555 5555 5555 5557", "1/21" and "265" for "Joseph Joy" , "3 Ashlawn" , "Letterkenny" , "Ireland" , "f92avy1"
    When on clicking PayNow button with invalid values
    Then I should get payment declined message
