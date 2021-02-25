package com.j1.xiaoxiang.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ReadFileUtils {
    /**
     * 读取线上文件
     *
     * @param fileUrl
     * @return
     */
    public static String readFile(String fileUrl) {

        URL url;
        URLConnection urlConn;
        HttpURLConnection httpConn = null;
        BufferedInputStream bis = null;
        try {
            url = new URL(fileUrl);
            urlConn = url.openConnection();
            urlConn.setConnectTimeout(10000);
            urlConn.setReadTimeout(20000);
            httpConn = (HttpURLConnection) urlConn;
            int httpResult = httpConn.getResponseCode();
            if (httpResult == HttpURLConnection.HTTP_OK) {
                // 取数据长度
                int fileSize = urlConn.getContentLength();
                byte[] b = new byte[fileSize];
                bis = new BufferedInputStream(httpConn.getInputStream());
                StringBuilder sb = new StringBuilder();
                while (bis.read(b) > 0) {
                    sb.append(new String(b, StandardCharsets.UTF_8));
                }
                return sb.toString();
            }
        } catch (IOException e) {
            log.error("读取文件[{}]失败", fileUrl, e);
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.error("读取文件[{}]失败", fileUrl, e);
                }
            }
        }
        return null;
    }
}
