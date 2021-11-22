package com.mine.algorithm.greedy;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BestTimeToBuyAndSellStockIi {
    /**
     * 买卖股票的最佳时机 Ⅱ (Easy)
     *
     * @param prices int[]  股票价格
     * @return ans int 获取的最大利润
     */
    public int maxProfit(int[] prices) {
        int ans = 0;
        // 每天都成交，确保每笔交易收入都大于0
        for (int i = 1; i < prices.length; i++) ans += Math.max(prices[i] - prices[i - 1], 0);
        return ans;
    }
}

class BestTimeToBuyAndSellStockIiRun{
    public static void main(String[] args) {

    }
}
