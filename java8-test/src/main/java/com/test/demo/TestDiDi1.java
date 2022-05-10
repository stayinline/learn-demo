package com.test.demo;


import java.util.*;
import java.util.stream.Collectors;

public class TestDiDi1 {

    //[1,2,2,5,5,5] -> [1,2,5,-1,-1,-1]
    public static void trim(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
            } else {
                while (j < nums.length - 1 && nums[j] == nums[i]) {
                    j++;
                }
                i++;
                nums[i] = nums[j];
            }
        }

        for (; i < nums.length; i++) {
            nums[i] = -1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 5, 5, 5};
        trim(nums);
        System.out.println(Arrays.toString(nums));

    }










    /**
     * @param n    服务个数
     * @param m    指定服务编号
     * @param deps 服务之间的依赖关系dependencies
     * @return
     */
    List<Integer> ret = new ArrayList<>();

    public List<Integer> serviceStart(int n, int m, List<List<Integer>> deps) {
        //边界校验
        if (n <= 0 || m < 0 || deps.size() != n) {
            return ret;
        }
        // key-> 服务编号，value-> 该编号服务依赖的前置服务编号合集
        Map<Integer, List<Integer>> map = new HashMap<>(n);
        for (int i = 0; i < deps.size(); i++) {
            List<Integer> list = deps.get(i);
            if (list.size() == 1) {
                map.put(i, new ArrayList<>());
            } else {
                map.put(i, list.subList(1, list.size()));
            }
        }
        // 拿到 m 的前置服务
        List<Integer> mdeps = map.get(m);
        if (mdeps.isEmpty()) {
            return ret;
        }

        buildRet(mdeps, map, m);
        ret = ret.stream().distinct().collect(Collectors.toList());
        return ret;
    }

    public void buildRet(List<Integer> mdeps, Map<Integer, List<Integer>> map, int m) {
        if (mdeps.isEmpty()) {
            return;
        }
        for (Integer mdep : mdeps) {
            ret.addAll(map.get(mdep));
            buildRet(map.get(mdep), map, m);
        }
    }

    public static int productsCount(int n, int m, int[] pitDepth) {
        int count = 0;
        if (m != pitDepth.length || pitDepth.length <= 0) {
            return count;
        }

        int len = 0;
        for (int i = 0; i < pitDepth.length; i++) {
            if (pitDepth[i] < 0) {
                len++;
            } else if (len > 0 && len / n > 0) {
                count += len / n;
                len = 0;
            }
        }
        return count;
    }

//    public static void main(String[] args) {
//        List<List<Integer>> deps = new ArrayList<>();
//        List<Integer> elem = new ArrayList<>();
//        elem.add(0);
//        deps.add(elem);
//
//        List<Integer> elem1 = new ArrayList<>();
//        elem1.add(1);
//        elem1.add(0);
//        deps.add(elem1);
//
//        List<Integer> elem2 = new ArrayList<>();
//        elem2.add(1);
//        elem2.add(1);
//        deps.add(elem2);
//
//        List<Integer> elem3 = new ArrayList<>();
//        elem3.add(2);
//        elem3.add(0);
//        elem3.add(1);
//        deps.add(elem3);
//        System.out.println(new TestDiDi().serviceStart(4, 2, deps));
//
//        int[] nums = {0, -1, -2, 0};
//        System.out.println(productsCount(2, 4, nums));
//
//    }
}
