/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/7/14 20:26
 */
public class sort {
    //bubbleSort
    public static void bubbleSort(int[] args){
        //0~n
        //0~n-1
        //0~n-2
        int n = args.length;
        for(int i = n-1;i <= n-1;i--){
            for(int j = 0;j < i;j++) {
                if (args[j] > args[j + 1]) {
                    swap(args, j, j + 1);
                }
            }

        }
       printArray(args);
    }
    //insertSort
    //0~1
    //0~2
    //0~n
    public static void insertSort(int[] args){
        int n = args.length;
        for(int i = 1;i<n;i++){
            if(args[i-1] > args[i]){
                swap(args,i-1,i);
            }
        }
        printArray(args);
    }
    public static void main(String[] args) {
        int[] zuo = new int[]{1,5,1,7677,12,7,7,9,42,6542,8};
        int[] zu = new int[]{1,3,14,51,5,6,6,9};
        bubbleSort(zuo);
        System.out.println();
        insertSort(zuo);
    }


    public static void swap(int[] args, int i, int j){
        int mid = args[i];
        args[i] = args[j];
        args[j] = mid;
    }

    public static void printArray(int[] array){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");

        }
    }
}

   