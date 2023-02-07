package designPattern;


public class SingletonDemo {
    public static void main(String[] args) {}
}

/**
 * 懶漢式, lazy loading(Y), thread safe(Y)
 * <p>
 * pros: 第一次調用才初始化, 節省內存
 * cons: 必須加鎖 synchronized 才保證單例, 且影響效率 (99% 情況下不需要同步)
 */
class Singleton1 {
    private static Singleton1 instance;

    private Singleton1() {}

    public static synchronized Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }

        return instance;
    }
}

/**
 * 餓漢式, lazy loading(N), thread safe(Y)
 * <p>
 * pros: 較常用, 沒加鎖執行效率提高
 * cons: class 加載就初始化, 浪費內存
 */
class Singleton2 {
    private static Singleton2 instance2 = new Singleton2();

    private Singleton2() {}

    public static Singleton2 getInstance() {
        return instance2;
    }
}

/**
 * 雙檢鎖 DCL, double-checked locking, lazy loading (Y), thread safe (Y)
 * <p>
 * pros: 安全多線程且高性能
 * cons: 實現較複雜
 */
class Singleton3 {
    private volatile static Singleton3 singleton;

    private Singleton3() {}

    public static Singleton3 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton3.class) {
                if (singleton == null) {
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }
}

/**
 * 登記式, lazy loading (Y), thread safe (Y)
 * <p>
 * pros: 與雙檢鎖相同, 但實現更簡單,
 * cons:
 */
class Singleton4 {
    private static class SingletonHolder {
        private static final Singleton4 INSTANCE = new Singleton4();
    }

    private Singleton4() {}

    public static final Singleton4 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}