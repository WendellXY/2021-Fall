package com.ltc.treebuilder;

import java.util.ArrayList;

public class SimpleStack {
    ArrayList<String> list = new ArrayList<>();

    public SimpleStack() { }

    public String pop() {
        return list.remove(list.size() - 1);
    }

    public String peek() {
        return list.get(list.size() - 1);
    }

    public void push(String element) {
        list.add(element);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}
