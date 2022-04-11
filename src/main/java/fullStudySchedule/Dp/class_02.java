package fullStudySchedule.Dp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2021/12/8 8:34
 */
public class class_02 {

    /**
     * 6种模型
     * 1、从左往右的尝试模型  要不要index位置的值
     * 2、范围模型 【L...R】
     * 3、样本对应模型
     * 4、业务限制模型
     * 5、
     * 6、
     */




    /**
     * 最大回文子串问题
     *
     * 范围模型
     */



    /**
     * 拓扑图      weight 1450   height 900
     *
     * 中心管理位置  x: 680,      y: 300,
     *
     *
     * 300数据源
     */

    static class Node{
        int x;
        int y;
        String name;

        public Node() {
        }

        public Node(int x, int y, String name) {
            this.x = x;
            this.y = y;
            this.name = name;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    /*static Node[][] generateDataPosition(int XLen ,int YLen, int num){


        if (num == 0) {
            return null;
        }
        int area = XLen * YLen;//区域面积

        System.out.println("区域面积："+area);
        double k = Math.ceil((double) (area / num));//计算每个格子的区域面积

        System.out.println("每个格子的面积"+k);

        DecimalFormat df = new DecimalFormat("0.00");
        double midPara = Double.valueOf(df.format((float)XLen / YLen));//格子的长宽比例

        System.out.println("长宽比例："+midPara);
        //计算每个各子的长宽

        int SY = new Double(Math.sqrt(k / midPara)).intValue();//小格子的Y
        int SX = new Double(midPara * SY).intValue();//小格子的X


        System.out.println("SY:"+SY);
        System.out.println("SX:"+SX);

        //获取每个格子坐标的x、y的长度        每个普遍的格子都依赖  左和上 格子的位置

        int y = YLen/SY;
        int x = XLen/SX;

        System.out.println("X:"+x);
        System.out.println("X:"+y);

        Node[][] dp = new Node[y + 1][x + 1];

        for (int i = 0; i <= y; i++) {
            for (int j = 0; j <= x; j++) {
                dp[i][j] = new Node(0, 0);

            }
        }

        dp[0][0] = new Node(0,0);

        //第一行
        for (int j = 1; j <= x; j++) {
            if(SX*j <=1450+SX) {
                dp[0][j] = new Node(SY *j, SY);
            }
        }
        //第一列
        for (int i = 1; i <= y; i++) {
            if(i*SY<= 900+SY){
                dp[i][0] = new Node(SX ,i *SY);
            }
        }


        //普遍位置
       *//* for (int i = 1; i < y; i++) {
            for (int j = 1; j < x; j++) {
                if(i*SY<= 1450+SX && SX*j <=900+SY) {

                    dp[i][j] = new Node(dp[i-1][j].getX() + SX, dp[i][j-1].getY() + SY);
                }
            }
        }*//*

        for (int i = 1; i <= x; i++) {
            if(i*SX <= 1450+SX) {
                for (int j = 1; j <= y; j++) {
                    if (SY * j <= 900 + SY) {
                        dp[i][j] = new Node(dp[i][j-1].getX() + SX, dp[i-1][j].getY() + SY);
                    }
                }
            }
        }

        return dp;

    }*/

    public static void main(String[] args) {



        /*Node[][] nodes = generateDataPosition(1450,900,299);

        int x= nodes.length;
        int y = nodes[0].length;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(nodes[i][j].getX()+","+nodes[i][j].getY()+" ");
            }
            System.out.println();
        }*/
        //System.out.println(new Double(getRandom(0,1450)).intValue());

        List<Node> list = new ArrayList<Node>();
        list = generteNode();

        for(int m =0;m<100;m++){
            force(list);
        }
        for(Node node : list){

            System.out.println(node.getX()+","+node.getY()+",name"+node.getName());
        }




    }


    /**
     * 产生随机数
     * @param min
     * @param max
     * @return
     */
    private static double getRandom(int min, int max) {
        return min + Math.random() * (max - min);
    }

    /**
     * 生成随机坐标
     *
     * @return
     */
   /* private Node getPosition() {

        int x = new Double(getRandom(0, 1450)).intValue();
        int y = new Double(getRandom(0, 900)).intValue();
        return new Node(x, y, i);
    }*/


    private static List<Node> generteNode(){
        List<Node> links = new ArrayList<Node>();
        for(int i =0; i< 229;i++){
            int x = new Double(getRandom(0, 1450)).intValue();
            int y = new Double(getRandom(0, 900)).intValue();
            links.add(new Node(x,y,String.valueOf(i)));
        }
        return links;
    }






    private static void force(List<Node> nodeList){
        // 需要参数
        int maxInterval = 300; // 平衡位置间距
        int maxOffset = 10; // 最大变化位移
        int minOffset = 0; // 最小变化位移
        int count = 100; // force次数
        int attenuation = 40; // 力衰减

        int N = nodeList.size();
        for(int i=0; i <N;i++ ){
            for(int j=0; j <N; j++){
                if(nodeList.get(i).name.equals(nodeList.get(j).name)){
                    return;
                }
                int x1 = nodeList.get(i).x;
                int y1 = nodeList.get(i).y;
                int x2 = nodeList.get(j).x;
                int y2 = nodeList.get(j).y;
                //计算两点距离
                int interval = new Double(Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))).intValue();

                int forceOffset = 0;
                int x3, y3;

                if (interval > maxInterval) {
                    forceOffset = (interval - maxInterval) / attenuation; // 力衰减
                    forceOffset = forceOffset > maxOffset ? maxOffset : forceOffset;
                    forceOffset = forceOffset < minOffset ? minOffset : forceOffset;
                    //forceOffset += e.childs.length / attenuation;
                    // console.log('如果大于平横间距，靠拢', interval, d.id + '-' + e.id, ~~forceOffset);
                    int k = forceOffset / interval;
                    x3 = k * (x1 - x2) + x2;
                    y3 = k * (y1 - y2) + y2;
                } else if (interval < maxInterval && interval > 0) { // 如果小于平横间距，分开
                    forceOffset = (maxInterval - interval) / attenuation; // 力衰减
                    forceOffset = forceOffset > maxOffset ? maxOffset : forceOffset;
                    forceOffset = forceOffset < minOffset ? minOffset : forceOffset;
                    //forceOffset += e.childs.length / attenuation;
                    // console.log('如果小于平横间距，分开', interval, d.id + '-' + e.id, ~~forceOffset);
                    int k = forceOffset / (interval + forceOffset);
                    x3 = (k * x1 - x2) / (k - 1);
                    y3 = (k * y1 - y2) / (k - 1);
                } else {
                    x3 = x2;
                    y3 = y2;
                }

                // 边界设置
                x3 = x3 > 1450 ? x3 -= 10 : null;
                x3 = x3 < 0 ? x3 += 10 : null;
                y3 = y3 > 900 ? y3 -= 10 : null;
                y3 = y3 < 0 ? y3 += 10 : null;
                nodeList.get(j).x = x3;
                nodeList.get(j).y = y3;
            }
        }
    }



}



   