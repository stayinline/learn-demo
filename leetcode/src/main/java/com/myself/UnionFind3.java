package com.myself;

import java.util.Arrays;

/**
 * 并查集实现类3.0: 根据rank优化合并方式
 * <p>
 * 在 find 的过程中，从底向上，如果此时访问的节点不是根节点的话，
 * 那么我们可以把这个节点尽量的往上挪一挪，减少数的层数，这个过程就叫做路径压缩
 *
 * @author hemaoling
 */
public class UnionFind3 {
    /**
     * 含义：i 的父节点是 parent[i]
     */
    private int[] parent;

    /**
     * 含义：rank[i] 表示以 i 为根的集合所表示的树的层数
     */
    private int[] rank;


    /**
     * 元素总个数
     */
    private int count;


    /**
     * 构造函数：每个节点的父节点是自己，也就是每个元素相互独立
     */
    UnionFind3(int count) {
        this.count = count;
        this.parent = new int[count];
        this.rank = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 一直寻找k的父节点，直到找到根节点
     *
     * @param k 要寻找的元素
     * @return 返回值是k的根节点，如果k不属于现有集合，那么会返回k，也就是k自己是个集合
     */
    private int findRootOfK(int k) {
        assert (k >= 0 && k <= count);
        while (k != parent[k]) {
            k = parent[k];
        }
        return k;
    }


    /**
     * 判断 k 是否在某个集合中
     */
    public boolean isInSomeCollection(int k) {
        int ret = findRootOfK(k);
        return ret == k;
    }

    /**
     * 判断p q是否在同一个集合
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isInTheSameOne(int p, int q) {
        return findRootOfK(p) == findRootOfK(q);
    }

    /**
     * 合并两个元素到同一个集合中
     */
    public void unionElements(int p, int q) {
        int rootP = findRootOfK(p);
        int rootQ = findRootOfK(q);
        if (rootP == rootQ) {
            return;
        }

        //将元素少的合并到多的下面
        if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else {
            parent[rootP] = rootQ;
            rank[rootP] += 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("原 始 元 素：arr =   " + Arrays.toString(arr));
        UnionFind3 unionFind = new UnionFind3(10);

        System.out.println("原始父子关系：parent= " + Arrays.toString(unionFind.parent));

        doMerge(unionFind);

    }

    private static void doMerge(UnionFind3 unionFind) {
        unionFind.unionElements(2, 3);
        System.out.println("合并2、3之后：parent= " + Arrays.toString(unionFind.parent));
        System.out.println("合并2、3之后：rank  = " + Arrays.toString(unionFind.rank));
        System.out.println("");
        unionFind.unionElements(4, 3);
        System.out.println("合并4、3之后：parent= " + Arrays.toString(unionFind.parent));
        System.out.println("合并4、3之后：rank  = " + Arrays.toString(unionFind.rank));
        System.out.println("");

        unionFind.unionElements(5, 3);
        System.out.println("合并5、3之后：parent= " + Arrays.toString(unionFind.parent));
        System.out.println("合并5、3之后：rank  = " + Arrays.toString(unionFind.rank));
        System.out.println("");

        unionFind.unionElements(9, 3);
        System.out.println("合并9、3之后：parent= " + Arrays.toString(unionFind.parent));
        System.out.println("合并9、3之后：rank  = " + Arrays.toString(unionFind.rank));
    }
}
