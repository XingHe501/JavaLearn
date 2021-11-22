package com.mine.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created: 2021/06/03 20:03
 *
 * https://leetcode-cn.com/problems/contiguous-array/
 */
public class ContiguousArray {
    /**
     * 基本分析
     * 根据题意，我们可以轻易发现如下性质：
     * 如果答案非 0，那么子数组长度必然为偶数，且长度至少为 2
     * <p>
     * 前缀和 + 哈希表
     * 具体的，我们在预处理前缀和时，将 nums[i] 为 0 的值当做 -1 处理。
     * 从而将问题转化为：如何求得最长一段区间和为 0 的子数组。
     * 同时使用「哈希表」来记录「某个前缀和出现的最小下标」是多少。
     * 再结合「如果答案非 0，子数组长度至少为 2的特性，我们让循环从 2 开始，并在循环开始前往「哈希表」存入哨兵，从而实现不需要处理边界问题。
     *
     * @param nums int[]
     * @return ans int
     */
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) sums[i] = sums[i - 1] + (nums[i - 1] == 1 ? 1 : -1);
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int i = 2; i <= n; i++) {
            if (!map.containsKey(sums[i - 2])) map.put(sums[i - 2], i - 2);
            if (map.containsKey(sums[i])) ans = Math.max(ans, i - map.get(sums[i]));
        }
        return ans;
    }
}

class ContiguousArrayRun {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0};
        ContiguousArray contiguousArray = new ContiguousArray();
        System.out.println(contiguousArray.findMaxLength(nums));
    }
}