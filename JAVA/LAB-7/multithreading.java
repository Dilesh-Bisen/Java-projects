public class multithreading {

    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread thread1 = new Thread(new IncrementTask(counter));
        Thread thread2 = new Thread(new DecrementTask(counter));
        thread1.start();
        thread2.start();
    }
}

class Counter {
    int x = 0;

    public void incr() {
        x++;
        System.out.println("Increment : " + x);
    }

    public void decr() {
        x--;
        System.out.println("Decrement : " + x);
    }
}

class IncrementTask implements Runnable {
    private Counter counter;

    public IncrementTask(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            counter.incr();
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class DecrementTask implements Runnable {
    private Counter counter;

    public DecrementTask(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            counter.decr();
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
