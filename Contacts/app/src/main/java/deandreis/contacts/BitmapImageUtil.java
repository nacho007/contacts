package deandreis.contacts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by ignaciodeandreisdenis on 25/8/17.
 */

class BitmapImageUtil {

    public static Bitmap loadFrom(String url, Context context, int defaultDrawable) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            try {
                return BitmapFactory.decodeStream(is);
            } finally {
                is.close();
            }
        } catch (Exception e) {
            return BitmapFactory.decodeResource(context.getResources(), defaultDrawable);
        }
    }

}
