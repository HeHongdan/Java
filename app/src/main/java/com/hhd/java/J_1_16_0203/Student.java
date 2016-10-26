package com.hhd.java.J_1_16_0203;

/**
 * Created by HHJ on 2016/5/23.
 */
class Student {
    private String name;
    private String age;

    @Override
    public boolean equals(Object o) {
        System.out.println("equals");

        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (!getName().equals(student.getName())) return false;
        return getAge().equals(student.getAge());

    }

    @Override
    public int hashCode() {
        System.out.println("hashCode");

        int result = getName().hashCode();
        result = 31 * result + getAge().hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Student(String age, String name) {
        this.age = age;
        this.name = name;
    }
}
