package com.edger.javademo.innerclass;

class Test {
    public static void main(String[] args) {
        // Outter outter = new Outter();
        // Outter.Inner inner = outter.new Inner();
        //
        // Outter.Inner inner1 = outter.getInnerInstance();
    }

    public void test(final int b) {
        final int a = 10;
        new Thread(){
            @Override
            public void run() {
                System.out.println(a);
                System.out.println(b);
            };
        }.start();
    }
}
