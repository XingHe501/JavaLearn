package com.mine.algorithm;

/**
 * Created: 2021/05/21 20:21
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequence {
    /**
     * 动态规划
     *
     * 时间复杂度：O(n * m)O(n∗m)
     * 空间复杂度：O(n * m)O(n∗m)
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                /**
                 * 两个值相等  f[i][j] = f[i−1][j−1]+1。代表使用 s1[i−1] 与 s2[j−1] 形成最长公共子序列的长度。
                 * 不相等     f[i][j] = max(f[i-1][j], f[i][j-1])。 代表不使用 s1[i-1] 形成最长公共子序列的长度、不使用 s2[j−1] 形成最长公共子序列的长度。这两种情况中的最大值。
                 */
                f[i][j] = (text1.charAt(i - 1) == text2.charAt(j - 1)) ? f[i - 1][j - 1] + 1 : Math.max(f[i][j - 1], f[i - 1][j]);
            }
        }
        return f[m][n];
    }
}
class LongestCommonSubsequenceRun{
    public static void main(String[] args) {
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
        System.out.println(longestCommonSubsequence.longestCommonSubsequence("abc", "def"));
    }
}