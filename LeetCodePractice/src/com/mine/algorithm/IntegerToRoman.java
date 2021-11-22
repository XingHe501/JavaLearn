package com.mine.algorithm;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.lang.*;

/**
 * https://leetcode-cn.com/problems/integer-to-roman/
 * */
public class IntegerToRoman {
    /**
     * 若当前数值value不超过num，则从num中不断减去value，直至num小于value，然后遍历下一个数值-符号对。若遍历中num 为 0 则跳出循环。
     * 时间复杂度：O(1)。由于 valueSymbols 长度是固定的，且这 13 字符中的每个字符的出现次数均不会超过 3，因此循环次数有一个确定的上限。对于本题给出的数据范围，循环次数不会超过 15 次.
     * 空间复杂度：O(1)。
     */
    public String intToRomanAnswerOne(int num) {
        StringBuilder value = new StringBuilder();
        for (Map.Entry<Integer, String> v : initDictionary().entrySet())
        {
            while (num >= v.getKey()) {
                num -= v.getKey();
                value.append(v.getValue());
            }
            if (num == 0) {
                break;
            }
        }
        return value.toString();
    }

    /**
     * 每个位上的数字，在硬编码表中查找对应的罗马字符，并将结果拼接在一起，即为对应的罗马数字
     * 时间复杂度：O(1)计算量与输入数字的大小无关。
     * 空间复杂度：O(1)
     * */
    public String intToRomanAnswerTwo(int num){
        return thousands()[num / 1000] + hundreds()[num % 1000 / 100] + tens()[num % 100 / 10] + ones()[num % 10];
    }


    public static Map<Integer, String> initDictionary() {
        Map<Integer, String> romanChar = new TreeMap<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }
                }
        );
        romanChar.put(1, "I");
        romanChar.put(4, "IV");
        romanChar.put(5, "V");
        romanChar.put(9, "IX");
        romanChar.put(10, "X");
        romanChar.put(40, "XL");
        romanChar.put(50, "L");
        romanChar.put(90, "XC");
        romanChar.put(100, "C");
        romanChar.put(400, "CD");
        romanChar.put(500, "D");
        romanChar.put(900, "CM");
        romanChar.put(1000, "M");
        return romanChar;
    }

    public static String[] ones() {
        return new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    }

    public static String[] tens() {
        return new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    }

    public static String[] hundreds() {return new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};}

    public static String[] thousands() {
        return new String[]{"", "M", "MM", "MMM"};
    }
}

class IntegerToRomanRun{
    public static void main(String[] args) {
        IntegerToRoman integerToRoman = new IntegerToRoman();

        var a = System.currentTimeMillis();
        System.out.println(integerToRoman.intToRomanAnswerOne(3492));
        System.out.println(integerToRoman.intToRomanAnswerOne(1232));
        System.out.println(integerToRoman.intToRomanAnswerOne(2121));
        System.out.println(integerToRoman.intToRomanAnswerOne(3129));
        System.out.println(integerToRoman.intToRomanAnswerOne(1249));
        System.out.println(integerToRoman.intToRomanAnswerOne(3829));
        System.out.println(System.currentTimeMillis() - a);

        var b = System.currentTimeMillis();
        System.out.println(integerToRoman.intToRomanAnswerTwo(3429));
        System.out.println(integerToRoman.intToRomanAnswerTwo(1232));
        System.out.println(integerToRoman.intToRomanAnswerTwo(2121));
        System.out.println(integerToRoman.intToRomanAnswerTwo(3129));
        System.out.println(integerToRoman.intToRomanAnswerTwo(1249));
        System.out.println(integerToRoman.intToRomanAnswerTwo(3289));
        System.out.println(System.currentTimeMillis() - b);
    }
}
