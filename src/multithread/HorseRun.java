package multithread;

public class HorseRun {

    public static void main(String[] args) {
        // 5 匹馬要出發了，請下注
        new Thread(new Horse("FIRE")).start();
        new Thread(new Horse("ROCKET")).start();
        new Thread(new Horse("BULLET")).start();
        new Thread(new Horse("WIND")).start();
        new Thread(new Horse("THUNDER")).start();
    }

}
