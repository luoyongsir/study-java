package com.test;

import java.io.File;
import java.util.regex.Pattern;

/**
 * 根据正则删除文件夹里不需要的文件或子文件夹
 *
 * @author Luo Yong
 * @date 2017-03-12
 */
public final class CleanDirs {

	/**
	 * 匹配文件
	 */
	private static final String REG_FILE = "^(.*)(.iml|cliport.txt|.flattened-pom.xml)";
	private static final Pattern PATTERN_FILE = Pattern.compile(REG_FILE);
	/**
	 * 匹配文件夹
	 */
	private static final String REG_DIR = "^(.*)(.git|target|.idea)";
	private static final Pattern PATTERN_DIR = Pattern.compile(REG_DIR);

	public static void main(String[] args) {
		String[] dirs = {};
		// String[] dirs = {"D:\\Workspaces\\cooperation"};
		clean(dirs);
	}

	private static void clean(final String... dirs) {
		if (dirs.length == 0) {
			return;
		}
		for (String dir : dirs) {
			delete(dir);
			System.out.println("\n=============清理 " + dir + " 成功=============");
		}
	}

	/**
	 * 根据正则清理文件
	 * @param dir
	 */
	private static void delete(final String dir) {
		File baseDir = new File(dir);
		// 判断目录是否存在
		if (!baseDir.exists() || !baseDir.isDirectory()) {
			return;
		}
		String tempPath;
		File[] files = baseDir.listFiles();
		for (File file : files) {
			tempPath = file.getAbsolutePath();
			if (file.isFile()) {
				if (PATTERN_FILE.matcher(tempPath).matches()) {
					// 匹配文件成功，删除文件
					System.out.println("删除文件：" + tempPath);
					file.delete();
				}
			} else if (file.isDirectory()) {
				if (PATTERN_DIR.matcher(tempPath).matches()) {
					// 匹配文件夹成功，删除文件夹
					System.out.println("删除文件夹：" + tempPath);
					deleteDir(tempPath);
				} else {
					delete(tempPath);
				}
			}
		}
	}

	/**
	 * 删除目录以及目录下的文件
	 * @param tempPath 被删除目录的路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	private static boolean deleteDir(String tempPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!tempPath.endsWith(File.separator)) {
			tempPath = tempPath + File.separator;
		}
		File dirFile = new File(tempPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				flag = file.delete();
			} else if (file.isDirectory()) {
				// 删除子目录
				flag = deleteDir(file.getAbsolutePath());
			}
			if (!flag) {
				return flag;
			}
		}
		// 删除当前目录
		return dirFile.delete();
	}

	private CleanDirs() {
	}
}
