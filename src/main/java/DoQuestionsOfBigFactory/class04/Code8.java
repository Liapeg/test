package DoQuestionsOfBigFactory.class04;

import sun.reflect.generics.tree.Tree;

import java.lang.reflect.Array;
import java.util.*;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/3/1 10:49
 */
public class Code8 {

    /**
     * https://leetcode.com/problems/the-skyline-problem/
     */

    public static class Node {
        int loc;
        boolean add;
        int height;

        public Node(int loc, boolean add, int height) {
            this.loc = loc;
            //true:加  false:减
            this.add = add;
            this.height = height;
        }
    }

    public static class compareNode implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.loc - o2.loc;
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {

        int N = buildings.length;
        int M = buildings[0].length;
        Node[] nodes = new Node[N *2];

        for (int i = 0; i < N; i++) {
            nodes[i*2] = new Node(buildings[i][0], true, buildings[i][2]);
            nodes[i*2+1] = new Node(buildings[i][1], false, buildings[i][2]);
        }

        Arrays.sort(nodes, new compareNode());
        //高度出现的次数
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
        //最大高度最后出现的位置
        TreeMap<Integer, Integer> maxLength = new TreeMap<Integer, Integer>();
        for(Node node : nodes){
            if(node.add){
                if(treeMap.containsKey(node.height)){
                    treeMap.put(node.height, treeMap.get(node.height)+1);
                }else {
                    treeMap.put(node.height, 1);
                }
            }else {
                if(treeMap.get(node.height) == 1){
                    treeMap.remove(node.height);
                }else {
                    treeMap.put(node.height, treeMap.get(node.height) -1);
                }
            }
            //treemap为空时，最后结束位置的高度被减为0，删除了
            if(treeMap.isEmpty()){
                maxLength.put(node.loc, 0);
            }else {
                maxLength.put(node.loc, treeMap.lastKey());
            }

        }
        List<List<Integer>> list = new ArrayList<List<Integer>>();

       for(Map.Entry<Integer, Integer> map :maxLength.entrySet()){
           int key = map.getKey();
           int value = map.getValue();
           //为空时直接放入，第一个坐标
           //只要后面的坐标不比前面高 则不增加坐标，更新高度
           //maxlength是按照loc从小到大排列的
           if(list.isEmpty() || list.get(list.size()-1).get(1) != value){
               list.add(new ArrayList<>(Arrays.asList(key, value)));
           }
       }
       return list;
    }

}

   