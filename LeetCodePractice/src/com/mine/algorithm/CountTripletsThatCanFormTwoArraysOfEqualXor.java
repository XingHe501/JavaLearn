package com.mine.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created: 2021/05/18 10:46
 *
 * https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXor {
    /**
     * 二重循环
     *
     * 两个相等的数进行异或运算必定是0，a、b两个数相等，所以需要求 i 到 k 的数异或为 0 的区间，
     * 因为 j > i，k >= j， 所以 k 必须大于 i，只要有异或为0的区间，就相加
     * 看实例1中的一个结果（0，1，2）、（0，2，2），可以知道0 -2区间异或为0，这里 i 就是0， k 就是 2，因为 j > i ; j <= k， 所以 j 可以取 1 或 2，也就是 k - i
     *
     * 时间复杂度 O(n2) n为arr的长度
     * 空间复杂度 O(n)
     * @param arr int[]
     * @return ans int 返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
     */
    public int countTriplets(int[] arr) {
        int ans = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int sum = 0;
            for (int k = i; k < n; k++) {
                sum ^= arr[k];
                if (sum == 0 && k > i) ans += (k - i);
            }
        }
        return ans;
    }

    /**
     * 前缀异或
     *  本质上是利用集合（区间结果）的容斥原理。只不过前缀和需要利用「减法（逆运算）」做容斥，而前缀异或是利用「相同数值进行异或结果为 0（偶数次的异或结果为 0）」的特性实现容斥。
     *
     * 时间复杂度：O(n3)
     * 空间复杂度：O(n)
     * @param arr int[]
     * @return ans int 返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
     */
    public int countTripletsAnswerTwo(int[] arr){
        int ans = 0;
        int n = arr.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] ^ arr[i - 1];
        // 0<=i < j <= k < n
        // j > i; k>=j; k > i;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                for (int k = j; k <= n; k++) {
                    int a = sum[j - 1] ^ sum[i - 1];
                    int b = sum[k] ^ sum[j - 1];
                    if (a == b) ans++;
                }
            }
        }
        return  ans;
    }

    /**
     * 哈希表
     *
     * 使用前缀异或数组可得：
     *  X(i, k) = sum[k] ^ sum[i-1]
     *
     * @param arr int[]
     * @return ans int 返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
     */
    public int countTripletsAnswerThree(int[] arr){
        int n = arr.length;
        // 预处理前缀异或数组
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] ^ arr[i - 1];
        int ans = 0;
        // 记录出现过的异或结果，存储格式 ：[下标1，下标2,...]
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int k = 0; k <=n; k++) {
            List<Integer> list = map.getOrDefault(sum[k], new ArrayList<>());
            for (var idx : list){
                int i = idx + 1;
                ans += k - i;
            }
            list.add(k);
            map.put(sum[k], list);
        }
        return ans;
    }
}

class RCountTripletsThatCanFormTwoArraysOfEqualXorRun{
    public static void main(String[] args){
        CountTripletsThatCanFormTwoArraysOfEqualXor countTripletsThatCanFormTwoArraysOfEqualXor = new CountTripletsThatCanFormTwoArraysOfEqualXor();
        System.out.println(countTripletsThatCanFormTwoArraysOfEqualXor.countTriplets(new int[]{2,3,1,6,7}));
        System.out.println(countTripletsThatCanFormTwoArraysOfEqualXor.countTripletsAnswerTwo(new int[]{2,3,1,6,7}));
        System.out.println(countTripletsThatCanFormTwoArraysOfEqualXor.countTripletsAnswerThree(new int[]{1,1,1,1,1}));
    }
}