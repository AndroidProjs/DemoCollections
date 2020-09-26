package com.edger.javademo.object;

class ObjectMethodWaitAndNotify {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyRunnable r = new MyRunnable();
        Thread t = new Thread(r);
        t.start();
        synchronized (r) {
            try {
                System.out.println("main thread 等待t线程执行完");
                r.wait();
                System.out.println("被 notify 唤醒，得以继续执行");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("main thread 本想等待，但被意外打断了");
            }
            System.out.println("线程 t 执行相加结果" + r.getTotal());
        }
    }

}

class MyRunnable implements Runnable {
    private int total;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        synchronized (this) {
            System.out.println("Thread name is:" + Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
                total += i;
            }
            notify();
            System.out.println("执行 notify 后同步代码块中依然可以继续执行直至完毕");
        }
        System.out.println("执行 notify 后且同步代码块外的代码执行时机取决于线程调度");
    }

    public int getTotal() {
        return total;
    }
}
