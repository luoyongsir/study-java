package com.test.dijkstra;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: 罗勇
 * @date: 2021-11-15 16:33
 */
@Getter
@Setter
@EqualsAndHashCode
public class Point {

    /**
     * 表id
     */
    private Long x;

    /**
     * 关联字段id
     */
    private Long y;

    public Point(Long x) {
        this.x = x;
    }

    public Point(Long x, Long y) {
        this.x = x;
        this.y = y;
    }
}
