package com.mine.algorithm;

import com.mine.util.Util;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2y0c2/
 */
public class IntersectionOfTwoArraysII {
    /**
     * 先排序，值相同表示为交集，两者都+1
     * 不同，小的往后+1
     * 超出长度结束
     *
     * @param nums1
     * @param nums2
     * @return int[]
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        var list = new ArrayList<Integer>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) i++;
            if (nums1[i] > nums2[j]) j++;
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int index = 0;
        int[] res = new int[list.size()];
        for (Integer integer : list) {
            res[index++] = integer;
        }
        return res;
    }
}

class IntersectionOfTwoArraysIIRun {
    public static void main(String[] args) throws Exception {
        Util util = new Util();
        IntersectionOfTwoArraysII intersectionOfTwoArraysII = new IntersectionOfTwoArraysII();
        var nums1 = new int[]{1, 2};
        var nums2 = new int[]{1, 1};
        util.EquipResult(intersectionOfTwoArraysII.intersect(nums1, nums2), new int[]{2, 2});
    }
}