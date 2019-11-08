package com.github.qq120011676.vine;

import com.github.qq120011676.chunk.Chunk;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.function.Consumer;

public class Block {
    private Chunk chunk = new Chunk();
    private static final String PREVIOUS_SIGN_NAME = "Previous-Sign";
    private static final String DATE_NAME = "Date";
    private static final String SIGN_TYPE_NAME = "Sign-Type";
    private static final String SIGN_NAME = "Sign";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    public Block(String content, String previousSign) {
        this.chunk.setContent(content);
        this.chunk.header(PREVIOUS_SIGN_NAME, previousSign);
        this.chunk.header(DATE_NAME, ZonedDateTime.now().format(DATE_TIME_FORMATTER));
    }

    protected Block(Chunk chunk) {
        this.chunk = chunk;
    }

    public String content() {
        return this.chunk.getContent();
    }

    public String previousSign() {
        return this.chunk.header(PREVIOUS_SIGN_NAME);
    }

    public ZonedDateTime date() {
        return ZonedDateTime.parse(this.chunk.header(DATE_NAME));
    }

    public String signType() {
        return this.chunk.header(SIGN_TYPE_NAME);
    }

    public String sign() {
        return this.chunk.header(SIGN_NAME);
    }

    public String header(String name) {
        return this.chunk.header(name);
    }

    public void header(String name, String value) {
        this.chunk.header(name, value);
    }

    public Map<String, String> headers() {
        return this.chunk.getHeaders();
    }

    @Override
    public String toString() {
        return this.chunk.toString();
    }

    public String toJson() {
        return this.chunk.toJson();
    }

    public String generateSign() {
        StringBuilder signData = new StringBuilder();
        this.chunk.getHeaders().keySet().stream().sorted().forEach(new Consumer<String>() {
            @Override
            public void accept(String name) {
                signData.append(name);
                signData.append(chunk.header(name));
            }
        });
        signData.append(this.chunk.getContent());
        String sign = DigestUtils.sha3_512Hex(signData.toString());
        this.chunk.header(SIGN_TYPE_NAME, sign);
        this.chunk.header(SIGN_TYPE_NAME, "SHA3-512_HEX");
        return sign;
    }

    public boolean verify() {
        StringBuilder signData = new StringBuilder();
        this.chunk.getHeaders().keySet().stream().sorted().forEach(new Consumer<String>() {
            @Override
            public void accept(String name) {
                if (!SIGN_NAME.equals(name)) {
                    signData.append(name);
                    signData.append(chunk.header(name));
                }
            }
        });
        signData.append(this.chunk.getContent());
        return this.sign().equals(DigestUtils.sha3_512Hex(signData.toString()));
    }

    public void writer(String pathname) throws IOException {
        this.chunk.writer(pathname);
    }

    public void writer(OutputStream outputStream) throws IOException {
        this.chunk.writer(outputStream);
    }

    public void writerJson(String pathname) throws IOException {
        this.chunk.writerJson(pathname);
    }

    public void writerJson(OutputStream outputStream) throws IOException {
        this.chunk.writerJson(outputStream);
    }

    public static Block reader(String pathname) throws IOException {
        return new Block(Chunk.reader(pathname));
    }

    public static Block readerUrl(String url) throws IOException {
        return new Block(Chunk.readerUrl(url));
    }

    public static Block reader(InputStream inputStream) throws IOException {
        return new Block(Chunk.reader(inputStream));
    }

    public static Block parse(String s) throws IOException {
        return new Block(Chunk.parse(s));
    }

    public static Block readerJson(String pathname) throws IOException {
        return new Block(Chunk.readerJson(pathname));
    }

    public static Block readerJsonUrl(String url) throws IOException {
        return new Block(Chunk.readerJsonUrl(url));
    }

    public static Block readerJson(InputStream inputStream) throws IOException {
        return new Block(Chunk.readerJson(inputStream));
    }

    public static Block parseJson(String json) {
        return new Block(Chunk.parseJson(json));
    }

}
