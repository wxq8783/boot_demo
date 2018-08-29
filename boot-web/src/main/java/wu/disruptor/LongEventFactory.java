package com.wu.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 声明一个EventFactory来实例化Event对象
 */
public class LongEventFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
