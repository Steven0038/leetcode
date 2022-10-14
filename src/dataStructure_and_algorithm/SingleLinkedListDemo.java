package dataStructure_and_algorithm;

/**
 * ref. https://hackmd.io/@Aquamay/H1nxBOLcO/https:%2F%2Fhackmd.io%2F@Aquamay%2FHJxij_U9u
 */
public class SingleLinkedListDemo {
    public static void main(String[] args){
        HeroNode hero1 = new HeroNode(1, "宋江", "及時雨");
        HeroNode hero2 = new HeroNode(2, "盧俊義", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吳用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林沖", "豹子頭");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        singleLinkedList.list();

        //按照編號順序添加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero3);  //試著加入已經存在的編號的英雄

        singleLinkedList.list();

        // 修改節點數據
        HeroNode newHeroNode = new HeroNode(2, "小盧", "玉麒麟~~");
        singleLinkedList.updata(newHeroNode);
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
        if(head.next == null)  return 0;
        int length = 0;
        HeroNode cur = head.next;
        while(cur != null) {
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
     *     //添加節點到單向鏈結串列
     *     //思路:當不考慮編號順序時
     *     //1.找到當前鏈結串列的最後節點
     *     //2.將最後節點的next域指向新的節點
     */
    public void add(HeroNode heroNode) {

        //因為head節點不能動,因此我們需要一個輔助遍歷 temp
        HeroNode temp = head;

        //遍歷鏈結串列,找到最後
        while(true) {
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

        while(true) {
            if(temp.next == null) break;
            if(temp.next.no > heroNode.no) { //位置找到, 就在temp的後面插入
                break;
            }else if(temp.next.no == heroNode.no) { //編號已經存在
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //判斷flag的值
        if(flag) {
            System.out.printf("準備添加的英雄編號 %d 已經存在，不能添加\n",heroNode.no);
        }else{
            //插入到鏈結串列中, temp的後面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改節點的資訊,根據編號來修改
    public void updata(HeroNode newHeroNode) {
        if(head.next == null) {
            System.out.println("鏈結串列為空");
            return;
        }
        //找到需要修改的節點
        //定義一個輔助變數
        HeroNode temp = head.next;
        boolean flag = false; //是否找到該節點

        while(true) {
            if (temp == null) break; //已遍歷結束
            if (temp.no == newHeroNode.no) { //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //根據flag 判斷是否找到
        if(flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            System.out.printf("沒有找到編號 %d 的節點, 不能修改\n",newHeroNode.no);
        }

    }

    public void delete(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false;

        while(true) {

            if (temp.next == null) break;
            if(temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag) {
            System.out.printf("刪除了 %d 節點.\n",heroNode.no);
            temp.next = temp.next.next;
        } else {
            System.out.printf("要刪除的節點 %d 不存在\n",heroNode.no);
        }
    }

    //顯示鏈結串列
    public void list() {
        if(head.next == null) {
            System.out.println("鏈結串列為空");
            return;
        }

        HeroNode temp = head.next;

        while(true) {
            if(temp == null) {
                break;
            }
            //輸出節點訊息
            System.out.println(temp);
            //將temp後移, 不然是死循環
            temp = temp.next;
        }
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
