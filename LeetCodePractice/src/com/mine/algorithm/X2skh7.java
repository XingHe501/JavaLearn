package com.mine.algorithm;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2skh7/
 */
public class X2skh7 {
    public void rotate(int[] nums, int k) {
        if (nums == null || k == 0) return;
        var length = nums.length;
        var tempArrays = new int[length];
        System.arraycopy(nums, 0, tempArrays, 0, length);
        if (k == length) return;
        // 要位移的次数
        for (int i = 0; i < length; i++) {
            nums[(i + k) % length] = tempArrays[i];
        }
    }
}

class X2skh7Run {
    public static void main(String[] args) {
        X2skh7 x2skh7 = new X2skh7();
        var nums = new int[]{-2, -100, 3, 99};
        x2skh7.rotate(nums, 1);
        System.out.println(Arrays.toString(nums));
    }
}