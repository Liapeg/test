package DoQuestionsOfBigFactory.class07;

import sun.reflect.generics.tree.Tree;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/8 16:14
 */
public class Code05 {
    public static class Node {
        public boolean end;
        public Node[] nexts;

        public Node() {
            end = false;
            nexts = new Node[26];
        }
    }
    /**
     * 假设所有字符都是小写字母
     * 大字符串是str
     * arr是去重的单词表, 每个单词都不是空字符串且可以使用任意次.
     * 使用arr中的单词有多少种拼接str的方式. 返回方法数.
     */

    //递归版本
    public static int splitWays(String str, String[] arr){

        Set set = new HashSet();
        for(String s : arr){
            set.add(s);
        }
        int N = str.length();
        return process(str,set, 0 ,N);
    }

    //i...N-1的字符分解有多少种方法 返回方法数
    //i表示当前来到的字符    O（N^3）
    public static int process(String str, Set arr, int i, int N){
        //base-case  没有字符可以分解 。。来到最后位置。。。。前面所作的决定是一种方法
        if(i == N){
            return 1;
        }
        int ways = 0;
        //i往后的字符依次遍历
        for(int end = i; end < N;end++){
            String cur = str.substring(i,end+1);
            //如果已i开头，i。。。。N任意位置结尾的一个字符包含在arr，则在这种分割中找到了一种方法，从下个字符开始交给process去处理
            if(arr.contains(cur)){
                ways += process(str, arr, end +1, N);
            }
        }
        return ways;
    }

    /**
     * 暴力递归改    动态规划版本   借助前缀树
     */

    static class TreeNode{
        boolean end;
        TreeNode[] nexts;

        //默认不是尾巴
        //26个小写字母 所以26个分支！！！
        public TreeNode() {
            end = false;
            nexts = new TreeNode[26];
        }
    }
    public static int ways(String str, String[] arr){
        if(str == null || str.length() == 0){
            return 0;
        }
        char[] chars = str.toCharArray();
        int N = chars.length;
        //一个可变参数
        int[] dp = new int[N+1];
        //组装前缀树
        TreeNode root = new TreeNode();
        for(int i = 0; i< arr.length ;i++){
            TreeNode cur = root;
            char[] srrChar = arr[i].toCharArray();
            for(int j = 0; j< srrChar.length;j++){
                int path = srrChar[j] - 'a';
                if(cur.nexts[path] == null){
                    cur.nexts[path] = new TreeNode();
                }
                cur = cur.nexts[path];
            }
            //###############################出现的问题 ， 设置该点是结尾点。。。。。。。。。########################################
            cur.end = true;
        }
        //dp
        dp[N] = 1;
        for (int i = N-1; i >= 0; i--) {
            TreeNode indexNode = root;
            for(int end = i; end < N;end++){
                int path = chars[end] - 'a';
                if(indexNode.nexts[path] == null){
                    //没有到这个的路径
                    break;
                }
                indexNode = indexNode.nexts[path];
                if (indexNode.end) {
                    dp[i] += dp[end +1];
                }
            }

        }

        return dp[0];
    }



    public static int ways4(String s, String[] arr) {
        if (s == null || s.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        Node root = new Node();
        for (String str : arr) {
            char[] chs = str.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
            }
            node.end = true;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            Node cur = root;
            for (int end = i; end < N; end++) {
                int path = str[end] - 'a';
                if (cur.nexts[path] == null) {
                    break;
                }
                cur = cur.nexts[path];
                if (cur.end) {
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }



    // 以下的逻辑都是为了测试
    public static class RandomSample {
        public String str;
        public String[] arr;

        public RandomSample(String s, String[] a) {
            str = s;
            arr = a;
        }
    }

    // 随机样本产生器
    public static RandomSample generateRandomSample(char[] candidates, int num, int len, int joint) {
        String[] seeds = randomSeeds(candidates, num, len);
        HashSet<String> set = new HashSet<>();
        for (String str : seeds) {
            set.add(str);
        }
        String[] arr = new String[set.size()];
        int index = 0;
        for (String str : set) {
            arr[index++] = str;
        }
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < joint; i++) {
            all.append(arr[(int) (Math.random() * arr.length)]);
        }
        return new RandomSample(all.toString(), arr);
    }

    public static String[] randomSeeds(char[] candidates, int num, int len) {
        String[] arr = new String[(int) (Math.random() * num) + 1];
        for (int i = 0; i < arr.length; i++) {
            char[] str = new char[(int) (Math.random() * len) + 1];
            for (int j = 0; j < str.length; j++) {
                str[j] = candidates[(int) (Math.random() * candidates.length)];
            }
            arr[i] = String.valueOf(str);
        }
        return arr;
    }

    public static void main(String[] args) {
        char[] candidates = { 'a', 'b' };
        int num = 20;
        int len = 4;
        int joint = 5;
        int testTimes = 30000;
        boolean testResult = true;
        for (int i = 0; i < testTimes; i++) {
            RandomSample sample = generateRandomSample(candidates, num, len, joint);
            int ans1 = ways(sample.str, sample.arr);
            int ans2 = splitWays(sample.str, sample.arr);
            //int ans3 = ways3(sample.str, sample.arr);
            int ans4 = ways4(sample.str, sample.arr);
            if (ans1 != ans2 || ans1 != ans4 || ans2 != ans4) {
                testResult = false;
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans4);
                break;
            }
        }
        System.out.println(testTimes + "次随机测试是否通过：" + testResult);
    }


}

   