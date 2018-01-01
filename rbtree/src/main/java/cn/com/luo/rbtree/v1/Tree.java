package cn.com.luo.rbtree.v1;

import java.util.TreeMap;

public class Tree<K,V> {
    private static final boolean RED   = false;
    private static final boolean BLACK = true;
    //键
    K key;
    //值
    V value;
    //左孩子
    Tree<K,V> left = null;
    //右孩子
    Tree<K,V> right = null;
    //父亲
    Tree<K,V> parent;
    //颜色
    boolean color = BLACK;

    Tree(K key, V value, Tree<K,V> parent) {
        this.key = key;
        this.value = value;
        this.parent = parent;
    }

    public K getKey() {
        return key;
    }


    public V getValue() {
        return value;
    }


    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        TreeMap treeMap  = new TreeMap();
        return oldValue;
    }
}
