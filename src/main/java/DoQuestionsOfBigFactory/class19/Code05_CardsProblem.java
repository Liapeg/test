package DoQuestionsOfBigFactory.class19;

import java.util.LinkedList;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/8/3 17:45
 */
public class Code05_CardsProblem {

    /*
     * 一张扑克有3个属性，每种属性有3种值（A、B、C）
     * 比如"AAA"，第一个属性值A，第二个属性值A，第三个属性值A
     * 比如"BCA"，第一个属性值B，第二个属性值C，第三个属性值A
     * 给定一个字符串类型的数组cards[]，每一个字符串代表一张扑克
     * 从中挑选三张扑克，一个属性达标的条件是：这个属性在三张扑克中全一样，或全不一样
     * 挑选的三张扑克达标的要求是：每种属性都满足上面的条件
     * 比如："ABC"、"CBC"、"BBC"
     * 第一张第一个属性为"A"、第二张第一个属性为"C"、第三张第一个属性为"B"，全不一样
     * 第一张第二个属性为"B"、第二张第二个属性为"B"、第三张第二个属性为"B"，全一样
     * 第一张第三个属性为"C"、第二张第三个属性为"C"、第三张第三个属性为"C"，全一样
     * 每种属性都满足在三张扑克中全一样，或全不一样，所以这三张扑克达标
     * 返回在cards[]中任意挑选三张扑克，达标的方法数
     *
     * */

    public static int cardNum(String[] cards){
        //总共有27中牌面
        int[] counts = new int[27];
        int ways = 0;
        //收集每种牌面有多少种
        for(String str : cards){
            char[] chars = str.toCharArray();
            counts[(chars[0] -'A')*9 + (chars[1] -'A')*3 + chars[2]-'A']++;
        }

        //第一种情况 选一样的牌
        for(int i= 0; i< 27;i++){
            if(counts[i] >2){
                int n = counts[i];
                ways += n == 3 ? 1 : ((n-1)*(n-2)*n/6);
            }
        }

        LinkedList<Integer> path = new LinkedList<>();
        //剩余的情况 依次从牌面中选择 要或者不要
        for(int j = 0; j< 27;j++){
            if(counts[j] !=0){
                path.addLast(j);
                ways +=process(counts, j, path);
                path.pollLast();
            }
        }
        return ways;
    }

    //counts 计数数组   pre 上一个牌面选到哪了  list之前选择的牌面
    public static int process(int[] counts, int pre, LinkedList<Integer> list){
        //base-case
        int way = 0;
        if(list.size() ==3){
            way = getWays(counts, list);
        }
        //往后选择下一个牌面
        for(int next =pre+1; next < 27;next++){
            if(counts[next] !=0){
                list.addLast(next);
                way +=process(counts, next, list);
                list.pollLast();
            }
        }
        return way;
    }

    public static int getWays(int[] counts, LinkedList<Integer> path){

        int v1 = path.get(0);
        int v2 = path.get(1);
        int v3 = path.get(2);


        for(int i = 9;i>0;i/=3){
            int cur1 = v1/i;
            int cur2 = v2/i;
            int cur3 = v3/i;
            v1 %=i;//去掉最高位
            v2 %=i;
            v3 %=i;
            if((cur1 != cur2 && cur1 !=cur3 && cur2 != cur3) || (cur1== cur2 && cur1 == cur3)){
                continue;
            }else {
               return 0;
            }
        }
        v1 = path.get(0);
        v2 = path.get(1);
        v3 = path.get(2);
        return counts[v1] * counts[v2] * counts[v3];
    }


    public static int ways1(String[] cards) {
        LinkedList<String> picks = new LinkedList<>();
        return process1(cards, 0, picks);
    }

    public static int process1(String[] cards, int index, LinkedList<String> picks) {
        if (picks.size() == 3) {
            return getWays1(picks);
        }
        if (index == cards.length) {
            return 0;
        }
        int ways = process1(cards, index + 1, picks);
        picks.addLast(cards[index]);
        ways += process1(cards, index + 1, picks);
        picks.pollLast();
        return ways;
    }

    public static int getWays1(LinkedList<String> picks) {
        char[] s1 = picks.get(0).toCharArray();
        char[] s2 = picks.get(1).toCharArray();
        char[] s3 = picks.get(2).toCharArray();
        for (int i = 0; i < 3; i++) {
            if ((s1[i] != s2[i] && s1[i] != s3[i] && s2[i] != s3[i]) || (s1[i] == s2[i] && s1[i] == s3[i])) {
                continue;
            }
            return 0;
        }
        return 1;
    }



    // for test
    public static String[] generateCards(int size) {
        int n = (int) (Math.random() * size) + 3;
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            char cha0 = (char) ((int) (Math.random() * 3) + 'A');
            char cha1 = (char) ((int) (Math.random() * 3) + 'A');
            char cha2 = (char) ((int) (Math.random() * 3) + 'A');
            ans[i] = String.valueOf(cha0) + String.valueOf(cha1) + String.valueOf(cha2);
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int size = 20;
        int testTime = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            String[] arr = generateCards(size);
            int ans1 = ways1(arr);
            int ans2 = cardNum(arr);
            if (ans1 != ans2) {
                for (String str : arr) {
                    System.out.println(str);
                }
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test finish");

        long start = 0;
        long end = 0;
        String[] arr = generateCards(10000000);
        System.out.println("arr size : " + arr.length + " runtime test begin");
        start = System.currentTimeMillis();
        cardNum(arr);
        end = System.currentTimeMillis();
        System.out.println("run time : " + (end - start) + " ms");
        System.out.println("runtime test end");
    }
}

   