package disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/11 16:04
 */
public class DataEventFactory implements EventFactory<LongEvent> {

    /**
     * 创建实例工厂
     * @return
     */
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}

   