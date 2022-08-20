package DoQuestionsOfBigFactory.class18;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/27 19:31
 */
public class Manacher {

    /**
     * 解决最大回文子串问题
     */

    public static int manacher(String str){

        //处理字符串
        //1234 -> #1#2#3#4#
        char[] chars = dealString(str);
        int N = chars.length;
        //回文半径数组
        int[] res = new int[N];
        //最远右扩的点
        int C= -1;
        //最远右扩 扩不到的位置
        int R = -1;
        int max= 0;
        //计算每个点的回文半径
        for(int i =0; i< N;i++){
            //两大类情况 1、当前点未被R覆盖住 暴力扩
            //2、当前点被R覆盖了  分3种小情况 （可以优化的点）
            //1）i对于C的对应点i` 的回文半径在R`内  那么他的回文半径是 res【i`】
            //2）i对于C的对应点i` 的回文半径超过了R`外  那么他的回文半径是 R-i
            //3）i对于C的对应点i` 的回文半径压在R`上  那么他的回文半径是 最少是R-i 然后再向外扩
            //先赋最小值
            res[i] = R > i ? Math.min(res[2*C-i],R-i ):1;
            while (i-res[i] > -1 && i+res[i]<N){

                if(chars[i-res[i]] == chars[i+res[i]]){
                    res[i]++;
                }else {
                    break;
                }
            }
            //当前i推高了R
            if(res[i] +i > R){
                R= i + res[i];
                C= i;
            }
            max = Math.max(max, res[i]);
        }
        return max-1;
    }


    public static char[] dealString(String str){
        char[] chars = str.toCharArray();
        char[] res = new char[2*chars.length+1];
        for(int i=0;i <res.length;i++){
            if(i%2!=0){
                res[i] = chars[i/2];
            }else {
                res[i] = '#';
            }
        }

        return res;
    }


    public static void main(String[] args) {
        String str = "23133141234214141320";
        char[] chars =dealString(str);
        for(char c : chars){
            System.out.print(c);
        }
    }
}

   