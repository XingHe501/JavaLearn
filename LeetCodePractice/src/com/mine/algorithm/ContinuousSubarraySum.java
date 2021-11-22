package com.mine.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * Created: 2021/06/02 20:05
 *
 * https://leetcode-cn.com/problems/continuous-subarray-sum/
 */
public class ContinuousSubarraySum {
    /**
     * 前缀和
     *
     * 预处理前缀和数组 sum，方便快速求得某一段区间的和。然后假定 [i, j] 是我们的目标区间，那么有：
     *  sum[j] - sum[i - 1] = n * k
     * 经过简单的变形可得：
     *  (sum[j] / k) - (sum[i-1] / k) = n
     * 要使得两者除 k 相减为整数，需要满足 sum[j] 和 sum[i - 1]] 对 k 取余相同。
     * 也就是说，我们只需要枚举右端点 j，然后在枚举右端点 j 的时候检查之前是否出现过左端点 i，使得 sum[j] 和 sum[i - 1] 对 k 取余相同。
     *
     * @param nums int[]
     * @param k int
     * @return boolean
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) sums[i] = sums[i - 1] + nums[i - 1];
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            set.add(sums[i - 2] % k);
            if(set.contains(sums[i] % k)) return true;
        }
        return false;
    }
}

class ContinuousSubarraySumRun {
    public static void main(String[] args) {
        ContinuousSubarraySum continuousSubarraySum = new ContinuousSubarraySum();
        int[] nums = new int[]{23,2,6,4,7};
        int k = 13;
        System.out.println(continuousSubarraySum.checkSubarraySum(nums, k));
    }
}