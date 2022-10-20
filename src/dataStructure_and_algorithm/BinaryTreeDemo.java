package dataStructure_and_algorithm;

public class BinaryTreeDemo {
    public static void main(String[] args) throws Exception {
        BinaryTree binaryTree = new BinaryTree();
        TreeHeroNode root = new TreeHeroNode(1, "宋江");
        TreeHeroNode node2 = new TreeHeroNode(2, "吳用");
        TreeHeroNode node3 = new TreeHeroNode(3, "盧俊義");
        TreeHeroNode node4 = new TreeHeroNode(4, "林沖");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);

        System.out.println("===前序遍歷===");
        binaryTree.preOrder();
        System.out.println("===中序遍歷===");
        binaryTree.inOrder();
        System.out.println("===後序遍歷===");
        binaryTree.postOrder();
    }
}

class BinaryTree {
    private TreeHeroNode root;

    public void setRoot(TreeHeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二元樹為空");
        }
    }

    public void inOrder() {
        if (this.root != null) {
            this.root.inOrder();
        } else {
            System.out.println("二元樹為空");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二元樹為空");
        }
    }
}

// 先創建 HeroNode 節點
class TreeHeroNode {
    private int no;
    private String name;
    private TreeHeroNode left;
    private TreeHeroNode right;

    public TreeHeroNode(int no, String name) {
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

    public TreeHeroNode getLeft() {
        return left;
    }

    public void setLeft(TreeHeroNode left) {
        this.left = left;
    }

    public TreeHeroNode getRight() {
        return right;
    }

    public void setRight(TreeHeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode [no = " + no + ", name = " + name + " ]";
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void inOrder() {
        if (this.left != null) {
            this.left.inOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.inOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }
}