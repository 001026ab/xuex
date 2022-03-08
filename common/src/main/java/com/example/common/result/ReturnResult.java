package com.example.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/17 19:03
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "统一返回结果类")
public class ReturnResult<T> {


    /**
     * 结果状态
     */
    private Boolean resultStatus;

    /**
     * 错误详情描述
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "错误详情描述")
    private String errorMessage;
    /**
     * 错误编号
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "错误编号")
    private String errorCode;

    /**
     * 泛型返回数据
     */
    private T data;
}
