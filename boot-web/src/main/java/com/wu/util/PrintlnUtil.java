package com.wu.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.MessageFormat;

public class PrintlnUtil {

    private  static final Log logger = LogFactory.getLog(PrintlnUtil.class);

    public static void printTack(String prefix){
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();

        if(sts == null){
            logger.info("invalid stack");
            return;
        }

        StringBuilder sbd = new StringBuilder();

        for(StackTraceElement element : sts){
            if(sbd.length() > 0){
                sbd.append("<-");
                sbd.append(System.getProperty("line.separator"));
            }
            sbd.append(MessageFormat.format("{0}.{1}() {2}",element.getClassName(),element.getMethodName(),element.getLineNumber()));
        }
        logger.info(prefix
                + "\n************************************************************\n"
                + sbd.toString()
                + "\n************************************************************");
    }

}
