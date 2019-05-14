package com.study.school_manager.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author sdy
 * @date 2019/4/29 15:36
 */
public class UrlUtil {
    public static void saveBinary(URL u,String localPath) throws IOException {
        URLConnection uc = u.openConnection();
        int contentLength = uc.getContentLength();
        try (InputStream raw = uc.getInputStream()) {
            InputStream in = new BufferedInputStream(raw);
            byte[] data = new byte[contentLength];
            int offset = 0;
            while (offset < contentLength) {
                int bytesRead = in.read(data, offset, data.length - offset);
                if (bytesRead == -1) {
                    break;
                }
                offset += bytesRead;
            }
            if (offset != contentLength) {
                throw new IOException("Only read " + offset
                        + " bytes; Expected " + contentLength + " bytes");
            }
            try (FileOutputStream fout = new FileOutputStream(localPath)) {
                fout.write(data);
                fout.flush();
            }
        }
    }

    /***
     * 检查path是否可以连接
     * @param path
     */
    public static boolean checkUrl(String path){
        try {
            URL url = new URL(path);
            HttpURLConnection con =(HttpURLConnection)url.openConnection();
            int state = con.getResponseCode();
            if(state==200){
                return true;
            }else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
