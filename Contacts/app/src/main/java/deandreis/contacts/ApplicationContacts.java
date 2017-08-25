package deandreis.contacts;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by ignaciodeandreisdenis on 25/8/17.
 */

public class ApplicationContacts extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
