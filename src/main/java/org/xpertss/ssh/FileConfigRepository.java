package org.xpertss.ssh;

import org.xpertss.ssh.utils.Lists;
import org.xpertss.ssh.utils.Strings;
import xpertss.ssh.Config;
import xpertss.ssh.ConfigRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileConfigRepository implements ConfigRepository {

    private final List<HostEntry> entries;

    private FileConfigRepository(List<HostEntry> entries)
    {
        this.entries = entries;
    }




    @Override
    public Config getConfig(String host)
    {
        Map<String,String[]> options = new HashMap<>();
        for(HostEntry entry : entries) {
            if(entry.matches(host)) {
                entry.margeTo(options);
            }
        }
        return new DefaultConfig(options);
    }


    public static ConfigRepository of(Path path) throws IOException
    {
        if(Files.isDirectory(path)) path = path.resolve("config");
        if(Files.exists(path) && Files.isReadable(path)) {
            List<HostEntry> entries = new ArrayList<>();
            HostEntry entry = null;
            for(String line : Files.readAllLines(path)) {
                line = line.trim();
                if(line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=|\\s+", 2);
                if(parts[0].equalsIgnoreCase("Host")) {
                    if(entry != null) entries.add(entry);
                    Rule[] rules = Rule.of(parts[1]);
                    entry = new HostEntry(rules);
                } else if(entry != null) {
                    entry.add(parts[0], parts[1]);
                }
            }
            return new FileConfigRepository(entries);
        }
        throw new IOException(String.format("%s cannot be read", path));
    }


    private static class HostEntry {

        private final List<Rule> positve;
        private final List<Rule> negative;
        private final Map<String,String[]> options = new LinkedHashMap<>();

        private HostEntry(Rule[] rules)
        {
            this.positve = Arrays.stream(rules).filter(Rule::isNotNegated).collect(Collectors.toList());
            this.negative = Arrays.stream(rules).filter(Rule::isNegated).collect(Collectors.toList());
        }

        private void add(String key, String value)
        {
            options.putIfAbsent(Strings.lowerCase(key), value.split(",|\\s+"));
        }

        private void margeTo(Map<String,String[]> target)
        {
            options.forEach(target::putIfAbsent);
        }

        private boolean matches(String host)
        {
            return Lists.any(rule -> rule.matches(host), positve)
                    && Lists.none(rule -> rule.matches(host), negative);
        }

    }

    private static class Rule {

        private final boolean negate;
        private final Pattern pattern;

        private Rule(Pattern pattern, boolean negate)
        {
            this.pattern = pattern;
            this.negate = negate;
        }

        private boolean isNegated() { return negate; }
        private boolean isNotNegated() { return !negate; }

        private boolean matches(String host)
        {
            Matcher matcher = pattern.matcher(host);
            return matcher.matches();
        }

        private static Rule[] of(String host)
        {
            List<Rule> rules = new ArrayList<>();
            for(String pattern : host.split("\\s+")) {
                boolean negate = pattern.startsWith("!");
                if(negate) pattern = pattern.substring(1);
                Pattern p = Pattern.compile(pattern.replace("*", ".*").replace("?", "."));
                rules.add(new Rule(p, negate));
            }
            return rules.toArray(new Rule[rules.size()]);
        }

    }
}
