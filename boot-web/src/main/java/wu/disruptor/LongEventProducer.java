package com.wu.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * 生成事件的源
 */
public class LongEventProducer {
    private RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * onData 用来发布事件，每调用一次就发布一次
     * 它的参数会提供事件 传递给 消费者
     * @param bb
     */
    public void onData(ByteBuffer bb){
        //可以把RingBuffer 看出一个事件队列，那么 next()就是下一个事件槽
        Long sequence = ringBuffer.next();

        try {
            LongEvent event = ringBuffer.get(sequence);
            event.setLongValue(bb.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }

    }
}
