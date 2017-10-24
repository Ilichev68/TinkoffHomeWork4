package com.homework.android.tinkoffasync;


/**
 * Created by Павел on 24.10.2017.
 */

public class ThreadDownloadManager {

    private static ThreadDownloadManager instance;

    private static DownloadListener sInstanceDownloadListener;
    public static boolean isStopped = false;


    public static void registerListener(DownloadListener downloadListener) {
        if (instance == null) {
            instance = new ThreadDownloadManager();
        }
        sInstanceDownloadListener = downloadListener;
    }


    private ThreadDownloadManager() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                while (i < (MyArray.getArray().size() + 1)) {
                    if (sInstanceDownloadListener != null) {
                        i++;
                        if (i == (MyArray.getArray().size() + 1)) {
                            sInstanceDownloadListener.onDownloadUpdate("Download is done");
                        }
                        try {
                            Thread.sleep(5000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (isStopped) {
                            break;
                        }
                    }
                }
            }
        }).start();
    }

    public interface DownloadListener {

        void onDownloadUpdate(String value);
    }
}
