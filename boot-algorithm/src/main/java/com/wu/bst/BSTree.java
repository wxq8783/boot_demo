package com.wu.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *二分搜索树
 */
public class BSTree<E extends Comparable<E>> {


    private class Node {
        public E e;

        public Node left;

        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }


    }

    public Node root;

    public int size;

    public BSTree() {
        this.root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        if(root == null){
            root = new Node(e);
            size++;
        }else{
            add(root,e);
        }
    }
    private void add(Node node , E e){
        if(e.equals(node.e)){
            return;
        }else if(e.compareTo(node.e) < 0 && node.left == null){
            node.left = new Node(e);
            size++;
            return;
        }else if(e.compareTo(node.e) > 0 && node.right == null){
            node.right =  new Node(e);
            size++;
            return;
        }
        if(e.compareTo((E) node.e) < 0){
            add(node.left,e);
        }else{
            add(node.right,e);
        }
    }

    public void add1(E e){
        root = add1(root,e);
    }

    private Node add1(Node node , E e){

        if(node == null){
            size++;
            return new Node(e);
        }

        if(e.compareTo((E) node.e) < 0){
            node.left = add1(node,e);
        }else{
            node.right = add1(node,e);
        }
        return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }

    public boolean contains(Node node , E e){
        if(node == null){
            return false;
        }
        if(node.e.compareTo(e) == 0){
            return true;
        }else if(node.e.compareTo(e) < 0){
             return contains(node.right , e);
        }else {
             return contains(node.left,e);
        }
    }

    //前序遍历  非递归实现
    public void preOrderNR(){
        if(root == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()){
            Node node = stack.pop();
            System.out.println(node.e);
            if(node.right != null)
                stack.push(node.right);
            if(node.left != null)
                stack.push(node.left);

        }
    }

    //前序遍历
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //中序遍历 从小到大排序
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node== null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //后序遍历
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //层序遍历--广度遍历
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.remove();
            System.out.println(node.e);
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }
    }

    //查询最小值
    public Node minNode() throws IllegalAccessException {
        return minNode(root);
    }

    private Node minNode(Node node) throws IllegalAccessException {
        if(node == null){
            throw new IllegalAccessException("BST is empty");
        }
        if(node.left == null){
            return node;
        }
        return minNode(node.left);
    }

    //删除最小值
    public Node removeMin() throws IllegalAccessException {
        Node node = minNode();
        root = removeMin(root);
        return node;
    }
    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
         node.left = removeMin(node.left);
        return node;
    }


    //查询最大值
    public Node maxNode() throws IllegalAccessException {
        return maxNode(root);
    }

    private Node maxNode(Node node) throws IllegalAccessException {
        if(node == null){
            throw new IllegalAccessException("BST si empty");
        }
        if(node.right == null){
            return node;
        }
        return maxNode(node.right);
    }

    //删除最大值
    public Node removeMax() throws IllegalAccessException {
        Node maxNode = maxNode();
        root = removeMax(root);
        return maxNode;
    }

    private Node removeMax(Node node){
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }


    public void removeNode(E e) throws IllegalAccessException {
        root = removeNode(root,e);
    }

    private Node removeNode(Node node,E e) throws IllegalAccessException {
        if(node == null){
            return null;
        }
        if(e.compareTo(node.e) < 0){
            node.left = removeNode(node.left,e);
            return node;
        }
        if(e.compareTo(node.e) > 0){
            node.right = removeNode(node.right,e);
            return node;
        }
        //剩下就是要删除是节点

        //节点的左节点为空
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;

        }
        //节点的右节点为空
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        //剩下的左右节点都子树
        /**
         * 1）获取到要删除节点的右子树的最小节点
         * 2)删除掉这个右子树的最小节点
         * 3)这个最小节点 替换 要删除的节点
         */
        Node successor = minNode(node.right);
        successor.left = node.left;
        successor.right = removeMin(node.right);

        node.left = null;
        node.right = null;
        return successor;
    }
}
