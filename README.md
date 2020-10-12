# Pixelogic-task
automation task requested by pixelogic 

Setting up the project:

  Driver used:
    Gecko driver, make sure the path is set up correctly in the environment variables so it can run. 

  Dependencies used: 
    TestNg 
    Rest assured 
    Maven 

Note: pom.xml file includes the needed dependencies except for testNg which needs to be installed prior to running the task (if you are using eclipse you can find it in the market place in the help menu)

Code Flow of Pixelogic task:
  The testing task is consisted of two classes
  
  Base class: 
    It contains a private method random string generator depending on the size given to it and the string used to generate the result.
    
  Sign up class:
    Sign up class inherits from the base class and it contains:
    
  @Before class set up method that sets up the following:
    It’s used to set up the web driver and the disabled options in the gecko driver Which is used to have the website on Sign up class. 
   
  @Before Method:
    findElements method is used to locate the elements on the sign up page 
    
  @Test (priority=1):
    signUpSuccessfully method is used to do a successful sign up process, assert on the creation of account and that response code is 200 
    
  @Test (priority=2):
    loginSuccessfully method is used to do a successful log in, assert on the response code 
    
  @Test(priority=3)
    registeringWithUsedEmailAssertion method is used to assert  that a user can’t register with a used email address. 
    
  @Test(priority=4)
    registerWithInvalidEmailAddress method is used to assert that a user can’t register with an email address that doesn’t contain @
    
  @Test(priority = 5)
      registerWithInvalidEmailAddressWithoutcom method is used to assert that a user can’t register with an email address that doesn’t contain .com
      
  @Test(priority = 6)
      smallLetterInFirstNameAssertion method is used to assert that a user can’t register with a first name that starts with small letter.
      
  @Test(priority=7)
      smallLetterInLastNameAssertion method is used to assert that a user can’t register with a last name that starts with a small letter.
      
  @Test(priority = 8)
      firstNameEqualLastNameAssertion method is used to assert that a user can’t register with a first name that is equal to the last name.
      
  @Test(priority=9)
      InvalidMobileNumberAssertion method is used to assert that a user can’t register with a mobile number that contains letters or special characters and less than 2 numbers.
      
  @Test(priority=10)
    invalidPasswordLessthanSixCharacters method is used to assert that a user can’t register with a password less than 6 characters.
    
  @Test(priority = 11)
    PasswordValidation method is used to validate that password must contain at least one capital case letter and one small letter and one number.
    
  @After class 
  close the browser when done with test cases 
