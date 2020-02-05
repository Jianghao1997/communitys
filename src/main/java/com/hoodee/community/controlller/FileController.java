package com.hoodee.community.controlller;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Package: com.hoodee.community.controlller
 * Description：文件上传功能
 * Author: jianghao
 * Date:  2020.02.05 17:18
 * Modified By:
 */
@Controller
public class FileController {
    @ResponseBody
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public JSONObject hello(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach) {

        JSONObject jsonObject=new JSONObject();

        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            String rootPath = request.getSession().getServletContext().getRealPath("/static/images/blog/");

            System.out.println("editormd上传图片："+rootPath);

            /**
             * 文件路径不存在则需要创建文件路径
             */
            File filePath = new File(rootPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            // 最终文件名
            File realFile = new File(rootPath + File.separator + attach.getOriginalFilename());
            FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);

            // 下面response返回的json格式是editor.md所限制的，规范输出就OK
            System.out.println(attach.getOriginalFilename());
            jsonObject.put("success", 1);
            jsonObject.put("message", "上传成功");
            jsonObject.put("url", rootPath + attach.getOriginalFilename());
        } catch (Exception e) {
            jsonObject.put("success", 0);
        }

        return jsonObject;
    }
}
