package com.github.qq120011676.vine.entity;

import com.google.gson.Gson;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
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
    private static final String ENCODE_TYPE = "Encode-type";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    private static final String HEADER_SEPARATOR = ": ";
    private static final Charset ENCODING = StandardCharsets.UTF_8;

    public Block(String content, String previousSign) {
        this.content = content;
        headers.put(PREVIOUS_SIGN, previousSign);
        headers.put(DATE, ZonedDateTime.now().format(DATE_TIME_FORMATTER));
        headers.put(SIGN_TYPE, "SHA3-512_HEX");
        headers.put(SIGN, DigestUtils.sha3_512Hex(this.content));
        headers.put(ENCODE_TYPE, "None");
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

    public String getEncodeType() {
        return this.headers.get(ENCODE_TYPE);
    }

    public void setEncodeType(String encodeType) {
        this.headers.put(ENCODE_TYPE, encodeType);
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

    public void writer(String pathname) throws IOException {
        FileUtils.writeStringToFile(new File(pathname), this.toString(), ENCODING);
    }

    public void writerJson(String pathname) throws IOException {
        FileUtils.writeStringToFile(new File(pathname), this.toJson(), ENCODING);
    }

    public static Block parseString(String s) throws IOException {
        Block block = new Block();
        String[] lines = s.split("\\r?\\n");
        boolean header = true;
        for (String line : lines) {
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

    public static Block parse(String pathname) throws IOException {
        return parse(new File(pathname));
    }

    public static Block parse(File file) throws IOException {
        return parse(new FileInputStream(file));
    }

    public static Block parse(InputStream inputStream) throws IOException {
        return parseString(IOUtils.toString(inputStream, ENCODING));
    }

    public static Block parseJsonString(String json) {
        return new Gson().fromJson(json, Block.class);
    }

    public static Block parseJson(String pathname) throws IOException {
        return parseJson(new File(pathname));
    }

    public static Block parseJson(File file) throws IOException {
        return parseJson(new FileInputStream(file));
    }

    public static Block parseJson(InputStream inputStream) throws IOException {
        return parseJsonString(IOUtils.toString(inputStream, ENCODING));
    }
}
