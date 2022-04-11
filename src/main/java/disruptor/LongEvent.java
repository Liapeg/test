package disruptor;

import lombok.Data;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/11 15:58
 * 生产端
 */

/**
 * 消息体
 */
@Data
public class LongEvent{

    String name;

    public LongEvent() {
    }

    public LongEvent(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "name='" + name + '\'' +
                '}';
    }
}

   