package thread.rocketmq.thread;

public class ThreadTest {
    public static void main(String[] args) {
//        PullMessageService pullMessageService = new PullMessageService();
//        pullMessageService.start();
        RebalanceService service = new RebalanceService();
        service.start();
        service.weakUp();
    }
}
