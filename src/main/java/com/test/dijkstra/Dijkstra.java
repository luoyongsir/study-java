package com.test.dijkstra;

import java.util.*;

/**
 * 基于 Dijkstra 贪心算法的最短路径寻找
 *
 * @author: 罗勇
 * @date: 2021-11-15 14:16
 */
public class Dijkstra {

    /**
     * 默认权重
     */
    public static final int DEF_WEIGHT = 100;
    /**
     * 表示无穷大的常量
     */
    private static final int INFINITY = 99999;
    /**
     * 点的键值集合,标识从起点到每个点的权重总和
     */
    private Map<Point, Integer> pointKeys = new HashMap<>();
    /**
     * 边集合,两个点成一条边.用来表示那些点可以连通和所需的权重.
     */
    private List<Edge> edges = new ArrayList<>();
    /**
     * 从起点到每个点的最短路径集合,当算法执行完毕时,里面保存着从起点到各个点的最短路径
     */
    private Map<Point, Dist> paths = new HashMap<>();

    public Dijkstra(Set<Point> source, List<Edge> edges, Point start) {
        init(source, edges, start);
    }

    /**
     * 初始化
     *
     * @param source 点的集合
     * @param edges  边的集合
     * @param start  寻径的起点
     */
    private void init(Set<Point> source, List<Edge> edges, Point start) {
        this.edges = edges;
        // 取出每个点
        for (Point v : source) {
            // 把每个点放入键值集合
            if (Objects.equals(v, start)) {
                // 起点到起点的键值为0
                pointKeys.put(v, 0);

                // 初始化从起点到起点的路径
                Dist dist = new Dist();
                // 路程点为起点
                dist.setPoint(start);
                // 前一个点为null
                dist.setPreDist(null);
                // 路程开销为0
                dist.setWeight(0);
                // 放入路径集合
                paths.put(start, dist);
            } else {
                // 到其他点为无穷大
                pointKeys.put(v, INFINITY);
            }
        }
        dijkstra();
    }

    /**
     * dijkstra 算法实现
     */
    private void dijkstra() {
        // 从当前点走向下一个点 下一个点原来所需要的开销
        Integer endPointKey;
        // 键值集合所存在的点大于0
        while (pointKeys.size() > 0) {
            // 获得当前键值几个拥有最小键值(开销)的点 称为当前点
            Point minKeyPoint = getMinKey(pointKeys);
            // 获得拥有最小键值点的键值
            int keyValue = pointKeys.get(minKeyPoint);
            // 寻找与这个点相邻的边
            List<Edge> adjacentEdges = getEdges(this.edges, minKeyPoint);
            // 遍历相邻的边
            for (Edge edge : adjacentEdges) {
                // 从起点到当前点的开销 + 通过这条边到下一个点的开销 称为当前开销
                int currentKey = keyValue + edge.getWeight();
                // 获取从起点到当前边对面点的开销
                endPointKey = pointKeys.get(edge.getEnd());
                // 若获取不到 则说明对面的点已经不存在于键值集合 通常是个环路
                if (endPointKey == null) {
                    // 可能是个环路 或 不可达
                    continue;
                }
                // 更新路径
                updatePath(endPointKey, edge, currentKey);
            }
            pointKeys.remove(minKeyPoint);
        }
    }

    /**
     * 更新路径
     *
     * @param endPointKey
     * @param edge
     * @param currentKey
     */
    private void updatePath(Integer endPointKey, Edge edge, int currentKey) {
        // 若当前开销 小于 原来从起点到边对面点的开销 则更新对点的键值
        if (currentKey < endPointKey) {
            // 覆盖原来的键值
            pointKeys.put(edge.getEnd(), currentKey);
            // 创建新的路程
            Dist advance = new Dist();
            // 起点为 对面边的点
            advance.setPoint(edge.getEnd());
            // 前一个点为当前点
            advance.setPreDist(paths.get(edge.getStart()));
            // 路程长度为边的权重
            advance.setWeight(edge.getWeight());
            // 放入路径集合
            paths.put(edge.getEnd(), advance);
        }
    }

    /**
     * 算法执行完毕后 寻找重点为参数点的路径 返回路径点的栈 因为终点会被最后找到,所以返回一个栈
     * 而不是数组,这样遍历比较方便.直接执行出栈操作就可以得到正确的从起点到终点所需要的点
     *
     * @param end 需要寻的终点
     * @return
     */
    public Stack<Point> getPaths(Point end) {
        Stack<Point> stack = null;
        for (Map.Entry<Point, Dist> entry : paths.entrySet()) {
            if (!Objects.equals(entry.getKey(), end)) {
                continue;
            }
            stack = new Stack<>();
            Dist td = entry.getValue();
            while (td != null) {
                stack.push(td.getPoint());
                td = td.getPreDist();
            }
            break;
        }
        return stack;
    }

    /**
     * 在集合中寻找边开销最小的元素
     *
     * @param pointKeys
     * @return
     */
    private Point getMinKey(Map<Point, Integer> pointKeys) {
        int minValue = INFINITY;
        Point minKeyPoint = null;
        for (Map.Entry<Point, Integer> entry : pointKeys.entrySet()) {
            Point point = entry.getKey();
            Integer value = entry.getValue();
            if (minKeyPoint == null) {
                minKeyPoint = point;
            }
            if (value < minValue) {
                minKeyPoint = point;
                minValue = value;
            }
        }
        return minKeyPoint;
    }

    /**
     * 寻找与参数点相邻的边
     *
     * @param edges
     * @param point
     * @return
     */
    private List<Edge> getEdges(List<Edge> edges, Point point) {
        List<Edge> tempEdges = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getStart().equals(point)) {
                tempEdges.add(edge);
            }
        }
        return tempEdges;
    }

    /**
     * 寻找两点组成的边
     *
     * @param start
     * @param end
     * @return
     */
    public Edge getEdge(Point start, Point end) {
        for (Edge edge : edges) {
            if (Objects.equals(edge.getStart(), start) && Objects.equals(edge.getEnd(), end)) {
                return edge;
            }
        }
        return null;
    }

}
