package com.github.qq120011676.vine.entity;

import com.google.gson.Gson;
import lombok.Data;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@Data
public class Chunk implements Serializable {
    private Map<String, String> headers = new HashMap<>();
    private String content;

    private static final String HEADER_SEPARATOR = ": ";
    private static final Charset ENCODING = StandardCharsets.UTF_8;

    public String header(String name) {
        return this.headers.get(name);
    }

    public void header(String name, String value) {
        this.headers.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> names = this.getHeaders().keySet();
        for (String name : names) {
            stringBuilder.append(MessageFormat.format("{0}{1}{2}", name, HEADER_SEPARATOR, this.getHeaders().get(name)));
            stringBuilder.append(System.lineSeparator());
        }
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append(this.getContent());
        return stringBuilder.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public void writer(String pathname) throws IOException {
        write(this.toString(), pathname);
    }

    public void writerJson(String pathname) throws IOException {
        write(this.toJson(), pathname);
    }

    public static Chunk reader(String pathname) throws IOException {
        return parse(read(pathname));
    }

    public static Chunk parse(String s) throws IOException {
        Chunk chunk = new Chunk();
        String[] lines = s.split("\\r?\\n");
        boolean header = true;
        for (String line : lines) {
            if (header && Pattern.matches("^\\s*$", line)) {
                header = false;
                continue;
            }
            if (header) {
                String[] nv = line.split(HEADER_SEPARATOR);
                chunk.header(nv[0], nv[1]);
            } else {
                chunk.setContent(line);
            }
        }
        return chunk;
    }

    public static Chunk readerJson(String pathname) throws IOException {
        return parseJson(read(pathname));
    }

    public static Chunk parseJson(String json) {
        System.out.println(json);
        return new Gson().fromJson(json, Chunk.class);
    }

    private static String read(String pathname) throws IOException {
        return FileUtils.readFileToString(new File(pathname), ENCODING);
    }


    private static void write(String content, String pathname) throws IOException {
        FileUtils.writeStringToFile(new File(pathname), content, ENCODING);
    }
}
