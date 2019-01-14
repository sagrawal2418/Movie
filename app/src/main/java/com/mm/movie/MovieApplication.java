package com.mm.movie;

import android.app.Application;

/**
 * Main application class to initialise app level variables.<br/><br/>
 * <p>
 * Please note that this application is created using MVP Clean architecture.
 * I have given preference to MVP clean over all other architectures like MVC, MVP, MVVM, VIPER due to great testability, quick debugging and compatibility with most of the old coding.
 * For more detail please check out:<br/>
 *
 * @see <a href="https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029</a><br/>
 * Another best architecture could be @see <a href="https://proandroiddev.com/building-modern-apps-using-the-android-architecture-guidelines-3238fff96f14</a>
 */
public class MovieApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        UniversalImageLoader.init(this);
    }
}
