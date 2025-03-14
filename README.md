

# Kotlin Product Flavors Example 

This repository demonstrates how to configure **Product Flavors**  in an Android project using **Kotlin DSL** . The project shows how to manage different versions of an app for **development** , **testing** , and **production**  environments using Gradle and Kotlin DSL.

### Key Features: 

 
- **Product Flavors**  for managing different app versions (dev, test, prod).
 
- **Gradle Kotlin **  to configure flavors, signing, and build types.
 
- **Dynamic `KeyHelper` object**  to manage sensitive information like keystore paths and API keys.
 
- **Environment-specific configurations**  for OneSignal, server URLs, and app icons.
 
- **`key.properties` file**  for storing sensitive information securely.



---


**Project Structure** 


```plaintext
buildSrc/
 └── src/
     └── main/
         └── kotlin/
             └── KeyHelper.kt   // Contains shared logic for accessing key properties.
         └── key.properties      // Stores sensitive configurations such as API keys, server URLs, and keystore paths.
android/
 └── build.gradle.kts            // Defines the build script and product flavors configuration.
 └── app/                        // Android app module.
 └── src/                        // Source code for the Android app.
```



---


**Setup Instructions** 
 
2. **Clone the repository:** 


```bash
git clone https://github.com/alfayedoficial/KotlinDSL-ProductFlavors.git
```
 
4. **Install dependencies:** 

  - Make sure to have Android Studio installed and configure it with the necessary dependencies for Kotlin DSL.

  - Sync Gradle and build the project in Android Studio.
 
6. **
Configure `key.properties`:** 
 
  - Add your actual keystore file path, passwords, OneSignal App IDs, and server URLs in `key.properties`.



---


**How Product Flavors Work** 

This project demonstrates three product flavors:

 
- **`_dev`** : Development version with specific configurations for the development environment (e.g., development API URL, OneSignal App ID).
 
- **`_test`** : Testing version for staging or QA (e.g., test API URL, OneSignal App ID).
 
- **`_prod`** : Production version with live configurations (e.g., production API URL, OneSignal App ID).

These flavors are defined in the `build.gradle.kts`**  file and are linked to different `BuildConfig` fields and environment-specific configurations.


---


**Key Helper Logic** 
The `KeyHelper` object is used to access sensitive configurations (like keystore information, API keys, server URLs) securely from `key.properties`** .


```kotlin
object KeyHelper {

    const val KEY_STORE_FILE = "keyStoreFile"
    const val KEY_STORE_PASS = "keyStorePassword"
    const val KEY_ALIAS = "keyAlias"
    const val KEY_PASS = "keyPassword"

    private val properties by lazy {
        Properties().apply { load(FileInputStream(File("key.properties"))) }
    }

    fun getValue(key: String): String {
        return properties.getProperty(key)
    }
}
```



---


**App Info Display** 
The app dynamically displays information about the current build, such as the app's `APPLICATION_ID`, `FLAVOR`, `BUILD_TYPE`, and more.


```kotlin
@Composable
fun AppInfo(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "APPLICATION_ID: ${BuildConfig.APPLICATION_ID}")
        Text(text = "BUILD_TYPE: ${BuildConfig.BUILD_TYPE}")
        Text(text = "FLAVOR: ${BuildConfig.FLAVOR}")
        Text(text = "VERSION_CODE: ${BuildConfig.VERSION_CODE}")
        Text(text = "VERSION_NAME: ${BuildConfig.VERSION_NAME}")
        Text(text = "BASE_URL: ${BuildConfig.BASE_URL}")
    }
}
```



---


**Conclusion** 
This repository provides a clean and efficient approach for managing multiple versions of your Android app with **product flavors**  using **Kotlin DSL** . By configuring **gradle**  and using a central `KeyHelper`** , you can easily manage sensitive information across different build variants. This structure ensures that your development, testing, and production builds are cleanly separated while maintaining a common codebase.


---


**READ Full Article** 
(https://medium.com/@alfayedoficial/how-to-set-up-and-use-product-flavors-in-your-kotlin-android-project-804a4ad7bc10)


