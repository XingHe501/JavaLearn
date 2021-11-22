package com.mine.algorithm;

/**
 * Created: 2021/05/27 20:36
 * https://leetcode-cn.com/problems/hamming-distance/
 */
public class HammingDistance {
    // 最高位的二进制位编号为 30
    static final int HIGH_BIT = 30;

    public int hammingDistance(int x, int y) {
        int[] xInteger = new int[HIGH_BIT+1], yInteger = new int[HIGH_BIT+1];
        var xChar = Integer.toBinaryString(x).toCharArray();
        var yChar = Integer.toBinaryString(y).toCharArray();
        insertArray(xInteger, xChar);
        insertArray(yInteger, yChar);
        int ans = 0;
        for (int i = xInteger.length - 1; i >= 0; i--) ans += xInteger[i] == yInteger[i] ? 0 : 1;
        return ans;
    }

    public void insertArray(int[] integerArray, char[] charArray) {
        int i = charArray.length - 1, y = integerArray.length - 1;
        while (i >= 0) {
            integerArray[y] = Character.getNumericValue(charArray[i]);
            i--;
            y--;
        }
    }

    /**
     * 逐位比较
     * 本身不改变 x 和 y，每次取不同的偏移位进行比较，不同则加一。
     * 循环固定取满 32 。
     * @param x
     * @param y
     * @return
     */
    public int hammingDistanceAnswerTwo(int x, int y) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int a = (x >> i) & 1, b = (y >> i) & 1;
            ans += a ^ b;
        }
        return ans;
    }
}

class HammingDistanceRun{
    public static void main(String[] args) {
        HammingDistance hammingDistance = new HammingDistance();
        System.out.println(hammingDistance.hammingDistance(1, 4));
    }
}