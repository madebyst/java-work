package tree;

import tree.model.TNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: suntong
 * @Date:Created in 18:18 2019/7/11
 * 二叉树的一些查找算法
 */
public class SearchOfTree {
    /**
     * 查找前驱节点（中序遍历的顺序）
     * @param node
     * @return
     */
    //如果该节点有左子树，那么该节点的前驱就是该节点左子树中最右边的节点；
    //如果该节点没有左子树，从当前节点开始往上寻找，直到当前节点是其父节点的右孩子，那么这个父节点，就是起始节点的前驱
    public static TNode getPioneerNode(TNode node) {
        if (node == null) {
            return node;
        }
        if (node.left != null) {
            return getMostRight(node.left);
        } else {
            TNode parent = node.parent;
            while (parent != null && node != parent.right) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
    private static TNode getMostRight(TNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * 查找后继节点（中序遍历的顺序）
     * @param node
     * @return
     */
    //如果该节点有右子树，则该节点的后继是该节点的右子树中最左边的元素；
    //如果该节点没有右子树，从当前节点开始往上寻找，直到当前的节点是其父节点的左孩子，那么这个父节点就是起始节点的后继。
    public static TNode getSuccessorNode(TNode node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return getMostLeft(node.right);
        } else {
            TNode parent = node.parent;
            while (parent != null && node != parent.left) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
    private static TNode getMostLeft(TNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 从左往右观察一棵树，打印所看到的节点
     * @param root
     */
    public static void printTree1(TNode root) {
        Queue<TNode> queue = new LinkedList<>();
        queue.offer(root);
        TNode last = root;	//记录当前层最后一个节点
        TNode nextlast = root;	//记录下一层的最后一个节点
        int flag = 1;
        while (!queue.isEmpty()) {
            TNode t = queue.peek();
            if(flag == 1){
                System.out.print(queue.poll().data + " ");
                flag = 0;
            }else{
                queue.poll();
            }

            if (t.left != null) {
                queue.offer(t.left);
                nextlast = t.left;
            }
            if (t.right != null) {
                queue.offer(t.right);
                nextlast = t.right;
            }
            // 如果当前输出结点是最右结点，那么换行
            if (last == t) {
                flag = 1;
                last = nextlast;
            }
        }
    }

    /**
     * 从右往左观察一棵树，打印所看到的节点
     * @param root
     */
    public static void printTree2(TNode root){
        Queue<TNode> queue = new LinkedList<>();
        queue.offer(root);
        TNode First = root;
        TNode nextFirst = root;
        int flag = 1;
        while(!queue.isEmpty()){
            TNode tmp = queue.peek();
            if(flag == 1){
                System.out.print(queue.poll().data + " ");
                flag = 0;
            }else{
                queue.poll();
            }

            if(tmp.right != null){
                queue.offer(tmp.right);
                nextFirst = tmp.right;
            }
            if(tmp.left != null){
                queue.offer(tmp.left);
                nextFirst = tmp.left;
            }

            if(First == tmp){
                flag = 1;
                First = nextFirst;
            }
        }
    }
    /*---------------------------------------二叉树节点的最小公共祖先（以下）--------------------------------------------------*/
    /**
     * 二叉树是二叉搜索树（递归）
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TNode commonAncestor(TNode root, TNode p, TNode q){
        if(root == null || root==p || root == q){
            return root;
        }

        if(root.data > p.data && root.data > q.data){
            return commonAncestor(root.left,p,q);
        }else if(root.data < p.data && root.data < q.data){
            return commonAncestor(root.right,p,q);
        }else{
            return root;
        }
    }

    /**
     * 二叉搜索树（非递归）
     * @param t
     * @param u
     * @param v
     * @return
     */
    public int query(TNode t, TNode u, TNode v) {
        int left = u.data;
        int right = v.data;

        //二叉查找树内，如果左结点大于右结点，不对，交换
        if (left > right) {
            int temp = left;
            left = right;
            right = temp;
        }

        while (true) {
            //如果t小于u、v，往t的右子树中查找
            if (t.data < left) {
                t = t.right;
                //如果t大于u、v，往t的左子树中查找
            } else if (t.data > right) {
                t = t.left;
            } else {
                return t.data;
            }
        }
    }

    /**
     * 普通二叉树（如果带有带有parent指针）
     */
    //问题转换为：求两个单链表的第一个相交节点

    /**
     * 普通二叉树不带有parent指针（递归）
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TNode lowestCommonAncestor(TNode root, TNode p, TNode q){
        if(root==null||root==p||root==q)
            return root;
        TNode left = lowestCommonAncestor(root.left,p,q);
        TNode right = lowestCommonAncestor(root.right,p,q);
        //left和right都不为空的话，不存在祖先关系，返回root
        if (left != null)
            return left;
        if (right != null)
            return right;
        return root;
    }

    //迭代解法:需要我们保存下由root根节点到p和q节点的路径，并且将路径存入list中，则问题转化为求两个list集合的最后一个共同元素。
    public TNode lowestCommonAncestor2(TNode root, TNode p, TNode q) {
        if(root==null || p==null || q==null)
            return null;
        List<TNode> pathp = new ArrayList<>();
        List<TNode> pathq = new ArrayList<>();
        pathp.add(root);
        pathq.add(root);

        getPath(root, p, pathp);
        getPath(root, q, pathq);

        TNode lca = null;
        for(int i=0; i<pathp.size() && i<pathq.size(); i++) {
            if(pathp.get(i) == pathq.get(i))
                lca = pathp.get(i);
            else
                break;
        }
        return lca;
    }

    private boolean getPath(TNode root, TNode n, List<TNode> path) {
        if(root==n)
            return true;

        if(root.left!=null) {
            path.add(root.left);
            if(getPath(root.left, n, path))
                return true;
            path.remove(path.size()-1);
        }

        if(root.right!=null) {
            path.add(root.right);
            if(getPath(root.right, n, path))
                return true;
            path.remove(path.size()-1);
        }
        return false;
    }
}
