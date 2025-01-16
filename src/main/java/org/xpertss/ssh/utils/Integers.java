package org.xpertss.ssh.utils;

public final class Integers {

    public static Integer parse(String str)
    {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }
    }

}
