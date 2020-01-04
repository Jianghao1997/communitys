package com.hoodee.community.dto;

import com.hoodee.community.exception.CustomizeErrorCode;
import com.hoodee.community.exception.CustomizeException;
import com.sun.xml.internal.bind.v2.runtime.property.StructureLoaderBuilder;
import lombok.Data;
import org.springframework.web.servlet.ModelAndView;

/**
 * Package: com.hoodee.community.dto
 * Description：
 * Author: jianghao
 * Date:  2020.01.04 9:30
 * Modified By:
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code,String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {

        return errorOf(errorCode.getcode(),errorCode.getMessage());
    }

    public static ResultDTO errorOf(CustomizeException ex) {
        return errorOf(ex.getCode(),ex.getMessage());
    }

    public static ResultDTO okOf(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

}
