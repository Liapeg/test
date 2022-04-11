package DoQuestionsOfBigFactory.class03;

import java.util.HashSet;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/21 17:23
 */

/**
 * 只由小写字母（a~z）组成的一批字符串
 * 都放在字符类型的数组String[] arr中
 * 如果其中某两个字符串所含有的字符种类完全一样
 * 就将两个字符串算作一类
 * 比如：baacbba和bac就算作一类
 * 返回arr中有多少类？
 */
public class Code2 {
    public static int types(String[] str){
        if(str == null || str.length < 1){
            return 0;
        }
        HashSet set = new HashSet();
        for(String s : str){
            int cur = 0;
            char[] chars = s.toCharArray();
            for(char c : chars){
                cur |= (1 << (c-'a'));
            }
            set.add(cur);
        }
        return set.size();
    }

    public static int types1(String[] arr) {
        HashSet<String> types = new HashSet<String>();
        for (String str : arr) {
            char[] chs = str.toCharArray();
            boolean[] map = new boolean[26];
            for (int i = 0; i < chs.length; i++) {
                map[chs[i] - 'a'] = true;
            }
            String key = "";
            for (int i = 0; i < 26; i++) {
                if (map[i]) {
                    key += String.valueOf((char) (i + 'a'));
                }
            }
            types.add(key);
        }
        return types.size();
    }


    public static void main(String[] args) {
        int possibilities = 5;
        int strMaxSize = 10;
        int arrMaxSize = 100;
        int testTimes = 500000;
        System.out.println("test begin, test time : " + testTimes);
        for (int i = 0; i < testTimes; i++) {
            String[] arr = getRandomStringArray(possibilities, strMaxSize, arrMaxSize);
            int ans1 = types1(arr);
            int ans2 = types(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");

    }

    // for test
    public static String[] getRandomStringArray(int possibilities, int strMaxSize, int arrMaxSize) {
        String[] ans = new String[(int) (Math.random() * arrMaxSize) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = getRandomString(possibilities, strMaxSize);
        }
        return ans;
    }
    // for test
    public static String getRandomString(int possibilities, int strMaxSize) {
        char[] ans = new char[(int) (Math.random() * strMaxSize) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }
}

   