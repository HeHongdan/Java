package com.hhd.java.J_1_09_0406;

/**
 * 参考威哥：
 * 链表数据结构
 * 用于适合频繁进行添加、插入，删除操作
 */
public class Test16 {
    public static void main(String[] args) {
        NodeManager nm = new NodeManager();
        nm.addNode("1");
        nm.addNode("2");
        nm.addNode("3");
        nm.addNode("4");
        nm.addNode("5");
        nm.printNode();
        nm.delNode("4");
        nm.printNode();
    }
}

/**
 * 节点管理类
 */
class NodeManager {
    private Node root;//根节点

    public void addNode(String name) {
        if (root == null) {
            root = new Node(name);//创建一个节点（数据）
        } else {
            root.add(name);//（否则）调用节点的添加方法（在根节点的下一个位置添加）
        }
    }

    public void delNode(String name) {
        if (root.getName().equals(name)) {//HHD：（传进来的）名称对比（根节点的）相同
            root = root.next;//HHD：把当前的设置为当前的下一个（也就是删除了当前的节点）
        } else {
            root.del(name);//HHD：（否则）调用节点的删除方法（递归遍历根节点的后面节点）
        }
    }

    public void printNode() {
        if (root != null) {
            System.out.print(root.getName() + "->");//HHD：打印根节点
            root.print();//HHD：打印根节点后面的节点（递归遍历根节点的后面节点）
            System.out.println();//HHD：换行
        }

    }


    /**
     * 每个节点对象
     */
    class Node {
        private String name;
        private Node next;//表示当前节点下一个节点

        public String getName() {
            return name;
        }

        public Node(String name) {//HHD：含一参数的构造方法
            this.name = name;
        }

        //添加节点
        public void add(String name) {
            if (this.next == null) {//HHD：下一个为空
                this.next = new Node(name);
            } else {
                this.next.add(name);//递归//HHD：下一个不为空，就再调用add（递归）跳到下一个
            }
        }

        //删除节点
        public void del(String name) {
            if (this.next != null) {//HHD：下一个不为空
                if (this.next.name.equals(name)) {//HHD：名称对比相同
                    this.next = this.next.next;//HHD：把当前的下一个设置为当前的下一个的下一个（也就是删除了当前的下一个）
                } else {
                    this.next.del(name);//递归
                }
            }
        }

        //打印所有节点
        public void print() {
            if (this.next != null) {//HHD：下一个不为空
                System.out.print(this.next.getName() + "->");//HHD：打印下一个的名称
                this.next.print();//递归
            }
        }
    }
}


