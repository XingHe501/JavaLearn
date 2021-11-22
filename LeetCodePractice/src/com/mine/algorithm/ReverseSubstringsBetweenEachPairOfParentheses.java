package com.mine.algorithm;

import java.util.Stack;

/**
 * Created: 2021/05/26 19:46
 *
 * https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {
    public String reverseParentheses(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if(chars[i] == '(')
                stack.push(i);
            if (chars[i] == ')')
                reverse(chars, stack.pop() + 1, i - 1);
        }
        StringBuffer ans = new StringBuffer();
        for (int k = 0; k < stack.size(); k++) {
            if(chars[k] != ')' && chars[k] != '(')
                ans.append(chars[k]);
        }
        return ans.toString();
    }
    public void reverse(char[] arr, int left, int right){
        while (right > left)
        {
            char tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            right--;
            left++;
        }
    }
}

class ReverseSubstringsBetweenEachPairOfParenthesesRun{
    public static void main(String[] args) {
        ReverseSubstringsBetweenEachPairOfParentheses reverseSubstringsBetweenEachPairOfParentheses = new ReverseSubstringsBetweenEachPairOfParentheses();
        System.out.println(reverseSubstringsBetweenEachPairOfParentheses.reverseParentheses("(abcd)"));
        System.out.println(reverseSubstringsBetweenEachPairOfParentheses.reverseParentheses("(u(love)i)"));
        System.out.println(reverseSubstringsBetweenEachPairOfParentheses.reverseParentheses("(ed(et(oc))el)"));
        System.out.println(reverseSubstringsBetweenEachPairOfParentheses.reverseParentheses("a(bcdefghijkl(mno)p)q"));
    }
}