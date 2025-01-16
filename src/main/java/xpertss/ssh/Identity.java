package xpertss.ssh;

import java.security.Key;
import java.security.PrivateKey;

public interface Identity {


    /**
     * Returns the name of this identity. It will be useful to identify this object in the
     * {@link IdentityRepository}.
     *
     * @return the name of this identity
     */
    public String getName();




    public PrivateKey getKey();


    /**
     * Disposes internally allocated data, like byte array for the private key.
     */
    public void destroy();




    // TODO Do we need the following?

    /**
     * Returns the name of the key algorithm.
     *
     * @return the name of the key algorithm
     */
    public String getAlgName();


    /**
     * Returns <code>true</code> if this identity is cyphered.
     *
     * @return <code>true</code> if this identity is cyphered.
     */
    public boolean isEncrypted();


}
