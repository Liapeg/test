package fullStudySchedule.Dp;

import org.omg.CORBA.OBJ_ADAPTER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2021/12/1 19:33
 */
public class class_01 {


    /**
     *假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
     *开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
     *如果机器人来到1位置，那么下一步只能往右来到2位置；
     *如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
     *如果机器人来到中间位置，那么下一步可以往左走或者往右走；
     *规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
     *给定四个参数 N、M、K、P，返回方法数。
     */
    public static void main(String[] args) {//cur 5 rest 2 aim 4 N 6
        //System.out.println(robbit(5,2,4,6));
        System.out.println(robot(2,6,4,5));
        System.out.println(robot2(2,6,4,5));

    }
    public static int robot(int cur, int rest, int aim, int N){
        return process(cur,rest,aim,N);
    }

    public static int process(int cur, int rest , int aim, int N){
        if(rest == 0){
            return cur==aim ? 1:0;
        }

        if(cur==1){
            return process(2,rest-1,aim,N);
        }
        if(cur==N){
            return process(N-1, rest-1, aim,N);
        }

        return process(cur-1, rest-1, aim, N) + process(cur+1, rest-1, aim, N);
    }


    /**
     * 改dp
     */
    //cur 1-N
    //rest 0-k
    public static int robot2(int cur, int rest ,int aim ,int N){
        int[][] dp = new int[N+1][rest+1];


        dp[aim][0] = 1;


        for(int i = 1;i<= rest;i++){
            dp[1][i] = dp[2][i-1];
            for(int j =2;j< N;j++){
                dp[j][i] = dp[j-1][i-1]+dp[j+1][i-1];
            }
            dp[N][i] = dp[N-1][i-1];
        }

        return dp[cur][rest];

    }


    /**
     * 给定a，b两个数组。从a里取出数x，每次只能从头或者尾部取，然后接着从b里取数，
     * 第i次取第i-1下标的b中元素，每次取完之后算出一个值：c=x*b[i-1],
     * 最后要把a中的数都取完，求所有取法中，c求和后的最大值
     *
     * 测试用例  【1，100】 【2，1】   答案201
     */

    int ans = 0;
    public int testDoubleArray(int[] a, int[] b){

        if(a == null || b ==null || a.length==0 || b.length ==0 || a.length<b.length){
            return 0;
        }
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int m:a){
            list.add(m);
        }
        return process(list, b ,0);
    }

    //index 来到i的第几个位置
    //rest a中还剩几个数
    public int process(LinkedList<Integer> a, int[] b, int index){

        if( a.isEmpty()){
            return ans;
        }

        int m1 = a.removeFirst();
        int p1 = process(a, b, index +1);
        a.addFirst(m1);

        int m2 = a.removeLast();
        int p2 = process(a, b, index +1);
        a.addLast(m2);
        return Math.max(p1,p2);
    }






}

   