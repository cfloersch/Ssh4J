package xpertss.ssh;

public interface Config {

    // TODO Hostname, User, Port called out explicitly. What about Compression, IdentityFile, or LogLevel?


    // HostName
    public String getHostname();

    // User
    public String getUser();

    // Port
    public int getPort();

    // IdentityFile
    public Identity getIdentity();


    // https://phoenixnap.com/kb/ssh-config
    // ConnectTimeout
    // PasswordAuthentication (yes|no)
    // StrictKeyChecking (yes|no)
    // LogLevel (QUIET, FATAL, ERROR, INFO, VERBOSE, DEBUG, DEBUG1, DEBUG2, and DEBUG3)
    // Compression (yes|no)
    // CompressionLevel(1-9) 6 is default
    // AddressFamily (inet|inet6|any)
    // CheckHostIP (yes|no)
    // Ciphers (aes128-ctr, aes192-ctr, aes256-ctr, arcfour256, arcfour128,
    //aes128-cbc, 3des-cbc, blowfish-cbc, cast128-cbc, aes192-cbc, aes256-cbc, arcfour)
    // HostKeyAlgorithms
    // TCPKeepAlive (yes|no)
    // VerifyHostKeyDNS (no|yes|ask)





    public String getValue(String key);

    public String[] getValues(String key);


}
