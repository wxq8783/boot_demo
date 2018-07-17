package com.wu.netty.cpt8;

import org.jboss.marshalling.*;

import java.io.IOException;

public class MarshallingCodecFactory {

    protected static Marshaller buildMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();

        configuration.setVersion(5);

        Marshaller marshaller = marshallerFactory.createMarshaller(configuration);

        return marshaller;

    }



    protected static Unmarshaller buildUnMarshalling() throws IOException {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();

        configuration.setVersion(5);

        Unmarshaller unmarshaller = marshallerFactory.createUnmarshaller(configuration) ;

        return unmarshaller;
    }
}
