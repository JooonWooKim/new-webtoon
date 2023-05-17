package com.naver.webtoon.domain.webtoon.enums;

import com.naver.webtoon.global.common.exception.WebtoonException;

import static com.naver.webtoon.global.common.exception.ErrorCode.NOT_FOUND_SERIALIZED_STATUS;

public enum SerializedStatus {
    SERIALIZED,
    PAUSE,
    COMPLETE;

    public static SerializedStatus toEnum(String serializedStatus){
        return switch(serializedStatus){
            case "SERIALIZED" -> SERIALIZED;
            case "PAUSE" -> PAUSE;
            case "COMPLETE" -> COMPLETE;
            default -> throw new WebtoonException(NOT_FOUND_SERIALIZED_STATUS);
        };
    }
}
