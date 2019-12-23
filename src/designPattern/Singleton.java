package designPattern;

/**
 * 懶漢式, 線程安全 但效率很低, 99%的時候用不到 synchronize
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
 *  餓漢式 線程安全, 沒有 lazy loading, 容易產生垃圾對像, 推薦使用
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
