package com.edger.javademo.innerclass;

class Outter {

    private Inner mInner = null;

    public Outter() {

    }

    public Inner getInnerInstance() {
        if (mInner == null) {
            mInner = new Inner();
        }
        return mInner;
    }

    class Inner {
        public Inner() {

        }
    }
}
