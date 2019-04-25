package com.test.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DimenToolBack {
    static String path;

    public static void gen() {
        //XML file under this folder is the initial value reference
        File file = new File("./src/com/zcn/res/dimens.xml");
        path = file.getAbsolutePath();
        path = path.substring(0, path.indexOf(".") - 1);
        path = path.replace("\\", "/");
        System.out.println("输出文件路径：" + path);

        BufferedReader reader = null;
        StringBuilder w480_320dp = new StringBuilder();
        StringBuilder w480_800dp = new StringBuilder();
        StringBuilder w1920_1080dp = new StringBuilder();
        //StringBuilder w1280_720dp = new StringBuilder();

        try {
            System.out.println("生成不同分辨率：");
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;

            // Read in one line at a time, until null is the end of the file
            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains("</dimen>")) {
                    //tempString = tempString.replaceAll(" ", "");

                    String start = tempString.substring(0, tempString.indexOf(">") + 1);
                    String end = tempString.substring(tempString.lastIndexOf("<") - 2);
                    //cut down the value in dimen tag
                    double num = Double.parseDouble
                            (tempString.substring(tempString.indexOf(">") + 1,
                                    tempString.indexOf("</dimen>") - 2));

                    //depending different size,calculate new value, Joining together new string
                    w480_320dp.append(start).append(num * 0.5).append(end).append("\r\n");
                    w480_800dp.append(start).append(num * 0.75).append(end).append("\r\n");
                    w1920_1080dp.append(start).append(num * 1.5).append(end).append("\r\n");
                } else {
                    w480_320dp.append(tempString).append("");
                    w480_800dp.append(tempString).append("");
                    w1920_1080dp.append(tempString).append("");
                }
                line++;
            }

            reader.close();

            String w480_320file = "./src/com/zcn/res/w480_320dp/dimens.xml";
            String w480_800file = "./src/com/zcn/res/w480_800dp/dimens.xml";
            String w1920_1080file = "./src/com/zcn/res/w1920_1080dp/dimens.xml";

            createFile(w480_320file, w480_320dp.toString());
            createFile(w480_800file, w480_800dp.toString());
            createFile(w1920_1080file, w1920_1080dp.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static boolean createFile(String destFileName, String info) {
        destFileName = path + destFileName.substring(destFileName.indexOf(".") + 1);
        System.out.println("文件名：" + destFileName);
        File file = new File(destFileName);
        if (!file.exists()) {
            //判断目标文件所在的目录是否存在
            if (!file.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建父目录
                System.out.println("目标文件所在目录不存在，准备创建它！" + file.getParentFile());
                if (!file.getParentFile().mkdirs()) {
                    System.out.println("创建目标文件所在目录失败！");
                    return false;
                }
            }
            //创建目标文件
            try {
                if (file.createNewFile()) {
                    System.out.println("创建单个文件" + destFileName + "成功！");
                    writeFile(destFileName, info);
                    return true;
                } else {
                    System.out.println("创建单个文件" + destFileName + "失败！");
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * write method
     */

    public static void writeFile(String file, String text) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }

    public static void main(String[] args) {
        gen();
    }
}
