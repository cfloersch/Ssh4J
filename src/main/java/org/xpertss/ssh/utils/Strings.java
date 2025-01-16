package org.xpertss.ssh.utils;

public final class Strings {

    public static String lowerCase(String str)
    {
        return (str == null) ? str : str.toLowerCase();
    }

    public static boolean isEmpty(String str)
    {
        return str == null || str.isEmpty();
    }
}
