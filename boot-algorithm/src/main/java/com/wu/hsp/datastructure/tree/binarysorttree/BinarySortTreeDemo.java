package com.wu.hsp.datastructure.tree.binarysorttree;

public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{5,3,7,2,4,9,1,8};
        BinarySortTree binarySortTree = new BinarySortTree();
        for(int value : arr){
            binarySortTree.add(value);
        }
        binarySortTree.delNode(5);
        binarySortTree.infixOrder();
//        Node node = binarySortTree.searchParent(5);
//        System.out.println(node);
    }
}

class BinarySortTree{
    public Node root ;

    public void add(int value){
        if(root == null){
            root = new Node(value);
        }else{
            root.add(new Node(value));
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

    public int delRightTreeMin(Node node){

        return 0;
    }

    public Node delNode(int value){
        //考虑3种情况
        //1、叶子节点的删除
        //2、只有一个子节点的删除
        //3、有两个子节点的的删除
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
        if(temp.left == null && temp.right == null){
            if(parentNode.left != null && parentNode.left.value == value){
                parentNode.left = null;
            }
            if(parentNode.right != null && parentNode.right.value == value){
                parentNode.right = null;
            }
        }else if(temp.left != null && temp.right != null){
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
