package com.mine.practice;

/**
 * Created: 2021/09/13 20:50
 * <p>
 * FileName: RandomNumberProgram
 * <p>
 * Subject:
 * <p>
 * SpecialSubject:
 * <p>
 * Difficulty:
 * <p>
 * Doc:
 * Lehmer 算法： X(i) = a * X(i-1) mod m
 * 新的随机数 =  常数a 乘以 旧的随机数  取常数a的模
 * <p>
 * Example:
 * <p>
 * Constraints:
 * <p>
 * Hidden Constraint One:
 * <p>
 * Hidden Constraint Two:
 * <p>
 * Hidden Constraint Three:
 * <p>
 * Https:
 * <p>
 *
 * @author Admin
 * @version jdk 11
 */
public class RandomNumberProgram {
    private final int a = 16807; // a = 7 ^ 5
    private final int m = 2147483647; // m = 2^31 - 1
    private final int q = 127773; // q = m / a
    private final int r = 2836;// r = m % a
    private int seed;

    // 可以构造无参函数获取系统日期时间并将其转换为整数，并将其用作种子。
    public RandomNumberProgram(int seed_) {
        if (seed_ <= 0) seed_ = seed_ + m;
        if (seed_ == Integer.MAX_VALUE) seed_ = seed_ - 1;
        seed = seed_;
    }


    public double Next() {
        int hi = seed / q;
        int lo = seed % q;
        seed = (a * lo) - (r * hi);
        if (seed <= 0) seed = seed + m;
        return (seed * 1.0) / m;
    }

}

class RandomNumberProgramTest {
    public static void main(String[] args) {
        RandomNumberProgram rnp = new RandomNumberProgram(1000);
        var a = rnp.Next();
        var b = rnp.Next();
        var c = rnp.Next();
        var d = rnp.Next();
        var e = rnp.Next();

        System.out.println(a);
    }
}