package DoQuestionsOfBigFactory.class12;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/5/22 17:13
 */
public class FindKthMinNumber {
    /**
     * https://leetcode.com/problems/median-of-two-sorted-arrays/
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数 。
     * 进阶：找出数组中第k小的数，复杂度为 O（log（min(M+N)））
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;
        if (nums1.length != 0 && nums2.length != 0) {
            if (even) {
                return (double) (getKNumOfArray(nums1, nums2, size / 2) + getKNumOfArray(nums1, nums2, size / 2 + 1)) / 2D;
            } else {
                return getKNumOfArray(nums1, nums2, size / 2 + 1);
            }
        } else if (nums1.length != 0) {
            if (even) {
                return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
            } else {
                return nums1[size / 2];
            }
        } else if (nums2.length != 0) {
            if (even) {
                return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
            } else {
                return nums2[size / 2];
            }
        } else {
            return 0;
        }
    }


    //两个等长数组的上中位数
    public int getUpMedian(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
        //两个等长的数组
        int mid1 = 0;
        int mid2 = 0;
        while (s1 < e1) {
            mid1 = (e1 + s1) >> 1;
            mid2 = (e2 + s2) >> 1;
            if (arr1[mid1] == arr2[mid2]) {
                return arr1[mid1];
            }
            //奇数
            if (((e1 - s1 + 1) & 1) == 1) {
                if (arr1[mid1] > arr2[mid2]) {
                    if (arr2[mid2] >= arr1[mid1 - 1]) {
                        return arr2[mid2];
                    }
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                } else {
                    //arr[mid1] < arr[mid2]
                    if (arr1[mid1] >= arr2[mid2 - 1]) {
                        return arr1[mid1];
                    }
                    s1 = mid1 + 1;
                    e2 = mid2 - 1;
                }

            } else {
                //偶数
                if (arr1[mid1] > arr2[mid2]) {
                    e1 = mid1;
                    s2 = mid2 +1;
                } else {
                    s1 = mid1 +1;
                    e2 = mid2 ;
                }
            }
        }
        return Math.min(arr1[s1], arr2[s2]);
    }

    /**
     * 第二问  两个有序数组中第k小的数
     */

    public int getKNumOfArray(int[] arr1, int[] arr2, int k){
        if(arr1 == null || arr2 == null || k <= 0 || k > (arr1.length + arr2.length)) {
            return 0;
        }
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int n = longs.length;
        int m = shorts.length;
        //k大于0 小于短数组长度
        if(k <= m){
            //两个数组都取前k个数 所以肯定是偶数
            return getUpMedian(longs, 0, k-1,shorts, 0, k-1);
        //k大于长数组长度 但<=总长度
        }
        if(k > n){
            //奇数时候 手动淘汰一个  长数组或短数组都可以
            //长数组中   如果长数组干过了短数组所有数  那么 他前k-s -1个数不可能   是第k小的数
            //短数组中   如果短数组干过了长数组的所有数  那么他 前k-l-1个数不可能  是第k小的数
            if(longs[k - m -1] >= shorts[m-1]){
                return longs[k-m-1];
            }
            if(shorts[k-n-1] >= longs[n-1]){
                return shorts[k-n-1];
            }
            return getUpMedian(longs,k-m, n-1, shorts, k-n, m-1);
        }
        //k大于短数组长度 但小于但<=长数组长度    m<k<=n


        if(longs[k-m-1] >= shorts[m-1]) {
            //只能淘汰长数组
            return longs[k-m-1];
        }
        return getUpMedian(longs,k-m, k-1, shorts, 0, m-1);

    }
}

   