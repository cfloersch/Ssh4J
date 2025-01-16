package org.xpertss.ssh;

import org.xpertss.ssh.utils.Strings;
import xpertss.ssh.Config;
import xpertss.ssh.Identity;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DefaultConfig implements Config {

    private static final Map<String,String[]> defaults = new HashMap<>();
    static {
        defaults.put("Compression", new String[] {"yes"});
    }

    private final Map<String,String[]> options;

    public DefaultConfig(Map<String,String[]> options)
    {
        this.options = options;
    }




    @Override
    public String getHostname()
    {
        return getValue("HostName");
    }

    @Override
    public String getUser()
    {
        return getValue("User");
    }

    @Override
    public int getPort()
    {
        try {
            return Integer.parseInt(getValue("Port"));
        } catch (Exception e) {
            return 22;
        }
    }

    @Override
    public Identity getIdentity()
    {
        String path = getValue("IdentityFile");
        if(!Strings.isEmpty(path)) {
            Path file = Paths.get(path);
            if(Files.exists(file) && Files.isReadable(file)) {
                return null;    // TODO Impl
            }
        }
        return null;
    }




    @Override
    public String getValue(String key)
    {
        String[] results = getValues(key);
        return (results != null) ? results[0] : null;
    }

    @Override
    public String[] getValues(String key)
    {
        String[] results = options.get(Strings.lowerCase(key));
        return (results != null) ? results : defaults.get(Strings.lowerCase(key));
    }

}
