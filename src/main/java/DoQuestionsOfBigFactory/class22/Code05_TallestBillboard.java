package DoQuestionsOfBigFactory.class22;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *https://leetcode.com/problems/tallest-billboard/
 * @version 2.0
 * @author lianp
 * @date 2022/8/15 20:13
 */
public class Code05_TallestBillboard {


    /**
     * 你正在安装一个广告牌，并希望它高度最大。这块广告牌将有两个钢制支架，两边各一个。每个钢支架的高度必须相等。
     *
     * 你有一堆可以焊接在一起的钢筋 rods。举个例子，如果钢筋的长度为 1、2 和 3，则可以将它们焊接在一起形成长度为 6的支架。
     *
     * 思路：子集问题
     * 在map中记录 key：两个集合的差值 value；相同差值的集合中最好那一对集合的较小的累计和 也就是基线
     */
    public int tallestBillboard(int[] rods) {


        Map<Integer, Integer> dp = new HashMap<>();
        Map<Integer,Integer> cur;
        //开始是两个空集合
        dp.put(0,0);
        for(int num : rods){
            //老map往新map更新
            //把之前的情况拷贝下来
            cur = new HashMap<>(dp);
            for(int i : cur.keySet()){
                //i 之前的差值
                //累加和
                int val = cur.get(i);
                //加到较大值的那个集合中
                dp.put(num + i, Math.max(val,dp.getOrDefault(num+i,0)));
                //加到较小的那个集合中
                int diffMin = dp.getOrDefault(Math.abs(num-i),0);
                if(num >= i){
                    dp.put(num -i, Math.max( val+i, diffMin));
                }else {
                    dp.put(i -num, Math.max(val+num , diffMin));
                }
            }
        }

        return dp.get(0);
    }

}

   