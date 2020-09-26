package com.edger.javademo.object;

class ObjectMethodEquals {
    public static void main(String[] args) {
        User u1 = new User();
        u1.setUid(111);
        u1.setName("张三");

        User u2 = new User();
        u2.setUid(111);
        u2.setName("张三丰");

        //返回true
        System.out.println(u1.equals(u2));
    }
}
