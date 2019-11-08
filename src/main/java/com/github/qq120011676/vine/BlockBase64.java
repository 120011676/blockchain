package com.github.qq120011676.vine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Map;

public class BlockBase64 {
    private BlockCode blockCode;
    private static final String ENCODE_TYPE = "Base64";

    public BlockBase64(byte[] content, String previousSign) {
        blockCode = new BlockCode(Base64.getEncoder().encodeToString(content), ENCODE_TYPE, previousSign);
    }

    protected BlockBase64(BlockCode blockCode) {
        this.blockCode = blockCode;
    }

    public byte[] content() {
        return Base64.getDecoder().decode(this.blockCode.content());
    }

    public String encodeType() {
        return this.blockCode.encodeType();
    }

    public String previousSign() {
        return this.blockCode.previousSign();
    }

    public ZonedDateTime date() {
        return this.blockCode.date();
    }

    public String signType() {
        return this.blockCode.signType();
    }

    public String sign() {
        return this.blockCode.sign();
    }

    public String header(String name) {
        return this.blockCode.header(name);
    }

    public void header(String name, String value) {
        this.blockCode.header(name, value);
    }

    public Map<String, String> headers() {
        return this.blockCode.headers();
    }

    public String toJson() {
        return this.blockCode.toJson();
    }

    public String generateSign() {
        return this.blockCode.generateSign();
    }

    public boolean verify() {
        return this.blockCode.verify();
    }

    public void writer(String pathname) throws IOException {
        this.blockCode.writer(pathname);
    }

    public void writer(OutputStream outputStream) throws IOException {
        this.blockCode.writer(outputStream);
    }

    public void writerJson(String pathname) throws IOException {
        this.blockCode.writerJson(pathname);
    }

    public void writerJson(OutputStream outputStream) throws IOException {
        this.blockCode.writerJson(outputStream);
    }

    public static BlockBase64 reader(String pathname) throws IOException {
        return new BlockBase64(BlockCode.reader(pathname));
    }

    public static BlockBase64 readerUrl(String url) throws IOException {
        return new BlockBase64(BlockCode.readerUrl(url));
    }

    public static BlockBase64 reader(InputStream inputStream) throws IOException {
        return new BlockBase64(BlockCode.reader(inputStream));
    }

    public static BlockBase64 parse(String s) throws IOException {
        return new BlockBase64(BlockCode.reader(s));
    }

    public static BlockBase64 readerJson(String pathname) throws IOException {
        return new BlockBase64(BlockCode.readerJson(pathname));
    }

    public static BlockBase64 readerJsonUrl(String url) throws IOException {
        return new BlockBase64(BlockCode.readerJsonUrl(url));
    }

    public static BlockBase64 readerJson(InputStream inputStream) throws IOException {
        return new BlockBase64(BlockCode.readerJson(inputStream));
    }

    public static BlockBase64 parseJson(String json) {
        return new BlockBase64(BlockCode.parseJson(json));
    }
}
