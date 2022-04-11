package fullStudySchedule.Heap;
/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/9/27 19:39
 * 堆和堆排序
 */

public class HeapAndHeadSort {


    /**
     * 数组形式   大根堆 小根堆
     */


    //堆中index位置的元素 如果比自己的父亲大 依次往上移动
    public void heapInsert(int[] arr, int index){
        while(arr[index] > arr[(index-1)/2]){
            swap(arr, index, (index-1)/2);
            index = (index-1)/2;
        }
    }

    //从index的位置开始  不断下沉
    //停的时候     没有孩子/没有孩子比他大
    public void heapify(int[] arr, int index, int heapSize){
        int left = index*2 +1;//
        while (left < heapSize){//没有孩子
            int largest = left + 1 < heapSize && arr[left+1] > arr[left] ? left+1 : left;//左孩子大还是右孩子大
            largest = arr[largest] > arr[index] ? largest:index;//较大者和父亲比较
            if(largest == index){
                break;
            }
            swap(arr, index, largest);
            index= largest;
            left = index*2 + 1;

        }
    }

    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    //最大线段和问题

    //手写堆
    public static class heapGreater{

    }
}
