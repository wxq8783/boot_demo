package com.wu.netty.cpt8;

import org.jboss.marshalling.Marshaller;

import java.io.IOException;


public class MarshallingEncoder {
    private static final byte[]  LENGTH_PLACEHOLDER = new byte[4];

    Marshaller marshaller;

    public MarshallingEncoder() throws IOException {
        this.marshaller = MarshallingCodecFactory.buildMarshalling();
    }


}
