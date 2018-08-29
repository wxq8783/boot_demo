package com.wu.thread.cpt3.cpt3_2;

import java.util.ArrayList;
import java.util.List;

public class MyList {
    public static List list = new ArrayList();

    public static void addItme(){
        list.add("anyThing");
    }

    public static int size(){
        return list.size();
    }
}
