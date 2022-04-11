package DoQuestionsOfBigFactory;
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/2/15 9:51
 */
public class class2 {


    /**
     * 给定数组hard和money，长度都为N
     * hard[i]表示i号的难度， money[i]表示i号工作的收入
     * 给定数组ability，长度都为M，ability[j]表示j号人的能力
     * 每一号工作，都可以提供无数的岗位，难度和收入都一样
     * 但是人的能力必须>=这份工作的难度，才能上班
     * 返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
     */
    public static int[] bestIncome(int[] hard, int[] money, int[] ability){
        int N = hard.length;
        int M = ability.length;
        int[] income = new int[M];

        for(int i =0; i < M; i++){
            income[i] = process(hard, money, ability[i]);
        }
        return income;
    }

    public static int process(int[] h, int[] m, int a){
        int ans = 0;


        return ans;
    }


    /**
     *贩卖机只支持硬币支付，且收退都只支持10 ，50，100三种面额
     * 一次购买只能出一瓶可乐，且投钱和找零都遵循优先使用大钱的原则
     * 需要购买的可乐数量是m，
     * 其中手头拥有的10、50、100的数量分别为a、b、c
     * 可乐的价格是x(x是10的倍数)
     * 请计算出需要投入硬币次数？
     */
    public int coinNum(){
        int ans = 0;






        return ans;
    }

    /**
     * 已知一个消息流会不断地吐出整数 1~N，
     * 但不一定按照顺序依次吐出
     * 如果上次打印的序号为i， 那么当i+1出现时
     * 请打印 i+1 及其之后接收过的并且连续的所有数
     * 直到1~N全部接收并打印完
     * 请设计这种接收并打印的结构
     */
    public void treeStructure(){

    }


    /**
     * 现有司机N*2人，调度中心会将所有司机平分给A、B两个区域
     * 第 i 个司机去A可得收入为income[i][0]，
     * 第 i 个司机去B可得收入为income[i][1]，
     * 返回所有调度方案中能使所有司机总收入最高的方案，是多少钱
     */
    public int mostMoney(int N, int[][] income){
        int ans = 0;



        return ans;
    }
}

   