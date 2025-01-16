package xpertss.ssh;

public interface UserInfo {

    // TODO Modify this to use JAAS

    String getPassphrase();

    String getPassword();

    boolean promptPassword(String message);

    boolean promptPassphrase(String message);

    boolean promptYesNo(String message);

    void showMessage(String message);

}
