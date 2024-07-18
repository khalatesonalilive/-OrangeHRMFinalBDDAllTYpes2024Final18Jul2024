Feature: Test OrangeHRM Application

  Scenario: Test Browser Functionality
    Given user enter the "edge" browser and open the Login Url

  Scenario: Testing the Login Functionality
    When user enter valid credentials and click on login button
    Then user validate title
    And user validate url

  Scenario Outline: PIMPage Functionality
    When user click on pim link
    Then user validate the url using url
      | pim |
    And user click on the Add Employee link and enter "<firstname>","<lastname>" and click on save button
    And capture the Employee id and click on Employee list
    And enter employee id in employee id textbox and click on search button
    And select the search employee and click on delete
    And validate the user deleted or not

    Examples: 
      | firstname | lastname |
      | Shivanya  | Bhosale  |
      | Shanaya   | Sharma   |
      | Raj       | Malhotra |
