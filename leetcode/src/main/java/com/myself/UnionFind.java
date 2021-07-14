package com.myself;

import java.util.Arrays;

/**
 * 并查集实现类1.0:未优化路径
 *
 * @author hemaoling
 */
public class UnionFind {
    /**
     * i 的父节点是 parent[i]
     */
    private int[] parent;

    /**
     * 元素总个数
     */
    private int count;


    /**
     * 构造函数：每个节点的父节点是自己，也就是每个元素相互独立
     */
    UnionFind(int count) {
        this.count = count;
        this.parent = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = i;
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
        if (ret == k) {
            return true;
        }
        return false;
    }

    /**
     * 判断p q是否在同一个集合
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isInTheOne(int p, int q) {
        return findRootOfK(p) == findRootOfK(q);
    }

    /**
     * 合并两个元素到同一个集合中
     *
     * @param p
     * @param q
     */
    public void unionElements(int p, int q) {
        int rootP = findRootOfK(p);
        int rootQ = findRootOfK(q);
        if (rootP != rootQ) {
            // 这里合并到同一个集合即可，只记录合并关系，不关注谁是谁的父节点
            parent[rootP] = rootQ;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("原 始 元 素：arr =   " + Arrays.toString(arr));
        UnionFind unionFind = new UnionFind(10);

        System.out.println("原始父子关系：parent= " + Arrays.toString(unionFind.parent));

        doMerge(unionFind);

    }

    private static void doMerge(UnionFind unionFind) {
        unionFind.unionElements(2, 3);
        System.out.println("合并2、3之后：parent= " + Arrays.toString(unionFind.parent));

        unionFind.unionElements(4, 3);
        System.out.println("合并4、3之后：parent= " + Arrays.toString(unionFind.parent));

        unionFind.unionElements(5, 3);
        System.out.println("合并5、3之后：parent= " + Arrays.toString(unionFind.parent));

        unionFind.unionElements(9, 3);
        System.out.println("合并9、3之后：parent= " + Arrays.toString(unionFind.parent));
    }
}
