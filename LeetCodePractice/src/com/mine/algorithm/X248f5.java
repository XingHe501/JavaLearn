package com.mine.algorithm;

import java.util.HashMap;
/**
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x248f5/
 */
public class X248f5 {
    // todo 优化，可以使用set，避免存储value
    boolean containsDuplicate(int[] nums) {
        var tempMap = new HashMap<Integer, Integer>();
        for (int num : nums) {
            // 如果put添加会返回key所关联的值，不存在返回null
            if (tempMap.put(num, num) != null) return true;
        }
        return false;
    }
}
class X248f5Run{
    public static void main(String[] args) {
        X248f5 x248f5 = new X248f5();
        assert false;
        assert x248f5.containsDuplicate(new int[]{1, 2, 3, 4});
    }
}