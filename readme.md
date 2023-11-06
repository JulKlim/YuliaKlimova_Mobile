**How to run tests for native and web applications:**
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

__________________________________________________________
**How to obtain and use Tokens:**

1. To obtain a token log in to the Mobitru site using your credentials >> Navigate to "User Settings", select "Access Token", generate a token and copy its value.
2. In order to avoid using token value directly in a project create a new system variable on your computer to store the token value (e.g., I named it "MOBITRU_TOKEN").
3. In pom.xml file of the project replace the token value in the line pointing to Mobitru with a reference to your system variable using the syntax ${env.MOBITRU_TOKEN}.

__________________________________________________________

**How to run Tests on Cloud Devices:**

_For Web Tests:_

Open the project in IntelliJ IDEA and run the following commands:
* **For Android:** mvn clean test -P CloudWeb_Android
* **For iOS:** mvn clean test -P CloudWeb_iOS

_For Native Application Tests:_

**For iOS:**
* Go to the Mobitru site and open the tab with iOS devices
* Select any iOS device and copy its UDID value into the Cloud_Native_iOS.xml file. 
* Start the device in the Mobitru site and install the EPAMTestApp.ipa application to the device. 
* Run the command: mvn clean test -P CloudNative_iOS in the project

**Android:**
* Go to the Mobitru site and open the tab with Android devices. 
* Select any Android device and copy its deviceName and serial values into the Cloud_Native_Android.xml file. 
* Start the device in the Mobitru site and install the EPAMTestApp.apk application to the device. 
* Run the command: mvn clean test -P CloudNative_Android in the project