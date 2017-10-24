package com.homework.android.tinkoffasync;

import java.util.ArrayList;

/**
 * Created by Павел on 24.10.2017.
 */

public class MyArray {

    private static ArrayList myArray;

    public static ArrayList getArray() {
        if (myArray == null) {
            myArray = new ArrayList<>();
            myArray.add("first");
            myArray.add("second");
        }
        return myArray;
    }
}
