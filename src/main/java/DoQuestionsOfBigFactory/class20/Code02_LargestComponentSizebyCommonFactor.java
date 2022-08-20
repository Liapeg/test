package DoQuestionsOfBigFactory.class20;

import DoQuestionsOfBigFactory.class13.Code04_hitBricks;
import sun.security.mscapi.CPublicKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/8/5 10:14
 */
public class Code02_LargestComponentSizebyCommonFactor {

    /**
     * https://leetcode.com/problems/largest-component-size-by-common-factor/
     * 给定一个由不同正整数的组成的非空数组nums ，考虑下面的图：
     *
     * 有nums.length个节点，按从nums[0]到nums[nums.length - 1]标记；
     * 只有当nums[i]和nums[j]共用一个大于 1 的公因数时，nums[i]和nums[j]之间才有一条边。
     * 返回 图中最大连通组件的大小 。
     * 思路 ：并查集
     *
     *算一个数的非1因子时，成对的算 所以小于 根号N的所有因子
     * map中记住每个数的因子 和他所在的位置
     *
     * 并查集中将相同因子的位置uion在一起
     * 最后返回最大的size
     */

    public int largestComponentSize(int[] nums) {
        int N = nums.length;;
        FindUion findUion = new FindUion(N);
        //因子的map
        Map<Integer, Integer> map = new HashMap<>();
        //主函数怎么调
        for(int i = 0; i< N;i++){
            //根号N
            int count = (int)Math.sqrt(nums[i]);
            for(int j =1;j <= count;j++){
                if(nums[i] % j==0){
                    if(j != 1){
                        if(!map.containsKey(j)){
                            map.put(j,nums[i]);
                        }else {
                            findUion.uion(map.get(j), nums[i]);
                        }
                    }
                    int other = nums[i] /j;
                    if(other != 1){
                        if(!map.containsKey(other)){
                            map.put(other,nums[i]);
                        }else {
                            findUion.uion(map.get(other), nums[i]);
                        }
                    }
                }
            }
        }



        return findUion.maxSize();
    }

    public class FindUion{

        int[] parents;
        int[] sizes;
        int[] help;

        public FindUion(int N) {
            parents = new int[N];
            sizes = new int[N];
            for(int i=0; i< N;i++){
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        private int findParent(int i){
            int h = 0;
            //依次向上遍历 找到他的父节点
            while(i != parents[i]){
                help[h++] = i;
                i = parents[i];
            }

            //沿路找到的父都设置为1
            for(h--;h>=0;h--){
                parents[help[h]] = i;
            }

            return i;
        }

        //把两个节点连在一起
        public void uion(int i , int j){
            int f1 = findParent(i);
            int f2 = findParent(j);
            if(f1 != f2){
                //父不相等时 连在一起
                int big = sizes[f1] >= sizes[f2] ? f1 :f2;

                int small = big == f1 ? f2 : f1;
                parents[small] = big;
                sizes[big] = sizes[f1] + sizes[f2];
            }
        }
        //
        public int maxSize(){
            int ans = 0;
            for(int num : sizes){
                ans = Math.max(num, ans);
            }

            return ans;
        }
    }






}

   