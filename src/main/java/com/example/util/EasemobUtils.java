package com.example.util;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/5/27.
 * 环信通信
 */
public class EasemobUtils {
    //注册用户到环信
    public static int registerUser(String json) throws Exception {
        URL url = new URL("http://192.168.10.36/ssy/ccf/users");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Connection", "Keep-Alive");

        connection.setRequestProperty("Content-Type","Application/json;charset=UTF-8");
        OutputStream out = connection.getOutputStream();
        out.write(json.getBytes("UTF-8"));
        out.flush();
        out.close();
        if (connection.getResponseCode() == 200) {
            return 1;
        }else {
            return 0;
        }

    }
}
