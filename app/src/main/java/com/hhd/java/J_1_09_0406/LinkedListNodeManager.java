package com.hhd.java.J_1_09_0406;

/**
 * 节点管理类
 * Created by HHJ on 2016/5/20.
 */
public class LinkedListNodeManager {

    private LinkedListNode root;//第一个（根）节点

    //添加节点
    public void addNode(String name) {
        if (root == null) {
            root = new LinkedListNode(name);
        } else {
            root.add(name);
        }
    }

    //删除节点
    public void delNode(String name) {
        if (root.getName().equals(name)) {
            root = root.next;
        } else {
            root.del(name);
        }
    }

    //查询所有节点
    public String queryAll() {
        if (root != null) {
            String n = root.getName();
            root.query();
            return n;
        }
        return null;
    }
}
