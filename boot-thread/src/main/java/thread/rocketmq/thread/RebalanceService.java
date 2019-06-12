package thread.rocketmq.thread;

public class RebalanceService extends ServiceThread {
    @Override
    public String getThreadName() {
        return "rebalanceService";
    }

    @Override
    public void run() {
        while (!this.isStopped()){
            waitForRunning(100000);
            action();
        }
    }
}
