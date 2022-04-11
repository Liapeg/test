import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.*;

/**
 * TODO
 *
 * @version 2.0
 * @author lianpeng
 * @date 2021/7/3 10:20
 */
public class springApplication {

    public static void main(String[] strings) throws IOException {

        //1.先准备输入方向：
        //键盘录入：
        InputStream in = System.in;//属于字节流
        //字节流--》字符流：
        InputStreamReader isr = new InputStreamReader(in);
        //在isr外面再套一个缓冲流：
        BufferedReader br = new BufferedReader(isr);
        //2.再准备输出方向：
        //准备目标文件
        File f = new File("d:\\test.txt");
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        //3.开始动作：
        String s = br.readLine();
        while(!s.equals("exit")){
            bw.write(s);
            bw.newLine();//文件中换行
            s = br.readLine();
        }
        //4.关闭流：
        bw.close();
        br.close();




    }
}

   