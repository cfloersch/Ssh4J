package xpertss.ssh;

// https://linuxize.com/post/using-the-ssh-config-file/
public interface ConfigRepository {

    public Config getConfig(String host);

    // TODO Lets make this more of a Builder Approach where we start with the default, allow
    // property by property modification, loading of system config files, loading of user
    // config files, etc.

    // Can I make a generic enum where the generic extends Class and is used for each enum
    // property to define what it's value type is? Then using generics I could impl a config
    // public <T> T getValue(Enum<T> enum) or similar and have a type safe way of managing
    // the various TYPES of values which can be string, string[], boolean, and int
}
