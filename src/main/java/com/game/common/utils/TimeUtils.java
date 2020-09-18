package com.game.common.utils;

public class TimeUtils {
    private static TimeUtils timeUtils = null;

    private TimeUtils() {
    }

    public static TimeUtils getInstance() {
        if (timeUtils == null)
            timeUtils = new TimeUtils();
        return timeUtils;
    }

    public String getTime(long time) {
        String result = "Khoảng ";
        time /= 1000;
        if (time < 60)
            return result + time + " giây trước";
        time /= 60;
        if (time < 60)
            return result + time + " phút trước";
        time /= 60;
        if (time < 24)
            return result + time + " giờ trước";
        time /= 24;
        if (time < 7)
            return result + time + " tuần trước";
        time /= 7;
        if (time < 12)
            return result + time + " tháng trước";
        return result + "năm trước";
    }
}
