package DoQuestionsOfBigFactory.class09;

import java.util.HashMap;
import java.util.List;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/14 17:24
 */
public class UnionFindSet {


    public class Node<V>{
        V value;

        public Node() {
        }

        public Node(V value) {
            this.value = value;
        }


    }

    public class unionFind<V>{
        //节点map
        HashMap<V, Node<V>> nodes = new HashMap<>();
        //父节点map  key为子  value为父
        HashMap<Node<V>, Node<V>> parents = new HashMap<>();
        //父节点所在集合的大小
        HashMap<Node<V>, Integer> sizeMap = new HashMap<>();

        public void unionFindSet(List<V> arr){
            for(V val : arr){
                Node node = new Node(val);
                nodes.put(val, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }


        //两个方法
        //findParent
        public Node<V> findParent(V a){

            return new Node<>();
        }
        //union
        public void union(V a, V b){

        }
    }






}

   