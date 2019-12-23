package designPattern;

/**
 * �i�~��, �u�{�w�� ���Ĳv�ܧC, 99%���ɭԥΤ��� synchronize
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
 *  �j�~�� �u�{�w��, �S�� lazy loading, �e�����ͩU���ﹳ, ���˨ϥ�
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
