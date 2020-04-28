package com.zhd.study.suanfa;

import java.util.LinkedList;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-04-24 20:13
 */
public class Graph {

    public LinkedList<Integer>[] adj;
    public int v;

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
    }
}
