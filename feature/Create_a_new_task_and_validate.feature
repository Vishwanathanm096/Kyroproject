#Assessment

Feature: Create_a_new_task_and_validate
  

  Scenario: Verify the created new task in the view task 
    Given Login into the Kyro Application
    And Navigate to Dashboard and then navigate to Kyro Version 4.0
    When Create a new task 
    Then validate the new task created in the list view 
  
    
