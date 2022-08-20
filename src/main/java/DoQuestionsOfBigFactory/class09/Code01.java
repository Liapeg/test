package DoQuestionsOfBigFactory.class09;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/10 11:51
 */
public class Code01 {


    /**
     * 定义何为step sum？
     * 比如680，680 + 68 + 6 = 754，680的step sum叫754
     * 给定一个正数num，判断它是不是某个数的step sum
     */
    public static boolean isStepSum(int num){
        //1、一个数的步骤和小于等于这个数
        //2、比他小的数的步骤和也小于他
        boolean ans = false;
        //二分确定某个数的步骤和和当前步骤和
        int left = 0;
        int right = num;
        while (left <= right){
            int mid = left + ((right -left)>>1);
            int curStep = computeStep(mid);
            if(curStep > num){
                right = mid -1;
            }else if (curStep < num){
                left = mid +1;
            }else {
                return true;
            }
        }
        return ans;


    }
    public static int computeStep(int num){
        int sum = 0;
        while (num !=0){
            sum += num;
            num /=10;
        }
        return sum;
    }
    // for test
    public static HashMap<Integer, Integer> generateStepSumNumberMap(int numMax) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= numMax; i++) {
            map.put(stepSum(i), i);
        }
        return map;
    }
    public static int stepSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num;
            num /= 10;
        }
        return sum;
    }

    // for test
    public static void main(String[] args) {
        int max = 1000;
        int maxStepSum = stepSum(max);
        HashMap<Integer, Integer> ans = generateStepSumNumberMap(max);
        System.out.println("测试开始");
        for (int i = 0; i <= maxStepSum; i++) {
            if (isStepSum(i) ^ ans.containsKey(i)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }

}

   