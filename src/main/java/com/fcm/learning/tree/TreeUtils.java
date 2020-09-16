package com.fcm.learning.tree;

import java.util.*;

public class TreeUtils {

  /**
   * 生成一个有序的完全二叉树
   * <p>      1         </p>
   * <p>    /   \       </p>
   * <p>    2    3      </p>
   * <p>   / \  / \     </p>
   * <p>  4  5 6   7    </p>
   *
   * @param nums
   * @return
   */
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


  //设置pre值为更大值域的long,以防溢出
  long pre = Long.MIN_VALUE;

  /**
   * 假设一个二叉搜索树具有如下特征：
   * <br/>
   * <ul>
   * <li> 节点的左子树只包含小于当前节点的数。</li>
   * <li> 节点的右子树只包含大于当前节点的数。</li>
   * <li> 所有左子树和右子树自身必须也是二叉搜索树。</li>
   * </ul>
   *
   * @param root 根节点
   * @return 是否二叉搜索树
   */
  public boolean isValidBST(TreeNode root) {
    //已搜索到叶子节点
    if (root == null) {
      return true;
    }
    //中序遍历开始,遍历左子树
    if (!isValidBST(root.left)) {
      return false;
    }
    // 如果当前节点小于已中序遍历中的任何节点，则为false
    if (root.val <= pre) {
      return false;
    }
    pre = root.val;
    //遍历右子树
    return isValidBST(root.right);
  }


  public static void main(String[] args) {
    TreeNode root = initOrderedTree(7);

    System.out.println("前序：");
    preOrderTraversal(root);
    System.out.println();
    preOrderTraversalNonRecursive(root);
    System.out.println("\n中序：");
    midOrderTraversal(root);
    System.out.println();
    midOrderTraversalNonRecursive(root);
    System.out.println("\n后序：");
    lastOrderTraversal(root);
    System.out.println();
    lastOrderTraversalNonRecursive(root);
    System.out.println("\n层次遍历：");
    levelTraversal(root);
  }


  /**
   * 先序：考察到一个节点后，即刻输出该节点的值，并继续遍历其左右子树。(根左右)<b>根出现在开头</b>
   *
   * @param root
   */
  public static void preOrderTraversal(TreeNode root) {
    if (root != null) {
      System.out.print(root.val + " ");
      preOrderTraversal(root.left);
      preOrderTraversal(root.right);
    }
  }

  /**
   * 先序：考察到一个节点后，即刻输出该节点的值，并继续遍历其左右子树。(根左右)<b>根出现在开头</b>
   *
   * @param root
   */
  public static void preOrderTraversalNonRecursive(TreeNode root) {
    if (root != null) {
      Stack<TreeNode> treeNodeStack = new Stack<>();
      treeNodeStack.push(root);
      while (!treeNodeStack.isEmpty()) {
        TreeNode node = treeNodeStack.pop();
        System.out.print(node.val + " ");
        if (node.right != null) {
          treeNodeStack.push(node.right);
        }
        if (node.left != null) {
          treeNodeStack.push(node.left);
        }
      }
    }
  }

  /**
   * 中序：考察到一个节点后，将其暂存，遍历完左子树后，再输出该节点的值，然后遍历右子树。(左根右) <b>根出现在中间</b>
   *
   * @param root
   */
  public static void midOrderTraversal(TreeNode root) {

    if (root != null) {
      midOrderTraversal(root.left);
      System.out.print(root.val + " ");
      midOrderTraversal(root.right);
    }
  }


  /**
   * 中序：考察到一个节点后，将其暂存，遍历完左子树后，再输出该节点的值，然后遍历右子树。(左根右) <b>根出现在中间</b>
   *
   * @param root
   */
  public static List<Integer> midOrderTraversalNonRecursive(TreeNode root) {
    List<Integer> resultList = new ArrayList<>();
    if (root != null) {
      //后进先出
      Stack<TreeNode> stack = new Stack<>();
      //添加根节点
      stack.push(root);
      //从左子树开始遍历
      TreeNode node = root.left;
      while (!stack.isEmpty() || node != null) {
        while (node != null) {
          stack.push(node);
          node = node.left;
        }
        //左子树遍历完毕，添加左子树根节点
        resultList.add(stack.peek().val);
        System.out.print(stack.peek().val + " ");
        //遍历右子树
        node = stack.pop().right;
      }

    }
    return resultList;
  }


  /**
   * 后序：考察到一个节点后，将其暂存，遍历完左右子树后，再输出该节点的值。(左右根)<b>根出现在最后</b>
   *
   * @param root
   */
  public static void lastOrderTraversal(TreeNode root) {
    if (root != null) {
      lastOrderTraversal(root.left);
      lastOrderTraversal(root.right);
      System.out.print(root.val + " ");
    }
  }

  /**
   * 后序：考察到一个节点后，将其暂存，遍历完左右子树后，再输出该节点的值。(左右根)<b>根出现在最后</b>
   *
   * @param root
   */
  public static List<Integer> lastOrderTraversalNonRecursive(TreeNode root) {
    //TODO 不对 要仔细思考
    List<Integer> resultList = new ArrayList<>();
    if (root != null) {
      //后进先出
      Stack<TreeNode> stack = new Stack<>();
      //添加根节点
      stack.push(root);
      //从左子树开始遍历
      TreeNode pre = root;
      TreeNode node = root.left;
      while (node.left != null || node.right != null) {
        while (node.left != null) {
          pre = node;
          stack.push(node.left);
          node = node.left;
        }
        //遍历右子树
        node = pre.right;
      }
      while (!stack.isEmpty()) {
        //左子树遍历完毕，添加左子树节点值
        TreeNode cur = stack.pop();
        resultList.add(cur.val);
        System.out.print(cur.val + " ");
      }

    }
    return resultList;
  }

  /**
   * 层次遍历
   *
   * @param root
   */
  public static void levelTraversal(TreeNode root) {
    Queue<TreeNode> treeNodes = new ArrayDeque<>();
    treeNodes.add(root);
    while (!treeNodes.isEmpty()) {
      TreeNode temp = treeNodes.poll();
      System.out.print(temp.val + " ");
      if (temp.left != null) {
        treeNodes.add(temp.left);
      }
      if (temp.right != null) {
        treeNodes.add(temp.right);
      }
    }
  }

  /**
   * 求树的节点数
   *
   * @param node 根节点
   * @return 节点个数
   */
  public static int countNode(TreeNode node) {
    if (node == null) {
      return 0;
    }
    return countNode(node.left) + countNode(node.right) + 1;
  }

  /**
   * 求树的深度
   *
   * @param root
   * @return 树的深度
   */
  public static int depthOfTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftDepth = depthOfTree(root.left) + 1;
    int rightDepth = depthOfTree(root.left) + 1;
    return Math.max(leftDepth, rightDepth);
  }

  /**
   * 求二叉树第k层的结点个数
   */
  public static int countOfTreeAtLevel(TreeNode root, int level) {
    if (root == null) {
      return 0;
    }
    if (level == 1) {
      return 1;
    }
    return countOfTreeAtLevel(root.left, level - 1) + countOfTreeAtLevel(root.right, level - 1);
  }

  /**
   * 最低公共祖先，即 LCA（Lowest Common Ancestor）
   * </br>
   * <img src="https://resource.ethsonliu.com/image/20180407_02.jpg"/>
   *
   * @param root
   * @param node1
   * @param node2
   * @return
   */
  public static TreeNode findLCA(TreeNode root, TreeNode node1, TreeNode node2) {
    if (root == null) {
      return null;
    }
    if (root == node1 || root == node2) {
      return root;
    }
    TreeNode left = findLCA(root.left, node1, node2);
    TreeNode right = findLCA(root.right, node1, node2);
    // 分别在左右子树
    if (left != null && right != null) {
      return root;
    }
    // 都在同一个子树
    return left != null ? left : right;
  }

}
