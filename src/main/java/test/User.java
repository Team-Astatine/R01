package test;

import java.util.List;

public class User {
    private String name;
    private int age;
    private List<String> ip;

    public User(String name, int age, List<String> ip) {
        this.name = name;
        this.age = age;
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getIp() {
        return ip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIp(List<String> ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", ip=" + ip +
                '}';
    }
}
