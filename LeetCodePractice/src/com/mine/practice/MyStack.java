package com.mine.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created: 2021/05/26 20:16
 * FileName: MyStack
 * What's the daily question: 2021/05/26
 * Difficulty:
 * Subject:
 * Doc:
 * Example:
 * Constraints:
 * Hidden Constraint One:
 * Hidden Constraint Two:
 * Hidden Constraint Three:
 *
 * @author Admin
 * @version jdk
 */
// "static void main" must be defined in a public class.
class MyStack {
    private List<Integer> data;               // store elements
    public MyStack() {
        data = new ArrayList<>();
    }
    /** Insert an element into the stack. */
    public void push(int x) {
        data.add(x);
    }
    /** Checks whether the queue is empty or not. */
    public boolean isEmpty() {
        return data.isEmpty();
    }
    /** Get the top item from the queue. */
    public int top() {
        return data.get(data.size() - 1);
    }
    /** Delete an element from the queue. Return true if the operation is successful. */
    public boolean pop() {
        if (isEmpty()) {
            return false;
        }
        data.remove(data.size() - 1);
        return true;
    }
};

class Main {
    public static void main(String[] args) {
//        MyStack s = new MyStack();
//        s.push(1);
//        s.push(2);
//        s.push(3);
//        for (int i = 0; i < 4; ++i) {
//            if (!s.isEmpty()) {
//                System.out.println(s.top());
//            }
//            System.out.println(s.pop());
//        }

        // 1. Initialize a stack.
        Stack<Integer> s = new Stack<>();
        // 2. Push new element.
        s.push(5);
        s.push(13);
        s.push(8);
        s.push(6);
        // 3. Check if stack is empty.
        if (s.empty() == true) {
            System.out.println("Stack is empty!");
            return;
        }
        // 4. Pop an element. 返回并移除栈顶一个元素
        s.pop();
        // 5. Get the top element.
        System.out.println("The top element is: " + s.peek());
        // 6. Get the size of the stack.
        System.out.println("The size is: " + s.size());
    }
}