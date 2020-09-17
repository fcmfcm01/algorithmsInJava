package com.fcm.learning.tree.binarytree;

import java.util.*;

public class TreeUtils {

  //设置pre值为更大值域的long,以防溢出
  long pre = Long.MIN_VALUE;

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
    List<Integer> resultList = new ArrayList<>();
    if (root != null) {
      Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
      TreeNode node = root;
      TreeNode lastVisit = root;
      while (node != null || !treeNodeStack.isEmpty()) {
        //遍历左子树
        while (node != null) {
          treeNodeStack.push(node);
          node = node.left;
        }
        //查看当前栈顶元素
        node = treeNodeStack.peek();
        //如果其右子树也为空，或者右子树已经访问
        //则可以直接输出当前节点的值
        if (node.right == null || node.right == lastVisit) {
          resultList.add(node.val);
          treeNodeStack.pop();
          lastVisit = node;
          //当前节点已访问
          node = null;
        } else {
          //否则，继续遍历右子树
          node = node.right;
        }
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
    TreeUtils.postorderTraversal(root);
    System.out.println("\n层次遍历：");
    levelTraversal(root);
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

  public static List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    if (root != null) {
      //建立stack
      Stack<TreeNode> nodeStack = new Stack<>();
      //初始化当前访问节点和上次访问节点
      TreeNode node = root;
      TreeNode lastVisited = root;
      //开始循环
      while (!nodeStack.isEmpty() || node != null) {
        //遍历左子树
        while (node != null) {
          nodeStack.push(node);
          node = node.left;
        }
        //得到栈顶元素
        node = nodeStack.peek();

        //如果栈顶元素右节点为空，或者该右节点已被访问过，输出当前节点值
        if (node.right == null || node.right == lastVisited) {
          TreeNode temp = nodeStack.pop();
//          System.out.println(temp.val);
          list.add(temp.val);
          lastVisited = temp;
          //当前节点被访问过了，置为空
          node = null;
        } else {
          //否则继续遍历右子树
          node = node.right;
        }
      }
    }
    return list;
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
   * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
   * <p>
   * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
   * <p>
   * <br/>
   * <ol>
   * <li>先取中位数，中位数满足如下要求：</li>
   * <ol>
   *   <li>左边的数都小于该数</li>
   *   <li>右边的数都大于该数</li>
   * </ol>
   * <li>对左子数组和右子数组分别递归生成树，设置左右节点到上一层节点</li>
   * <ul>
   *   <li>结束条件：当前子数组只包含一个元素</li>
   * </ul>
   * </ol>
   *
   * @param nums
   * @return
   */
  public static TreeNode sortedArrayToBST(int[] nums) {
    TreeNode root = null;
    if (nums != null && nums.length > 0) {
      int mid = nums.length / 2;
      root = new TreeNode(nums[mid]);
      if (nums.length > 1) {
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, nums.length));
      }
      return root;
    }
    return root;
  }

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
}
