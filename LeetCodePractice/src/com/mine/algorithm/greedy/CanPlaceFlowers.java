package com.mine.algorithm.greedy;

/**
 * https://leetcode-cn.com/problems/can-place-flowers/
 */
public class CanPlaceFlowers {
    /**
     * @param flowerbed int[] 花坛
     * @param n         int 要至多种花的数量
     * @return Boolean 给的值是否可以
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length && n > 0; i++) {
            if (flowerbed[i] == 1) {
                // 当前位置已经种花，直接跳两个到下个可以种花的位置
                i += 2;
            } else if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                // 两种情况，上面确保了i每次都是可以种花的位置
                // 1. i当前是最后一个位置，一定可以种花
                // 2. i没到最后一个，判断下一个位置有没有花就可以
                n--;
                i += 2;
            } else {
                // 不是最后一格，下一个种花位置有花，直接跳到下下个种花的位置
                // [1,0,0,1,0,0]
                //      i
                i += 3;
            }
        }
        return n <= 0;
    }
}

class CanPlaceFlowersRun{
    public static void main(String[] args) {

    }
}