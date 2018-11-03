package com.wu.im.codec;

public interface Serializer {

    /**
     * JSON序列化
     */
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * java对象转换成二进制
     * @param object
     * @return
     */
    byte[] serialize(Object object);


    <T> T deserialize(Class<T> clazz , byte[] bytes);

}
