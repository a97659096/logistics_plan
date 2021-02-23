package com.logistics.plan.utils;


import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    /**
     * 导出
     *
     * @param file csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param dataList 数据
     * @return
     */
    public static boolean exportCsv(File file, List<String> dataList){
        //文件存在时进行覆盖
        if(file.exists())
        {
            file.delete();
        }

        boolean isSucess;

        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw =new BufferedWriter(osw);
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append("\r");
                }
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            try {
                bw.close();
                bw=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                osw.close();
                osw=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
                out=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return isSucess;
    }

    public static boolean exportCsvByPlanning(File file, List<String> dataList){
        //文件存在时进行覆盖
        if(file.exists())
        {
            file.delete();
        }

        boolean isSucess;

        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw =new BufferedWriter(osw);
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append("\r\n");
                }
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            try {
                bw.close();
                bw=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                osw.close();
                osw=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
                out=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return isSucess;
    }


    public static String getFileCoding(File file) {
        String encoding = null;
        try {
            Path path = Paths.get(file.getPath());
            byte[] data = Files.readAllBytes(path);
            CharsetDetector detector = new CharsetDetector();
            detector.setText(data);
            CharsetMatch match = detector.detect();
            if (match == null) {
                return encoding;
            }
            encoding = match.getName();
            if (encoding.contains("ISO-8859")){
                encoding = "GBK";
            }
        } catch (IOException var6) {
        }
        return encoding;
    }

    /**
     * 导入
     *
     * @param file csv文件(路径+文件)
     * @return
     */
    public static List<String> importCsv(File file) throws Exception{
        List<String> dataList=new ArrayList<String>();

        DataInputStream in=null;
        BufferedReader br=null;
        try {
            in=new DataInputStream(new FileInputStream(file));
//            byte[] uft8bom={(byte)0xef,(byte)0xbb,(byte)0xbf};
//            in.read(uft8bom);
            br = new BufferedReader(new InputStreamReader(in, getFileCoding(file)));
            String line = null;
            while ((line = br.readLine()) != null) {
                char c = line.charAt(0);
                //65279是空字符
                if(c==65279) {
                    line = line.substring(1);
                }
                dataList.add(line.toString());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(br!=null){
                try {
                    br.close();
                    br=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return dataList;
    }


    /**
     * 去除 字符串收尾的 特殊的Unicode [ "\uFEFF" ]
     * csv 文件可能会带有该编码
     * @param str
     * @return
     */
    public static String specialUnicode(String str){
        if (str.startsWith("\uFEFF")){
            str = str.replace("\uFEFF", "");
        }else if (str.endsWith("\uFEFF")){
            str = str.replace("\uFEFF","");
        }
        return str;
    }

}
