package com.example.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ReStartLin
 * @data 2019/6/4 15:02
 * @classDesc: 功能描述: 前端 统一返回模板
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "统一返回结果类")
public class MyResult<T> {
    /**
     * 结果状态 true 成功 false 失败
     */
    @ApiModelProperty(value = "结果状态", example = "true")
    private boolean resultStatus;
    /**
     * 如果错误则返回 错误代码
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "错误代码")
    private String errorCode;
    /**
     * 错误详情描述
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(value = "错误详情描述")
    private String errorMessage;
    /**
     * 结果为true 时 业务数据 可为null
     */
    @ApiModelProperty(value = "业务数据")
    private T resultData;

}
