package com.wu.im.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;



public class ByteBufTest {
    public static void main(String[] args) {
        //新创建一个ByteBuf
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9,100);

        //print("allocate ByteBuf(9, 100)", buffer);

        //write 方法改变写指针 写完之后写指针未到capacity，buffer仍然可写
        buffer.writeBytes(new byte[]{1,2,3,4});
        //print("writeBytes(1,2,3,4)",buffer);

        //写完int类型后 写指针增加4
        buffer.writeInt(12);
        //print("writeInt(12)",buffer);

        //写完之后 指针等于capacity的时候，buffer不可写
        buffer.writeBytes(new byte[]{5});
        //print("writeBytes(5)",buffer);

        //buffer不可写，则开始扩容 扩容之后capacity随即改变
        buffer.writeBytes(new byte[]{6});
        //print("writeBytes(6)",buffer);

        System.out.println("getByte(3) return:"+buffer.getByte(3));
        System.out.println("getShort(3) return:"+buffer.getShort(3));
        System.out.println("getInt(3) return:"+buffer.getInt(3));

        print("getByte():",buffer);

        //set方法不改变读写指针
        buffer.setByte(buffer.readerIndex()+1,0);
        print("setByte():",buffer);

        //read方法改变读指针
        byte[] dst = new byte[buffer.readableBytes()];
        buffer.readBytes(dst);
        print("readBytes("+dst.length+")",buffer);
    }

    public static void print(String action , ByteBuf buffer){
        System.out.println("after ===========" + action + "============");
        System.out.println("capacity()         :"+buffer.capacity());
        System.out.println("maxCapacity()      :"+buffer.maxCapacity());
        System.out.println("readIndex()        :"+buffer.readerIndex());
        System.out.println("readableBytes()    :"+buffer.readableBytes());//writeIndex-readIndex
        System.out.println("isReadable()       :"+buffer.isReadable());//false/true
        System.out.println("writerIndex()      :"+buffer.writerIndex());
        System.out.println("writableBytes()    :"+buffer.writableBytes());//capacity-writerIndex
        System.out.println("isWritable()       :"+buffer.isWritable());
        System.out.println("maxWritableBytes() :"+buffer.maxWritableBytes());
        System.out.println();
    }
}
