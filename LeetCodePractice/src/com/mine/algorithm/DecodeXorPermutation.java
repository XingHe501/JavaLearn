package com.mine.algorithm;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/decode-xored-permutation/
 */

public class DecodeXorPermutation {
    /**
     * 1. 相同值异或，结果为0
     * A ^ A = 0
     * 2. 任何数值与0进行异或，结果都是它本身
     * A ^ 0 = A
     * 3. 异或运算满足交换律
     * A ^ B = C; A = B ^ C; B = A ^ C;
     * */
    public static int[] decode(int[] encoded) {
        // perm length:n, encoded length: n-1
        // perm = [a, b, c, d, e]
        // encoded = [f, g, h, i] = [perm[0] ^ perm[1], perm[1] ^ perm[2], perm[2] ^ perm[3],... , perm[n-2] ^ perm[n-1]] = [a^b, b^c, c^d, d^e]
        // f^h = a^b^c^d, 可得encoded 从0开始，每隔一位开始异或的值  等于  perm除去n-1位以外所有的异或结果
        // perm所有值的异或结果 a^b^c^d^e = f^h^e => e = perm[n-1] = perm[All ^ Value] ^ f^h

        int n = encoded.length + 1;
        int[] perm = new int[n];

        // 获取encoded从第0位，每隔一位进行异或的结果
        int encodedSeptalPositionXorValue = 0;
        for (int i = 0; i < n - 1; i += 2) encodedSeptalPositionXorValue ^= encoded[i];

        // 求得 perm 的所有异或结果。因为perm是由前n个正整数排列的， 由题可得
        int permAllXorValue = 0;
        for (int i = 1; i <= n; i++) permAllXorValue ^= i;

        // perm的最后一位值
        perm[n-1] = encodedSeptalPositionXorValue ^ permAllXorValue;

        // 有最后一位，从后往前推
        for (int i = n - 2; i >= 0; i--) {
            perm[i] = encoded[i] ^ perm[i + 1];
        }

        return perm;
    }
}

class  DecodeXorPermutationRun{
    public static void main(String[] args) {
        System.out.println(Arrays.toString(DecodeXorPermutation.decode(new int[]{6,5,4,6}))); // [2,4,1,5,3]
    }
}
