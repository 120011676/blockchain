package com.github.qq120011676.vine.entity;

import com.github.qq120011676.chunk.Chunk;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

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
        this.chunk.header(SIGN_TYPE_NAME, "SHA3-512_HEX");
        this.chunk.header(SIGN_NAME, DigestUtils.sha3_512Hex(content));
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

    public Map<String, String> headers(String name) {
        return this.chunk.getHeaders();
    }
}
