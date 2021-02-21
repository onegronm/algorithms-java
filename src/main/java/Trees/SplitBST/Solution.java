package Trees.SplitBST;

import java.util.*;
import static org.junit.Assert.*;
/**
 * Difficulty: hard
 * Companies: Salesforce
 * https://leetcode.com/problems/split-bst/
 */

public class Solution {

    public TreeNode[] splitBST(TreeNode root, int V){

        if (root == null){
            return new TreeNode[] { null, null };
        }

        TreeNode[] result = new TreeNode[2];

        // find target node
        TreeNode target = getTarget(root,V);
        // copy target and its children into a new tree
        TreeNode treeCopy = copy(target);
        // remove the target from the original tree
        if (!deleteNode(root, V)){
            root = null;
        }

        // find node not belonging in tree copy and remove it
        TreeNode notBelonging = findNodeGreaterThanRoot(treeCopy);
        if (notBelonging != null){

            TreeNode copyNotBelonging = copy(notBelonging);
            deleteNode(treeCopy, notBelonging.val);

            // insert not belonging into original tree
            insertNode(root, copyNotBelonging);
        }

        result[0] = treeCopy;
        result[1] = root;

        return result;
    }

    public boolean deleteNode(TreeNode root, int v){
        TreeNode[] nodeToDelete = findNode(null, root, v);

        if (nodeToDelete != null){
            TreeNode parent = nodeToDelete[0];
            if (parent != null){
                if(parent.left != null && parent.left.equals(nodeToDelete[1])){
                    parent.left = null;
                    return true;
                }
                else if (parent.right != null && parent.right.equals(nodeToDelete[1])) {
                    parent.right = null;
                    return true;
                }
            }
        }
        return false;
    }

    public void insertNode(TreeNode tree, TreeNode a){
        if (tree != null){
            if (tree.left != null && a.val <= tree.val){
                insertNode(tree.left, a);
            }
            if (tree.right != null && a.val > tree.val){
                insertNode(tree.right, a);
            }
            if (a.val <= tree.val) tree.left = a;
            else tree.right = a;
        }
    }

    public TreeNode findNodeGreaterThanRoot(TreeNode root){
        if (root != null){
            if (root.right != null && root.right.val > root.val){
                return root.right;
            }
        }
        return null;
    }

    public TreeNode copy(TreeNode root){
        TreeNode copy = null;
        if (root != null) {
            copy = new TreeNode(root.val);
            if (root.left != null){
                copy.left = copy(root.left);
            }
            if (root.right != null){
                copy.right = copy(root.right);
            }
        }
        return copy;
    }

    public TreeNode getLessThanNode(TreeNode root, int v) {
        if (root == null) return null;
        if (root.val < v) return root;

        TreeNode target;
        if (root.val <= v){
            // check right
            target = findNode(root, root.right, v)[1];
        }
        else{
            // check left
            target = findNode(root, root.left, v)[1];
        }

        return target;
    }

    public TreeNode[] findNode(TreeNode parent, TreeNode root, int v) {
        TreeNode[] result = new TreeNode[2];
        if (root == null) return null;
        if (root.val == v) {
            result[0] = parent;
            result[1] = root;
            return result;
        }

        if (root.val <= v){
            // check right
            result = findNode(root, root.right, v);
        }
        else{
            // check left
            result = findNode(root, root.left, v);
        }

        return result;
    }

    public TreeNode getTarget(TreeNode root, int v){
        TreeNode target = findNode(null, root, v)[1];
        // if cannot find, get the nearest
        if (target == null) {
            target = getLessThanNode(root, v);
        }
        return target;
    }

    public static void main(String[] args){
        /*
        findTest();
        getChildrenTest();
        findLessThanTest();
        findLessThanNodeTest();

         */
        test0();
        test1();
        test2();
    }

    public static void findTest(){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        root.right = new TreeNode(6, new TreeNode(5), new TreeNode(7));

        Solution s = new Solution();
        TreeNode targetNode = s.findNode(null, root, 4)[1];
        assertEquals(4, targetNode.val);
        /*
        targetNode = s.findNode(root, 2);
        assertEquals(2, targetNode.val);
        targetNode = s.findNode(root, 1);
        assertEquals(1, targetNode.val);
        targetNode = s.findNode(root, 3);
        assertEquals(3, targetNode.val);
        targetNode = s.findNode(root, 6);
        assertEquals(6, targetNode.val);
        targetNode = s.findNode(root, 5);
        assertEquals(5, targetNode.val);
        targetNode = s.findNode(root, 7);
        assertEquals(7, targetNode.val);
        targetNode = s.findNode(root, 8);
        assertNull(targetNode);

         */
    }
/*
    public static void getChildrenTest(){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        root.right = new TreeNode(6, new TreeNode(5), new TreeNode(7));
        Solution s = new Solution();
        TreeNode target = s.findNode(null, root, 4)[1];
        List<TreeNode> children = s.getNodes(target);
        assertEquals(7, children.size());
    }

    public static void findLessThanTest(){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        root.right = new TreeNode(6, new TreeNode(5), new TreeNode(7));
        Solution s = new Solution();
        List<TreeNode> children = s.getLessThanNodes(root, 2);
        assertEquals(2, children.size());
    }

    public static void findLessThanNodeTest(){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        root.right = new TreeNode(6, new TreeNode(5), new TreeNode(7));
        Solution s = new Solution();
        TreeNode t = s.getTarget(root, 7);
    }
    */

    public static void test0(){
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        root.right = new TreeNode(6, new TreeNode(5), new TreeNode(7));
        Solution s = new Solution();
        TreeNode[] n;
        n = s.splitBST(root,2);
        assertEquals(n[0].val, 2);
        assertEquals(n[1].val, 4);
    }

    public static void test1(){
        TreeNode root = new TreeNode(1);
        Solution s = new Solution();
        TreeNode[] n;
        n = s.splitBST(root,1);
        assertEquals(n[0].val, 1);
        assertNull(n[1]);
    }

    public static void test2(){
        Solution s = new Solution();
        TreeNode[] n = s.splitBST(null,1);
        assertNull(n[0]);
        assertNull(n[1]);
    }
}