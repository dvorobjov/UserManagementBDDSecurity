@authentication
Feature: Authentication
  Verify that the authentication system is robust

  @iriusrisk-cwe-525-repost
  Scenario: When authentication credentials are sent to the server, it should respond with a response page.
    Given a new browser instance
    When the user logs in
    Then the response page is exists
