How to run tests for native and web applications:
1. Clone the Repository
2. Start Emulator or Physical Device:
Android emulator or a physical Android device should be connected and ready for testing .
3. Make sure that Chrome is on the emulator or physical device and works
4. Start Appium Server:
Launch the Appium server on your machine. Ensure it is successfully running.
5. Run Tests via Maven: open Maven panel and select the profile with tests you want to run ('native' or 'web'). Open Lifecycle section and select "Test"
6. Check the results in console output
7. In case any changes are needed for test data make necessary changes in 'nativeMobileTestData.json' file (for tests in native application) 
or in TestDataProvider class in 'searchValues' provider (web application)
