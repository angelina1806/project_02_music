package com.company;

import com.company.myException.TypeException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        //создаем файл html
        File f = new File("H:\\music.html");
        //записываем данные в html
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("<li>eeeeeeeeeeeedddddddddd " +
                    "<ul>ffffffffffff</ul> " +
                    "<ul>eeeeeeeeeeeeeeee</ul>" +
                    "</li>"
            );
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        //вызываем метод, который будет считывать содержимое каталогов
        OpenDirectory("H:\\mus_lab");
        getMd5();
    }

    //считываем содержимое папки и подпапок в ней
    public static void OpenDirectory(String pathname){
        ArrayList<String> directoryList = new ArrayList<>();
        File folder = new File(pathname);
        File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile() & listOfFiles[i].getName().endsWith(".mp3")) {
                    System.out.println( listOfFiles[i].getName());
                    // путь к папке  + listOfFiles[i].getAbsolutePath()
                } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Directory " + listOfFiles[i].getName());
                    directoryList.add(listOfFiles[i].getName());
                }
            }
            if (directoryList.isEmpty() == false) {
                for (String directory : directoryList) {
                    OpenDirectory(pathname + "\\" + directory);
                }
            } else {
                return;
            }
        }

    //определяем контрольную сумму
    public static String getMd5() throws IOException
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(Files.readAllBytes(Paths.get("H:\\mus_lab\\AC DC - Are You Ready  (audiopoisk.com).mp3")));
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            System.out.println("hash =   " + hashtext);
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


}
