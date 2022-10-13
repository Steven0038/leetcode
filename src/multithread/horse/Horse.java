package multithread.horse;

public class Horse implements Runnable {
    volatile static int rankh = 1;    // 利用 static 使其他 Horse 共用此變數(名次)

    int runSpeed;    // 跑速不大於 5
    int sleepTime;    // 休息不大於 1 秒
    int totalSleepTime = 0; // 總共是睡了多久啊

    int range = 0;    // 起跑點
    int end = 150;    // 終點
    String horseName;

    public Horse(String name) {
        horseName = name;
    }

    public void run() {
        while (range < end) {
            runSpeed = (int) (Math.random() * 6); // 跑速 (0<=x<6 -> 0~5)
            sleepTime = (int) (Math.random() * 1001); // 打盹的時間 (0<=x<1001 -> 0~1000)
            totalSleepTime += sleepTime; // 目前為止睡了多久
            try {
                Thread.sleep(sleepTime); // 小睡片刻
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            range += runSpeed; // 跑了多遠啊
            System.out.println(horseName + " run " + (range > 500 ? 500 : range) + " meters !!"); // 賽跑情形
        }

        System.out.println("Rank " + rankh++ + " is " + horseName + " ,total rest " + totalSleepTime + " seconds.");
    }
}

