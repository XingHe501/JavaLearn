package com.mine.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created: 2021/05/25 14:06
 *
 * https://leetcode-cn.com/problems/make-the-xor-of-all-segments-equal-to-zero/
 */
public class MakeTheXorOfAllSegmentsEqualToZero {
    public int minChanges(int[] nums, int k) {
        int max = 1024, n = nums.length;
        int[][] f = new int[k][max];
        int[] g = new int[k];
        for (int i = 0; i < k; i++) {
            Arrays.fill(f[i], 0x3f3f3f3f);
            g[i] = 0x3f3f3f3f;
        }
        for (int i = 0, cnt = 0; i < k; i++, cnt = 0) {
            // 使用 map 和 cnt 分别统计当前列的「每个数的出现次数」和「有多少个数」
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i; j < n; j += k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                cnt++;
            }
            if (i == 0) { // 第 0 列：只需要考虑如何将该列变为 xor 即可
                for (int xor = 0; xor < max; xor++) {
                    f[0][xor] = Math.min(f[0][xor], cnt - map.getOrDefault(xor, 0));
                    g[0] = Math.min(g[0], f[0][xor]);
                }
            } else { // 其他列：考虑与前面列的关系
                for (int xor = 0; xor < max; xor++) {
                    f[i][xor] = g[i - 1] + cnt; // 整列替换
                    for (int cur : map.keySet()) { // 部分替换
                        f[i][xor] = Math.min(f[i][xor], f[i - 1][xor ^ cur] + cnt - map.get(cur));
                    }
                    g[i] = Math.min(g[i], f[i][xor]);
                }
            }
        }
        return f[k - 1][0];
    }
}

/**
 * nums =    [a,   b,     c,       d,         e]
 * sums = [0, a, a^b, a^b^c, a^b^c^d, a^b^c^d^e]
 * [left, right] 区间长度为k
 * a^b^c = 0
 * a^a^b^a^b^c = 0
 * (a^a)^(b^b)^a^c = 0
 * a^c = 0
 * nums[left] = nums[right + 1]
 */

class MakeTheXorOfAllSegmentsEqualToZeroRun {
    public static void main(String[] args) {
        MakeTheXorOfAllSegmentsEqualToZero makeTheXorOfAllSegmentsEqualToZero = new MakeTheXorOfAllSegmentsEqualToZero();
        System.out.println(makeTheXorOfAllSegmentsEqualToZero.minChanges(new int[]{1, 2, 0, 3, 0}, 1));
    }
}