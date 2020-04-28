package com.zhd.study.suanfa;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-26 10:47
 */
public class ShenDuGraph {
    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);

        printGraph(graph.adj);

        LinkedList<Integer>[] inverseAdj = new LinkedList[graph.v];

        for (int i = 0; i < graph.v; i++) {
            inverseAdj[i] = new LinkedList<>();
        }

        for (int i = 0; i < graph.adj.length; i++) {
            LinkedList<Integer> linkedList = graph.adj[i];
            for (Integer vlue : linkedList) {
                inverseAdj[vlue].add(i);
            }
        }

        printGraph(inverseAdj);
    }

    public static void printGraph(LinkedList<Integer>[] adj) {
        System.out.println(Arrays.toString(adj));
    }
}
