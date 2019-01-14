package com.mm.movie;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class UniversalImageLoader {
    /**
     * Method to get instance of DisplayImageOption which is used to display image using universal image loader. <br/>
     * <p>
     * This method will load all normal image using Bitmap configuration 565 which consumes least memory as compared to all other configs available.
     *
     * @return DisplayImageOptions
     */
    private static DisplayImageOptions getImageDIO() {
        DisplayImageOptions dio = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.placeholder_image)
                .showImageForEmptyUri(R.drawable.placeholder_image)
                .showImageOnFail(R.drawable.placeholder_image)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer())
                .build();
        return dio;
    }

    private static DisplayImageOptions getRoundedImageDIO() {
        DisplayImageOptions dio = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.placeholder_image)
                .showImageForEmptyUri(R.drawable.placeholder_image)
                .showImageOnFail(R.drawable.placeholder_image)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(10))
                .build();
        return dio;
    }

    private static DisplayImageOptions getCircularImageDIO() {
        DisplayImageOptions dio = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.placeholder_image)
                .showImageForEmptyUri(R.drawable.placeholder_image)
                .showImageOnFail(R.drawable.placeholder_image)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(500))
                .build();
        return dio;
    }

    /**
     * This method is mandatory to call. Without this method call image loader is not going to work. Best place for this method call is the app initialisation.
     *
     * @param context the context
     */
    public static void init(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    /**
     * This singleton is created according to Bill Pugh implementation. For more detail check https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples
     */
    public static class ImageDIOHelper {
        public static final DisplayImageOptions INSTANCE = getImageDIO();
    }

    /**
     * This singleton is created according to Bill Pugh implementation. For more detail check https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples
     */
    public static class RoundedImageDIOHelper {
        public static final DisplayImageOptions INSTANCE = getRoundedImageDIO();
    }

    /**
     * This singleton is created according to Bill Pugh implementation. For more detail check https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples
     */
    public static class CircularImageDIOHelper {
        public static final DisplayImageOptions INSTANCE = getCircularImageDIO();
    }
}
