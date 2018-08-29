package com.wu.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * 事件消费者，也就是一个事件处理器
 */
public class LongEventHandle implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("--------------打印事件："+longEvent.getLongValue());
    }
}
