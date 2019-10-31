package com.github.qq120011676.vine.entity;

import com.google.gson.Gson;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
public class Block implements Serializable {
    private Map<String, String> headers = new HashMap<>();
    private String content;
    private static final String PREVIOUS_SIGN = "Previous-Sign";
    private static final String DATE = "Date";
    private static final String SIGN_TYPE = "Sign-Type";
    private static final String SIGN = "Sign";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    private static final String HEADER_SEPARATOR = ": ";

    public Block(String content, String previousSign) {
        this.content = content;
        headers.put(PREVIOUS_SIGN, previousSign);
        headers.put(DATE, ZonedDateTime.now().format(DATE_TIME_FORMATTER));
        headers.put(SIGN_TYPE, "SHA3-512_HEX");
        headers.put(SIGN, DigestUtils.sha3_512Hex(this.content));
    }

    public String getPreviousSign() {
        return this.headers.get(PREVIOUS_SIGN);
    }

    public void setPreviousSign(String previousSign) {
        this.headers.put(PREVIOUS_SIGN, previousSign);
    }

    public ZonedDateTime getDate() {
        return ZonedDateTime.parse(this.headers.get(DATE));
    }

    public void setDate(ZonedDateTime zonedDateTime) {
        this.headers.put(DATE, zonedDateTime.format(DATE_TIME_FORMATTER));
    }

    public String getSignType() {
        return this.headers.get(SIGN_TYPE);
    }

    public void setSignType(String signType) {
        this.headers.put(SIGN_TYPE, signType);
    }

    public String getSign() {
        return this.headers.get(SIGN);
    }

    public void setSign(String sign) {
        this.headers.put(SIGN, sign);
    }

    public String header(String name) {
        return this.headers.get(name);
    }

    public void header(String name, String value) {
        this.headers.put(name, value);
    }

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


//    public static Block parseString(String s) {
//        Block block = new Block();
//        String[] lines = s.split("\\r?\\n");
//        boolean header = true;
//        for (String line : lines) {
//            if (header && Pattern.matches("^\\s*$", line)) {
//                header = false;
//                continue;
//            }
//            if (header) {
//                String[] nv = line.split(HEADER_SEPARATOR);
//                block.header(nv[0], nv[1]);
//            } else {
//                block.setContent(line);
//            }
//        }
//        return block;
//    }

    public static Block parse(String s) throws IOException {
        return parse(new StringReader(s));
    }

    public static Block parse(File file) throws IOException {
        return parse(new FileReader(file));
    }

    public static Block parse(InputStream inputStream) throws IOException {
        return parse(new InputStreamReader(inputStream));
    }

    public static Block parse(Reader reader) throws IOException {
        try (LineNumberReader lineNumberReader = new LineNumberReader(reader)) {
            Block block = new Block();
            boolean header = true;
            String line;
            while ((line = lineNumberReader.readLine()) != null) {
                if (header && Pattern.matches("^\\s*$", line)) {
                    header = false;
                    continue;
                }
                if (header) {
                    String[] nv = line.split(HEADER_SEPARATOR);
                    block.header(nv[0], nv[1]);
                } else {
                    block.setContent(line);
                }
            }
            return block;
        }
    }

    public static Block parseJson(String json) {
        return new Gson().fromJson(json, Block.class);
    }

    public static Block parseJson(File file) throws IOException {
        return parseJson(IOUtils.toString(new FileReader(file)));
    }

    public static Block parseJson(InputStream inputStream) throws IOException {
        return parseJson(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
    }
}
