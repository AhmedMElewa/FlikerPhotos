# Fliker Photos App
Simple mobile application lets users view a list that presents the movie's photos as a list using Flickr APIs.
When a user clicks on any photo, it display it on a full screen page.
## Description
* A “ Fliker Photos App” application lets  is a project to showcase development skills in developing Android apps. It fetches images from a server and caches them offline.
    * App is handelling if there is no internet connection
    * if the internet connection lost, the app display in the end of the list retery button to load more photos when the internet connects again.

### Installing
You can download the final apk [here](https://drive.google.com/file/d/1fN_UwssXC3W_u4A_6mPWeNnXBAMRtbCr/view?usp=sharing)

#### Project characteristics and tech-stack
* Tech-stack
    * 100% kotlin & Coroutines
    * Retrofit
    * Jetpack
        * stateFlow/flow
        * pagination 3
        * ViewModel
        * Dagger-Hilt
        * room 
        * Navigation Componenet
        * Data binding
    * Unit/Ui Test 
    * connected to AppCenter (Build require some handling on kotlin version)
* Architecture
    * Clean Architecture
    * MVVM
