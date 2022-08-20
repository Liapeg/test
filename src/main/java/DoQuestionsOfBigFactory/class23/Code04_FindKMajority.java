package DoQuestionsOfBigFactory.class23;

import java.util.*;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/8/18 17:11
 */
public class Code04_FindKMajority {
    /**
     * 超级水王问题
     * 扩展1：摩尔投票
     * 扩展2：给定一个正数K，返回所有出现次数>N/K的数
     */

    /**
     * 水王问题： 水王在数组中出现超过 N/2次的数 叫做水王数  事件复杂度 O（N）额外空间复杂度O（1）
     * 思路： 每次删掉两个不同的数 最后剩下的有可能是水王数
     * @param arr
     * @return
     */
    public static int  waterKingProblem(int[] arr){
        int ans = arr[0];
        int N = arr.length;
        int help = -1;
        int bloods=0;
        for(int i=0;i<N;i++){
            //没有候选人 当前数立为候选人 血量为1
            if(help == -1){
                help = arr[i];
                bloods = 1;
            }
            if(arr[i] == help){
                //当前数跟候选人一致  血量++
                bloods++;
            }else {
                //当前数跟候选人不同 当前数不要 候选人-- 血量是0了 则候选人为空
                bloods --;
                if(bloods == 0){
                    help = -1;
                }
            }
        }
        //此时候选人没有了 说明没有水王数 有数存在在去遍历是不是水王数
        if(help == -1){
            return -1;
        }
        int count = 0;
        for(int i =0;i<N;i++){
            if(arr[i] == help){
                count++;
            }
        }
        if(count > (N>>1)){
            return help;
        }else {
            return -1;
        }
    }

    public static Object[] waterLKingII(int[] arr, int k){
        //思路同水王问题
        Map<Integer,Integer> help = new HashMap<>();
        int N = arr.length;
        //添加k个候选人 如果当前数跟候选人不一致 候选人统一减一滴血
        for(int i = 0;i<N;i++){
            if(help.containsKey(arr[i])){
                help.put(arr[i], help.get(arr[i]+1));
            }else if(help.size() <=k){
                help.put(arr[i],1);
            }else {
                List<Integer> list = new ArrayList<>();
                //这个数不要了 所有的候选人-1
                for(int key : help.keySet()){
                    int num = help.get(key)-1;
                    help.put(key, num);
                    if(num ==0){
                        list.add(key);
                    }
                }
                for(int key : list){
                    help.remove(key);
                }
            }
        }
        if(help.isEmpty()){
            return null;
        }
        Set<Integer> ans = new HashSet<>();
        int M = N/k;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0;i< N;i++){
            if(help.containsKey(arr[i])){
                map.put(arr[i], map.getOrDefault(arr[i],0)+1);
            }
        }
        for(int key : map.keySet()){
            if(map.containsKey(key)){
                int count = map.get(key)+1;
                map.put(key, count);
                if(count > M){
                    ans.add(key);
                }
            }
        }

        return ans.toArray();
    }

    public static void main(String[] args) {
        int[] arr = { 2, 2, 3, 1, 1, 2, 1,3,3,7,8,4,8,9};
        //System.out.println(waterKingProblem(arr));
        Object[] objects = waterLKingII(arr, 3);
        for(Object o :objects){
            System.out.println(o);
        }
    }



}

   