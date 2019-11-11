package com.github.qq120011676.vine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class BlockCode {
    private Block block;
    private static final String ENCODE_TYPE_NAME = "Encode-type";

    public BlockCode(String content, String encodeType, String previousSign) {
        Map<String, String> headers = new HashMap<>();
        headers.put(ENCODE_TYPE_NAME, encodeType);
        this.block = new Block(content, previousSign, headers);
    }

    protected BlockCode(Block block) {
        this.block = block;
    }

    public String encodeType() {
        return this.block.header(ENCODE_TYPE_NAME);
    }

    public String content() {
        return this.block.content();
    }

    public String previousSign() {
        return this.block.previousSign();
    }

    public ZonedDateTime date() {
        return this.block.date();
    }

    public String signType() {
        return this.block.signType();
    }

    public String sign() {
        return this.block.sign();
    }

    public String header(String name) {
        return this.block.header(name);
    }

    public void header(String name, String value) {
        this.block.header(name, value);
    }

    public Map<String, String> headers() {
        return this.block.headers();
    }

    public String toJson() {
        return this.block.toJson();
    }

    public boolean verify() {
        return this.block.verify();
    }

    public void writer(String pathname) throws IOException {
        this.block.writer(pathname);
    }

    public void writer(OutputStream outputStream) throws IOException {
        this.block.writer(outputStream);
    }

    public void writerJson(String pathname) throws IOException {
        this.block.writerJson(pathname);
    }

    public void writerJson(OutputStream outputStream) throws IOException {
        this.block.writerJson(outputStream);
    }

    public static BlockCode reader(String pathname) throws IOException {
        return new BlockCode(Block.reader(pathname));
    }

    public static BlockCode readerUrl(String url) throws IOException {
        return new BlockCode(Block.readerUrl(url));
    }

    public static BlockCode reader(InputStream inputStream) throws IOException {
        return new BlockCode(Block.reader(inputStream));
    }

    public static BlockCode parse(String s) throws IOException {
        return new BlockCode(Block.parse(s));
    }

    public static BlockCode readerJson(String pathname) throws IOException {
        return new BlockCode(Block.readerJson(pathname));
    }

    public static BlockCode readerJsonUrl(String url) throws IOException {
        return new BlockCode(Block.readerJsonUrl(url));
    }

    public static BlockCode readerJson(InputStream inputStream) throws IOException {
        return new BlockCode(Block.readerJson(inputStream));
    }

    public static BlockCode parseJson(String json) {
        return new BlockCode(Block.parseJson(json));
    }

}
