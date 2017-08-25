# RX-EXAMPLES-ANDROID

![banner](images/banner.png)

<h3>A collection of stand-alone applications to show how Rx can substitute
for various common problems faced by android developers.</h3>

### Show some :heart: and star the repo to support the project
[![GitHub stars](https://img.shields.io/github/stars/GurpreetSK95/rx-examples-android.svg?style=social&label=Star)](https://github.com/GurpreetSK95/rx-examples-android)
[![GitHub forks](https://img.shields.io/github/forks/GurpreetSK95/rx-examples-android.svg?style=social&label=Fork)](https://github.com/GurpreetSK95/rx-examples-android/fork)
[![GitHub watchers](https://img.shields.io/github/watchers/GurpreetSK95/rx-examples-android.svg?style=social&label=Watch)](https://github.com/GurpreetSK95/rx-examples-android)
[![GitHub followers](https://img.shields.io/github/followers/GurpreetSK95.svg?style=social&label=Follow)](https://github.com/GurpreetSK95/rx-examples-android)
[![Twitter Follow](https://img.shields.io/twitter/follow/GurpreetK95.svg?style=social)](https://twitter.com/GurpreetK95)

## Contents

1. [Substitute Asynctask](https://github.com/GurpreetSK95/rx-examples-android/tree/master/SubstituteAsynctask) ([![download](images/ic_download.png)](https://kinolien.github.com/gitzip/?download=https://github.com/GurpreetSK95/rx-examples-android/tree/master/SubstituteAsynctask))
2. [Using RxJava with Retrofit](https://github.com/GurpreetSK95/rx-examples-android/tree/master/Rx%2BRetrofit) ([![download](images/ic_download.png)](https://kinolien.github.com/gitzip/?download=https://github.com/GurpreetSK95/rx-examples-android/tree/master/Rx%2BRetrofit))
3. [Observe Text Changes](https://github.com/GurpreetSK95/rx-examples-android/tree/master/EdittextChangeObserve) ([![download](images/ic_download.png)](https://kinolien.github.com/gitzip/?download=https://github.com/GurpreetSK95/rx-examples-android/tree/master/EdittextChangeObserve))

## Attributions

Thanks to [Nisrulz](https://github.com/nisrulz/android-examples) for great work on [Android Examples](https://github.com/nisrulz/android-examples) and
for providing a way for developers to learn and get into Open Source.

This project is a direct implementation of his views expressed [here](https://android.jlelse.eu/want-to-step-up-your-android-learning-game-you-need-to-read-this-first-e0cb9a7816a3).

# Pull Requests
All pull requests and feedback is welcome. It usually will take me within 24-48 hours to
respond to any issue or request. Here are some basic rules to follow to ensure timely
addition of your request:

  1. Match coding style (braces, spacing, etc.) This is best achieved using `Reformat Code` feature of Android Studio `CMD`+`Option`+`L` on Mac and `CTRL` + `ALT` + `L` on Linux + Windows .
  2. If its a feature, bug fix, or anything please only change code to what you specify.
  3. Please keep PR titles easy to read and descriptive of changes, this will make them easier to merge :)
  4. Pull requests _must_ be made against `develop` branch. Any other branch (unless specified by the maintainers) will get rejected.
  5. Check for existing [issues](https://github.com/nisrulz/android-examples/issues) first, before filing an issue.
  6. Make sure you follow the set standard as all other projects in this repo do

      * Upgrade your gradle wrapper to the one all other apps are using. Use the below command at root of your project

          ```
          ./gradlew wrapper --gradle-version <version_name>
          ```
          i.e
          `./gradlew wrapper --gradle-version 4.0```

      * Use `ext` variables as defined in [`dependencies.gradle`](/dependencies.gradle), in your `build.gradle` files to make sure all apps are in sync with configurations and dependencies. Take a look [here](/SubstituteAsynctask/app/build.gradle) and [here](/SubstituteAsynctask/build.gradle)

      * Use the package name of the format `me.gurpreetsk.*` where `*` is the example you are adding to the repo. I am trying to follow a set standard in the repo, please adhere to that.
  7. *Most importantly, If you find anything that I've done wrong or in a sub-optimal way(Hey, I'm just learning along!), ping me at [@GurpreetK95](https://www.twitter.com/GurpreetK95)*

### Created & Maintained By
[Gurpreet Singh](https://github.com/GurpreetSK95) ([@GurpreetK95](https://www.twitter.com/GurpreetK95))

License
=======

    Copyright 2017 Gurpreet Singh

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

