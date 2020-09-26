package com.edger.javademo.collections;

import java.io.Serializable;
import java.util.ArrayList;

class ArrayListDemo implements Serializable {

    private static final long serialVersionUID = -8178055902346699460L;

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("语文：99");
        list.add("数学：98");
        list.add("英语：100");
    }
}
