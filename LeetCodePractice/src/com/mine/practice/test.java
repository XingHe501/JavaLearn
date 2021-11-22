package com.mine.practice;

public class test {
}

class testRun {
    public static void main(String[] args) {
        // and 运算符
        System.out.println(192 & 255);
    }

    static String toBinary(int a) {
        var charArray = Integer.toString(a, 2).toCharArray();
        StringBuilder ans = new StringBuilder();
        var num = 8 - charArray.length;
        while (num-- > 0)
            ans.append("0");

        for (char c : charArray)
            ans.append(c);

        return ans.toString();
    }
}
