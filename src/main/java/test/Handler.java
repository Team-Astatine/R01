package test;

import org.jetbrains.annotations.NotNull;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Handler {
    private Map<String, User> userMap;

    public Handler() {
        this.userMap = new HashMap<>();
    }

    public void create(User user) {
        userMap.put(user.getName(), user);
    }

    public User read(@NotNull User user) {
        return userMap.get(user.getName());
    }

    public void updateData(User @NotNull [] userData) {
        userMap.clear();
        for (User user : userData) {
            System.out.println(user);
            userMap.put(user.getName(), user);
        }
    }

    public Map<String, User> getMap() {
        return userMap;
    }

    public void add() {
        User user1 = new User(
                "jwonLEE",
                25,
                new ArrayList<>(
                        Arrays.asList(new String[] {
                                new InetSocketAddress("192.168.0.1", 80).getAddress().getHostAddress(),
                                new InetSocketAddress("192.168.0.2", 80).getAddress().getHostAddress(),
                                new InetSocketAddress("192.168.0.3", 80).getAddress().getHostAddress(),
                                new InetSocketAddress("192.168.0.4", 80).getAddress().getHostAddress(),
                        })
                )
        );

        User user2 = new User(
                "samyang",
                25,
                new ArrayList<>(
                        Arrays.asList(new String[] {
                                new InetSocketAddress("192.168.0.5", 80).getAddress().getHostAddress(),
                                new InetSocketAddress("192.168.0.6", 80).getAddress().getHostAddress(),
                                new InetSocketAddress("192.168.0.7", 80).getAddress().getHostAddress(),
                                new InetSocketAddress("192.168.0.8", 80).getAddress().getHostAddress(),
                        })
                )
        );

        User user3 = new User(
                "dururu",
                25,
                new ArrayList<>(
                        Arrays.asList(new String[] {
                                new InetSocketAddress("192.168.0.9", 80).getAddress().getHostAddress(),
                                new InetSocketAddress("192.168.0.10", 80).getAddress().getHostAddress(),
                                new InetSocketAddress("192.168.0.11", 80).getAddress().getHostAddress(),
                                new InetSocketAddress("192.168.0.12", 80).getAddress().getHostAddress(),
                        })
                )
        );

        create(user1);
        create(user2);
        create(user3);
    }
}
