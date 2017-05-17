package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户实体
 * Created by yuyufeng on 2017/5/17.
 */
public class User {
    private Long userId;
    private String userName;

    public User() {
    }

    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        for (long i = 0; i < 8; i++) {
            users.add(new User(i,"用户"+i));
        }
        System.out.println(users);
    }
}
