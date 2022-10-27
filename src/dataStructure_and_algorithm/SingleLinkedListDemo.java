package dataStructure_and_algorithm;

/**
 * ref. https://hackmd.io/@Aquamay/H1nxBOLcO/https:%2F%2Fhackmd.io%2F@Aquamay%2FHJxij_U9u
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及時雨");
        HeroNode hero2 = new HeroNode(2, "盧俊義", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吳用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林沖", "豹子頭");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        // 添加到鍊表尾部
        singleLinkedList.addToTail(hero1);
        singleLinkedList.addToTail(hero2);
        singleLinkedList.addToTail(hero3);
        singleLinkedList.addToTail(hero4);
        singleLinkedList.list();

        // 找中間節點(two pointer)
        HeroNode middleNode = singleLinkedList.findMiddleNode();
        System.out.printf("中間節點 編號: %s, 名稱: %s", middleNode.no, middleNode.name);
        System.out.println();

        // 找倒數第 N 個節點
        int n = 2;
        HeroNode lastNthNode = singleLinkedList.findLastNthNode(n);
        System.out.printf("倒數第 %s 個節點, 編號: %s, 名稱: %s", n, lastNthNode.no, lastNthNode.name);
        System.out.println();

        // 得到反轉的鍊表
        System.out.println("=====鍊表反轉=====");
        HeroNode reversedHead = singleLinkedList.reverseLinkedListRecursive();
        while (reversedHead.next != null) {
            System.out.println(reversedHead);
            reversedHead = reversedHead.next;
        }
        System.out.println("=====鍊表反轉 END=====");

        // 按照編號順序添加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero3);  //試著加入已經存在的編號的英雄
        singleLinkedList.list();

        // 插入節點到指定位置
        int position = 3;
        String name = "貓";
        singleLinkedList.addToPosition(position, name);
        singleLinkedList.list();

        // 修改節點數據
        HeroNode newHeroNode = new HeroNode(2, "小盧", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("=====修改後的結果=====");
        singleLinkedList.list();

        // 修改節點數據
        System.out.println("=====刪除後的結果=====");
        singleLinkedList.delete(hero4); //被刪除的節點不會有其他引用指向，會被垃圾回收機制回收。
        singleLinkedList.list();
        singleLinkedList.delete(hero2); //刪除編號2
        singleLinkedList.list();
        singleLinkedList.delete(hero3); //刪除編號3
        singleLinkedList.list();
        singleLinkedList.delete(hero1); //刪除編號4
        singleLinkedList.list();

        // 求鏈結串列中有效節點的個數(若是有頭節點的鏈結串列，則不統計頭節點。)
        System.out.println(getLength(singleLinkedList.getHead())); //獲得節點個數
    }

    public static int getLength(HeroNode head) {
        if (head.next == null) return 0;
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        return length;
    }
}

//定義SingleLinkedList 管理英雄
class SingleLinkedList {
    //先初始化一個頭節點,頭節點不要動,不存放具體數據
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加英雄時，直接添加到鏈結串列的尾部
     * //添加節點到單向鏈結串列
     * //思路:當不考慮編號順序時
     * //1.找到當前鏈結串列的最後節點
     * //2.將最後節點的next域指向新的節點
     */
    public void addToTail(HeroNode heroNode) {

        //因為head節點不能動,因此我們需要一個輔助遍歷 temp
        HeroNode temp = head;

        //遍歷鏈結串列,找到最後
        while (true) {
            if (temp.next == null) {
                break;
            }

            temp = temp.next; //沒找到就將temp後移一個節點
        }

        //當退出while循環時,temp就指向了鏈結串列的最後
        //將最後這個節點的next指向新的節點
        temp.next = heroNode;

    }

    // 按照編號順序插入數據
    public void addByOrder(HeroNode heroNode) {
        //因為頭節點不能動,因此仍用輔助變數來幫助找到添加的位置
        //因為是單向鏈結串列，所以temp是位於添加位置的前一個節點
        HeroNode temp = head;
        boolean flag = false; //添加的編號是否已經存在

        while (true) {
            if (temp.next == null) break;
            if (temp.next.no > heroNode.no) { //位置找到, 就在temp的後面插入
                break;
            } else if (temp.next.no == heroNode.no) { //編號已經存在
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //判斷flag的值
        if (flag) {
            System.out.printf("準備添加的英雄編號 %d 已經存在，不能添加\n", heroNode.no);
        } else {
            //插入到鏈結串列中, temp的後面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改節點的資訊,根據編號來修改
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("鏈結串列為空");
            return;
        }
        //找到需要修改的節點
        //定義一個輔助變數
        HeroNode temp = head.next;
        boolean flag = false; //是否找到該節點

        while (true) {
            if (temp == null) break; //已遍歷結束
            if (temp.no == newHeroNode.no) { //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //根據flag 判斷是否找到
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("沒有找到編號 %d 的節點, 不能修改\n", newHeroNode.no);
        }

    }

    public void delete(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;

        while (true) {
            if (temp.next == null) break;
            if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            System.out.printf("刪除了 %d 節點.\n", heroNode.no);
            temp.next = temp.next.next;
        } else {
            System.out.printf("要刪除的節點 %d 不存在\n", heroNode.no);
        }
    }

    //顯示鏈結串列
    public void list() {
        if (head.next == null) {
            System.out.println("鏈結串列為空");
            return;
        }

        HeroNode temp = head.next;

        while (temp != null) {
            //輸出節點訊息
            System.out.println(temp);
            //將temp後移, 不然是死循環
            temp = temp.next;
        }
    }

    public void addToPosition(int position, String name) {
        HeroNode newNode = new HeroNode(position, name, name);
        if (position == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            HeroNode prev = head;
            for (int i = 0; i < position - 1; i++) {
                prev = prev.next; // 先找到要插入位置的前一個節點
            }
            HeroNode next = prev.next; // 找出要插入的節點的下一個節點
            newNode.next = next; // 指定插入節點的下一個節點
            prev.next = newNode; // 將前一個節點的下一個節點指定給插入節點
        }
    }

    public HeroNode findMiddleNode() {
        HeroNode slow = head;
        HeroNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow; // 快指針是慢指針的2倍速度, 快指針到底之後, 滿指針就會停留在中點(偶數鍊表, 奇數鍊表就會停在中間二個節點其中一個)
    }

    /**
     * 讓快指針先走 n 步, 之後一起走. 快指針碰底為 Null 之後, 慢指針位置就是倒數第 N 個節點
     *
     * @param n Nth
     * @return the last Nth Node
     * @see _3.leetcode_linkedList.RemoveNthNodeFromTheEndOfList
     */
    public HeroNode findLastNthNode(int n) {
        HeroNode slow = head;
        HeroNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null) { // NOTE
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public HeroNode reverseLinkedListRecursive() {
        return reverse(head);
    }

    /**
     * 遞迴反轉鏈表
     *
     * @param head of list to be reversed
     * @return reversed linked list head
     * @see _3.leetcode_linkedList.ReverseLinkedList iterative solution
     */
    public HeroNode reverse(HeroNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        HeroNode reversedHead = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return reversedHead;
    }
}

//定義HeroNode, 每個HeroNode 對象就是一個節點
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一個節點

    //構造函數
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //重寫toString
    @Override
    public String toString() {
        return "HeroNode [no = " + no + ", name = " + name + ", nickname = " + nickname + "]";
    }

}
