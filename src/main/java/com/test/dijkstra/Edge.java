package com.test.dijkstra;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 点与点组成的边
 *
 * @author: 罗勇
 * @date: 2021-11-15 14:46
 */
@Getter
@Setter
@EqualsAndHashCode
public class Edge {
    /**
     * 起点
     */
    private Point start;
    /**
     * 结束点
     */
    private Point end;
    /**
     * 权重
     */
    private int weight;

    public Edge(Point start, Point end) {
        this.start = start;
        this.end = end;
        // 默认权重
        this.weight = Dijkstra.DEF_WEIGHT;
    }

    public Edge(Point start, Point end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
