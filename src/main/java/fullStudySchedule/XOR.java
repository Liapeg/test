package fullStudySchedule;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 异或
 */
public class XOR {
    //无进位相加

    public static void main(String[] args) {

        int[] a = {1,2,2,1,3,7,8,8,7,1,3,3,3,7,8,8,7,5,5,2,2,5,5};

        int[] b = {1,2,2,1,3,7,8,8,7,1,3,3,3,7,8,8,7,5,5,2,2,5,5,1,11,11,11,11,323};
        //System.out.println(findOneNumber(a));
        //findTwoNumber(a);
        //KM(b,1,4);

        //System.out.println(randomInt(2));
        int [] c= randomArray(10,5,3,5);
        printArr(c);
        System.out.println();
        KM(c,3,5);

    }

    /**
     * 交换数组中两个位置u的数   必须保证两个位置不同
     */
    static public void swap(int[] arr , int i, int j){
        arr[i] = arr[i]^arr[j];
        arr[j] = arr[i]^arr[j];
        arr[i] = arr[i]^arr[j];
    }

    /**
     * 一个数组中，一个数出现了奇数次。其他数都出现了偶数次，找出这个数
     */
    static public int findOneNumber(int[] arr){
        int ans = 0;
        for(int i = 0;i<arr.length;i++){
            ans ^= arr[i];
        }
        return ans;
    }

    /**
     * 取一个数二进制最右侧的1
     */
    static public int findOne(int a){
        return a & (~a+1);
    }

    /**
     * 一个数组中，两个数a b出现了奇数次，其他数出现了偶数次，找出这两个数
     *
     *
     */
    static public void findTwoNumber(int[] arr){
        int oneOnly = 0;
        for(int i = 0;i<arr.length;i++){
            oneOnly ^= arr[i];
        }
        System.out.println(oneOnly);
        int oneOnlys = 0;
        int one = findOne(oneOnly);
        //得到a^b的值 取这个值最右侧的1 此时a或b该位置必有一个为1
        for(int j =0;j<arr.length;j++){
            if ((arr[j] & one)!=0){
                oneOnlys ^= arr[j];
            }
        }
        System.out.println(oneOnlys +"---"+(oneOnly^oneOnlys));
    }


    /**
     * 在一个数组中，一个数出现了k次，其他数都出现了m次，输出这个数  其中m>0 k<m 固定额外空间复杂度
     *
     *
     *方法：申请一个固定长度32的数组，将数组中每个数二进制中的1加到申请的数组对应i的位置，
     * 32位数组中每个位置的数字%m 如果等于0 则第一个数在该位置上是1
     *
     *
     */
    static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    static public void KM(int[] arr , int k, int m){
        if(map.size()==0){
            createMap(map);
        }
        int[] midArr = new int[32];
        for (int i=0;i<arr.length;i++){
            while (arr[i] != 0){
                int one = arr[i] &(-arr[i]);
                midArr[map.get(one)]++;
                arr[i] ^=one;

            }
        }

        //System.out.println(midArr.toString());
        int ans = 0;
        for(int l=0;l<32;l++){
            if(midArr[l]%m != 0){
                ans |=(1<<l);
            }
        }

        System.out.println(ans);
    }

    static public void createMap(HashMap<Integer, Integer> map){
        int  value = 1;
        for(int i=0;i<32;i++){
            map.put(value,i);
            value <<=1;
        }

    }


    /**
     * 对数器
     * 实现随机长度数组 随机最大值 其中一个数出现了k次 其他数都出现了m次，m>0 其他数的种类为l
     *
     */

    static public int[] randomArray(int maxValue,int kinds,int k, int m){
        int kNum= randomInt(maxValue);//真命天子
        int kNumTims = Math.random() < 0.5 ? k:(int) (Math.random()*(m-1))+1;//真命天子出现的次数
        int kings = (int)(Math.random()*kinds) +2;//保证数字种类最少为2
        int[] arr = new int[kNumTims+(kinds-1)*m];//随机数组的长度
        int index= 0;
        for (;index<kNumTims;index++){
            arr[index]=kNum;
        }
        kinds--;
        HashSet set = new HashSet();
        set.add(kNum);
        while (kinds!=0){
            int mNum = 0;
            do {
                mNum = randomInt(maxValue);
            }while (set.contains(mNum));
            kinds--;
            set.add(mNum);
            for(int i=0;i<m;i++){
                arr[index++] = mNum;
            }

        }

        //随机arr中的数
        for(int j=0;j<arr.length;j++){
            //调换j和cur位置的数
            int cur = (int)Math.random()*arr.length;
            int tmp=arr[j];
            arr[j] = arr[cur];
            arr[cur] = tmp;


        }


        return arr;
    }


    /**
     * 产生一个range范围的随机数
     * @param range
     * @return
     */
    static public int randomInt(int range){
        return ((int) (Math.random()*range+1) - (int) (Math.random()*range+1));
    }

    /**
     * 打印数组
     */
    static public void printArr(int[] arr){
        for(int n=0;n<arr.length;n++){
            System.out.print(arr[n]+"  ");
        }
    }


}

   