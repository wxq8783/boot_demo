package thread.segment.third.second;

public class SynchronizedDemo {

    public static void main(String[] args) {

    }

    private static void doEcho(String message){
        Object object = new Object();
        synchronized (object){
            echo(message);
        }
    }

    private synchronized static void echo(String message){
        System.out.println(message);
    }
}
