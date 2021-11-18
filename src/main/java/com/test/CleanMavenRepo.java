package com.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 删除本地 maven 仓库里的无效文件
 * 1. 有 *.jar.lastUpdated 没有 *.jar 的删除当前目录所有文件（不删文件夹）
 * 2. 有 *.pom.lastUpdated 没有 *.pom 的删除当前目录所有文件（不删文件夹）
 * 3. 递归删除所有空文件夹
 *
 * @author Luo Yong
 * @date 2017-03-12
 */
public final class CleanMavenRepo {

    private CleanMavenRepo() {
    }

    public static void main(String[] args) {
//        String[] dirs = {};
        String[] dirs = {"D:\\Programs\\maven\\repo"};
        clean(dirs);
    }

    private static void clean(final String... dirs) {
        if (dirs.length == 0) {
            return;
        }
        List<String> pathList = new ArrayList<>();
        for (String dir : dirs) {
            delete(pathList, dir);
            deleteEmptyDir(dir);
            System.out.println("\n=============清理 " + dir + " 成功=============");
        }
    }

    /**
     * 根据正则清理文件
     *
     * @param pathList
     * @param dir
     */
    private static void delete(List<String> pathList, final String dir) {
        File baseDir = new File(dir);
        // 判断目录是否存在
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            return;
        }
        pathList.add(dir);
        String tempPath;
        File[] files = baseDir.listFiles();
        boolean a = false;
        boolean b = true;
        boolean a1 = false;
        boolean b1 = true;
        for (File file : files) {
            tempPath = file.getAbsolutePath();
            if (file.isFile()) {
                if (tempPath.endsWith(".jar.lastUpdated")) {
                    a = true;
                } else if (tempPath.endsWith(".jar")) {
                    b = false;
                } else if (tempPath.endsWith(".pom.lastUpdated")) {
                    a1 = true;
                } else if (tempPath.endsWith(".pom")) {
                    b1 = false;
                }
            }
        }

        if ((a && b) || (a1 && b1)) {
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }

        for (File file : files) {
            tempPath = file.getAbsolutePath();
            if (file.isDirectory()) {
                delete(pathList, tempPath);
            }
        }
    }

    /**
     * 递归删除空文件夹
     *
     * @param dir
     */
    private static void deleteEmptyDir(String dir) {
        File baseDir = new File(dir);
        // 判断目录是否存在
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            return;
        }
        String tempPath;
        File[] files = baseDir.listFiles();
        if (files.length > 0) {
            for (File file : files) {
                tempPath = file.getAbsolutePath();
                if (file.isDirectory()) {
                    deleteEmptyDir(tempPath);
                }
            }
        }

        files = baseDir.listFiles();
        if (files.length == 0) {
            System.out.println("删除文件夹 " + dir);
            baseDir.delete();
        }
    }
}
