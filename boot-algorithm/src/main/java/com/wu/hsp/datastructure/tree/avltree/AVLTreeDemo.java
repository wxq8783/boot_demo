package com.wu.hsp.datastructure.tree.avltree;

public class AVLTreeDemo {

    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        //int[] arr = { 10, 12, 8, 9, 7, 6 };
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        //创建一个 AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for(int i=0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("在平衡处理~~");
        System.out.println("树的高度=" + avlTree.root.height()); //3
        System.out.println("树的左子树高度=" + avlTree.root.leftHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.root.rightHeight()); // 2
        System.out.println("当前的根结点=" + avlTree.root);//8
    }
}

class AVLTree{
    public Node root ;

    public void add(Node node){
        if(root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    public void infixOrder(){
        if(root == null){
            return;
        }else{
            root.infixOrder();
        }
    }

    public Node search(int value){
        if(root == null){
            return null;
        }
        return root.search(value);
    }

    public Node searchParent(int value){
        if(root == null){
            return null;
        }
        return root.searchParent(value);
    }



    public Node delNode(int value){
        //考虑3种情况
        if(root == null){
            return null;
        }
        Node temp = search(value);
        if(temp == null){
            return null;
        }
        if(root.left == null && root.right == null){
            root = null;
            return temp;
        }
        Node parentNode = searchParent(value);
        //1、叶子节点的删除
        if(temp.left == null && temp.right == null){
            if(parentNode.left != null && parentNode.left.value == value){
                parentNode.left = null;
            }
            if(parentNode.right != null && parentNode.right.value == value){
                parentNode.right = null;
            }
        }else if(temp.left != null && temp.right != null){ //3、有两个子节点的的删除
            //找到删除节点的右子树下最小节点minNode  这个最小节点替换要删除的节点(先删除这个minNode, 把value值替换要删除的值) 有多个属性会有问题
            Node minNode = temp.right;;
            while(minNode.left != null){
                minNode = minNode.left;
            }
            int minValue = minNode.value;
            delNode(minValue);
            temp.value = minValue;
//            Node tempLeft = temp.left;
//            Node tempRight = temp.right;
//            Node minNode = tempRight;
//            while(minNode.left != null){
//                minNode = minNode.left;
//            }
//            minNode.left = tempLeft;
//            if(parentNode == null){//temp为根节点
//                root = tempRight;
//                minNode.left = tempLeft;
//            }else{
//                if(parentNode.left.value == value){
//                    parentNode.left = tempRight;
//                }
//                if(parentNode.right.value == value){
//                    parentNode.right = tempRight;
//                }
//            }

        }else{
            //2、只有一个子节点的删除
            if(parentNode == null){
                if(temp.left != null){
                   root = temp.left;

                }
                if(temp.right != null){
                    root = temp.right;
                }
            }

            if(parentNode.right != null && parentNode.right.value == temp.value){
                if(temp.left != null){
                    parentNode.right = temp.left;

                }
                if(temp.right != null){
                    parentNode.right = temp.right;
                }
            }

            if(parentNode.left != null && parentNode.left.value == temp.value){
                if(temp.left != null){
                    parentNode.left = temp.left;

                }
                if(temp.right != null){
                    parentNode.left = temp.right;
                }
            }

        }
        return temp;
    }
}

class Node{

    public int value;

    public Node left;

    public Node right;

    public Node(int value) {
        this.value = value;
    }


    public int leftHeight(){
        if(left == null){
            return 0;
        }
        return left.height();
    }

    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }

    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    public void add(Node node){
        if(node == null){
            return;
        }
        if(this.value > node.value){
            if(this.left == null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }else{
            if(this.right == null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }

        if(rightHeight() - leftHeight()   > 1){// 右子树的高度 大于 左子树  左旋转
            Node tempNode = this.right;
            if(tempNode.leftHeight() > tempNode.rightHeight()){
                //先右旋转
                tempNode.rightRotate();
            }
            //最后左旋转
            this.leftRotate();
            return;//旋转完退出
        }
        if(leftHeight() - rightHeight() > 1){//左子树的高度 大于 右子树 右旋转
            //当前节点的左节点 的 右子树高度 大于 左子树 先进行一次左旋转
            Node tempNode = this.left; //当前节点的左节点
            if(tempNode.rightHeight() > tempNode.leftHeight()){
                //先进行一次左旋转
                tempNode.leftRotate();
            }
            this.rightRotate();
            //然后进行右旋转
        }

    }

    //左旋转
    public void leftRotate(){
        //创建新的节点 已当前节点的值
        Node newNode = new Node(this.value);
        //把新的节点的左子树 设置成当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树 设置成 当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值替换为右子树的值
        this.value = right.value;
        //把当前节点的右子树 替换为 当前节点的右子树的右子树
        this.right = this.right.right;
        //把当前节点的左子树 设置为新的节点
        this.left = newNode;
    }

    //右旋转
    public void rightRotate(){
        Node newNode = new Node(this.value);

        newNode.left = this.left.right;

        newNode.right = this.right;

        this.value = this.left.value;

        this.left = this.left.left;

        this.right = newNode;
    }

    public Node search(int value){
        if(this.value == value){
            return this;
        }else if(this.value > value){
            return this.left == null ? null : this.left.search(value);
        }else{
            return this.right == null ? null : this.right.search(value);
        }
    }

    public Node searchParent(int value){
        if((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        }
        if(this.value > value && this.left != null){
            return this.left.searchParent(value);
        }else if(this.value <= value && this.right != null){
            return this.right.searchParent(value);
        }else{
            return null;
        }

    }





    //中序变量
    public void infixOrder(){

        if(this.left != null){
            this.left.infixOrder();
        }

        System.out.println(this+" ");

        if(this.right != null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
