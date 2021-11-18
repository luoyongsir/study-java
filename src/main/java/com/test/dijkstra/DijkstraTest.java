package com.test.dijkstra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author: 罗勇
 * @date: 2021-11-15 15:55
 */
public class DijkstraTest {
    public static void main(String[] args) {
        //声明点
        Point A = new Point(1L, 1L);
        Point B = new Point(2L);
        Point C = new Point(3L);
        Point D = new Point(4L);
        Point E = new Point(5L);
        //放入点集合
        Set<Point> source = new HashSet<>();
        source.add(A);
        source.add(B);
        source.add(C);
        source.add(D);
        source.add(E);
        //声明边
        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.add(new Edge(A, B, 10));
        edges.add(new Edge(A, C, 5));
        edges.add(new Edge(B, C, 2));
        edges.add(new Edge(B, D, 1));
        edges.add(new Edge(C, B, 3));
        edges.add(new Edge(C, D, 9));
        edges.add(new Edge(C, E, 2));
        edges.add(new Edge(D, E, 4));
        edges.add(new Edge(E, D, 6));
        edges.add(new Edge(E, A, 7));

//		edges.add(new Edge(A, B,1));
//		edges.add(new Edge(A, C));
//		edges.add(new Edge(B, C));
//		edges.add(new Edge(B, D));
//		edges.add(new Edge(C, B));
//		edges.add(new Edge(C, D));
//		edges.add(new Edge(C, E));
//		edges.add(new Edge(D, E));
//		edges.add(new Edge(E, D));
//		edges.add(new Edge(E, A));

        Dijkstra d = new Dijkstra(source, edges, A);

        // 提供 点的集合 边的集合 起点 终点 开始寻径
        Stack<Point> points = d.getPaths(D);
        while (points.size() > 0) {
            Point p = points.pop();
            // 打印 打印结果 : 1>3>2>4
            System.out.print(p.getX() + ">");
        }
        System.out.println();
    }
}
