package com.homework.android.tinkoffasync;

/**
 * Created by Павел on 24.10.2017.
 */

public class ThreadShowManager {

    private static ThreadShowManager instance;

    private static ShowListener instanceShowListener;
    public static boolean isStopped = false;


    public static void registerListener(ShowListener listener) {
        if (instance == null) {
            instance = new ThreadShowManager();
        }
        instanceShowListener = listener;
    }

    private ThreadShowManager() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                while (i < (MyArray.getArray().size()+1)) {
                    if (instanceShowListener != null) {
                        instanceShowListener.onShowUpdate(String.valueOf(i++));
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

    public interface ShowListener {

        void onShowUpdate(String value);
    }
}
