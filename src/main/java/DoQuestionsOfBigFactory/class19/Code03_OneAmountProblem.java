package DoQuestionsOfBigFactory.class19;

import com.mysql.cj.jdbc.PreparedStatementWrapper;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/7/28 18:21
 */

public class Code03_OneAmountProblem {

    /**
     * 给定一个正数N，比如N = 13，在纸上把所有数都列出来如下：
     * 1 2 3 4 5 6 7 8 9 10 11 12 13
     * 可以数出1这个字符出现了6次
     * 给定一个正数N，如果把1~N都列出来，
     * 返回1这个字符出现的多少次
     *
     * 数形bp
     */
    public static int oneAmountProblem(int N){

        if(N <1){
            return 0;
        }
        //位数
        int len = getLengthOfNum(N);
        //
        int tmp = getNumPow(len-1);
        int first = N/tmp;

        int firstNum = first ==1 ? N%tmp +1 : tmp;
        int otherNum = first * (len-1) * tmp/10;
        return firstNum + otherNum + oneAmountProblem(N%tmp);
    }

    public static int getLengthOfNum(int num){
        int ans = 0;
        while (num !=0){
            ans++;
            num/=10;
        }

        return ans;
    }

    public static int getNumPow(int num){
        return (int)Math.pow(10, num);
    }
    /*public static void main(String[] args) {
        //System.out.println(getLengthOfNum(10011123));
        //System.out.println(getNumPow(5));
    }*/

    public static int solution1(int num) {
        if (num < 1) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i != num + 1; i++) {
            count += get1Nums(i);
        }
        return count;
    }

    public static int get1Nums(int num) {
        int res = 0;
        while (num != 0) {
            if (num % 10 == 1) {
                res++;
            }
            num /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        int num = 221112;
        long start1 = System.currentTimeMillis();
        System.out.println(solution1(num));
        long end1 = System.currentTimeMillis();
        System.out.println("cost time: " + (end1 - start1) + " ms");

        long start2 = System.currentTimeMillis();
        System.out.println(oneAmountProblem(num));
        long end2 = System.currentTimeMillis();
        System.out.println("cost time: " + (end2 - start2) + " ms");

    }
}
