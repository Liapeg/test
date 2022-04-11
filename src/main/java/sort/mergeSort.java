package sort;
/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/8/26 20:40
 */
public class mergeSort {
    //guibing
    public static void main(String[] args) {
        //取中间数
        //左边有序
        //右边有序
        //merge（）

        //排序
        int[] a = {1,3,4,1,6,8,6};
        process(a,0,a.length-1);
        printArray(a);
    }
    public static void process(int[] arr, int l, int r){
        if(l == r){
            return;
        }
        int m = l + ((r-l)>>1);
        process(arr, l, m);
        process(arr, m+1, r);
        merge(arr,l,m,r);
    }

    public static void merge(int[] arr, int l, int mid ,int r){
        int [] help = new int[r-l+1];
        int index = 0;
        int lINdex = l;
        int rINdex = mid+1;
        while (lINdex <= mid&& rINdex <= r){
            help[index++] = arr[lINdex] < arr[rINdex] ? arr[lINdex++]:arr[rINdex++];
        }
        //左侧未越界，右侧越界 将左侧复制到help数组
        while(lINdex <= mid){
            help[index++] = arr[lINdex++];
        }
        //右侧未越界，左侧越界 将右侧复制到help数组
        while (rINdex <= r){
            help[index++] = arr[rINdex++];
        }
        for(int j =0;j<help.length;j++){
            arr[l+j] = help[j];
        }
    }
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

   