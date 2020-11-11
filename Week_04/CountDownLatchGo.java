import java.util.concurrent.CountDownLatch;
public class CountDownLatchGo {


        private volatile Integer value = null;
        private CountDownLatch latch;

        private void sum() {
            value = fibo(36);
            latch.countDown();
        }

        private int fibo(int a) {
            if ( a < 2) {
                return 1;
            }
            return fibo(a-1) + fibo(a-2);
        }

        private int getValue() throws InterruptedException {
            latch.await();
            return value;
        }


        private void setLatch(CountDownLatch latch) {
            this.latch = latch;
        }

        public static void main(String[] args) throws InterruptedException {

            long start = System.currentTimeMillis();
            // 在这里创建一个线程或线程池，
            // 异步执行 下面方法

            CountDownLatch latch = new CountDownLatch(1);
            final CountDownLatchGo method = new CountDownLatchGo();
            method.setLatch(latch);
            Thread thread = new Thread(() -> {
                method.sum();
            });
            thread.start();

            int result = method.getValue(); //这是得到的返回值

            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + result);

            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

            // 然后退出main线程
        }

}
