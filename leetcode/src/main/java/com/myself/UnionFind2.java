package com.myself;

import java.util.Arrays;

/**
 * 并查集实现类2.0: 优化路径
 * 存在问题：合并两个结点的时候，只根据每个结点下面的子节点数来判断，并不是最优的合并方式
 *
 * @author hemaoling
 */
public class UnionFind2 {
    /**
     * 含义：i 的父节点是 parent[i]
     */
    private int[] parent;

    /**
     * 含义： size[i]表示以 i 为根的结点的个数
     * 作用：用来优化合并后产生某个根节点挂载的结点高度很高的情况
     */
    private int[] size;

    /**
     * 元素总个数
     */
    private int count;


    /**
     * 构造函数：每个节点的父节点是自己，也就是每个元素相互独立
     */
    UnionFind2(int count) {
        this.count = count;
        this.parent = new int[count];
        this.size = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = i;
            size[i] = 1;
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
     */
    public void unionElements(int p, int q) {
        int rootP = findRootOfK(p);
        int rootQ = findRootOfK(q);
        if (rootP == rootQ) {
            return;
        }

        //将元素少的合并到多的下面
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];

        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("原 始 元 素：arr =   " + Arrays.toString(arr));
        UnionFind2 unionFind = new UnionFind2(10);

        System.out.println("原始父子关系：parent= " + Arrays.toString(unionFind.parent));

        doMerge(unionFind);

    }

    private static void doMerge(UnionFind2 unionFind) {
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
