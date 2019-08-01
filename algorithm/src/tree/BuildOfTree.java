package tree;

import tree.model.TNode;

/**
 * @Author: suntong
 * @Date:Created in 15:49 2019/7/12
 * 二叉树的构建
 */
public class BuildOfTree {
    /**
     * 根据前序和中序构建二叉树
     * @param pre
     * @param startpre
     * @param endpre
     * @param in
     * @param startin
     * @param endin
     * @return
     */
    public TNode reBuildTree(int[] pre,int startpre,int endpre,int[] in,int startin,int endin){
        if(startpre > endpre || startin > endin){
            return null;
        }
        TNode root = new TNode(pre[startpre]);
        for(int i = startin;i <= endin;++i){
            if(in[i] == pre[startpre]){
                root.left = reBuildTree(pre,startpre + 1,i - startin + startpre,in,startin,i - 1);
                root.right = reBuildTree(pre,i - startin + startpre + 1 ,endpre,in,i+1,endin);
            }
        }
        return root;
    }
    public TNode reConstructBinaryTree(int [] pre, int [] in) {
        TNode root = reBuildTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }

    /**
     * 根据中序和后序构建树
     * @param post
     * @param postStart
     * @param postEnd
     * @param in
     * @param inStart
     * @param inEnd
     * @return
     */
    public TNode buildTree(int [] post,int postStart,int postEnd,int [] in,int inStart,int inEnd) {
        if(postStart > postEnd || inStart > inEnd){
            return null;
        }
        TNode root = new TNode(post[postEnd]);
        for(int i = inStart; i<= inEnd;++i)
            if(in[i] == post[postEnd]){
                root.left = reBuildTree(post,postStart,i - inStart + postStart,in,inStart,i - 1);
                root.right = reBuildTree(post,i,postEnd - 1,in,i+1,inEnd);
            }
        return  root;
    }
    public TNode reConstructBinaryTree2(int [] end,int [] in) {
        TNode treeNode=buildTree(end,0,end.length-1,in,0,in.length-1);
        return treeNode;
    }
}
