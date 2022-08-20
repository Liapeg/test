package sort;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/14 14:34
 */
public class RecursionMergeSort {


    public static void mergeSort(int[] arr){
        //归并排序----递归+合并
        //1、左右两边分别有序
        //2、左右合并，左指针、右指针谁小拷贝谁，相同先拷贝左边的 越界的直接复制
        if(arr == null || arr.length ==0){
            return;
        }
        process(arr, 0 , arr.length-1);
    }
    //保证arr在L...R上有序
    public static void process(int[] arr, int L, int R){
        if(L == R){
            return;
        }

        //l不等于r时
        int mid = L + ((R-L)>>1);
        //左边有序
        process(arr, L, mid);
        //右边有序
        process(arr, mid +1, R);
        merge(arr, L, R , mid);
    }

    public static void merge(int[] arr, int L, int R, int mid){
        int[] help = new int[R-L+1];
        int count = 0;
        //左指针
        int p1 = L;
        //右指针
        int p2 = mid +1;
        //p1、 p2都不越界
        while (p1 <= mid && p2 <= R){
            help[count++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }
        //p1越界
        while (p2 <= R){
            //合并右边
            help[count++] = arr[p2++];
        }
        //p2越界
        while (p1 <= mid){
            //合并左边
            help[count++] = arr[p1++];
        }

        for(int i = 0;i < help.length;i++){
            arr[i+L] = help[i];
        }

    }


    //for test
    public static int[] generate(int maxLen, int maxVal){
        int length = (int)(Math.random() * maxLen) +1;
         int[] arr = new int[length];
         for(int i = 0; i< length; i++){
             arr[i] = (int)(Math.random() * maxVal) +1;
         }
         return arr;
    }

    //for test
    public static void printArr(int[] arr){
        for(int in : arr){
            System.out.print(in + " ");

        }
        System.out.println();
    }
    public static void main(String[] args) {

        int times = 100;
        for(int j = 0; j< times; j++){
            int[] arr = generate(20,100);
            printArr(arr);
            mergeSort(arr);
            printArr(arr);
            System.out.println();
        }
    }

}

   