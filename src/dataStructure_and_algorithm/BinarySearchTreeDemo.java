package dataStructure_and_algorithm;

public class BinarySearchTreeDemo {
    public static void main(String[] args) {
        int arr[] = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        // 循環添加節點到二元搜尋樹
        for (int i = 0; i < arr.length; i++) {
            binarySearchTree.add(new Node(arr[i]));
        }
        // 中序遍歷二元搜尋樹
        System.out.println("===中序遍歷二元搜尋樹===");
        binarySearchTree.infixOrder(); // 1, 2, 3, 5, 7, 9, 10, 12

        System.out.println("===刪除節點 3 ===");
        binarySearchTree.delNode(3);
        binarySearchTree.infixOrder(); // 1, 2, 5, 7, 9, 10, 12
    }

}

// 創建二元搜尋樹
class BinarySearchTree {
    private Node root;

    // 搜尋要刪除的節點
    public Node search(int value) {
        if (root == null) return null;
        else return root.search(value);
    }

    // 搜尋父節點
    public Node searchParent(int value) {
        if (root == null) return null;
        else return root.searchParent(value);
    }

    // 1. 返回以node 為根節點的二元搜尋樹的最小節點的值
    // 2. 刪除 node 為根節點的二元搜尋樹的最小節點

    /**
     * @param node 傳入的節點(當作二元搜尋樹的根結點)
     * @return 返回的 以node 為根節點的二元搜尋樹的最小節點的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        // 循環的搜尋左節點, 就會找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 這時target 指向了最小節點
        // 刪除最小節點
        delNode(target.value);
        return target.value;
    }

    // 刪除節點
    public void delNode(int value) {
        if (root == null) return;
        else {
            // 先找到要刪除的節點 targetNode
            Node targetNode = search(value);
            // 沒找到要刪除的節點
            if (targetNode == null) return;
            // 如果當前二元搜尋樹只有一個節點
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 去找到targetNode的父節點
            Node parent = searchParent(value);
            // 1. 如果要刪除的節點是葉子節點
            if (targetNode.left == null && targetNode.right == null) {
                // 判斷 targetNode 是父節點的左子節點還是右子節點
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { // 3. 刪除有兩棵子樹的節點
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else { // 2. 刪除只有一棵子樹的節點
                // 如果要刪除的節點有左子節點
                if (targetNode.left != null) {
                    if (parent != null) {
                        // 如果 targetNode 是 parent 的左子節點
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { // 要刪除的節點有右子節點
                    if (parent != null) {
                        // 如果 targetNode 是 parent 的右子節點
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    // 添加節點的方法
    public void add(Node node) {
        if (root == null) {
            root = node; // root為空則直接指向node
        } else {
            root.add(node);
        }
    }

    // 中序遍歷
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二元搜尋樹為空!");
        }
    }

}

// 創建Node節點
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 搜尋要刪除的節點

    /**
     * @param value 希望刪除的節點的值
     * @return 如果找到返回該節點, 否則返回null
     */
    public Node search(int value) {
        if (value == this.value) { // 找到該節點
            return this;
        } else if (value < this.value) { // 如果搜尋的值小於當前節點, 向左子樹遞迴搜尋
            // 如果左子節點為空
            if (this.left == null) return null;
            return this.left.search(value);
        } else {
            // 如果右子節點為空
            if (this.right == null) return null;
            return this.right.search(value);
        }
    }

    // 搜尋要刪除節點的父節點

    /**
     * @param value 要找的節點的值
     * @return 返回的是要刪除的節點的父節點, 如果沒有則返回null
     */
    public Node searchParent(int value) {
        // 如果當前節點就是要刪除節點的父節點, 就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果搜尋的值小於當前節點的值, 並且當前節點的左子節點不為空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value); // 向左子樹遞迴搜尋
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); // 向右子樹遞迴搜尋
            } else {
                return null; // 沒有找到父節點
            }

        }
    }

    @Override
    public String toString() {
        return "Node [value = " + value + " ]";
    }

    // 用遞迴的形式添加節點的方法
    public void add(Node node) {
        if (node == null) return;
        // 判斷傳入的節點的值, 和當前根節點的值的關係
        if (node.value < this.value) {
            // 如果當前節點左子節點為空
            if (this.left == null) {
                this.left = node;
            } else {
                // 遞迴的向左子樹添加
                this.left.add(node);
            }
        } else { // 添加的節點大於等於當前節點
            if (this.right == null) {
                this.right = node;
            } else {
                // 遞迴的向右子樹添加
                this.right.add(node);
            }
        }
    }

    // 中序遍歷 (Inorder Traversal), 左中右
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

}