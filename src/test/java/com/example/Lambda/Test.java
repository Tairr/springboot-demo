package com.example.Lambda;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by tangjinhui on 2017/10/12.
 */
public class Test {
    public static void main(String[] args) {
//        File dir = new File("/an/dir/");
//        FileFilter directoryFilter = (File f) -> f.isDirectory();

        File dir = new File("D:/test.txt");
        FileFilter fr = (File f) -> f.isDirectory();
        File[] fs = dir.listFiles(fr);
    }
}
