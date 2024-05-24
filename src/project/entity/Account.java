package project.entity;

import java.io.Serializable;
import java.util.Scanner;

public class Account implements Serializable {
    private int accId;
    private String username;
    private String password;
    private boolean permission;
    private String userId;
    private boolean accStatus;

    public Account() {
    }

    public Account(int accId, String username, String password, boolean permission, String userId, boolean accStatus) {
        this.accId = accId;
        this.username = username;
        this.password = password;
        this.permission = permission;
        this.userId = userId;
        this.accStatus = accStatus;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAccStatus() {
        return accStatus;
    }

    public void setAccStatus(boolean accStatus) {
        this.accStatus = accStatus;
    }


}
