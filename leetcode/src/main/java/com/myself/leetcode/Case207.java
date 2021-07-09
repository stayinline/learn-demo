package com.myself.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Case207 {

    // List<Integer>:下标[0]存储的是前驱课程、下标[1]存储的是后继课程
    List<List<Integer>> edges;
    // numCourses门课程的遍历情况
    int[] visited;
    boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    /**
     * 深度优先搜索，遍历 edges，
     * @param u
     */
    public void dfs(int u) {
        visited[u] = 1;
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }

    public static void main(String[] args) {
        int[][] c = {{1, 2}};
        int a = 3;
        new Case207().canFinish(a, c);
    }

}
