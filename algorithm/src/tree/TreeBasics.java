package tree;

import tree.model.TNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: suntong
 * @Date:Created in 16:44 2019/7/11
 * 二叉树基础
 */
public class TreeBasics {
    /**
     * 前序遍历（递归）
     * @param node
     */
    public static void preOrder(TNode node){
        if(node != null){
            System.out.println(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 非递归前序遍历
     * @param root
     */
    public static void preOrderTravel(TNode root) {
        if (root == null) {
            return;
        }

        Stack<TNode> s = new Stack<TNode>();
        s.push(root);

        while (!s.isEmpty()) {
            TNode cur = s.pop();
            System.out.print(cur.data + "  ");

            if (cur.right != null) {
                s.push(cur.right);
            }

            if (cur.left != null) {
                s.push(cur.left);
            }
        }
    }

    /**
     * 中序遍历（非递归）
     * @param root
     */
    public static void inorderTravel(TNode root) {
        if (root == null) {
            return;
        }
        Stack<TNode> s = new Stack<TNode>();
        TNode cur = root;

        while (true) {
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }
            if (s.isEmpty()) {
                break;
            }
            cur = s.pop();
            System.out.print(cur.data + "  ");

            cur = cur.right;
        }
    }

    /**
     * 后序遍历（非递归）
     * 从左到右的后序 与从右到左的前序的逆序是一样的
     * @param root
     */
    public static void postorderTravel(TNode root) {
        if (root == null) {
            return;
        }

        Stack<TNode> s = new Stack<TNode>();
        Stack<TNode> out = new Stack<TNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TNode node = s.pop();
            out.push(node);

            if (node.left != null) {
                s.push(node.left);
            }

            if (node.right != null) {
                s.push(node.right);
            }
        }

        while (!out.isEmpty()) {
            System.out.print(out.pop().data + "  ");
        }
    }

    /**
     * 树的节点个数（递归）
     * @param root
     * @return
     */
    public static int getNodeNumber(TNode root) {
        if (root == null){
            return 0;
        }
        return getNodeNumber(root.left) + getNodeNumber(root.right) + 1;
    }

    //非递归
    public static int getNodeNum(TNode root) {
        if (root == null)
            return 0;

        Queue<TNode> q = new LinkedList<TNode>();
        q.offer(root);

        int count = 0;
        while (!q.isEmpty()) {
            TNode node = q.poll();
            if (node.left != null) {
                q.offer(node.left);
            }

            if (node.right != null) {
                q.offer(node.right);
            }
            count++;
        }
        return count;
    }

    /**
     * 求树的深度（递归）
     * @param root
     * @return
     */
    public static int getDepth(TNode root) {
        if (root == null) {
            return 0;
        }

        return getDepth(root.left) > getDepth(root.right) ? getDepth(root.left) + 1 : getDepth(root.right) + 1;
    }

    //非递归求深度
    public static int getDepth2(TNode root) {
        if (root == null) {
            return 0;
        }

        TNode flag = new TNode(-1);
        Queue<TNode> q = new LinkedList<TNode>();
        q.offer(root);
        q.offer(flag);

        int depth = 0;
        while (!q.isEmpty()) {
            TNode cur = q.poll();
            if (cur == flag) {
                depth++;
                if (!q.isEmpty()) {
                    q.offer(flag); // 如果下一层不为空，加flag
                }
            }

            if (cur.left != null) {
                q.offer(cur.left);
            }

            if (cur.left != null) {
                q.offer(cur.right);
            }
        }
        return depth;
    }

    /**
     * 层次遍历（非递归）
     * @param root
     */
    public static void levelTraversal(TNode root) {
        if (root == null)
            return;
        Queue<TNode> q = new LinkedList<TNode>();
        q.offer(root);

        while (!q.isEmpty()) {
            TNode cur = q.poll();
            System.out.print(cur.data + "  ");

            if (cur.left != null) {
                q.offer(cur.left);
            }

            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
    }
     //递归层次遍历
    public static void levelTraversalVisit(TNode root, int level, ArrayList<ArrayList<Integer>> ret) {
        if (root == null) {
            return;
        }
        if (level >= ret.size()) {
            ret.add(new ArrayList<Integer>());
        }

        ret.get(level).add(root.data);
        levelTraversalVisit(root.left, level + 1, ret);
        levelTraversalVisit(root.right, level + 1, ret);
    }
    public static void levelTraversalRec(TNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        levelTraversalVisit(root, 0, ret);
        System.out.println(ret);
    }

    /**
     * 二叉树第K层的节点个数
     * @param root
     * @param k
     * @return
     */
    public static int getNumLevel(TNode root, int k) {
        if (k <= 0) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }

        return getNumLevel(root.left, k - 1) + getNumLevel(root.right, k - 1);
    }

    /**
     * 求叶子结点的个数
     * 非递归解法可以利用中序非递归，每次pop一个就判断是否是叶子节点
     * @param root
     * @return
     */
    public static int getLeafNum(TNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return getLeafNum(root.left) + getLeafNum(root.right);
    }

    /**
     * 判断两颗二叉树是否相同
     * @param root1
     * @param root2
     * @return
     */
    public static boolean isSame(TNode root1, TNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        return root1.data == root2.data &&
                isSame(root1.left, root2.left) &&
                isSame(root1.right, root2.right);
    }

    /**
     * 判断是否是平衡二叉树
     * @param root
     * @return
     */
    public static boolean isAVLTree(TNode root) {
        if (root == null) {
            return true;
        }

        if (!isAVLTree(root.left) || !isAVLTree(root.right)) {
            return false;
        }

        int diff = Math.abs(getDepth(root.left) - getDepth(root.right));
        if (diff > 1) {
            return false;
        }

        return true;
    }

    /**
     * 判断是否是完全二叉树
     * 如果一个节点的左子树为null，则后面不能再有节点
     * @param root
     * @return
     */
    public static boolean isCompleteTree(TNode root) {
        if (root == null) {
            return false;
        }

        TNode levelNode = new TNode(0);
        Queue<TNode> q = new LinkedList<TNode>();

        q.offer(root);
        q.offer(levelNode);

        boolean flag = false;
        while (!q.isEmpty()) {
            TNode cur = q.poll();
            if (cur == levelNode) {
                if (!q.isEmpty()) {
                    q.offer(levelNode);
                }
                continue;
            }

            if (cur.left != null) {
                if (flag) {
                    return false;
                }
                q.offer(cur.left);
            } else {
                flag = true;
            }

            if (cur.right != null) {
                if (flag) {
                    return false;
                }
                q.offer(cur.right);
            } else {
                flag = true;
            }
        }
        return true;
    }
}


