# Df Movie Catalog  - Android

This ReadMe created to gives information about DF Movie Catalog POC Android project

## Getting Started

1. Clone this repository.
2. Import the project. Open Android Studio, click `Open an existing Android
   Studio project` and select the project. Gradle will build the project.
3. There is an example API Key for the movie db in build.gradle file. `API_KEY` can be changed manually in default config
```
    defaultConfig {
        applicationId Configs.package
        minSdkVersion Configs.minSdkVersion
        targetSdkVersion Configs.compileSdkVersion
        versionCode Configs.versionCode
        versionName Configs.versionName
        archivesBaseName = "$versionName"

        testInstrumentationRunner 'androidx.test.runner.AndroidJUnitRunner'

        multiDexEnabled true
        vectorDrawables.useSupportLibrary = true


        /**
         * This is example api key, you change any of them if you want
         */
        buildConfigField STRING, API_KEY, '"35c0f23baf76d98bb700ddb40010b61b"'
    }
```
3. Run the app.

### Contents
- [Application Architecture](#Architecture)
- [Dependency Management](#dependency-management)
- [Package Structure](#package-structure)
- [File Naming](#file-naming)

### Application Architecture
This project is developed with Kotlin. Also MVVM design pattern is applied during development. Here are the additional technology that used in the project
- Android Architecture Components
- Data binding
- Coroutines
- Retrofit
- Paging library

### Dependency Management

Run plugin to find which updates are available
> gradlew buildSrcVersions

This will only update the comments in buildSrc/Versions. You are responsible for updating manually the dependency version.

##### Add New Dependency
Manually add new dependency as usual in build.gradle file and then run plugin as above. Change the magic string dependency(com.dependency:xyz:1.0.0) in build.gradle file with Libs.xyz as defined in buildSrc/Libs.

### Package Structure
We prefer feature module packaging structure. Each package has its own <ins> data </ins>,<ins> di </ins>,<ins> domain </ins>,<ins> ui</ins> subfolder.

### File Naming

| Component        | Class Name               | Layout Name                   |
| ---------------- | ------------------------ | ----------------------------- |
| Activity         | MainActivity             | activity_main.xml |
| Fragment         | DiscoverMovieFragment    | fragment_discover.xml      |


### APK file
You can find example .apk file in [here](https://github.com/vedatkilic/df-movies-catalog-android/tree/develop/apkFile)

### Example usage video
You can find usage video file in [here](https://github.com/vedatkilic/df-movies-catalog-android/tree/develop/video)