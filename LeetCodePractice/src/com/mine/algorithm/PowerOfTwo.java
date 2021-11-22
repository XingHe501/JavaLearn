package com.mine.algorithm;

/**
 * Created: 2021/05/30 23:06
 *
 * https://leetcode-cn.com/problems/power-of-two
 */
public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        return ((n - 1) & n) == 0 & n > 0;
    }
}

class PowerOfTwoRun {
    public static void main(String[] args) {
        PowerOfTwo powerOfTwo = new PowerOfTwo();
        System.out.println(powerOfTwo.isPowerOfTwo(-2147483648));
    }
}