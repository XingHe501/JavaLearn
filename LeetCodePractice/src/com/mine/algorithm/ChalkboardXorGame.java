package com.mine.algorithm;

/**
 * Created: 2021/05/22 16:33
 * <p>
 * https://leetcode-cn.com/problems/chalkboard-xor-game/
 */
public class ChalkboardXorGame {
    /**
     * Alice 先手， Bob后手
     * 两个条件解释：
     * 1、flag == 0.  所有结果开始就等于0，那么Alice不需要进行任何操作就获胜，所以判断数组中 所有异或值 == 0
     * 2、nums.length / 2 == 0.
     * 如果是奇数，基于每步都使用最优解，那么Alice永远都是擦掉数组中最后一个值的人
     * 如果是偶数，同样基于每步使用最优解， Bob则是擦掉数组中最后一个值的人。
     */
    public boolean xorGame(int[] nums) {
        int flag = 0, numsSize = nums.length;
        for (int i = 0; i < numsSize; i++) flag ^= nums[i]; // => for (var num : nums) flag ^= num;
        return flag == 0 || numsSize % 2 == 0;
    }
}

class ChalkboardXorGameRun {
    public static void main(String[] args) {
        ChalkboardXorGame chalkboardXorGame = new ChalkboardXorGame();
        System.out.println(chalkboardXorGame.xorGame(new int[]{1, 0}));
    }
}

/*
 * todo 思路
 * 博弈论
 * 先手必胜态，
 *  异或值为0，先手必胜。
 * 后手必败态：
 *  什么情况下经过后手一次操作，可以让序列返回到先手的场面
 *  后手操作前的异或值，Sum （Sum=/=0), 去掉任意数字后异或为0
 *  因为，相同数值异或结果为 0， 去掉某个数值，等价于在原有异或和的基础上异或上这个值
 *  Sum‘ = Sum ^ nums[i] = 0
 *  后手必败，i取任何一位， 确保这个等式成立。
 *  由
 *      Sum ^ nums[0] = ... = Sum ^ nums[k] = ... = Sum ^ nums[n−1] = 0
 *      1^1 = 2^2 = 0 => 1^1 ^ 2^2 = 0
 *      「任意数值与 0 异或数值不变」
 *  可得
 *      (Sum ^ nums[0]) ^ ... ^ (Sum ^ nums[k]) ^ ... ^ ( Sum ^ nums[n−1]) = 0
 *  由
 *      交换律
 *  可得
 *     （Sum ^ ... ^ Sum) ^ (nums[0] ^ ... ^ nums[k] ^ ... ^ nums[n-1]) = 0
 *     =>
 *     （Sum ^ ... ^ Sum) ^ Sum = 0, Sum =/= 0
 *  由
 *      相同数值偶数次异或结果为 0
 *  可得
 *      后手操作后的序列为偶数，操作前的序列为奇数，先手操作前的序列为偶数
 */