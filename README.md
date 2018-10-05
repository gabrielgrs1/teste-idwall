# idDogs

[Download Apk](https://goo.gl/LZY98q)

idDog was an application developed for the test for android development of the company idWall, with the aim of showing photos of dogs divided into category.

<p align="center">
  <img src="http://i.imgur.com/Y70t6OM.gif" alt="Demo app"
       width="400" height="750">
</p>


## Used Libraries
* [ButterKnife](https://github.com/JakeWharton/butterknife)
* [Retrofit](https://github.com/square/retrofit)
* [Picasso](https://github.com/square/picasso)
* [Sneaker](https://github.com/Hamadakram/Sneaker)
* [AHBottomNavigation](https://github.com/aurelhubert/ahbottomnavigation)
* [okHttpLogInterceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
* [FacebookShimmer](https://github.com/facebook/Shimmer)


## How It Works

User informs an invalid email and triggers the `login` button. After this it is redirected to the `Husky image preview screen` and you can navigate between the categories in the `navigation bar` below. If you want to see the photo in more detail the user can click on the photo and see it in its actual size.


## Decisions taken during development
  - Library decisions
    - **Butterknife**: I decided to use butterknife to increase productivity at the time of bind and listeners from some views.
    - **Retrofit**: I decided to use the retrofit to consume the API's for facilitating the treatment of callbacks, speed in the  transmission and being a library that is very common in the day-to-day of most mobile development companies.
    - **Picasso**: I used Picasso to download the images because using it is very simple and brings great features including automatic image caching.
    - **Sneaker**: I used Sneaker for alert notifications because it is very easy to customize and it is of immense importance to interact with the user with any action taken in the application.
    - **AHBottomNavigation**: I used AHBottomNavigation for create my bottombar because she is a very simple to use and follow de material design guideline.
    - **okHttpLogInterceptor**: I decided to use the okhttp logging interceptor because during the development I felt the need to log the requests to know for sure what was happening, besides being a library commonly used for this functionality.
    - **Facebook Shimmer**: I used Facebook Shimmer to give user feedback at Image's loading because it is currently one of the most used libraries to give load feedback to the user, and I have decided to follow the current design pattern.
  
  - Architeture decisions
    - I decided to split every piece of code that had a function in different methods so that any developer who has not seen the code will still know for sure what's going on.
    - I decided to organize the UI classes in packages related to their type (Activity, adapter ...) because they find it easier to find something if the project grows.
    - I've used Parcelable to `serialize` objects by though it's harder to implement, it performs much better than `Serializable`.
    - I used `RecyclerView` with a `GridLayoutManager` because the performance of `RecyclerView` is better than a `ListView`.
    - I decided to store the token inside my `Application class` because it would be an element to use within the application at all times, however I did not see the need to store it using `SharedPreferences` since the user should only enter the email to view the images.



