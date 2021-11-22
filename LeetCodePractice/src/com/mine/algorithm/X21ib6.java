package com.mine.algorithm;


import com.mine.util.Util;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x21ib6/
 * <p>
 * 只出现一次的数字
 * <p>
 * 输入: [2,2,1]
 * <p>
 * 输出: 1
 * <p>
 * 输入: [4,1,2,1,2]
 * <p>
 * 输出: 4
 * <p>
 */
public class X21ib6 {
    // todo a ^ a = 0, a ^ 0 = a
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i : nums) {
            var bool = false;
            var count = 0;
            for (int j : nums) {
                if (i == j)
                    count++;
                if (count == 2)
                    bool = true;
            }
            if (!bool)
                ans = i;
        }
        return ans;

        /**
         * Answer
         * <p>
         * int ans = 0; for(var i : nums){ ans ^=i;} return ans;
         */
    }
}

class X21ib6Run {
    public static void main(String[] args) throws Exception {
        Util util = new Util();
        X21ib6 x21ib6 = new X21ib6();
        var numArrays = new int[] { 4, 1, 2, 1, 2 };
        util.EquipResult(x21ib6.singleNumber(numArrays), 4);
    }
}