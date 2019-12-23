package designPattern;

/**
 * 懶漢式, lazy loading(Y), thread safe(Y)
 * 
 * @author steven
 *
 */
public class Singleton {
	private static Singleton instance;

	protected Singleton() {
	}

	public static synchronized Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}

		return instance;
	}

}

/**
 * 餓漢式, lazy loading(N), thread safe(Y)
 * 
 * @author steven
 *
 */
class Singleton2 {
	private static Singleton2 instance2 = new Singleton2();

	private Singleton2() {
	}

	public static Singleton2 getInstance2() {
		return instance2;
	}
}

/**
 * DCL, double check locking
 * lazy loading (Y),
 * thread safe (Y)
 * @author steven
 *
 */
class Singleton3 {
	private volatile static Singleton3 instance3 = new Singleton3();

	private Singleton3() {
	}

	public static Singleton3 getInstance() {
		if (instance3 == null) {
			synchronized (Singleton3.class) {
				instance3 = new Singleton3();
			}
		}
		return instance3;
	}
}
