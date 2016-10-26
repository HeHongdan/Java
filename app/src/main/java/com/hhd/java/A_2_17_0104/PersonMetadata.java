package com.hhd.java.A_2_17_0104;

import android.provider.BaseColumns;

/**
 * 数据库(表结构)
 * Created by HHJ on 2016/5/25.
 */

public final class PersonMetadata {

    public static abstract class Person implements BaseColumns {

        public static final String TABLE_NAME = "person";
        public static final String NAME = "name";
        public static final String AGE = "age";

    }
}
