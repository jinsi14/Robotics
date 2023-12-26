package com.oldmutual;

import com.jcraft.jsch.UserInfo;

/**
 * An implementation of {@link UserInfo} using a supplied password
 */
public class PasswordBasedAuthentication implements UserInfo {
    private String password;

    /**
     * @param password the password to use
     */
    public PasswordBasedAuthentication(String password) {
        this.password = password;
    }

    @Override
    public String getPassphrase() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean promptPassword(String message) {
        return true;
    }

    @Override
    public boolean promptPassphrase(String message) {
        return false;
    }

    @Override
    public boolean promptYesNo(String message) {
        return false;
    }

    @Override
    public void showMessage(String message) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordBasedAuthentication that = (PasswordBasedAuthentication) o;

        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return password != null ? password.hashCode() : 0;
    }
}
