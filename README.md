# Setup

## Setup appium

1. Install homebrew:

    `ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`

2. Install node using homebrew:

    `brew install node`

3. Install appium for command line:

    `npm install -g appium`

4. Make sure you have Android Studio (and Android SDK) and Xcode installed

5. Install carthage  (used for iOS):

    `brew install carthage`

6. Use [Appium Desktop](https://github.com/appium/appium-desktop) to inspect the UI elements

Run the following commands to make sure you have the correct versions for:
   * `npm -v`
   * `node -v`
   * `appium -v`
   * `rvm -v` (Only if you use rvm)

 ## Setup automation project

1. Checkout the project

2. Download and install [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

3. Add the environment paths

 * Type __defaults write com.apple.finder AppleShowAllFiles YES__ in terminal to view hidden files
 * Alt+Right click on finder and click relaunch to activate changes
 * Navigate to your user's directory
 * If you don't have a `.bash_profile` file, you can create one by following these steps:
    * Start up Terminal
    *  Type __cd ~/__ to go to your home folder
    *  Type __touch .bash_profile__ to create your new file.
    *  Edit __.bash_profile__ with your favorite editor (or you can just type __open -e .bash_profile__ to open it in TextEdit.

 * Add the following to the bash_profile:

    * `export ANDROID_HOME={path to your android sdk}`
    * `export JAVA_HOME=/Library/Java/JavaVirtualMachines/{jdk version}/Contents/Home/`
    * `export PATH=${JAVA_HOME}/bin:$PATH`
    
4. Import the project in IntelliJ IDEA (using import from external model Gradle) and do a Gradle sync

5. Install Cucumber for Java plugin

 * From the menu, select IntelliJ IDEA -> Preferences
 * Go to __Plugins__
 * Click __Install JetBrains plugins__
 * Select `Cucumber for Java` and click install
 * You will have to restart IntelliJ

6. Duplicate all .properties files from __/src/test/resources/config/__ into __/src/test/resources/config/local/__ and update the new files with the values specific to your machine (usually the same way you've set up Appium UI app).

7. Run tests

 * Right-click on a test class/feature file and select __Run__
 * Use the appropriate value for the `PLATFORM` key in the global.properties should be added (Android or iOS)

__NOTE__ In case you encounter connection problems with the Appium server, you should update __rvm__ to the last version:

    `rvm get head`

8. If you encounter any errors, you can use the appium doctor:

    `npm install -g appium-doctor`

    `appium-doctor --[android/ios]`

9. Some tests do not work when the keyboard is visible. You may need to disable the software keyboard on your emulator, by using:

`adb shell settings put secure show_ime_with_hard_keyboard 0`

10. On iOS due to the way we're querying the list of acquaintances you will notice performance issues when using `AcquaintNativeiOS.app` since the UITableView (the list) has many entries.
This is by design - meant to highlight performance considerations. Feel free to point to `AcquaintNativeiOS-shortlist.app` to see the difference in impact.

# Additional links
## Appium

* [Appium](http://appium.io/) 
* [Appium Discussion Group](https://discuss.appium.io/) 
* [Appium Java Client](https://github.com/appium/java-client)
* [Appium Pro](https://appiumpro.com/) (will introduce you to all kinds of interesting and useful things you didn't know about Appium)
* [How to Pick the Right Locator Strategy](https://appiumpro.com/editions/60)
* [iOS Most Effective Lookup Strategy](https://github.com/facebook/WebDriverAgent/wiki/How-To-Achieve-The-Best-Lookup-Performance)

## Cucumber
* [Cucumber Blog](https://docs.cucumber.io/community/blog-posts/)
* [Fortech Internal Training - Mobile Automated Tests Presentation](https://drive.google.com/file/d/0BwIf0GcXSV41MTk1MFdtTTg0RmtXb0JiNGhaUWZqT0p2TDJN/view)
* [The Cucumber Book: Behaviour-Driven Development for Testers and Developers](https://www.amazon.com/Cucumber-Book-Behaviour-Driven-Development-Programmers/dp/1934356808) - Great resource for writing well structured Cucumber / Specflow tests (Specflow is just a .NET implementation of Cucumber)
