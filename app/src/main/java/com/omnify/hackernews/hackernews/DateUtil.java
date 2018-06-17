package com.omnify.hackernews.hackernews;

import android.text.format.DateUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String DATE_FORMAT_WITH_MIN = "dd MMM, yyyy - hh:mm";

    public static String getRelativeTime(long date)
    {
        return DateUtils.getRelativeTimeSpanString(date, new Date().getTime(), DateUtils.MINUTE_IN_MILLIS).toString();
    }
    public static String getCommentDateFormat(long date) {
        SimpleDateFormat desiredFormat = new SimpleDateFormat(DATE_FORMAT_WITH_MIN);
        return desiredFormat.format(date);
    }
}
