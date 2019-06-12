package thread.rocketmq.thread;

public class PullMessageService extends ServiceThread {
    @Override
    public String getThreadName() {
        return "pullMessageService";
    }

    @Override
    public void run() {
        while(!isStopped()){
            action();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
