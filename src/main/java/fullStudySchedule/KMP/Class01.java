package fullStudySchedule.KMP;


/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2021/12/22 8:40
 */
public class Class01 {

    static int getIndexOf(String s1, String s2){
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        int x =0;//str1遍历的位置
        int y =0;//str2的位置
        int[] next = getNextArray(str2);

        while (x < str1.length && y < str2.length){
            if(str1[x] == str2[y]){
                x++;
                y++;
            }else if(next[y] == -1){
                x++;
            }else {
                y = next[y];
            }
        }

        return y == str2.length ? x - y:-1;
    }

    static int[] getNextArray(char[] arr) {
        if(arr.length<2){
            return new int[]{-1};
        }
        int N = arr.length;
        int[] res = new int[N];

        res[0] = -1;
        res[1] = 0;
        int i = 2;
        int cn = 0;

        while (i < N){
            if(arr[cn] == arr[i-1]){
                res[i++] = ++cn;
            }else if(cn >0 ){
                cn = res[cn];
            }else {
                res[i++] = 0;
            }
        }
        return res;
    }


    /**
     * kmp二
     */

    static int[] getNextArray2(char[] arr){

        int[] res = new int[arr.length];

        res[0] = -1;
        res[1] = 0;
        int i =2;
        int cn =0;
        while (i< res.length){
            if(arr[i-1] == arr[cn]){
                res[i++] = ++cn;
            }else if(cn > 0){
                cn = res[cn];
            }else {
                res[i++] = 0;
            }
        }

        return res;
    }


    static int getIndexOf2(String s1, String s2){

        char[] str1 = s1.toCharArray();
        char[] str2 =s2.toCharArray();

        int x=0;
        int y=0;

        int[] next = getNextArray2(str2);
        while (x < str1.length && y < str2.length){
            if(str1[x] == str2[y]){//字符相等时 则 同时往下跳
                x++;
                y++;
            }else if(next[y] == -1){//y==0 则x从下个位置开始和y的起始位置对比
                x++;
            }else {//当前位置的next位置

                y = next[y];
            }
        }

        return y == str2.length ? x-y:-1;

    }


}

   