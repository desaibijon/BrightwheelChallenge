# BrightwheelChallenge

## Purpose
- Build an Android app that uses the GitHub REST API to display a list of the 100 most starred Github repositories. For each of the repositories, also display its top contributor.

## Components
- This app uses Retrofit to requests API responses and displays them using a list view.
- The app is designed to support various other functionalities by providing their execution and conveniently placing them in the component list.

### How to run?
- Launch the app from Android Studio into an emulator or an Android device capable of USB Debugging.
- Pick the operation from the list of components this app supports, for now it is only list of repos, and explore
- Currently the app only supports list of repos from github.

### App operations
- App shows a list of github repos using using MVVM architecture.
- Clicking on a repo takes the user to a list of contributors for this repo.

#### Libraries used:
- Retrofit
- AndroidX
- ViewModel
- Gson

#### Future improvements
- Add support for dark mode, there are no themes in the app at the moment.
- Localize the app to add support for different languages.
