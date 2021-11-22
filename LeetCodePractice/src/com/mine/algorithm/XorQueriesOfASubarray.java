package com.mine.algorithm;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/xor-queries-of-a-subarray
 */
public class XorQueriesOfASubarray {
    /**
     * My Answer
     * 执行用时: 1373 ms
     * 内存消耗: 49.2 MB
     * */
    public static int[] xorQueriesAnswerOne(int[] arr, int[][] queries) {
        var returnValue = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            var nextArr = queries[i];
            var temp = 0; // a ^ 0 = a
            for (int j = nextArr[0]; j <= nextArr[nextArr.length - 1]; j++) {
                temp ^= arr[j];
            }
            returnValue[i] = temp;
        }
        return returnValue;
    }

    /** 前缀和
     * 时间复杂度：令 arr 数组长度为 n，qs 数组的长度为 m。预处理前缀和数组复杂度为 O(n)；查询的复杂度为 O(m)。整体复杂度为 O(n + m)
     * 空间复杂度：O(n)
     * */
    public static int[] xorQueriesAnswerTwo(int[] arr, int[][] queries) {
        int n = arr.length, m = queries.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] ^ arr[i - 1];
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0] + 1, r = queries[i][1] + 1;
            ans[i] = sum[r] ^ sum[l - 1];
        }
        return ans;
    }
}
class XorQueriesOfASubarrayRun{
    public static void main(String[] args) {
        System.out.println(Arrays.toString(XorQueriesOfASubarray.xorQueriesAnswerOne(new int[]{4, 8, 2, 10}, new int[][]{{2, 3}, {1, 3}, {0, 0}, {0, 3}})));
        System.out.println(Arrays.toString(XorQueriesOfASubarray.xorQueriesAnswerTwo(new int[]{4, 8, 2, 10}, new int[][]{{2, 3}, {1, 3}, {0, 0}, {0, 3}}))); // [8,0,4,4]
    }
}
