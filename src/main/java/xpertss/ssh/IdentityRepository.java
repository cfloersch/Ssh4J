package xpertss.ssh;

import java.util.stream.Stream;

/**
 * A repository for SSH identities.
 * <p/>
 * A basic implementation may simply locate key files in the default
 * ~/.ssh/ directory and make them available.
 */
public interface IdentityRepository {

    public static final int UNAVAILABLE = 0;
    public static final int NOTRUNNING = 1;
    public static final int RUNNING = 2;

    public String getName();

    public int getStatus();

    public Stream<Identity> getIdentities();

    public boolean add(Identity identity);

    public boolean remove(Identity identity);

    public void clear();

}
