package com.wu.hsp.datastructure.tree;

public class BinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"卢俊义");
        HeroNode node3 = new HeroNode(3,"吴用");
        HeroNode node4 = new HeroNode(4,"林冲");

        BinaryTree tree = new BinaryTree(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);

//        tree.postOrder();
        HeroNode node = tree.preOderSearch(3);
        System.out.println("----->"+node);

    }

}


class BinaryTree{

    HeroNode rootNode;
    public BinaryTree(HeroNode rootNode){
        this.rootNode = rootNode;
    }

    //前序
    public void preOder(){
        if(rootNode != null){
            rootNode.preOder();
        }else{
            System.out.println("当前树为空");
        }
    }

    //中序
    public void infixOrder(){
        if(rootNode != null){
            rootNode.infixOrder();
        }else{
            System.out.println("当前树为空");
        }
    }
    //后续
    public void postOrder(){
        if(rootNode != null){
            rootNode.postOrder();
        }else{
            System.out.println("当前树为空");
        }
    }

    //前序查找
    public HeroNode preOderSearch(int no){
        if(this.rootNode.getNo() == no){
            return this.rootNode;
        }else{
            return rootNode.preOderSearch(no);
        }
    }
}

class HeroNode{
    private int no;
    private String name;

    private HeroNode left;

    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序
    public void preOder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOder();
        }
        if(this.right != null){
            this.right.preOder();
        }
    }

    //中序
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    public void postOrder(){
        if(this.left != null){
            this.left.postOrder();
        }
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public HeroNode preOderSearch(int no){
        if(this.getNo() == no){
            System.out.println("===");
            return this;
        }
        HeroNode resNode  = null;
        if(this.getLeft() != null){
            resNode = this.getLeft().preOderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(this.getRight() != null){
            resNode =this.getRight().preOderSearch(no);
        }
        return resNode;

    }
}
