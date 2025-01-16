package org.xpertss.ssh;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FileConfigRepositoryTest {


    @Test
    public void testHomePath()
    {
        Path path = Paths.get(System.getProperty("user.home"), "/.ssh/config");
        assertTrue(Files.exists(path));
        assertTrue(Files.isRegularFile(path));
        assertFalse(Files.isDirectory(path));
        assertTrue(Files.isReadable(path));
    }

    @Test
    public void testDirectoryPath()
    {
        Path path = Paths.get(System.getProperty("user.home"));
        assertTrue(Files.isDirectory(path));
        path = path.resolve(".ssh/config");
        assertTrue(Files.exists(path));
        assertTrue(Files.isRegularFile(path));
        assertFalse(Files.isDirectory(path));
        assertTrue(Files.isReadable(path));
    }

    @Test
    public void testSplit()
    {
        String pattern = "\\s+";
        String[] one = "two  space".split(pattern);
        assertEquals(2, one.length);

        String[] two = "two space".split(pattern);
        assertEquals(2, two.length);

        String[] three = "two\tspace".split(pattern);
        assertEquals(2, three.length);

        String[] four = "two\t\tspace".split(pattern);
        assertEquals(2, four.length);
    }

    @Test
    public void testSplitTwo()
    {
        String pattern = "=|\\s+";
        String[] one = "two  space".split(pattern);
        assertEquals(2, one.length);

        String[] two = "two space".split(pattern);
        assertEquals(2, two.length);

        String[] three = "two\tspace".split(pattern);
        assertEquals(2, three.length);

        String[] four = "two\t\tspace".split(pattern);
        assertEquals(2, four.length);

        String[] five = "two=space".split(pattern);
        assertEquals(2, five.length);
    }


    @Test
    public void testLoadConfig() throws Exception
    {
        Path config = getConfigPath();
        System.out.println(config.toAbsolutePath());
        assertTrue(Files.exists(config));
    }



    private static Path getConfigPath()
    {
        ClassLoader classLoader = FileConfigRepository.class.getClassLoader();
        URI resourceUri = URI.create(classLoader.getResource("config").toString());
        return Paths.get(resourceUri);
    }

}