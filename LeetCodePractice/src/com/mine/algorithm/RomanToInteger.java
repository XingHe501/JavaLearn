package com.mine.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/roman-to-integer
 */
public class RomanToInteger {
    /**
     * 从后往前相加，如果当前值比左边一位大，需要做减法运算
     * */
    public static int romanToInt(String s) {
        Map<String, Integer> romanChar = new HashMap<>();
        romanChar.put("I", 1);
        romanChar.put("V", 5);
        romanChar.put("X", 10);
        romanChar.put("L", 50);
        romanChar.put("C", 100);
        romanChar.put("D", 500);
        romanChar.put("M", 1000);

        int integer = 0;

        String[] sArr = s.split("");

        for (int i = sArr.length - 1; i >= 0; i--) {
            int afterValue = i - 1 >= 0 ? romanChar.get(sArr[i - 1]) : 0;
            int currentValue = romanChar.get(sArr[i]);
            if (currentValue > afterValue) {
                integer += currentValue - afterValue;
                i--;
                continue;
            }
            integer += currentValue;
        }

        return integer;
    }
    /**
     * 从0开始，判断下一位
     * */
    public static int romanToIntAnswerTwo(String s){
        Map<Character, Integer> romanChar = new HashMap<>(){{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};

        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            var value = romanChar.get(s.charAt(i));
            if (i < s.length() - 1 && value < romanChar.get(s.charAt(i + 1)))
                ans -= value;
            else
                ans += value;
        }

        return ans;
    }
    /**
     * 优化方案： map -> switch
     */
    public static int romanToIntAnswerThree(String s){
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            var value = getRomanValue(s.charAt(i));
            if (i < s.length() - 1 && value < getRomanValue(s.charAt(i + 1)))
                ans -= value;
            else
                ans += value;
        }

        return ans;
    }

   static int getRomanValue(Character s){
        switch (s){
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
            default : return 0;
        }
    }
}

class RomanToIntegerRun {
    public static void main(String[] args) {
        IntegerToRoman integerToRoman = new IntegerToRoman();
        String[] s = new String[3999];
        for (int i = 0; i < 3999; i++) {
            s[i] = integerToRoman.intToRomanAnswerTwo(i+1);
        }

        var a = System.currentTimeMillis();
        for (int i = 0; i < s.length; i++) {
            RomanToInteger.romanToInt(s[i]);
        }
        System.out.println(System.currentTimeMillis() - a); // 14

        var b = System.currentTimeMillis();
        for (int i = 0; i < s.length; i++) {
            RomanToInteger.romanToIntAnswerTwo(s[i]);
        }
        System.out.println(System.currentTimeMillis() - b); // 4

        var c = System.currentTimeMillis();
        for (int i = 0; i < s.length; i++) {
            RomanToInteger.romanToIntAnswerThree(s[i]);
        }
        System.out.println(System.currentTimeMillis() - c); // 2
    }
}
