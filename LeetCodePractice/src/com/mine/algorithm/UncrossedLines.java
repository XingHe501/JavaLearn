package com.mine.algorithm;

/**
 * Created: 2021/05/21 14:21
 *
 * https://leetcode-cn.com/problems/uncrossed-lines/
 */
public class UncrossedLines {
    /**
     * LCS 最长公共子序列
     * s1 = nums1, s2 = nums2
     * f[i][j] 代表考虑 s1 的前 0 ~ i 个字符、考虑 s2 的前 0 ~ j 的字符，形成的最长公共子序列长度。
     * 四种情况：
     *   不包含s1[i], 不包含s2[j]： f[i-1][j-1]
     *   包含s1[i],  包含s2[j] ： 前提s1[i] = s2[j], f[i-1][j-1] + 1
     *   不包含s1[i], 包含s2[j] ： f[i-1][j]表示 「必然不包含 s1[i]，但可能包含s2[j]」， f[i-1][j] 则是 情况一 与 当前情况 合集
     *      但是由于我们求的是「最大值」，只需要确保「不漏」即可保证答案的正确（某些情况被重复参与比较不影响正确性），因此这里直接使用 f[i−1][j] 进行表示没有问题。
     *   包含s1[i],  不包含s2[j]： 与情况 33 同理，直接使用 f[i][j - 1]表示没有问题
     *  f[i][j] 就是在上述所有情况中取 max 而来，由于情况 1 被 情况 3 和 情况 4 所包含，因此我们只需要考虑 f[i−1][j]、f[i][j−1] 和 f[i−1][j−1]+1 三种状态即可，其中最后一种状态需要满足 s1[i] = s2[j] 前提条件。
     *
     * 时间复杂度O(m*n)
     * 空间复杂度O(m*n)
     * @param nums1 int[]
     * @param nums2 int[]
     * @return ans int
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                f[i][j] = nums1[i - 1] == nums2[j - 1] ? Math.max(f[i][j], f[i - 1][j - 1] + 1) : Math.max(f[i - 1][j], f[i][j - 1]);
            }
        }
        return f[m][n];
    }
}

class UncrossedLinesRun{
    public static void main(String[] args) {
        UncrossedLines uncrossedLines = new UncrossedLines();
        System.out.println("Exam1:" + checkResult(uncrossedLines.maxUncrossedLines(new int[]{1, 4, 2}, new int[]{1, 2, 4}), 2));
        System.out.println("Exam2:" + checkResult(uncrossedLines.maxUncrossedLines(new int[]{2, 5, 1, 2, 5}, new int[]{10, 5, 2, 1, 5, 2}), 3));
        System.out.println("Exam3:" + checkResult(uncrossedLines.maxUncrossedLines(new int[]{1, 3, 7, 1, 7, 5}, new int[]{1, 9, 2, 5, 1}), 2));
        System.out.println("Exam4:" + checkResult(uncrossedLines.maxUncrossedLines(new int[]{1, 1, 2, 1, 2}, new int[]{1, 3, 2, 3, 1}), 3));
    }

    private static boolean checkResult(Object outputResult, Object expectedResult){
        return outputResult == expectedResult;
    }

}