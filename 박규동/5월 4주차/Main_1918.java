package com.company;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expr = br.readLine();

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            char cur = expr.charAt(i);

            if (cur >= 'A' && cur <= 'Z') sb.append(cur);
            else if (cur == '+' || cur == '-' || cur == ')') {
                while (!stack.isEmpty()) {
                    if (stack.peek() == '(') break;
                    sb.append(stack.pop());
                }
                if (cur != ')') stack.push(cur);
                else stack.pop(); // '('제거
            } else if (cur == '(') stack.push(cur);
            else {
                while (!stack.isEmpty()) {
                    if (stack.peek() == '+' || stack.peek() == '-' || stack.peek() == '(') break;
                    sb.append(stack.pop());
                }
                stack.push(cur);
            }
        }

        while (!stack.isEmpty()) sb.append(stack.pop());

        System.out.println(sb);

    }
}
