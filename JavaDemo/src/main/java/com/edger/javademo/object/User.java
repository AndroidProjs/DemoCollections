package com.edger.javademo.object;

public class User {

    private int uid;
    private String name;
    private int age;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    protected String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        return ((User) obj).getUid() == this.getUid();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.getUid();
        return result;
    }
}
