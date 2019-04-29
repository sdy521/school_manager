package com.study.study_manager.util;

import java.io.*;
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
}
