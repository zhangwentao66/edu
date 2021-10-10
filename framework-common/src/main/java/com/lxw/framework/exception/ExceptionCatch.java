package com.lxw.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.lxw.framework.model.response.CommonCode;
import com.lxw.framework.model.response.ResponseResult;
import com.lxw.framework.model.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice //对controller层进行增强，指定由controller层进行调用方法时，抛出的异常进行捕获
@Slf4j
public class ExceptionCatch {

    private static ImmutableMap<Class<? extends Throwable>,ResultCode> exceptionMap;

    private static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder = ImmutableMap.builder();

    static {
        builder.put(HttpMessageNotReadableException.class,CommonCode.FEIFACANSHU);
    }

    //捕获已知异常
    @ExceptionHandler({CustomException.class})
    @ResponseBody
    public ResponseResult customException(CustomException exception){

        ResultCode resultCode = exception.getResultCode();
        return new ResponseResult(resultCode);
    }

    //捕获未知异常
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseResult customException(Exception exception){
        //使用@Slf4j注解提供的log.error打印日志信息
        //2021-10-10 11:41:21.852 ERROR 24792 --- [io-31001-exec-2] c.l.framework.exception.ExceptionCatch   : Required request body is missing: public com.lxw.framework.domain.cms.response.CmsPageResult com.yh.cms.controller.CmsPageController.addCmsPage(com.lxw.framework.domain.cms.CmsPage)
//        log.info(exception.getMessage());
//        log.debug(exception.getMessage());  这里这两行没打印代码
        log.error(exception.getMessage());
        if (exceptionMap == null){
            exceptionMap =  builder.build();
        }
//        Class<? extends Exception> exceptionClass = exception.getClass();
        ResultCode code = exceptionMap.get(exception.getClass());
        if (code != null){
            return new ResponseResult(code);
        }else {
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }
    }
}
