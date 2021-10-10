package com.lxw.framework.exception;

import com.lxw.framework.model.response.ResultCode;
import lombok.*;

@Data
@AllArgsConstructor
public class CustomException extends RuntimeException {
    //    自定义异常类
    private ResultCode resultCode;

}
