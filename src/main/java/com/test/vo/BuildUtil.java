package com.test.vo;

import java.util.Date;

/**
 * 构建测试对象
 *
 * @author luoyong
 * @date 2018/5/14
 */
public class BuildUtil {

	private static BootPopup bootPopup;
	static {
		bootPopup = new BootPopup();
		bootPopup.setId(1);
		bootPopup.setStatus((byte) 1);
		bootPopup.setPopupName("对象复制性能测试");
		bootPopup.setStartTime(new Date());
		bootPopup.setShowTime((byte) 5);
		bootPopup.setPicUrl("https://www.baidu.com/");
		bootPopup.setActivityUrl("https://www.baidu.com/");
		bootPopup.setEditPerson("luoyong");
		bootPopup.setCreateTime(new Date());
		bootPopup.setUpdateTime(new Date());
	}

	public static BootPopup getBootPopup() {
		return bootPopup;
	}

}
