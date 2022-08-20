package DoQuestionsOfBigFactory.class09;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/14 8:49
 */
public class Code03 {

    /**
     * https://leetcode.com/problems/remove-invalid-parentheses/
     * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
     * 返回所有可能的结果。答案可以按 任意顺序 返回。
     */

    public List<String> removeInvalidParentheses(String s) {
        if(s == null || s.length() ==0){
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();

        remove(s, ans, 0 ,0 , new char[]{'(', ')'});
        return ans;
    }


    //欣赏一下leetcode投票第一的写法 非常妙！！！
    public void remove(String s, List<String> ans, int checkIndex, int deleteIndex, char[] chars){
        //记录来到i位置时，左括号和右括号的个数   遇到左括号 ++  右括号--
        int count = 0;
        for(int i = checkIndex; i< s.length(); i++){

            if(s.charAt(i) == chars[0]){
                count ++ ;
            }
            if(s.charAt(i) == chars[1]) {
                count --;
            }
            //此时右括号大于左括号了， 从deleteIndex位置开始依次删
            if(count < 0){
                //删除的右括号不能超过此时i的位置
                for(int j = deleteIndex; j < i; j++){
                    //此时遇到右括号， 可以删的情况
                    //1、是第一个位置的右括号
                    //2、前一个位置不是右括号的
                    //删完之后交给子函数去调
                    if(s.charAt(j) == chars[1] && (j == deleteIndex || s.charAt(j-1) != chars[1])){
                        s = s.substring(0,j) + s.substring(j +1, s.length());
                        remove(s, ans, checkIndex, deleteIndex, chars);
                    }
                }
                return;
            }
        }
        //来到这儿， 右括号处理完了
        //可能左括号比右括号多了
        //非常骚的操作！！！！！
        String reversed = new StringBuilder(s).reverse().toString();
        //如果此时第一个是左括号，那么他没反转
        if(chars[0] == '('){
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        }else {
            //此时左右括号都调整完了， 可以收集答案了
            ans.add(reversed);
        }
    }

}

   