package com.test.dijkstra;

import lombok.Getter;
import lombok.Setter;

/**
 * 单向链表
 *
 * @author: 罗勇
 * @date: 2021-11-15 14:46
 */
@Getter
@Setter
public class Dist {

    /**
     * 路程的开销
     */
    private int weight;

    /**
     * 路程点
     */
    private Point point;

    /**
     * 前一个点
     */
    private Dist preDist;
}
