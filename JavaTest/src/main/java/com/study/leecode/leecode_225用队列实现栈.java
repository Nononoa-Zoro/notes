package com.study.leecode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class leecode_225用队列实现栈 {
    class MyStack {
        Deque<Integer> queue;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            this.queue = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            this.queue.add(x);
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return queue.removeLast();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return queue.getLast();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return queue.isEmpty();
        }

    }
}
