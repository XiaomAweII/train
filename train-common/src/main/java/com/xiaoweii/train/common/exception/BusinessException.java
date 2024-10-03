package com.xiaoweii.train.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//最好是继承RuntimeException, 否则你在之后还需要将这个异常抛出
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private BusinessExceptionEnum e;

    /*
     * 不写入堆栈信息, 提高性能
     * */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
