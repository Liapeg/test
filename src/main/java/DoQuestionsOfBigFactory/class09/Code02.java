package DoQuestionsOfBigFactory.class09;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/13 10:15
 */

import org.omg.CORBA.INTERNAL;

/**
 *  给定一个数组arr，长度为N，arr中的值不是0就是1。arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
 * 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+1栈灯的状态
 * 问题一：如果N栈灯排成一条直线,请问最少按下多少次开关？
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关只能影响0和1位置的灯
 * N-1号灯的开关只能影响N-2和N-1位置的灯
 *
 * 问题二：如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关能影响N-1、0和1位置的灯
 * N-1号灯的开关能影响N-2、N-1和0位置的灯
 * @author
 */
public class Code02 {


    //无环递归
    public static int noRingLightProblem(int[] arr){
        if(arr ==null || arr.length == 0){
            return 0;
        }
        if(arr.length == 1){
            return arr[0]^1;
        }
        if(arr.length ==2){
            return (arr[0] ^arr[1]) ==0 ? arr[0]^1 : 0;
        }

        //1位置按
        int p1 = process(arr, 2, arr[1]^1, arr[0]^1);
        if(p1 != Integer.MAX_VALUE){
            p1++;
        }
        //1位置不按
        int p2 = process(arr, 2, arr[1],arr[0]);
        return Math.min(p1,p2);
    }
    //含义 process（i，）表示到前位置前面所有的灯都亮 最少需要多少按多少次按钮
    //当前来到i-1位置 他的下一个位置是i
    //curStatus 表示当前的位置  i-1位置的状态
    //preStatus 表示当前位置前一个的位置 即 i-2位置的状态

    public static int process(int[] arr, int i , int curStatus, int preStatus){
        //base-case
        //i在越界位置，此时是最后一个位置
        //当前位置状态和前一个位置状态一致 是一个有效的选择，否则是无效的选择
        if(i == arr.length){
            return (curStatus == preStatus) ? (curStatus ^1):(Integer.MAX_VALUE);
        }

        //来到一个普遍位置
        if(preStatus == 0){
            //必须按
            int p = process(arr, i+1, arr[i]^1, curStatus^1);
            return p == Integer.MAX_VALUE ? Integer.MAX_VALUE: (p+1);
        }else {
            //不按
            return process(arr, i+1, arr[i], curStatus);
        }

    }

    public static int noLoopMinStep1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        // 不变0位置的状态
        int p1 = process1(arr, 2, arr[0], arr[1]);
        // 改变0位置的状态
        int p2 = process1(arr, 2, arr[0] ^ 1, arr[1] ^ 1);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }
        return Math.min(p1, p2);
    }

    // 当前在哪个位置上，做选择，nextIndex - 1 = cur ，当前！
    // cur - 1 preStatus
    // cur  curStatus
    // 0....cur-2  全亮的！
    public static int process1(int[] arr, int nextIndex, int preStatus, int curStatus) {
        if (nextIndex == arr.length) { // 当前来到最后一个开关的位置
            return preStatus != curStatus ? (Integer.MAX_VALUE) : (curStatus ^ 1);
        }
        // 没到最后一个按钮呢！
        // i < arr.length
        if (preStatus == 0) { // 一定要改变
            curStatus ^= 1;
            int cur = arr[nextIndex] ^ 1;
            int next = process1(arr, nextIndex + 1, curStatus, cur);
            return next == Integer.MAX_VALUE ? next : (next + 1);
        } else { // 一定不能改变
            return process1(arr, nextIndex + 1, curStatus, arr[nextIndex]);
        }
    }


    // 无环改灯问题的暴力版本
    public static int noLoopRight(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        return f1(arr, 0);
    }

    public static int f1(int[] arr, int i) {
        if (i == arr.length) {
            return valid(arr) ? 0 : Integer.MAX_VALUE;
        }
        int p1 = f1(arr, i + 1);
        change1(arr, i);
        int p2 = f1(arr, i + 1);
        change1(arr, i);
        p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
        return Math.min(p1, p2);
    }

    public static void change1(int[] arr, int i) {
        if (i == 0) {
            arr[0] ^= 1;
            arr[1] ^= 1;
        } else if (i == arr.length - 1) {
            arr[i - 1] ^= 1;
            arr[i] ^= 1;
        } else {
            arr[i - 1] ^= 1;
            arr[i] ^= 1;
            arr[i + 1] ^= 1;
        }
    }

    public static boolean valid(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 有环版本递归
     * @param
     * @return
     */
    public static int hasRingLight(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }

        if (arr.length == 3) {
            return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        //四个位置来一遍
        //0按按钮 1按
        int p1 = processHas(arr,3, arr[2]^1, arr[1], arr[0], arr[arr.length-1]^1);
        //0不变 1不变
        int p2 = processHas(arr,3, arr[2], arr[1], arr[0], arr[arr.length-1]);
        //0不变 1变
        int p3 = processHas(arr,3, arr[2]^1, arr[1]^1, arr[0]^1, arr[arr.length-1]);
        //0变 1不变
        int p4 = processHas(arr,3, arr[2], arr[1]^1, arr[0]^1, arr[arr.length-1]^1);
        p1 = p1 != Integer.MAX_VALUE ? (p1 + 2) : p1;
        p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
        p4 = p4 != Integer.MAX_VALUE ? (p4 + 1) : p4;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));

    }

    //当前来到i位置，此时在i-1位置做决定，按或者不按
    public static  int processHas(int[] arr, int i, int curStatus, int preStatus, int firstStatus, int lastStatus){
       //base-case
       if(i == arr.length){
           return (curStatus == preStatus &&  curStatus== firstStatus) ? (lastStatus^1) : Integer.MAX_VALUE;
       }

       int yesCur = 0;
       int yesPre = 0;
       int noCur = 0;
       int noPre = 0;
       int yesEnd =0;
       int noEnd = 0;
       //
        if(i < (arr.length -1)){
            yesCur = arr[i]^1;
            yesPre = curStatus^1;
            noCur = arr[i];
            noPre = curStatus;
        }else if (i == (arr.length -1)){
            //来到length-2位置，这个位置的决定会影响end位置的状态
            yesCur = lastStatus^1;
            yesPre = curStatus^1;
            noCur = lastStatus;
            noPre = curStatus;
            yesEnd = lastStatus^1;
            noEnd = lastStatus;
        }

       //来到一个普遍位置
        if(preStatus == 0){
            //必须按
            int p1 = processHas(arr, i+1, yesCur, yesPre, firstStatus, (i == arr.length -1) ? yesEnd : lastStatus);
            return p1 != Integer.MAX_VALUE ? (p1+1): p1;
        }else {
            return processHas(arr, i+1, noCur, noPre, firstStatus, (i == arr.length -1) ? noEnd : lastStatus);
        }
    }



    // for test
    public static int loopMinStep1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        if (arr.length == 3) {
            return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        // 0不变，1不变
        int p1 = process2(arr, 3, arr[1], arr[2], arr[arr.length - 1], arr[0]);
        // 0改变，1不变
        int p2 = process2(arr, 3, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
        // 0不变，1改变
        int p3 = process2(arr, 3, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
        // 0改变，1改变
        int p4 = process2(arr, 3, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
        p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
        p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
        p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }


    // 下一个位置是，nextIndex
    // 当前位置是，nextIndex - 1 -> curIndex
    // 上一个位置是, nextIndex - 2 -> preIndex   preStatus
    // 当前位置是，nextIndex - 1, curStatus
    // endStatus, N-1位置的状态
    // firstStatus, 0位置的状态
    // 返回，让所有灯都亮，至少按下几次按钮

    // 当前来到的位置(nextIndex - 1)，一定不能是1！至少从2开始
    // nextIndex >= 3
    public static int process2(int[] arr, int nextIndex, int preStatus, int curStatus, int endStatus, int firstStatus) {

        if (nextIndex == arr.length) { // 最后一按钮！
            return (endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (endStatus ^ 1);
        }
        // 当前位置，nextIndex - 1
        // 当前的状态，叫curStatus
        // 如果不按下按钮，下一步的preStatus, curStatus
        // 如果按下按钮，下一步的preStatus, curStatus ^ 1
        // 如果不按下按钮，下一步的curStatus, arr[nextIndex]
        // 如果按下按钮，下一步的curStatus, arr[nextIndex] ^ 1
        int noNextPreStatus = 0;
        int yesNextPreStatus = 0;
        int noNextCurStatus =0;
        int yesNextCurStatus = 0;
        int noEndStatus = 0;
        int yesEndStatus = 0;
        if(nextIndex < arr.length - 1) {// 当前没来到N-2位置
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = arr[nextIndex];
            yesNextCurStatus = arr[nextIndex] ^ 1;
        } else if(nextIndex == arr.length - 1) {// 当前来到的就是N-2位置
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = endStatus;
            yesNextCurStatus = endStatus ^ 1;
            noEndStatus = endStatus;
            yesEndStatus = endStatus ^ 1;
        }
        if(preStatus == 0) {
            int next = process2(arr, nextIndex + 1, yesNextPreStatus, yesNextCurStatus,
                    nextIndex == arr.length - 1 ? yesEndStatus : endStatus, firstStatus);
            return next == Integer.MAX_VALUE ? next : (next + 1);
        }else {
            return process2(arr, nextIndex + 1, noNextPreStatus, noNextCurStatus,
                    nextIndex == arr.length - 1 ? noEndStatus : endStatus, firstStatus);

        }
//		int curStay = (nextIndex == arr.length - 1) ? endStatus : arr[nextIndex];
//		int curChange = (nextIndex == arr.length - 1) ? (endStatus ^ 1) : (arr[nextIndex] ^ 1);
//		int endChange = (nextIndex == arr.length - 1) ? curChange : endStatus;
//		if (preStatus == 0) {
//			int next = process2(arr, nextIndex + 1, curStatus ^ 1, curChange, endChange, firstStatus);
//			return next == Integer.MAX_VALUE ? next : (next + 1);
//		} else {
//			return process2(arr, nextIndex + 1, curStatus, curStay, endStatus, firstStatus);
//		}
    }



    //for test
    // 生成长度为len的随机数组，值只有0和1两种值
    public static int[] randomArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 2);
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("如果没有任何Oops打印，说明所有方法都正确");
        System.out.println("test begin");
        int testTime = 20;
        int lenMax = 12;
        /*for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * lenMax);
            int[] arr = randomArray(len);
            int ans1 = noLoopRight(arr);
            int ans2 = noRingLightProblem(arr);
            int ans3 = noLoopMinStep1(arr);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println(ans1);
                System.out.println(ans3);
                System.out.println(ans2);
                System.out.println("1 Oops!");
                break;
            }
        }*/
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * lenMax);
            int[] arr = randomArray(len);
            int ans1 = hasRingLight(arr);
            int ans2 = loopMinStep1(arr);
            //int ans3 = loopMinStep2(arr);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("2 Oops!");
                break;
            }
        }
        System.out.println("test end");

        /*int len = 100000000;
        System.out.println("性能测试");
        System.out.println("数组大小：" + len);
        int[] arr = randomArray(len);
        long start = 0;
        long end = 0;
        start = System.currentTimeMillis();
        noLoopMinStep2(arr);
        end = System.currentTimeMillis();
        System.out.println("noLoopMinStep2 run time: " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        loopMinStep2(arr);
        end = System.currentTimeMillis();
        System.out.println("loopMinStep2 run time: " + (end - start) + "(ms)");*/
    }


}

   