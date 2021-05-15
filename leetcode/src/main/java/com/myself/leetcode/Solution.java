package com.myself.leetcode;


import java.util.*;


class Solution {
    public String reverseLeftWords(String s, int n) {
        Queue<String> queue = new LinkedList();
        int i = 0;
        for (; i < n; i++) {
            queue.offer(String.valueOf(s.charAt(i)));
        }

        StringBuilder stringBuilder = new StringBuilder();
        ;
        stringBuilder.append(s.substring(i, s.length()));
        while (!queue.isEmpty()) {
            stringBuilder.append(queue.poll());
        }
        return stringBuilder.toString();
    }

    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(String.valueOf(s.charAt(i)));
            }
        }
        return sb.toString();
    }



    public int[] reversePrint1(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        int[] arr = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            arr[i++] = stack.pop();
        }

        return arr;
    }

    public int[] reversePrint(ListNode head) {
        int count = 0;
        ListNode pre = head;// 上一结点
        ListNode cur = head.next;// 当前结点
        ListNode tmp;// 临时结点，用于保存当前结点的指针域（即下一结点）
        while (cur != null) {// 当前结点为null，说明位于尾结点
            tmp = cur.next;
            cur.next = pre;// 反转指针域的指向
            // 指针往下移动
            pre = cur;
            cur = tmp;
            count++;
        }
        head.next = null;

        ListNode newHead = pre;
        int[] arr = new int[count];
        int i = 0;
        while (newHead != null) {
            arr[i] = newHead.val;
            newHead = newHead.next;
        }
        return arr;
    }

    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        if (N == 2) {
            return 1;
        }

        int current = 0;
        int prev1 = 1;
        int prev2 = 1;

        for (int i = 3; i <= N; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    public int fib2(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public int[] printNumbers(int n) {
        int n9 = getN9(n);
        int[] ret = new int[n9];
        for (int i = 1; i <= n9; i++) {
            ret[i - 1] = i;
        }
        return ret;
    }

    public static int getN9(int n) {
        int s = 9;
        for (int i = 1; i < n; i++) {
            s = s * 10 + 9;
        }
        return s;
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode pre = head;
        ListNode cur = head;
        int n = 0;
        while (cur.val != val) {
            cur = cur.next;
            n++;
        }

        while (n-- > 1) {
            pre = pre.next;
        }

        ListNode nxt = cur.next;
        pre.next = nxt;

        return head;
    }

    public int[] exchange(int[] nums) {
        int i = 0, j = nums.length - 1;

        while (i < j) {
            while (i < j && nums[i] % 2 != 0) {
                i++;
            }

            while (i < j && nums[j] % 2 == 0) {
                j--;
            }

            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        return nums;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode pre = head;
        ListNode nxt = head;
        while (k-- > 0) {
            nxt = nxt.next;
        }
        while (nxt != null) {
            nxt = nxt.next;
            pre = pre.next;
        }
        return pre;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;
        return newHead.next;

    }

    public TreeNode mirrorTree1(TreeNode root) {
        TreeNode cur = root;
        if (cur == null) {
            return null;
        }
        cur.left = root.right;
        cur.right = root.left;
        mirrorTree(cur.right);
        mirrorTree(cur.left);

        return root;
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode node = root.right;

        root.right = mirrorTree(root.left);
        root.left = mirrorTree(node);
        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        return root == null || recur(root.left, root.right);
    }

    boolean recur(TreeNode L, TreeNode R) {
        if (L == null && R == null) {
            return true;
        }
        if (L == null || R == null || L.val != R.val) {
            return false;
        }
        return recur(L.left, R.right) && recur(L.right, R.left);
    }

    public boolean isSymmetric2(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        midScan(root, list);
        System.out.println(list);
        int j = list.size() - 1;
        int i = 0;
        while (i < j) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public void midScan(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        midScan(root.left, list);
        list.add(root.val);
        midScan(root.right, list);
    }

    public static int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            ans.add(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = map.get(nums[i]);
            if (null != count) {
                count++;
            } else {
                count = 1;
            }
            map.put(nums[i], count);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > (nums.length / 2)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public int maxSubArray(int[] nums) {
        int sum = Integer.MIN_VALUE;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0) {
                sum = nums[i];
            } else {
                sum = sum + nums[i];
            }

            if (res < sum) {
                res = sum;
            }
        }
        return res;
    }

    public char firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (s.indexOf(c) == s.lastIndexOf(c)) {
                return c;
            }
        }
        return ' ';
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLength(headA);
        int lenB = getLength(headB);

        ListNode curA = headA;
        ListNode curB = headB;

        while (lenA != lenB) {
            if (lenA > lenB) {
                curA = curA.next;
                lenA--;
            }
            if (lenA < lenB) {
                curB = curB.next;
                lenB--;
            }
        }

        while (curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }

        return curA;

    }

    public int getLength(ListNode head) {
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    public int[][] findContinuousSequence(int target) {
        int i = 1;
        int j = 1;
        int sum = 0;

        ArrayList<int[]> res = new ArrayList<>();
        while (i <= target / 2) {
            if (sum > target) {
                sum -= i;
                i++;
            } else if (sum < target) {
                sum += j;
                j++;
            } else {
                int[] tmp = new int[j - i];
                for (int k = i; k < j; k++) {
                    tmp[k - i] = k;
                }

                res.add(tmp);
                sum -= i;
                i++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        //i=1-k;是为了初始化处理第一个窗口中的所有值，所以，更新队列、最大值的时候需哟判断i>0
        for (int j = 0, i = 1 - k; j < nums.length; i++, j++) {
            // 删除 deque 中对应的 nums[i-1]，
            // 因为队列中头部的这个nums[i - 1]元素已经记录过了，窗口向后滑动的时候，应该删掉
            if (i > 0 && deque.peekFirst() == nums[i - 1]) {
                deque.removeFirst();
            }
            // 保持 deque 递减
            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.removeLast();
            }
            deque.addLast(nums[j]);
            // 记录窗口最大值
            if (i >= 0) {
                res[i] = deque.peekFirst();
            }
        }
        return res;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //定义一个前置节点，用了当做头指针
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int flag = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            int sum = x + y + flag;
            flag = sum / 10;
            sum %= 10;
            cur.next = new ListNode(sum);
            cur = cur.next;

            if (l2 != null) {
                l2 = l2.next;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
        }

        //处理最后一个进位
        if (flag == 1) {
            cur.next = new ListNode(flag);
        }
        return pre.next;
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int r = 0;
        int res = 0;
        int len = s.length();
        for (int l = 0; l < len; l++) {
            //这里左指针不为0则说明这个元素是由右指针放入set的，左指针移动之后，应该删除
            if (l != 0) {
                set.remove(s.charAt(l - 1));
            }

            //判断set中是否包含当前字符，不包含就说明不是重复的
            while (r < len && !set.contains(s.charAt(r))) {
                set.add(s.charAt(r));
                r++;
            }

            res = Math.max(res, r - l + 1);
        }
        return res;
    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(nums, track, res);
        return res;
    }

    private void backTrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> res) {
        //这里当数组长度和路径track的长度相等时，退出递归
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //判断路径是否包含当前元素，包含说明已经采用过了，不包含时则可以采用
            if (track.contains(nums[i])) {
                continue;
            }

            track.add(nums[i]);

            //递归从头开始寻找可选队列中的元素
            backTrack(nums, track, res);

            //出递归的时候，一定是上一层中已经选取过当前元素，所以退回到上一层的时候需要把决策树的最后一层去掉
            track.removeLast();
        }

    }


    public static boolean judgeCircle1(String moves) {
        int r = 0, l = 0;
        char[] chars = moves.toCharArray();
        for (char c : chars) {

            if ('R' == c) {
                r++;
            } else if ('L' == c) {
                r--;
            } else if ('U' == c) {
                l++;
            } else if ('D' == c) {
                l--;
            }
        }
        return r == 0 && l == 0;
    }

    public boolean judgeCircle(String moves) {
        int[] arr = new int[26];
        for (char c : moves.toCharArray()) {
            arr[c - 'A']++;
        }
        return ((arr['R' - 'A'] == arr['L' - 'A']) && (arr['U' - 'A'] == arr['D' - 'A']));

    }


    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();

    public String minWindow(String s, String t) {
        int tLen = t.length();

        //将t全部存入ori
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }

        int l = 0, r = -1;//定义滑动窗口的左右指针
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            //判断s当前的字母是否可以放进cnt
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                //满足条件则尝试缩小滑动窗口的左指针
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    //判断 cnt是否包含全部的ori -》 cnt的每个字母出现的次数不小于ori中的即可
    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < p.length(); i++) {
            needs.put(p.charAt(i), needs.getOrDefault(p.charAt(i), 0) + 1);
        }

        int l = 0, r = 0;
        int match = 0;

        while (r < s.length()) {
            char c = s.charAt(r);
            if (needs.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (needs.get(c).equals(window.get(c))) {
                    match++;
                }
            }

            r++;

            while (match == needs.size()) {
                if (r - l == p.length()) {
                    res.add(l);
                }

                char c2 = s.charAt(l);
                l++;

                if (needs.containsKey(c2)) {
                    if (needs.get(c2).equals(window.get(c2))) {
                        match--;
                    }
                    window.put(c2, window.getOrDefault(c2, 0) - 1);
                }
            }
        }
        return res;
    }


    public List<Integer> findAnagrams2(String s, String p) {
        int n = s.length(), m = p.length();
        List<Integer> res = new ArrayList<>();
        if (n < m) {
            return res;
        }

        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        //初始化两个数组
        for (int i = 0; i < m; i++) {
            pCnt[p.charAt(i) - 'a']++;
            sCnt[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(sCnt, pCnt)) {
            res.add(0);
        }

        //从p的长度开始向后
        for (int i = m; i < n; i++) {
            //左指针(i-m记为左指针)开始移动，sCnt开始删除左指针的数据
            sCnt[s.charAt(i - m) - 'a']--;
            //右指针(i理解为右指针)同步移动，sCnt增加右指针的计数
            sCnt[s.charAt(i) - 'a']++;
            if (Arrays.equals(sCnt, pCnt)) {
                res.add(i - m + 1);
            }
        }
        return res;
    }

    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) {
            return false;
        }

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        for (int i = 0; i < len1; i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(s1Count, s2Count)) {
            return true;
        }

        for (int r = len1; r < len2; r++) {
            s2Count[s2.charAt(r - len1) - 'a']--;
            s2Count[s2.charAt(r) - 'a']++;
            if (Arrays.equals(s1Count, s2Count)) {
                return true;
            }
        }
        return false;
    }

    int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++) {
            if (isPrim[i]) {
                for (int j = i * i; j < n; j += i) {
                    //i的倍数不可能是质数
                    isPrim[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i]) {
                count++;
            }
        }

        return count;
    }

    // 时间复杂度 O(N)
    public boolean canFinish(int[] piles, int speed, int H) {
        int time = 0;
        for (int n : piles) {
            time += timeOf(n, speed);
        }
        return time <= H;
    }

    public int timeOf(int n, int speed) {
        return (n / speed) + ((n % speed > 0) ? 1 : 0);
    }

    public int getMax(int[] piles) {
        int max = 0;
        for (int n : piles) {
            max = Math.max(n, max);
        }
        return max;
    }

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }


    public static int[] findErrorNums(int[] nums) {
        int dup = -1, missing = -1;
        for (int i = 1; i <= nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i == nums[j]) {
                    count++;
                }
            }

            if (count == 2) {
                dup = i;
            } else if (count == 0) {
                missing = i;
            }

            if (dup > 0 && missing > 0) {
                break;
            }
        }
        return new int[]{dup, missing};
    }


    public static int coinChange(int[] coins, int amount) {
        return dpCoin(amount, coins);
    }


    public static int dpCoin(int amount, int[] coins) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int res = Integer.MAX_VALUE;

        for (int coin : coins) {
            int sub = dpCoin(amount - coin, coins);
            if (sub == -1)
                continue;
            res = Math.min(res, sub + 1);
        }

        if (res != Integer.MAX_VALUE)
            return res;
        else
            return -1;
    }


    public static int coinChangeMem(int[] coins, int amount) {
        int[] mem = new int[amount + 1];
        return dpCoinMem(amount, coins, mem);
    }

    public static int dpCoinMem(int amount, int[] coins, int[] mem) {
        for (int m : mem) {
            if (m == amount)
                return m;
        }

        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = dpCoinMem(amount - coin, coins, mem);
            if (sub == -1)
                continue;
            res = Math.min(res, sub + 1);
        }
        mem[amount] = res != Integer.MAX_VALUE ? res : -1;
        return mem[amount];
    }

    public static int coinChangeOfficial(int[] coins, int amount) {
        //初始化的值一定是amount=1(也就是全部由1元的硬币组成)
        int max = amount + 1;

        //创建备忘录并初始化为最大值
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);

        //base case(金额为0时对应的方案也是0)
        dp[0] = 0;

        //外层循环：遍历所有金额[状态]的取值
        for (int i = 1; i <= amount; i++) {
            //内层循环：遍历所有[选择]中的备选项，并且在所有备选项中求最小值
            for (int coin : coins) {
                //只有当备选项的面值小于待组成金额的值的时候，才能将当前备选项纳入，然后判断是否需要更新
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }

    public int maxProfit_k_1(int[] prices) {
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, -price);
        }
        return dp_i_0;
    }


    public int maxProfit22(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0; // 代表 dp[i-2][0]
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }


    public static int maxProfit(int max_k, int[] prices) {
        int n = prices.length;
        if (max_k > n / 2) {
            return maxProfit_k_inf(prices);
        }

        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) { /* 处理 base case */
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][max_k][0];
    }


    public static int maxProfit_k_inf(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }


    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int res = 0;

        int[] dp = new int[nums.length];
        // base case：dp 数组全都初始化为 1
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;

        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }


    public int numIdenticalPairs(int[] nums) {

        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
        }

        return count;

    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        for (int i = 0; i < row; i++) {
            int cou = matrix[i].length - 1;
            for (int j = cou; j >= 0; j--) {
                if (matrix[i][j] < target) {
                    break;
                } else if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int i = row - 1;
        int j = 0;
        //从左下角向右上角开始查找
        while (i >= 0 && j < col) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }


    public int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        // 自底向上求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j - 1] + 1
                    );
                }
            }
        }
        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }


    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s3 == null || s1 == null || s2 == null) {
            return false;
        }

        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();

        if (l1 + l2 != l3) {
            return false;
        }

        boolean[][] dp = new boolean[l1 + 1][l2 + 1];
        //base case
        dp[0][0] = true;
        //自底向上，从base case开始迭代，base case占据了下标0的位置，所以循环要到s1和s2的lengh
        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                //这里一定要减去1，否则s3的下标不准确
                int k = i + j - 1;
                if (i > 0) {
                    //s1和s3当前字符相等，并且上一个字符也相等
                    dp[i][j] = dp[i][j] || (s1.charAt(i - 1) == s3.charAt(k) && dp[i - 1][j]);
                }
                if (j > 0) {
                    //s2和s3当前字符相等，并且上一个字符也相等
                    dp[i][j] = dp[i][j] || (s2.charAt(j - 1) == s3.charAt(k) && dp[i][j - 1]);
                }
            }
        }
        return dp[l1][l2];
    }


    private static Map<Integer, Integer> map = new HashMap<>();

    public static int superEggDrop(int k, int n) {
        return dpEgg(k, n);
    }

    public static int dpEgg(int k, int n) {
        //base case 如果鸡蛋只有一个，只能遍历全部楼层
        if (k == 1) {
            return n;
        }
        //base case 如楼层数为0，显然不需要鸡蛋
        if (n == 0) {
            return 0;
        }

        Integer key = buildKey(k, n);
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int result = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            result = Math.min(
                    result,
                    Math.max(
                            dpEgg(k - 1, i - 1), /*鸡蛋碎了,鸡蛋数量k-1,然后开始尝试i-1以下的楼层*/
                            dpEgg(k, n - i) /*鸡蛋没碎,鸡蛋数量k不变,然后开始尝试*/
                    ) + 1 /*加1表示在第i层楼扔了一次*/
            );
        }
        map.put(key, result);
        return result;
    }

    private static Integer buildKey(int k, int n) {
        return n * 1000 + k;
    }

    private static Map<Integer, Integer> memo = new HashMap<Integer, Integer>();

    public static int superEggDropOfficial(int k, int n) {
        return dp(k, n);
    }

    public static int dp(int k, int n) {
        if (!memo.containsKey(buildKey(k, n))) {
            int ans;
            if (n == 0) {
                ans = 0;
            } else if (k == 1) {
                ans = n;
            } else {
                int lo = 1, hi = n;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2; /*二分找到中楼层x*/
                    int t1 = dp(k - 1, x - 1); /*中间楼层x鸡蛋碎了*/
                    int t2 = dp(k, n - x);/*中间楼层x鸡蛋没碎*/

                    //根据二分递归的结果更新下一次尝试的楼层区间
                    if (t1 < t2) {
                        lo = x;
                    } else if (t1 > t2) {
                        hi = x;
                    } else {
                        lo = hi = x;
                    }
                }

                ans = Math.min(
                        //下区间
                        Math.max(dp(k - 1, lo - 1),/*鸡蛋碎了,鸡蛋数量k-1,然后开始尝试i-1以下的楼层*/
                                dp(k, n - lo)),/*鸡蛋没碎,鸡蛋数量k不变,然后开始尝试*/
                        //上区间
                        Math.max(dp(k - 1, hi - 1),
                                dp(k, n - hi))
                ) + 1; /*加1表示在第i层楼扔了一次*/
            }

            memo.put(buildKey(k, n), ans);
        }

        return memo.get(n * 100 + k);
    }

    public static int nthUglyNumber(int n) {
        int i = 1;
        while (n > 0) {
            if (isUgly(i)) {
                System.out.println("th:" + n + "  == " + i);
                n--;
            }
            i++;
        }
        return i -= 1;
    }

    public static boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        //一直除以2、3、5，最终能整除的是丑数，否则不是
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }

    public static void test() {
        StopWatchUtils.start();

        int i = nthUglyNumber(1362);

        System.out.println(i + "  ,time:" + StopWatchUtils.stopAndGetTimeMillis());
    }


    public int longestPalindromeSubseq(String s) {
        if (null == s || "".equals(s)) {
            return 0;
        } else if (s.length() == 1) {
            return 1;
        }

        int n = s.length();
        //dp[i][j]记录的是s[i]和s[j]之间的字符串的最长回文子序列的长度
        int[][] dp = new int[n][n];
        //base case: 只有一个字符的时候,也就是i==j，此时显然dp[i][j]==dp[i][i]==1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        /* 状态转移方程：
         * if (s[i] == s[j])
         *     // 它俩一定在最长回文子序列中
         *     dp[i][j] = dp[i + 1][j - 1] + 2;
         * else
         *     // s[i+1..j] 和 s[i..j-1] 谁的回文子序列更长？
         *     dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
         */

        /*
         * i>j是不存在的情况，所以不需要处理，然后根据状态转移方程，显然dp数组需要斜着遍历或者反着遍历
         */

        // 反着遍历保证正确的状态转移
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 状态转移方程
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        // 整个 s 的最长回文子串长度
        return dp[0][n - 1];
    }

    //统计所有区间的重叠个数
    public static int maxOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        //按照区间的end端点(a[0]:start,a[1]:end)进行升序排列
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] < b[1])
                return -1;
            else if (a[1] > b[1])
                return 1;
            else return 0;
        });

        int count = 1; //当只有一个一个区间的时候,这个区间一定是不会和其他区间重叠的
        int minEnd = intervals[0][1];//第一个最小的end端点

        // intervals = {{1,2}, {2,3}, {3,4}, {1,3}}
        for (int[] interval : intervals) {
            int start = interval[0];
            //当前区间的start端点覆盖了minEnd,说明区间有重叠,count++,并更新minEnd
            if (start >= minEnd) {
                count++;
                minEnd = interval[1];
            }
        }
        return count;
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (null == intervals || intervals.length == 0) {
            return 0;
        }
        int count = maxOverlapIntervals(intervals);
        //区间长度减去重叠区间数就是需要移除区间的最小数量
        return intervals.length - count;
    }


    public static void test2() {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int i = maxOverlapIntervals(intervals);


        System.out.println(i);

        int i1 = eraseOverlapIntervals(intervals);

        System.out.println(i1);


    }

    /**
     * @param s
     * @param i  是s的指针
     * @param p
     * @param j  是p的指针
     * @param dp 二维数组，记录匹配情况
     * @return
     */
    public static boolean dpHelper(String s, int i, String p, int j, int[][] dp) {

        int slen = s.length();
        int plen = p.length();

        //base case :p 字符串匹配完了，此时判断s有没有匹配完即可
        if (j == plen) {
            return i == slen;
        }
        //base case :s 字符串匹配完了，此时仅仅判断p有没有匹配完是不行的，还有特殊情况 x*y*z*
        if (i == slen) {
            if ((plen - j) % 2 == 1) {
                return false;
            }
            for (; j + 1 < plen; j += 2) {
                if (p.charAt(j + 1) != '*') {
                    return false;
                }
            }
            return true;
        }

        if (dp[i][j] != -1) {
            return true;
        }

        boolean res = false;

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            if (j < plen - 1 && p.charAt(j + 1) == '*') {
                //  p字符串是* ， 被匹配0次或者多次
                res = dpHelper(s, i, p, j + 2, dp)
                        || dpHelper(s, i + 1, p, j, dp);
            } else {
                //  p字符串是* ，被匹配一次，继续比较下一个字符
                res = dpHelper(s, i + 1, p, j + 1, dp);
            }
        } else {
            //不匹配
            if (j < plen - 1 && p.charAt(j + 1) == '*') {
                // p字符串是* ，且被匹配0次
                res = dpHelper(s, i, p, j + 2, dp);
            } else {
                // 不匹配直接返回
                res = false;
            }
        }
        dp[i][j] = res ? 1 : -1;
        return res;
    }


    public static boolean isMatch(String s, String p) {
        int[][] dp = new int[s.length()][p.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                dp[i][j] = -1;
            }
        }
        return dpHelper(s, 0, p, 0, dp);
    }

    public boolean isMatchOfficial(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean dp[][] = new boolean[n + 1][m + 1];
        //base case
        dp[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if ((p.charAt(j - 1) == '*')) {
                    if (match(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    //看s[i]和p[j]是否匹配
                    if (match(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[n][m];

    }

    //看s[i]和p[j]是否匹配
    public boolean match(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (s.charAt(i - 1) == p.charAt(j - 1)) {
            return true;
        } else if (p.charAt(j - 1) == '.') {
            return true;
        } else {
            return false;
        }
    }


    public static void test3() {
        String s = "abcd";
        String p = "a.*";
        boolean match = isMatch(s, p);
        System.out.println(match);
    }

    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(robRange(nums, 0, n - 2),
                robRange(nums, 1, n - 1));
    }

    // 仅计算闭区间 [start,end] 的最优结果
    public int robRange(int[] nums, int start, int end) {
        int n = nums.length;
        //base case
        int dp_i_1 = 0, dp_i_2 = 0;
        int dp_i = 0;
        for (int i = end; i >= start; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }


    public int rob(int[] nums) {

        int[] dp = new int[nums.length];

        Arrays.fill(dp, -1);

        return dpRobHelper(nums, 0, dp);
    }

    // 返回 nums[start..] 能抢到的最大值
    private int dpRobHelper(int[] nums, int start, int[] dp) {
        if (start >= nums.length) {
            return 0;
        }
        if (dp[start] != -1) {
            return dp[start];
        }

        int res = Math.max(
                // 不抢，去下家
                dpRobHelper(nums, start + 1, dp),
                // 抢，去下下家
                nums[start] + dpRobHelper(nums, start + 2, dp)
        );
        dp[start] = res;
        return res;
    }

    private int dpRobHelper(int[] nums, int start) {
        if (start >= nums.length) {
            return 0;
        }

        int res = Math.max(
                // 不抢，去下家
                dpRobHelper(nums, start + 1),
                // 抢，去下下家
                nums[start] + dpRobHelper(nums, start + 2)
        );
        return res;
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;

    }

    private void dfs(int[] candidates, int begin, int len, int target,
                     Deque<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            path.addLast(candidates[i]);

            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res);

            // 状态重置
            path.removeLast();
        }
    }


    public int removeDuplicates(int[] nums) {

        if (nums.length <= 1) {
            return nums.length;
        }

        int i = 0, j = i + 1;

        while (j < nums.length) {

            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
            j++;

        }
        return i + 1;

    }

    public static List<List<Integer>> subsetsDigui(int[] nums) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        subsetsHelper(nums, list, 0, res);
        return res;
    }

    public static void subsetsHelper(int[] nums, List<Integer> list, int cur, List<List<Integer>> res) {
        if (cur == nums.length) {
            //这里要new一个新数组，list在后续的栈帧中会被移除元素
            res.add(new ArrayList<>(list));
            return;
        }
        //添加当前元素
        list.add(nums[cur]);
        //递归式的添加下一个元素
        subsetsHelper(nums, list, cur + 1, res);
        //return到这里的时候说明数组已经遍历到最后一个，移除list的最后一个，即递归树的x层
        list.remove(list.size() - 1);
        //接着递归递归树的x-1层
        subsetsHelper(nums, list, cur + 1, res);
    }


    public static void test4() {
        int[] nums = {1, 2, 3};
        System.out.println(subsetsDigui(nums));
    }

    List<Integer> list = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public void subsetsHelper2(int[] nums, List<Integer> list, int start) {
        ans.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            subsetsHelper2(nums, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        subsetsHelper2(nums, list, 0);
        return ans;
    }

    public List<List<Integer>> subsetsFor(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            list.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    list.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(list));
        }
        return ans;
    }

    public List<List<Integer>> combine(int n, int k) {
        subsetsHelper3(n, k, list, 0);
        return ans;

    }

    //k限制树的高度，n限制树的宽度
    public void subsetsHelper3(int n, int k, List<Integer> list, int start) {
        if (list.size() + (n - k + 1) < k) {
            return;
        }

        if (k == list.size()) {
            ans.add(new ArrayList<>(list));
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            subsetsHelper3(n, k, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    List<List<List<String>>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> tmp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                tmp.add(".");
            }
            board.add(tmp);
        }
        solveNQueensHelper(0, board);
        return res.get(0);
    }

    public void solveNQueensHelper(int row, List<List<String>> board) {
        if (row == board.size() - 1) {
            res.add(new ArrayList<>(board));
            System.out.println("添加的结果：" + res);
            return;
        }

        List<String> s = board.get(row);
        int len = s.size();

        for (int column = 0; column < len; column++) {
            if (!isValiad(board, row, column)) {
                continue;
            }
            s.set(row, "Q");

            solveNQueensHelper(row + 1, board);

            s.set(row, ".");
        }
    }


    //校验board[row][column]是否可以放置皇后
    private boolean isValiad(List<List<String>> board, int row, int column) {
        int n = board.size();
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < n; i++) {
            if ("Q".equals(board.get(i).get(column)))
                return false;
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = column + 1;
             i >= 0 && j < n; i--, j++) {
            if ("Q".equals(board.get(i).get(j)))
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = column - 1;
             i >= 0 && j >= 0; i--, j--) {
            if ("Q".equals(board.get(i).get(j)))
                return false;
        }
        return true;
    }

    public void test5() {
        List<List<String>> lists = new Solution().solveNQueensOfficial(4);
        System.out.println(lists);
    }


    public List<List<String>> solveNQueensOfficial(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonals1 = new HashSet<>();
        Set<Integer> diagonals2 = new HashSet<>();
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public void backtrack(List<List<String>> solutions, int[] queens, int n, int row,
                          Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        //row==n表示遍历到了最后一格子，此时应该把数组queens记录的摆放方式存入
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            //i遍历每一列
            for (int i = 0; i < n; i++) {
                //判断当前位置的列和对角线是被其他皇后占用
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }

                //没被占用，可以放置（queens[row] -> 第row行queen应该放置的列的下标，(row,queens[row])->是queen具体的坐标）
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                //递归下一行
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);

                //回溯要撤销上一次递归操作放置的元素
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }


    //int数组转List<String>
    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    public void test6() {
        TreeNode node = new TreeNode(0);
        List<TreeNode> list = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            node.val = i;
            list.add(node);
        }
        System.out.println(list);
    }

    public void test7() {
        Integer val;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            val = i;
            list.add(val);
        }
        System.out.println(list);
    }


    public TreeNode invertTree(TreeNode root) {

        invertTreeHelper(root);
        return root;

    }

    public void invertTreeHelper(TreeNode root) {

        if (root == null) {
            return;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

    }


    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        //递归压栈
        flatten(root.left);
        flatten(root.right);

        /*后序遍历位置：处理左子树和右子树*/

        TreeNode left = root.left;
        TreeNode right = root.right;

        //将原来的左子树作为现在的右子树，将现在的左子树置为null
        root.left = null;
        root.right = left;

        //找到原来左子树的最后，将原先的右子树挂到尾上，实现先序遍历的顺序
        TreeNode tmp = root;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        tmp.right = right;
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        connectHelper(root.left, root.right);
        return root;
    }

    public void connectHelper(Node left, Node right) {
        if (left == null || right == null) {
            return;
        }

        left.next = right;

        //递归处理拥有同一个父节点的结点
        connectHelper(left.left, left.right);
        connectHelper(right.left, right.right);

        //处理同一层，但不是共有一个父节点的结点
        connectHelper(left.right, right.left);
    }


    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructHelper(nums, 0, nums.length - 1);
    }

    public TreeNode constructHelper(int[] nums, int i, int j) {
        //数组遍历结束，说明当前没有元素可用于构建，返回null
        if (i > j) {
            return null;
        }

        //找到数组中的最大值及其下标
        int idx = 0;
        int max = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            if (max < nums[k]) {
                idx = k;
                max = nums[k];
            }
        }
        //用最大值构造当前根结点
        TreeNode root = new TreeNode(max);
        //当前根节点的左孩子一定在[i,idx-1]这个区间中，递归即可，右孩子同理
        root.left = constructHelper(nums, i, idx - 1);
        root.right = constructHelper(nums, idx + 1, j);
        return root;
    }


    public void test654() {
        int[] nums = {3, 2, 1, 6, 0, 5};
        StopWatchUtils.start();
        TreeNode root = constructMaximumBinaryTree(nums);
        StopWatchUtils.stopAndGetTimeMillis();
        TreeUtils.preOrder(root);
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTreeHelper(int[] preOrder, int preStart, int preEnd,
                                    int[] inOrder, int inStart, int inEnd) {

        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        //第一点：先序遍历的第一个一定是根节点
        int rootVal = preOrder[preStart];
        //找到中序遍历中根的位置，然后一分为二
        int idx = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == rootVal) {
                idx = i;
                break;
            }
        }

        TreeNode root = new TreeNode(rootVal);
        //第二点：如何控制两个遍历数组的起止位置
        //这里需要计算中序数组拆分之后的左右子数组元素个数leftSize，
        int leftSize = idx - inStart;

        // 左树-》中序遍历的数组起止位置因为idx的存在很好控制，显然是[inStart,idx-1]
        // 左树-》然后根据leftSize来找到前序数组的起始位置[preStart+1,preStart+leftSize]
        root.left = buildTreeHelper(preOrder, preStart + 1, preStart + leftSize,
                inOrder, inStart, idx - 1);
        // 右树-》根据左树的起止位置，结合数组的长度，同理可得：
        root.right = buildTreeHelper(preOrder, preStart + leftSize + 1, preEnd,
                inOrder, idx + 1, inEnd);
        return root;
    }

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return buildTreeHelper2(postorder, 0, postorder.length - 1,
                inorder, 0, inorder.length - 1);
    }


    public TreeNode buildTreeHelper2(int[] postOrder, int postStart, int postEnd,
                                     int[] inOrder, int inStart, int inEnd) {

        if (postStart > postEnd) {
            return null;
        }

        //第一点：后序遍历的最后一个一定是根节点
        int rootVal = postOrder[postEnd];
        //找到中序遍历中根的位置，然后一分为二
        int idx = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == rootVal) {
                idx = i;
                break;
            }
        }

        TreeNode root = new TreeNode(rootVal);
        //第二点：如何控制两个遍历数组的起止位置
        //这里需要计算中序数组拆分之后的左右子数组元素个数leftSize，
        int leftSize = idx - inStart;

        root.left = buildTreeHelper2(postOrder, postStart, postStart + leftSize - 1,
                inOrder, inStart, idx - 1);
        root.right = buildTreeHelper2(postOrder, postStart + leftSize, postEnd - 1,
                inOrder, idx + 1, inEnd);
        return root;
    }

    public void test106() {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        TreeNode root = new Solution().buildTree2(inorder, postorder);

        TreeUtils.preOrder(root);
    }

    //存放所有子树及其出现次数的map
    HashMap<String, Integer> allSubTree = new HashMap<>();

    //存放和有相同子树的重复结点中的任何一个
    LinkedList<TreeNode> result = new LinkedList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        //必须是后序遍历框架，因为要判断每个节点的子树长什么样
        findDuplicateSubtreesHelper(root);
        return result;
    }

    //拼接每个节点的后序遍历的序列串
    public String findDuplicateSubtreesHelper(TreeNode root) {
        if (null == root) {
            return "#";
        }

        String leftTree = findDuplicateSubtreesHelper(root.left);
        String rightTree = findDuplicateSubtreesHelper(root.right);

        //这里的拼接一定得是先子树、再根节点，保持后序的规则，否则拼接乱序会导致allSubTree无法判断
        //String subTree = leftTree + "," + root.val + "," + rightTree;
        String subTree = leftTree + "," + rightTree + "," + root.val;

        int count = allSubTree.getOrDefault(subTree, 0);

        if (count == 1) {
            //有某个结点的子树和当前结点相同，只记录到result中一次即可
            result.add(root);
        }

        //自己的子树存入所有子树的集合
        allSubTree.put(subTree, count + 1);
        return subTree;
    }

    int ret = 0;

    int rank = 0;

    public int kthSmallest(TreeNode root, int k) {
        kthSmallestHelper(root, k);
        return ret;
    }

    public void kthSmallestHelper(TreeNode root, int k) {
        if (null == root) {
            return;
        }
        kthSmallestHelper(root.left, k);

        rank++;
        if (rank == k) {
            ret = root.val;
            return;
        }
        kthSmallestHelper(root.right, k);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root || p == root || q == root) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //后序遍历等同于自下往上，所以应该用后序遍历框架

        //如果p和q都在以root为根的树中，那么left和right一定分别是p和q
        if (left != null && right != null) {
            return root;
        }

        //如果p和q都不在以root为根的树中，直接返回null
        if (left == null && right == null) {
            return null;
        }

        //如果p和q只有一个存在于root为根的树中，返回该节点
        return right == null ? left : right;
    }

    public void test236() {
        TreeNode root = new TreeNode(5);
        new Solution().lowestCommonAncestor(root, root, root);
    }


    int sum = 0;

    public int rangeSumBST(TreeNode root, int low, int high) {

        if (root == null) {
            return 0;
        }

        if (low <= root.val && root.val <= high) {
            sum += root.val;
        }

        rangeSumBST(root.left, low, high);
        rangeSumBST(root.right, low, high);
        return sum;
    }


    public int trap2(int[] height) {
        int len = height.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            int leftMax = 0, rightMax = 0;
            for (int left = 0; left <= i; left++) {
                leftMax = Math.max(height[left], leftMax);
            }
            for (int rigth = i; rigth < len; rigth++) {
                rightMax = Math.max(height[rigth], rightMax);
            }

            int waterI = Math.min(leftMax, rightMax) - height[i];
            res += waterI;
        }
        return res;
    }

    public int trap(int[] height) {
        int len = height.length;
        if (len == 0) return 0;

        int res = 0;
        int left = 0, right = len - 1;
        int leftMax = height[0], rightMax = height[len - 1];

        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            //当前位置能装多少水，只取决于leftMax和rightMax中较小的那一个
            if (leftMax > rightMax) {
                res += rightMax - height[right];
                right--;
            } else {
                res += leftMax - height[left];
                left++;
            }
        }
        return res;
    }


    public void test42() {
        int[] height = {4, 2, 0, 3, 2, 5};
        int trap = new Solution().trap(height);
        System.out.println(trap);
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        if (null == head || k <= 1) {
            return head;
        }
        ListNode start = head;
        ListNode end = start;
        for (int i = 0; i < k; i++) {
            //不足k个不翻转
            if (end == null) {
                return start;
            }
            end = end.next;
        }
        //这里将[start,end]区间反转
        ListNode curHead = reverseList(start, end);
        ListNode nextHead = reverseKGroup(end, k);

        //反转后 [start,end]区间 变成 [end,start]，所以连接当前区间的尾和下一区间的头，是start.next = nextHead
        start.next = nextHead;
        return curHead;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        //在头结点之前创建临时结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        //pre、end记录每次待翻转区间的上一个节点
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            //待翻转区间的上一个节点的next记为区间start
            ListNode start = pre.next;
            //next记录下一个待翻转区间的开始
            ListNode next = end.next;
            end.next = null;//反转[start,end]区间，并将pre.next连接到新的当前区间的新头
            pre.next = reverseList(start, null);
            start.next = next;//[start,end]区间反转之后为[end,start]，连接下一个结点
            //更新end和pre
            end = pre = start;
        }
        return dummy.next;
    }


    public void test25() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode head = new Solution().reverseKGroup2(node1, 2);

        printList(head);
    }

    private void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        //取出下一个节点，头插到当前，然后重新连接
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    public ListNode reverseList(ListNode start, ListNode end) {
        ListNode pre = null;
        ListNode cur = start;

        while (cur != end) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }


    public void test92() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode head = new Solution().reverseBetween(node1, 2, 4);

        printList(head);
    }


    public int[] twoSum(int[] numbers, int target) {
        if (numbers.length <= 1) {
            return numbers;
        }
        if (target < numbers[0] || target > numbers[numbers.length - 1]) {
            return new int[]{-1, -1};
        }

        int i = 0, j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                return new int[]{i + 1, j + 1};
            }
        }
        return new int[]{-1, -1};
    }


    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Integer val : map.keySet()) {
            Integer count = map.get(val);
            if (count == 1) {
                return val;
            }
        }
        return -1;
    }

    public void test137() {
        int[] nums = {2, 2, 3, 2};
        int i = new Solution().singleNumber(nums);
        System.out.println(i);
    }


    public Node connect2(Node root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                Node cur = queue.poll();

                if (i < count - 1) {
                    cur.next = queue.peek();
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {

        new Solution().test137();
//        new Solution().test7();
        //System.out.println(1 << 8);
        //System.out.println(1 << 0);

        //test3();
//        int[] coins = {1, 2, 5};
//        System.out.println(coinChange(coins, 100));
        //System.out.println(coinChangeMem(coins, 100));

//        int[] prices = {3, 2, 6, 5, 0, 3};
//        int k = 2;
//        int maxProfit = maxProfit(2, prices);
//        System.out.println(maxProfit);

//        int[][] ma = {
//                {1, 4, 7, 11, 15},
//                {2, 5, 8, 12, 19},
//                {3, 6, 9, 16, 22},
//                {10, 13, 14, 17, 24},
//                {18, 21, 23, 26, 30}};
//        int target = 5;
//        searchMatrix(ma, target);

//        String s1 = "aabcc";
//        String s2 = "dbbca";
//        String s3 = "aadbbcbcac";
//
//        isInterleave(s1, s2, s3);

//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        int i = superEggDropOfficial(4, 10000);
//        stopWatch.stop();
//        System.out.println(i + ",time=" + stopWatch.getTotalTimeMillis());

//        1 + Math.min(
//                Math.max(dp(k - 1, lo - 1),
//                        dp(k, n - lo)),
//
//                Math.max(dp(k - 1, hi - 1),
//                        dp(k, n - hi))
//        );

    }
}

