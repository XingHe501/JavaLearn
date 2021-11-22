package com.mine.algorithm;

/**
 * Created: 2021/05/24 14:00
 *
 * https://leetcode-cn.com/problems/strange-printer/
 */
public class StrangePrinter {

    /**
     * https://leetcode-cn.com/problems/strange-printer/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-xqeo9/
     *  动态规划
     * 定义f[l][r]为将[l,r]这一段打印成目标结果所消耗的最小打印次数。不失一般性考虑f[l][r]该如何转移:
     *  · 只打印1这个位置，此时f[][r]=f[l＋1][r]+1
     *  。不只打印l这个位置，而是从l打印到k(需要确保首位相同s[l]= s[k])∶f[l][r]= f[l][k-1]+f[k＋1][r],l<k<=r
     * 其中状态转移方程中的情况⒉需要说明一下:
     *  由于我们只确保s[]= s[k]，并不确保[1,k]之间的字符相同，根据我们基本分析可知，s[对]这个点可由打印s[Q]的时候一同打印，因此本身 s[k]并不独立消耗打印次数，
     *  所以这时候[l,k]这一段的最小打印次数应该取f[l][k一1]，而不是f[][]。
     * 最终的f[l][r]为上述所有方案中取min。
     * @param s String
     * @return int
     */
    public int strangePrinter(String s) {
        var charArray = s.toCharArray();
        int n = charArray.length;

        int[][] f = new int[n + 1][n + 1];
        for (int len = 1; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                f[l][r] = f[l + 1][r] + 1;
                for (int k = l + 1; k <= r; k++) {
                    if (s.charAt(l) == s.charAt(k)) {
                        f[l][r] = Math.min(f[l][r], f[l][k - 1] + f[k + 1][r]);
                    }
                }
            }
        }
        return f[0][n-1];
    }
}

class StrangePrinterRun{
    public static void main(String[] args) {
        StrangePrinter strangePrinter = new StrangePrinter();
        System.out.println(strangePrinter.strangePrinter("aba"));
    }
}