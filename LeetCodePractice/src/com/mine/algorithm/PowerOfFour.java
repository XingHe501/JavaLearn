package com.mine.algorithm;

import com.mine.util.Util;

/**
 * Created: 2021/05/31 17:26
 *
 * https://leetcode-cn.com/problems/power-of-four
 */
public class PowerOfFour {
    public boolean isPowerOfFour(int n) {
        switch (n)
        {
            case 1:
            case 4:
            case 16:
            case 64:
            case 256:
            case 1024:
            case 4096:
            case 16384:
            case 65536:
            case 262144:
            case 1048576:
            case 4194304:
            case 16777216:
            case 67108864:
            case 268435456:
            case 1073741824:
                return true;
            default:
                return false;
        }
    }

    /**
     * 使用lowbit函数
     *  4的幂次方一定也是2的幂次方
     * @param n
     * @return
     */
    public boolean isPowerOfFourAnsTwo(int n){
        Util util = new Util();
        int x = (int) Math.sqrt(n);
        return n > 0 && util.lowbit(n) == n && (x * x == n);
    }
}

class PowerOfFourRun{
    public static void main(String[] args) {
        PowerOfFour powerOfFour = new PowerOfFour();
        System.out.println(powerOfFour.isPowerOfFour(16));
        System.out.println(powerOfFour.isPowerOfFour(5));
        System.out.println(powerOfFour.isPowerOfFour(1));

        System.out.println((16 & -16));

        System.out.println(powerOfFour.isPowerOfFourAnsTwo(16));
        System.out.println(powerOfFour.isPowerOfFourAnsTwo(5));
        System.out.println(powerOfFour.isPowerOfFourAnsTwo(1));
    }
}