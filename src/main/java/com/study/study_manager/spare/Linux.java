package com.study.study_manager.spare;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * linux获取磁盘信息
 * @author sdy
 * @date 2019/5/10 13:40
 */
public class Linux {
    public static Desk getDeskUsage() {
        Desk desk = new Desk();
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("df -hl");// df -hl 查看硬盘空间
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(
                        p.getInputStream()));
                String str = null;
                String[] strArray = null;
                int line = 0;
                while ((str = in.readLine()) != null) {
                    line++;
                    if (line != 2) {
                        continue;
                    }
                    int m = 0;
                    strArray = str.split(" ");
                    for (String para : strArray) {
                        if (para.trim().length() == 0)
                            continue;
                        ++m;
                        if (para.endsWith("G") || para.endsWith("Gi")) {
                            // 目前的服务器
                            if (m == 2) {
                                desk.setTotal(para);
                            }
                            if (m == 3) {
                                desk.setUsed(para);
                            }
                        }
                        if (para.endsWith("%")) {
                            if (m == 5) {
                                desk.setUse_rate(para);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return desk;
    }

    public static class Desk {
        private String total;
        private String used;
        private String use_rate;

        public String toString(){
            return "总磁盘空间："+total+"，已使用："+used+"，使用率达："+use_rate;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getUsed() {
            return used;
        }

        public void setUsed(String used) {
            this.used = used;
        }

        public String getUse_rate() {
            return use_rate;
        }

        public void setUse_rate(String use_rate) {
            this.use_rate = use_rate;
        }

    }

    public static void main(String[] args) {
        System.out.println(getDeskUsage());
//        打印结果，博主的电脑是mac，所以显示Gi
//        总磁盘空间：234Gi，已使用：100Gi，使用率达：44%
    }
}
