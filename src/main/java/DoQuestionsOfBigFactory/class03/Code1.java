package DoQuestionsOfBigFactory.class03;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/21 16:38
 */
public class Code1 {

    public static int LongestSubstringWithoutRepeatingCharacters(String s){
        if(s == null || "".equals(s)){
            return 0;
        }
        int ans = 1;
        char[] chars = s.toCharArray();
        int N = chars.length;
        //字母组合的最大ascii码
        //上次出现的位置
        int[] location = new int[256];
        for(int l :location){
            l = -1;
        }
        //前一个字符的最大无重复长度
        int pre = 1;
        location[chars[0]] = 0;
        for(int i =1; i < N; i++){
            pre = Math.min(i - location[chars[i]], pre +1);
            location[chars[i]] = i;
            ans = Math.max(ans, pre);

        }
        return ans;
    }
}

   