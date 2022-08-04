package com.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
    private Integer code;
    private String error;
    private Long timestamp;
}
