package DoQuestionsOfBigFactory.class04;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/28 10:37
 */
public class Code6 {
    /**
     * 生成长度为size的达标数组，什么叫达标？
     * 达标：对于任意的 i<k<j，满足 [i] + [j] != [k] * 2
     * 给定一个正数size，返回长度为size的达标数组
     */
    public static int[] makeNo(int size){
        if(size == 1){
            return new int[]{1};
        }

        int halfSize = (size +1)/2;
        int[] ans = new int[size];

        int[] base = makeNo(halfSize);
        int index = 0;
        for(;index < halfSize; index++){
            ans[index] = base[index] * 2;
        }
        //halfSize * 2可能是大于size的
        //所以，循环从上一步的index。。。size
        //
        for(int i = 0;index < size; i++,index++){
            ans[index] = base[i] *2 -1;
        }

        return ans;
    }

    // 检验函数
    public static boolean isValid(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                for (int j = k + 1; j < N; j++) {
                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int N = 1; N < 1000; N++) {
            int[] arr = makeNo(N);
            if (!isValid(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
        //System.out.println(isValid(makeNo(1042)));
        //System.out.println(isValid(makeNo(2981)));
    }

}

   