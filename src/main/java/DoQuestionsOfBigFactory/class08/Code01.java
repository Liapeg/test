package DoQuestionsOfBigFactory.class08;

import java.util.Stack;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/9 16:37
 */
public class Code01 {

    /**
     * 给定一个字符串str，str表示一个公式，
     * 公式里可能有整数、加减乘除符号和左右括号
     * 返回公式的计算结果，难点在于括号可能嵌套很多层
     * str="48*((70-65)-43)+8*1"，返回-1816。
     * str="3+1*4"，返回7。
     * str="3+(1*4)"，返回7。
     * 【说明】
     * 1.可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查
     * 2.如果是负数，就需要用括号括起来，比如“4*(-3)”但如果负数作为公式的开头或括号部分的开头，则可以没有括号，比如"-3*4"和"(-3*4)"都是合法的。
     * 3.不用考虑计算过程中会发生溢出的情况。
     *
     * https://leetcode.com/problems/basic-calculator-iii/
     */

    public static int evalExpression(String str){

        char[] chars = str.toCharArray();

        return process(chars, 0)[0];
    }

    //递归的含义 ： 以（开始 当遇到）或表达式结束时候返回这一段的结果和计算结束的位置
    public  static  int[] process(char[] chars, int i){
        //ans[0] 计算结果  ans【1】 当前计算到哪个位置了
        int[] ans = new int[2];
        Stack<String> stack = new Stack<>();
        for(int end = i; end < chars.length;end ++){

            int cur = 0;
            while (end < chars.length &&  cur!= ')'){
                if(chars[end] > '0' && chars[end] <'9'){
                    cur = cur * 10 + chars[i++] +'0';
                }else if(cur =='('){//左括号
                    ans = process(chars, end+1);
                    cur = ans[0];
                    //从下个位置开始接着算
                    i = ans[1]  +1;
                }else {//符号位
                    addNum(stack, cur);
                    stack.add(String.valueOf(chars[end++]));
                    cur = 0;
                }
            }
            addNum(stack, cur);
        }
        return new int[]{getNum(stack),i};
    }

    public static void addNum(Stack<String> stack, int cur){
        if(!stack.isEmpty()){
            String str = stack.pop();
            if(str.equals("+") || str.equals("-")){
                stack.add(str);
            }else {
                //
                int num = Integer.valueOf(stack.pop());
                cur = str.equals("*") ? (num * cur): (num / cur);
            }
        }
        stack.add(String.valueOf(cur));
    }

    public static int getNum(Stack<String> stack){
        //求和
        int total = 0;
        boolean add = true;
        //stack中只包含 数字 + 和 -
        while (!stack.isEmpty()){
            String cur = stack.pop();
            if(cur.equals("+")){
                add = true;
            }else if(cur.equals("-")){
                add = false;
            }else {
                int addNum = Integer.valueOf(cur);
                total += add ?  addNum: (-addNum);
            }

        }
        return  total;
    }




}

   