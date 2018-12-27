package com.gusuran.authentication.rest.web.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T>{

    private Integer code;
    private String status;
    private Map<String, List<String>> errors;
    private T data;
}
