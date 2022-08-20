package DoQuestionsOfBigFactory.class12;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/5/9 16:51
 */
public class Code01 {
    /**
     *https://leetcode.com/problems/permutation-in-string/
     * 给定长度为m的字符串aim，以及一个长度为n的字符串str
     * 问能否在str中找到一个长度为m的连续子串，
     * 使得这个子串刚好由aim的m个字符组成，顺序无所谓，
     * 返回任意满足条件的一个子串的起始位置，未找到返回-1
     */
    public static boolean checkInclusion(String s, String a) {
        if (s == null || a == null || a.length() < s.length()) {
            return false;
        }

        int M = s.length();
        char[] aimChar = s.toCharArray();
        char[] sChar = a.toCharArray();
        int R = 0;

        //m个字符
        int all = M;
        //字母的ascii码所在位置
        int[] help = new int[256];
        for(int i =0; i< M;i++){
            help[aimChar[i]]++;
        }
        //先形成窗口
        for(;R< M;R++){
            if(help[sChar[R]]-- > 0){
                all--;
            }
        }

        //coding
        for(;R < a.length();R++){
            if(all == 0){
                return true;
            }
            if(help[sChar[R]]-- > 0){
                all--;
            }
            if(help[sChar[R-M]]++ >=0){
                all++;
            }
        }
        return all == 0? true:false;
    }


}

   