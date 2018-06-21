package com.test;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 清理maven仓库
 *
 * @author luoyong
 */
public class MavenClean {

	private static AtomicInteger emptyDirNum = new AtomicInteger(0);
	private static AtomicInteger lastUpdateNum = new AtomicInteger(0);
	private static AtomicInteger emptyNum = new AtomicInteger(1);

	/**
	 * 要清理的内容包括
	 * 1. 所有的.lastUpdated 结尾的文件
	 * 2. 清理之后的所有空文件夹
	 */
	public static void main(String[] args) {
		final String s = "D:\\Programs\\maven\\repo";
		File file = new File(s);
		while (emptyNum.get() != 0 && emptyNum.get() != -1) {
			emptyNum.set(0);
			cleanLastUpdate(file);
			cleanEmptyDir(file);
			System.out.println("删除文件:" + lastUpdateNum + " 个, 删除空文件夹:" + emptyDirNum + " 个");
		}
	}

	/**
	 * 删除所有.lastUpdated结尾的文件
	 *
	 * @param file
	 */
	public static void cleanLastUpdate(File file) {
		if (file != null) {
			if (file.getName().endsWith("lastUpdated")) {
				lastUpdateNum.incrementAndGet();
				System.out.println("要删除的文件是：" + file.getAbsolutePath());
				file.delete();
			}
			if (file.isDirectory()) {
				for (File item : file.listFiles()) {
					cleanLastUpdate(item);
				}
			}
		}
	}

	/**
	 * 删除所有的空文件夹
	 *
	 * @param file
	 */
	public static void cleanEmptyDir(File file) {
		if (file != null && file.isDirectory()) {
			File[] fs = file.listFiles();
			if (fs.length == 0) {
				emptyDirNum.incrementAndGet();
				emptyNum.incrementAndGet();
				System.out.println(file.getAbsolutePath() + " 是空文件夹被删除");
				file.delete();
			} else {
				for (File fc : fs) {
					cleanEmptyDir(fc);
				}
			}
		}
	}
}
