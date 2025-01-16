package xpertss.ssh;


public enum KeyType {

    DSS("ssh-dss"),
    RSA("ssh-rsa"),
    ECDSA256("ecdsa-sha2-nistp256"),
    ECDSA384("ecdsa-sha2-nistp384"),
    ECDSA521("ecdsa-sha2-nistp521"),
    ED25519("ssh-ed25519"),
    ED448("ssh-ed448");


    private final String name;

    private KeyType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

}
