package com.mine.algorithm;

/**
 * Created: 2021/06/09 20:34
 *
 * https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2zsx1/
 */
public class TheBestTimeToBuyAndSellStocks {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 99.60% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了59.67% 的用户
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) ans += Math.max(prices[i] - prices[i - 1], 0);
        return ans;
    }
}

class TheBestTimeToBuyAndSellStocksRun {
    public static void main(String[] args) {
        TheBestTimeToBuyAndSellStocks the = new TheBestTimeToBuyAndSellStocks();
        int[] prices = new int[]{1,2,3,4,5};
        System.out.println(the.maxProfit(prices)); // 0
    }
}