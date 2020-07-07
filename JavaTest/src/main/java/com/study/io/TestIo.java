package com.study.io;

import org.junit.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * file.createNewFile();//创建文件
 * file.exists();//检查文件是否存在
 */
public class TestIo {
    public static void main(String[] args) {
        String path = "H:" + File.separator + "io.txt";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test_FileInputStream() {
        String path = "H:" + File.separator + "input.txt";
        String content = "你好";
        File file = new File(path);
        FileOutputStream fos;
        try {
            fos =new FileOutputStream(file);
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInputStream fis ;
        try {
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            fis.read(buffer);
            String s = new String(buffer, StandardCharsets.UTF_8);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_FileReader(){
        FileReader reader = null;
        String path = "H:" + File.separator + "input.txt";
        File file = new File(path);
        try {
            reader=new FileReader(file);
            if(!file.exists()){
                file.createNewFile();
            }
            char[] buffer = new char[1024];
            reader.read(buffer);
            System.out.println(new String(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test_FileWriter() throws IOException{
        String path = "H:" + File.separator + "input.txt";
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("曾豪");
        //fileWriter不使用flush方法则不会写数据到文件 此时数据还在缓冲区
        fileWriter.flush();
        fileWriter.close();
    }
}
