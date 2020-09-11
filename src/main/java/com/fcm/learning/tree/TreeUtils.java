package com.fcm.learning.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeUtils {


  public static TreeNode initOrderedTree(int nums) {
    List<TreeNode> treeNodeList = new ArrayList<>();
    for (int i = 0; i < nums; i++) {
      treeNodeList.add(new TreeNode(i + 1));
    }

    for (int i = 0; i < nums / 2 - 1; i++) {
      TreeNode temp = treeNodeList.get(i);
      temp.left = treeNodeList.get(i * 2 + 1);
      temp.right = treeNodeList.get(i * 2 + 2);
    }

    int lastParent = nums / 2 - 1;
    treeNodeList.get(lastParent).left = treeNodeList.get(lastParent * 2 + 1);
    if (nums % 2 == 1) {
      treeNodeList.get(lastParent).right = treeNodeList.get(lastParent * 2 + 2);
    }
    return treeNodeList.get(0);
  }

  public static void inorder(TreeNode root) {
    if (root == null)
      return;
    System.out.print(root.value + " ");
    inorder(root.left);
    inorder(root.right);
  }

  public static void lastOrderTraversal(TreeNode root) {
    if (root != null) {
      preOrderTraversal(root.left);
      preOrderTraversal(root.right);
      System.out.print(root.value + " ");
    }
  }

  public static void main(String[] args) {
    TreeNode root = initOrderedTree(7);

    System.out.println("前序：");
    preOrderTraversal(root);
    System.out.println("\n中序：");
    midOrderTraversal(root);
    System.out.println("\n后序：");
    lastOrderTraversal(root);
    System.out.println("\n层次遍历：");
    levelTraversal(root);
  }

  public static void midOrderTraversal(TreeNode root) {

    if (root != null) {
      preOrderTraversal(root.left);
      System.out.print(root.value + " ");
      preOrderTraversal(root.right);
    }
  }

  /**
   * 先序：考察到一个节点后，即刻输出该节点的值，并继续遍历其左右子树。(根左右)<b>根出现在开头</b>
   * <p>
   * 中序：考察到一个节点后，将其暂存，遍历完左子树后，再输出该节点的值，然后遍历右子树。(左根右) <b>根出现在中间</b>
   * <p>
   * 后序：考察到一个节点后，将其暂存，遍历完左右子树后，再输出该节点的值。(左右根)<b>根出现在最后</b>
   *
   * @param root
   */
  public static void preOrderTraversal(TreeNode root) {
    if (root != null) {
      System.out.print(root.value + " ");
      preOrderTraversal(root.left);
      preOrderTraversal(root.right);
    }
  }

  /**
   * 层次遍历
   * @param root
   */
  public static void levelTraversal(TreeNode root){
    Queue<TreeNode> treeNodes= new ArrayDeque<>();
    treeNodes.add(root);
    TreeNode temp ;
    while (!treeNodes.isEmpty()){
      temp= treeNodes.poll();
      System.out.print(temp.value+" ");
      if(temp.left!=null){
        treeNodes.add(temp.left);
      }
      if(temp.right!=null){
        treeNodes.add(temp.right);
      }
    }
  }
}
