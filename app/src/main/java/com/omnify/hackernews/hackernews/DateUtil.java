package com.omnify.hackernews.hackernews;

import android.text.format.DateUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getRelativeTime(long date)
    {
        return DateUtils.getRelativeTimeSpanString(date, new Date().getTime(), DateUtils.MINUTE_IN_MILLIS).toString();
    }
}
