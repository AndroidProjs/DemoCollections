package com.edger.javademo.object;

class ObjectMethodClone implements Cloneable {
    public static void main(String[] args) {

        // protected 修饰的属性或方法表示：在同一个包内或者不同包的子类可以访问。

        // "不同包中的子类可以访问"，是指当两个类不在同一个包中的时候，
        // 继承自父类的子类内部且主调（调用者）为子类的引用时才能访问
        // 父类用 protected 修饰的成员（属性 / 方法）。 在子类内部，
        // 主调为父类的引用时并不能访问此 protected 修饰的成员！（super 关键字除外）

        /*
        Object o1 = new Object();
        // The method clone() from the type Object is not visible
        Object clone = o1.clone();
        */

        ObjectMethodClone ot1 = new ObjectMethodClone();

        try {
            ObjectMethodClone ot2 = (ObjectMethodClone) ot1.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
