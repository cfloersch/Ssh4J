package xpertss.ssh;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HostKey {

    // TODO Do we need marker or comment?

    private final KeyType keyType;
    private final PublicKey key;
    private final Set<String> hosts;

    public HostKey(KeyType keyType, PublicKey key, String ... hosts)
    {
        this.keyType = keyType;
        this.key = key;
        this.hosts = Arrays.stream(hosts)
                            .filter(Objects::nonNull)
                            .map(String::toLowerCase)
                            .collect(Collectors.toSet());
    }

    public KeyType getKeyType()
    {
        return keyType;
    }

    public PublicKey getPublicKey()
    {
        return key;
    }

    public String getFingerPrint(FingerPrintFormat format)
    {
        return format.generate(key.getEncoded());
    }

    public Stream<String> hosts()
    {
        return hosts.stream();
    }

    public boolean matches(String host)
    {
        return hosts.contains(host.toLowerCase());
    }


}
