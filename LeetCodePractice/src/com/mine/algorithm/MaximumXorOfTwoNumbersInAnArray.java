package com.mine.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * Created: 2021/05/16 14:17
 *
 * https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class MaximumXorOfTwoNumbersInAnArray {
    // 最高位的二进制位编号为 30
    static final int HIGH_BIT = 30;

    /**
     * 暴力算法
     * 直接进行循环遍历
     * 时间复杂度跟数组长度挂关系
     *
     * 已通过
     * 执行用时：433 ms
     * 内存消耗：39 MB
     * @param nums
     * @return 数组中最大的异或结果
     */
    public int findMaximumXOR(int[] nums) {
        int ans= 0;
        if(nums.length == 1){
            ans = 0;
        }else if (nums.length == 2) {
            ans = nums[0] ^ nums[1];
        }else{
            // 因为 A ^ A= 0,所以优先排除 i = j
            for (int i = 0; i < nums.length; i++) {
                int j = i + 1;
                while (j < nums.length){
                    var val = nums[i] ^ nums[j];
                    if (ans < val) ans = val;
                    j++;
                }
            }
        }
        return ans;
    }

    /**
     * 官方答案 哈希表
     *
     * 时间复杂度：O(nlogC)，其中 n 是数组 nums 的长度，C 是数组中的元素范围，在本题中 C < 2^{31}。
     * 枚举答案 ans 的每一个二进制位的时间复杂度为 O(logC)，在每一次枚举的过程中，我们需要 O(n) 的时间进行判断，因此总时间复杂度为 O(nlogC)。
     *
     * 空间复杂度：O(n)，即为哈希表需要使用的空间。
     *
     * @param nums
     * @return 数组中最大的异或结果
     */
    public int findMaximumXORAnswerTwo(int[] nums){
        int ans = 0;
        for (int k = HIGH_BIT; k >= 0; --k) {
            Set<Integer> seen = new HashSet<Integer>();
            // 将所有的 pre^k(a_j) 放入哈希表中
            // 如果只想保留从最高位开始到第 k 个二进制位为止的部分
            // 只需将其右移 k 位
            for (int num : nums) seen.add(num >> k);

            // 目前 ans 包含从最高位开始到第 k+1 个二进制位为止的部分
            // 我们将 ans 的第 k 个二进制位置为 1，即为 ans = ans*2+1
            int xNext = ans * 2 + 1;
            boolean found = false;

            // 枚举 i
            for (int num : nums) {
                if (seen.contains(xNext ^ (num >> k))) { // ?判断是否有相同的值
                    found = true;
                    break;
                }
            }
            if (found) ans = xNext;
            // 如果没有找到满足等式的 a_i 和 a_j，那么 ans 的第 k 个二进制位只能为 0。
            // 即为 ans = ans*2
            else ans = xNext - 1;
        }
        return ans;
    }


    /**
     * 官方答案 字典树（前缀树）
     * 时间复杂度：O(nlogC)，其中 nn 是数组 nums 的长度，CC 是数组中的元素范围，在本题中 C < 2^{31}。
     * 我们需要将 a_0 到 a_{n-2}加入字典树中，并且需要以 a_1a 到 a_{n-1}
     * 作为「参照对象」在字典树上进行遍历，每一项操作的单次时间复杂度为 O(logC)，因此总时间复杂度为O(nlogC)。
     *
     * 空间复杂度：O(nlogC)。每一个元素在字典树中需要使用 O(logC) 的空间，因此总空间复杂度为 O(nlogC)
     *
     * @param nums
     * @return 数组中最大的异或结果
     */
    public static int findMaximumXORAnswerThree(int[] nums) {
        int ans = 0;
        MaximumXorOfTwoNumbersInAnArray maximumXorOfTwoNumbersInAnArray = new MaximumXorOfTwoNumbersInAnArray();
        for (int i = 1; i < nums.length; ++i) {
            // 将 nums[i-1] 放入字典树，此时 nums[0 .. i-1] 都在字典树中
            maximumXorOfTwoNumbersInAnArray.add(nums[i - 1]);
            // 将 nums[i] 看作 ai，找出最大的 x 更新答案
            ans = Math.max(ans, maximumXorOfTwoNumbersInAnArray.check(nums[i]));
        }
        return ans;
    }
    class Trie {
        // 左子树指向表示 0 的子节点
        Trie left = null;
        // 右子树指向表示 1 的子节点
        Trie right = null;
    }
    // 字典树的根节点
    Trie root = new Trie();
    int check(int num) {
        Trie cur = root;
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; --k) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                // a_i 的第 k 个二进制位为 0，应当往表示 1 的子节点 right 走
                if (cur.right != null) {
                    cur = cur.right;
                    x = x * 2 + 1;
                } else {
                    cur = cur.left;
                    x = x * 2;
                }
            } else {
                // a_i 的第 k 个二进制位为 1，应当往表示 0 的子节点 left 走
                if (cur.left != null) {
                    cur = cur.left;
                    x = x * 2 + 1;
                } else {
                    cur = cur.right;
                    x = x * 2;
                }
            }
        }
        return x;
    }
    void add(int num) {
        Trie cur = root;
        for (int k = HIGH_BIT; k >= 0; --k) {
            int bit = (num >> k) & 1; // 如果相对应位都是1，则结果为1，否则为0
            if (bit == 0) {
                if (cur.left == null) {
                    cur.left = new Trie();
                }
                cur = cur.left;
            }
            else {
                if (cur.right == null) {
                    cur.right = new Trie();
                }
                cur = cur.right;
            }
        }
    }
}

class MaximumXorOfTwoNumbersInAnArrayRun{
    public static void main(String[] args) {
        var testData = new int[]{14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70};
        MaximumXorOfTwoNumbersInAnArray maximumXorOfTwoNumbersInAnArray = new MaximumXorOfTwoNumbersInAnArray();

        System.out.println(maximumXorOfTwoNumbersInAnArray.findMaximumXOR(testData));
        System.out.println(maximumXorOfTwoNumbersInAnArray.findMaximumXORAnswerTwo(testData));
        System.out.println(maximumXorOfTwoNumbersInAnArray.findMaximumXORAnswerThree(testData));
    }
}
