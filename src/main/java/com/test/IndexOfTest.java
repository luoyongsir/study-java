package com.test;

import org.junit.Test;

/**
 * String.indexOf(char) vs String.indexOf(String)
 * @author luoyong
 * @date 2017/12/24
 */
public class IndexOfTest {

	private static final String TEXT2 = "我说道：“爸爸，你走吧。”他望车外看了看，说：“我买几个橘子去。你就在此地，不要走动。”我看那边月台的栅栏外有几个卖东西的等着顾客。走到那边月台，须穿过铁道，须跳下去又爬上去。父亲是一个胖子，走过去自然要费事些。w本来要去的，他不肯，只好让他去。";

	@Test
	public void test() {
		int num = 10000000;
		System.out.println("固定字符串测试：");
		for (int i = 0; i < 10; i++) {
			indexOfStringConst(num);
			indexOfCharConst(num);
			System.out.println(" -------------------------------- ");
		}
		System.out.println("每次都用新对象测试：");
		for (int i = 0; i < 10; i++) {
			newString(num);
			indexOfString(num);
			indexOfChar(num);
			System.out.println(" -------------------------------- ");
		}
	}

	public void indexOfStringConst(int num) {
		long starTime = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			TEXT2.indexOf("w");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("indexOfStringConst 耗时：" + (endTime - starTime) + " 毫秒");
	}

	public void indexOfCharConst(int num) {
		long starTime = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			TEXT2.indexOf('w');
		}
		long endTime = System.currentTimeMillis();
		System.out.println("indexOfCharConst 耗时：" + (endTime - starTime) + " 毫秒");
	}

	public void newString(int num) {
		long starTime = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			new String(TEXT2 + i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("newString 耗时：" + (endTime - starTime) + " 毫秒");
	}

	public void indexOfString(int num) {
		long starTime = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			new String(TEXT2 + i).indexOf("w");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("indexOfString 耗时：" + (endTime - starTime) + " 毫秒");
	}

	public void indexOfChar(int num) {
		long starTime = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			new String(TEXT2 + i).indexOf('w');
		}
		long endTime = System.currentTimeMillis();
		System.out.println("indexOfChar 耗时：" + (endTime - starTime) + " 毫秒");
	}
}
