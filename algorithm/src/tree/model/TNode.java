package tree.model;

/**
 * @Author: suntong
 * @Date:Created in 16:40 2019/7/11
 */
public class TNode {
    public int data;
    public TNode left;
    public TNode right;
    public TNode parent;

    public TNode(int data){
        this.data = data;
    }
}
