package Trees.SplitBST;

import java.util.*;
import static org.junit.Assert.*;
/**
 * Difficulty: medium
 * Companies: Salesforce
 * https://leetcode.com/problems/split-bst/
 */

public class Solution {

    public TreeNode[] splitBST(TreeNode root, int V){
        TreeNode[] result = new TreeNode[2];

        // find target node
        TreeNode target = getTarget(root,V);
        // copy target and its children into a new tree
        TreeNode treeCopy = copy(target);
        // remove the target from the original tree
        deleteNode(root, V);

        result[0] = treeCopy;
        result[1] = root;

        return result;
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

    public void deleteNode(TreeNode root, int v){
        TreeNode[] nodeToDelete = findNode(null, root, v);

        if (nodeToDelete != null){
            TreeNode parent = nodeToDelete[0];
            if (parent != null){
                if(parent.left.equals(nodeToDelete[1])) parent.left = null;
                else if (parent.right.equals(nodeToDelete[1])) parent.right = null;
            }
        }
    }

    private List<TreeNode> getNodes(TreeNode target) {
        if (target != null) {
            List<TreeNode> children = new ArrayList<>();

            children.addAll(getNodes(target.left));
            children.add(target);
            children.addAll(getNodes(target.right));

            return children;
        }
        return new ArrayList<>();
    }

    public List<TreeNode> getLessThanNodes(TreeNode target, int v) {
        if (target != null) {
            List<TreeNode> children = new ArrayList<>();

            children.addAll(getLessThanNodes(target.left,v));
            if (target.val <= v)
                children.add(target);
            children.addAll(getLessThanNodes(target.right,v));

            return children;
        }
        return new ArrayList<>();
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
        findTest();
        getChildrenTest();
        findLessThanTest();
        test0();
        findLessThanNodeTest();
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
}