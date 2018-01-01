package cn.com.luo.rbtree.v1;
/**
 * 对x进行左旋，意味着，将“x的右孩子”设为“x的父亲节点”；
 * 即，将 x变成了一个左节点(x成了为z的左孩子)！    因此，左旋中的“左”，意味着“被旋转的节点将变成一个左节点”。
 * 旋转前:      X
 *           Y     Z
 * 旋转后     Z
 *          X
 *        Y
 *
 *    //记录以前的参数
 *    Tree<K,V> right  = p.right;
 *    Tree<K,V> left = p.left;
 *
 *    //定义新的根节点root,旋转赋值
 *    Tree<K,V> root = right;
 *    Tree<K,V> root.left = p;
 *    Tree<K,V> root.left.left = left;
 *
 *    //由于以前的right也就是旋转后的root的子节点已经被P所代替，所以将right.left赋值给root.right
 *    if(right.left != null)  root.right = right.left;
 *
 *
 *    让r能找到P以前的父节点
 *    r.parent = p.parent;
 *    让父节点能找到r节点
 *    if (p.parent == null)
 *                root = r;
 *            else if (p.parent.left == p)
 *                p.parent.left = r;
 *            else
 *                p.parent.right = r;
 *
 *            让r的左孩子可以找到P
 *            r.left = p;
 *            让P可以找到父节点r
 *            p.parent = r;
 *
 * */


public class RotateLeft<K,V> {
    private void rotateLeft_TreeMap(Tree<K,V> p) {
        if (p != null) {

            Tree<K,V> r = p.right;  //将右孩子记录为变换后的根节点r
            p.right = r.left;      //将根节点的左孩子也就是变化前p的右节点的左孩子
            //如果现在的根节点也就是以前的右节点的左孩子不为空，则将左孩子的父节点设为以前的根跟节点P也就是将左孩子
            if (r.left != null) {
                r.left.parent = p;
            }
//            r.parent = p.parent;
//            if (p.parent == null)
//                root = r;
//            else if (p.parent.left == p)
//                p.parent.left = r;
//            else
//                p.parent.right = r;
//            r.left = p;
//            p.parent = r;
        }
    }
}
