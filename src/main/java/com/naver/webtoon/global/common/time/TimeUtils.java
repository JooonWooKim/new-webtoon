package com.naver.webtoon.global.common.time;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeUtils {
    public static boolean validateUpdatedToday(LocalDateTime updateAt){
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(updateAt, currentTime);
        long hoursDifference = duration.toHours();
        return hoursDifference < 24;
    }

    public static boolean validateNewWebtoon(LocalDateTime updateAt){
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(updateAt, currentTime);
        long hoursDifference = duration.toDays();
        return hoursDifference < 30;
    }
}
