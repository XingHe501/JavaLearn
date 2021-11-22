package com.mine.algorithm;

import java.util.Random;

/**
 * Created: 2021/05/28 20:26
 *
 * https://leetcode-cn.com/problems/total-hamming-distance
 */
public class TotalHammingDistance {
    // 时间复杂度 O(32*n)
    // 空间复杂度 O(1)
    public int totalHammingDistance(int[] nums) {
        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            int s0 = 0, s1 = 0;
            for (int num : nums) {
                if (((num >> i) & 1) == 1) s1 ++; // 当前位上是0
                else s0++;
            }
            ans += s0 * s1;
        }
        return ans;
    }
}

class TotalHammingDistanceRun{
    public static void main(String[] args) {
        Random random = new Random();
        int length = 3;
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) nums[i] = random.nextInt(10*10000);

        TotalHammingDistance totalHammingDistance = new TotalHammingDistance();
        System.out.println(totalHammingDistance.totalHammingDistance(nums));
    }
}