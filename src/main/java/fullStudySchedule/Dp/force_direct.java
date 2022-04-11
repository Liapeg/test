/*
package fullStudySchedule.Dp;

import java.util.*;

*/
/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2021/12/12 19:31
 *//*


public class Topo {

    public static class Node{
        int id;
        int X;
        int Y;

        public Node() {
        }
    }
        public static List calculateTopo(List lineTempList) {
       //初始化数据

            Set nodes= new HashSet<>();

            Set edges= new HashSet<>();

            for (int i = 0; i < lineTempList.size(); i++) {
                Node node1 = new Node();

                node1.setId(lineTempList.get(i).getUplinkNodeId().toString());

                nodes.add(node1);

                Node node2 = new Node();

                node2.setId(lineTempList.get(i).getNodeId().toString());

                nodes.add(node2);

                Edge edge = new Edge();

                edge.setId1(lineTempList.get(i).getUplinkNodeId().toString());

                edge.setId2(lineTempList.get(i).getNodeId().toString());

                edges.add(edge);

            }

            Spring sp = new Spring();

            List lNodes = new ArrayList(nodes);

            List lEdges = new ArrayList(edges);

            //1.set Node(x,y) , Random 随机分布初始节点位置

            //canvas size 1024*768

            double start_x, start_y, initSize = 40.0;

            for (Node node:lNodes) {
                start_x = 0 + 1024 * .5;

                start_y = 0 + 768 * .5;

                node.setX(start_x + initSize * (Math.random() - .5));

                node.setY(start_y + initSize * (Math.random() - .5));

            }

            List reSetNodes = sp.springLayout(lNodes,lEdges);

//4.反复2,3步 迭代300次，迭代次数越多，整张图的平衡性越好

            for(int i=0; i<300; i++){
                reSetNodes = sp.springLayout(reSetNodes,lEdges);

            }

            return reSetNodes;

        }

    }

    //递归代码

    public class Spring {
        public List springLayout(List nodes,List edges) {
        //2计算每次迭代局部区域内两两节点间的斥力所产生的单位位移(一般为正值)

            int area = 1024 * 768;

            double k = Math.sqrt(area / (double)nodes.size());

            double diffx, diffy, diff;

            Map dispx = new HashMap();

            Map dispy = new HashMap();

            int ejectfactor = 6;//斥力，点与点之间的最短距离

            for (int v = 0; v < nodes.size(); v++) {
                dispx.put(nodes.get(v).getId(), 0.0);

                dispy.put(nodes.get(v).getId(), 0.0);

                for (int u = 0; u < nodes.size(); u++) {
                    if (u != v) {
                        diffx = nodes.get(v).getX() - nodes.get(u).getX();

                        diffy = nodes.get(v).getY() - nodes.get(u).getY();

                        diff = Math.sqrt(diffx * diffx + diffy * diffy);

                        if (diff < 30)

                            ejectfactor = 5;//斥力，点与点之间的最短距离

                        if (diff > 0 && diff < 250) {
                            String id = nodes.get(v).getId();

                            dispx.put(id,dispx.get(id) + diffx / diff * k * k / diff * ejectfactor);

                            dispy.put(id,dispy.get(id) + diffy / diff * k * k / diff* ejectfactor);

                        }

                    }

                }

            }

           //3. 计算每次迭代每条边的引力对两端节点所产生的单位位移(一般为负值)

            int condensefactor = 3;//引力，意味着点与点之间的最长距离，否则会被引力牵引拉拢

            Node visnodeS = null, visnodeE = null;

            for (int e = 0; e < edges.size(); e++) {
                String eStartID = edges.get(e).getId1();

                String eEndID = edges.get(e).getId2();

                visnodeS = getNodeById(nodes,eStartID);

                visnodeE = getNodeById(nodes,eEndID);

                diffx = visnodeS.getX() - visnodeE.getX();

                diffy = visnodeS.getY() - visnodeE.getY();

                diff = Math.sqrt(diffx * diffx + diffy * diffy);

                dispx.put(eStartID,dispx.get(eStartID) - diffx * diff / k * condensefactor);

                dispy.put(eStartID,dispy.get(eStartID) - diffy * diff / k* condensefactor);

                dispx.put(eEndID,dispx.get(eEndID) + diffx * diff / k * condensefactor);

                dispy.put(eEndID,dispy.get(eEndID) + diffy * diff / k * condensefactor);

            }

            //set x,y

            int maxt = 4 ,maxty = 3;

            for (int v = 0; v < nodes.size(); v++) {
                Node node = nodes.get(v);

                Double dx = dispx.get(node.getId());

                Double dy = dispy.get(node.getId());

                int disppx = (int) Math.floor(dx);

                int disppy = (int) Math.floor(dy);

                if (disppx < -maxt)

                    disppx = -maxt;

                if (disppx > maxt)

                    disppx = maxt;

                if (disppy < -maxty)

                    disppy = -maxty;

                if (disppy > maxty)

                    disppy = maxty;

                node.setX((node.getX()+disppx));

                node.setY((node.getY()+disppy));

            }

            return nodes;

    }

    private Node getNodeById(List nodes,String id){
        for(Node node:nodes){
            if(node.getId().equals(id)){
                return node;

            }

        }

        return null;

    }
}

   */
