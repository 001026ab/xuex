package com.example.prod2.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/8 13:59
 */

@RestController
public class Test3Controller {

    @GetMapping("downLoadFile3")
    public void testDownFile(HttpServletResponse response) {
        File file = new File("F:\\service\\xnestv3\\static\\app\\2021\\12\\15\\5f093aab0e8810c107459594646af9001639555672590.apk");
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + "5f093aab0e8810c107459594646af9001639555672590.apk");
        byte[] bytes = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(bytes);
            while (i != -1) {
                os.write(bytes, 0, bytes.length);
                os.flush();
                i = bis.read(bytes);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
