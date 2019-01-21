# SpeedRun-TechnicalTest

Android app written in [Kotlin](https://kotlinlang.org) which displays a list of games from www.speedrun.com. The uses MVVM and [Architecture components](https://developer.android.com/topic/libraries/architecture/) to make the code clean and the app maintainable, scalable, readable and testable.

## Libraries

* [Kotlin](https://kotlinlang.org)
* Clean architecture using MVVM
* Architecture components ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) and [Livedata](https://developer.android.com/topic/libraries/architecture/livedata)) that allow data to survive configuration changes such as screen rotations
* [RxJava 2](https://github.com/ReactiveX/RxJava) to extend the observer pattern supporting sequences of data and operations with them. This library makes possible a layered design making the code more testable and clean
* [Dagger 2.11+](https://google.github.io/dagger/) it's a dependency injection framework for Android. Versions starting at 2.11 make it even easier to inject dependencies
* Mockito/Junit for unit tests
* [Retrofit](https://square.github.io/retrofit/)/[Moshi](https://github.com/square/moshi) handles API calls
* [Glide](https://github.com/bumptech/glide) to load images and GIF's
* [Navigation](https://developer.android.com/topic/libraries/architecture/navigation.html) component simplifies the implementation of navigation in an Android app
* [Paging](https://developer.android.com/topic/libraries/architecture/paging/) makes easier load data from a datasource gradually
* [Data binding](https://developer.android.com/topic/libraries/data-binding/) allows to set data to UI components from the layouts using data classes
