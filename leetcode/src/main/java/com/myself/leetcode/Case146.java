package com.myself.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Case146 {


    public class LRUCache {

        // 双向循环链表
        // 优势在于不会存在空指针情况，一定是连接成环的，并且前驱和后继非常容易拿到
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;

            DLinkedNode() {
            }

            DLinkedNode(int _key, int _value) {
                key = _key;
                value = _value;
            }
        }

        //k:key, value: 双向循环链表的一个节点
        private Map<Integer, DLinkedNode> cache = new HashMap<>();
        private int size;
        private int capacity;
        private DLinkedNode head, tail;

        LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            // 使用伪头部和伪尾部节点
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            // 如果 key 存在，先通过哈希表定位，再移到头部
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                // 如果 key 不存在，创建一个新的节点
                DLinkedNode newNode = new DLinkedNode(key, value);
                // 添加进哈希表
                cache.put(key, newNode);
                // 添加至双向链表的头部
                addToHead(newNode);
                ++size;
                if (size > capacity) {
                    // 如果超出容量，删除双向链表的尾部节点
                    DLinkedNode tail = removeTail();
                    // 删除哈希表中对应的项
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
                node.value = value;
                moveToHead(node);
            }
        }

        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // 每次访问之后，将该节点移动到链表头部
        private void moveToHead(DLinkedNode node) {
            // 先摘除该结点，也就是连接其前后结点
            removeNode(node);
            // 再添加到头部
            addToHead(node);
        }

        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }
    }


    /**
     * java现有的数据结构实现
     * 比HashMap的优点在于，这个是双向循环链表，元素的put都是有序的，
     * 而HashMap底层是数组，对元素取模的方式离散了
     */
    class LRUCache1 extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache1(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }


    class LRUCacheError1 {

        // 这种方式有问题，LRU是最近最久未使用，换言之，元素必须是有序排列的，
        // map只能记录使用次数的多少，不能记录最近最久关系
        private Map<Integer, Integer> map;

        private Map<Integer, Integer> countMap;

        private int capacity;


        public LRUCacheError1(int capacity) {
            map = new HashMap<>(capacity);
            this.capacity = capacity;
            countMap = new HashMap<>(capacity);
        }

        public int get(int key) {
            Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
            for (Map.Entry<Integer, Integer> entry : entries) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
            return -1;
        }

        public void put(int key, int value) {
            int k = get(key);
            if (k != 1 || map.size() < capacity) {
                add(key, value);
            } else if (map.size() >= capacity) {
                remove();
                add(key, value);
            }

        }

        private void add(int key, int value) {
            map.put(key, value);
            countMap.put(key, countMap.getOrDefault(key, 1));
        }

        private void remove() {
            Integer minCount = Integer.MAX_VALUE;
            Integer minCountK = 0;
            Set<Map.Entry<Integer, Integer>> entries = countMap.entrySet();
            for (Map.Entry<Integer, Integer> entry : entries) {
                if (minCount > entry.getValue()) {
                    minCount = entry.getValue();
                    minCountK = entry.getKey();
                }
            }
            map.remove(minCountK);
        }
    }
}
