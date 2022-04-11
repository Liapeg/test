/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/8/5 21:10
 */
public class BitMap {
    public static class bitMap{
        private long[] map;

        public bitMap(int max) {
            map = new long[(max+64)>>6];
        }

        void add(int a){
            map[a>>6] |= 1L<<(a & 63);
        }

        void delete(int a){
            map[a>>6] &= 1L<<(a&63);
        }

        boolean contains(int a){
            boolean con = false;


            return con;
        }
    }
}
   