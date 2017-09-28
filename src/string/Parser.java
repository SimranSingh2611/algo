package string;

import java.util.*;

public class Parser {
    private static Map<String, Integer> precedence;
    static {
        precedence = new HashMap<>();
        precedence.put("not", 100);
        precedence.put("and", 50);
        precedence.put("or", 20);
        precedence.put("(", -10);
        precedence.put(")", -10);
    }

    /*
    Reverse Polish notation
     */
    public static List<String> RPN(String input) {
        String[] tokens = input.split(" ");

        List<String> outputQueue = new ArrayList<>();
        Deque<String> operatorStack = new ArrayDeque<>();

        for(String token : tokens) {
            if(token.equals("(")) {
                operatorStack.addFirst(token);
            } else if(token.equals(")")) {
                while(!operatorStack.getFirst().equals("("))
                    outputQueue.add(operatorStack.removeFirst());
                operatorStack.removeFirst();
            } else if(!precedence.containsKey(token)) {
                outputQueue.add(token);
            } else {
                while(operatorStack.size() > 0 && precedence.get(operatorStack.getFirst()) >= precedence.get(token) ) {
                    outputQueue.add(operatorStack.removeFirst());
                }
                operatorStack.addFirst(token);
            }
        }
        while(operatorStack.size() > 0) {
            outputQueue.add(operatorStack.removeFirst());
        }
        return outputQueue;
    }

    public static void main(String[] args) {
        String input = "( A or C ) and B";
        List<String> l = RPN(input);
        for(String s : l) System.out.println(s);
    }
}