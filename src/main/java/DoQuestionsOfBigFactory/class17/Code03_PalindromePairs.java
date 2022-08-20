package DoQuestionsOfBigFactory.class17;

import java.util.*;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/20 14:19
 */
public class Code03_PalindromePairs {

    /**
     * 给定一组 互不相同 的单词， 找出所有 不同 的索引对 (i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
     * https://leetcode.cn/problems/palindrome-pairs/
     *
     * 思路: 以一个单次的每个字母为头【0...i】上如果是回文，那么map中如果有剩下的字符串就可以拼在前面 整体构成回文串
     *      同样以每个字母出发到结尾 【i...N-1】 上如果是回文  。。。。。。。。。。。。。。。
     *
     *      mynache
     */

    //超时了 需要优化
    public static List<List<Integer>> palindromePairs(String[] words) {
        //缓存一份字符串的位置
        Map<String, Integer> stringMap= new HashMap<>();
        for(int i = 0; i< words.length;i++){
            stringMap.put(words[i], i);
        }

        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i< words.length;i++){
            int N = words[i].length();
            String reverseStr = reverseString(words[i]);
            if(stringMap.get(reverseStr) != null && stringMap.get(reverseStr) != i){
                List<Integer> list = new ArrayList<>();
                list.add(stringMap.get(reverseStr));
                list.add(i);
                result.add(list);
            }
            for(int j = 0; j< N;j++){
                if(isPalindromeString(words[i],0,j)){
                    if(stringMap.get(reverseStr.substring(0,N-j-1))!=null &&
                            stringMap.get(reverseStr.substring(0,N-j-1))!=i){
                        List<Integer> list = new ArrayList<>();
                        list.add(stringMap.get(reverseStr.substring(0,N-j-1)));
                        list.add(i);
                        result.add(list);
                    }
                }
            }
        }

        for(int i = 0; i< words.length;i++){
            int N = words[i].length();
            String reverseStr = reverseString(words[i]);
            for(int j = N-1; j >-1;j--){
                if(isPalindromeString(words[i],j,N-1)){
                    if(stringMap.get(reverseStr.substring(N-j,N))!=null
                            && stringMap.get(reverseStr.substring(N-j,N))!=i){
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(stringMap.get(reverseStr.substring(N-j,N)));
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }


    //判断一个字符串从i。。j上是否是回文串 只有26个字母
    public static boolean isPalindromeString(String str, int i, int j){
        char[] chars = str.toCharArray();
        while (i<j){
            if(chars[i++] != chars[j--]){
                return false;
            }
        }
        return true;
    }

    //反转字符串
    public static String reverseString(String str){
        char[] chars = str.toCharArray();
        int p1= 0;
        int p2 = chars.length-1;
        while (p1 <= p2){
            char tmp = chars[p1];
            chars[p1++] = chars[p2];
            chars[p2--] = tmp;
        }
        return new String(chars);
    }


    public static void main(String[] args) {
        String str = "eeqeergaadffdaa";
        //System.out.println(isPalindromeString(str, 0,0));
        System.out.println(reverseString(str));

    }



    //官方解

    List<String> wordsRev = new ArrayList<String>();
    Map<String, Integer> indices = new HashMap<String, Integer>();

    public List<List<Integer>> palindromePairs1(String[] words) {
        int n = words.length;
        for (String word: words) {
            wordsRev.add(new StringBuffer(word).reverse().toString());
        }
        for (int i = 0; i < n; ++i) {
            indices.put(wordsRev.get(i), i);
        }

        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int m = words[i].length();
            if (m == 0) {
                continue;
            }
            for (int j = 0; j <= m; j++) {
                if (isPalindrome(word, j, m - 1)) {
                    int leftId = findWord(word, 0, j - 1);
                    if (leftId != -1 && leftId != i) {
                        ret.add(Arrays.asList(i, leftId));
                    }
                }
                if (j != 0 && isPalindrome(word, 0, j - 1)) {
                    int rightId = findWord(word, j, m - 1);
                    if (rightId != -1 && rightId != i) {
                        ret.add(Arrays.asList(rightId, i));
                    }
                }
            }
        }
        return ret;
    }

    //是否是回文
    public boolean isPalindrome(String s, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(left + i) != s.charAt(right - i)) {
                return false;
            }
        }
        return true;
    }

    public int findWord(String s, int left, int right) {
        return indices.getOrDefault(s.substring(left, right + 1), -1);
    }

}

   