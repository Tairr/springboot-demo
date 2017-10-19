package com.example.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

/**
 * Created by dell on 2017/5/17.
 * Good Luck !
 * へ　　　　　／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */
public class FileLoadUtils {

    public static void fileDownLoad(HttpServletResponse response, String filePath){
        try {
            System.out.println("-------------------------------下载版本文件------------------------------------");
            String[] fp = filePath.split("\\\\");
            String fileName = fp[fp.length - 1];// 文件名称  
            System.out.println("-------------------------------上传文件名为=" + fileName);
            //下载机器码文件  
            response.setHeader("conent-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("ISO-8859-1"), "UTF-8"));
            OutputStream os = response.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            InputStream is = null;
            is = new FileInputStream(URLDecoder.decode(filePath,"UTF-8"));
            BufferedInputStream bis = new BufferedInputStream(is);
            int length = 0;
            byte[] temp = new byte[1 * 1024 * 10];
            while ((length = bis.read(temp)) != -1) {
                bos.write(temp, 0, length);
            }
            bos.flush();
            bis.close();
            bos.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
