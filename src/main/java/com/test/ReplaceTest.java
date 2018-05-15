package com.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * JDK String.replace() vs Apache Commons StringUtils.replace()
 * @author luoyong
 * @date 2017/12/23
 */
public class ReplaceTest {

	/**
	 * 匹配多个
	 */
	private static final String TEXT1 = "w说道：“爸爸，你走吧。”他望车外看了看，说：“w买几个橘子去。你就在此地，不要走动。”w看那边月台的栅栏外有几个卖东西的等着顾客。走到那边月台，须穿过铁道，须跳下去又爬上去。父亲是一个胖子，走过去自然要费事些。w本来要去的，他不肯，只好让他去。";
	/**
	 * 匹配一个
	 */
	private static final String TEXT2 = "我说道：“爸爸，你走吧。”他望车外看了看，说：“我买几个橘子去。你就在此地，不要走动。”我看那边月台的栅栏外有几个卖东西的等着顾客。走到那边月台，须穿过铁道，须跳下去又爬上去。父亲是一个胖子，走过去自然要费事些。w本来要去的，他不肯，只好让他去。";
	/**
	 * 没有匹配
	 */
	private static final String TEXT3 = "我说道：“爸爸，你走吧。”他望车外看了看，说：“我买几个橘子去。你就在此地，不要走动。”我看那边月台的栅栏外有几个卖东西的等着顾客。走到那边月台，须穿过铁道，须跳下去又爬上去。父亲是一个胖子，走过去自然要费事些。我本来要去的，他不肯，只好让他去。";

	@Test
	public void test() {
		int num = 1000000;
		System.out.println("匹配多个：");
		for (int i = 0; i < 10; i++) {
			execute(TEXT1, num);
		}
		System.out.println("匹配一个：");
		for (int i = 0; i < 10; i++) {
			execute(TEXT2, num);
		}
		System.out.println("没有匹配：");
		for (int i = 0; i < 10; i++) {
			execute(TEXT3, num);
		}
	}

	private void execute(String text, int num) {
		stringUtilsReplace(text, num);
		stringReplaceAll(text, num);
		stringReplace(text, num);
		System.out.println(" -------------------------------------- ");
	}

	private void stringUtilsReplace(String text, int num) {
		long starTime = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			StringUtils.replace(text, "w", "我");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("stringUtilsReplace 耗时：" + (endTime - starTime) + " 毫秒");
	}

	private void stringReplaceAll(String text, int num) {
		long starTime = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			text.replaceAll("w", "我");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("stringReplaceAll 耗时：" + (endTime - starTime) + " 毫秒");
	}

	private void stringReplace(String text, int num) {
		long starTime = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			text.replace("w", "我");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("stringReplace 耗时：" + (endTime - starTime) + " 毫秒");
	}
}
