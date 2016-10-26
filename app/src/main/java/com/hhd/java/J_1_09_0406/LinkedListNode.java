package com.hhd.java.J_1_09_0406;

/**
 * 每个节点对象
 * Created by HHJ on 2016/5/20.
 */
public class LinkedListNode {
    private String name;//当前节点名称
    LinkedListNode next;//下一个节点（指针）

    public String getName() {
        return name;
    }

    //一个参数的构造方法
    public LinkedListNode(String name) {
        this.name = name;
    }

    //添加
    public void add(String name) {
        if (next == null) {
            next = new LinkedListNode(name);
        } else {
            this.next.add(name);//递归
        }
    }

    //删除
    public void del(String name) {
        if (this.next != null) {
            if (this.next.name.equals(name)) {
                this.next = this.next.next;
            } else {
                this.next.del(name);//递归
            }
        }
    }

    //查询节点
    public String query() {
        if (this.next != null) {
            String n = this.next.getName();
            this.next.query();//递归
            return n;
        }
        return null;
    }

}
