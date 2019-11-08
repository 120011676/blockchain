package com.github.qq120011676.vine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Vine {
    public BlockHelper blockHelper() {
        return new BlockHelper();
    }

    public BlockCodeHelper blockCodeHelper() {
        return new BlockCodeHelper();
    }

    public BlockBase64Helper blockBase64Helper() {
        return new BlockBase64Helper();
    }

    public static class BlockHelper {

        public void writer(Block block, String pathname) throws IOException {
            block.writer(pathname);
        }

        public void writer(Block block, OutputStream outputStream) throws IOException {
            block.writer(outputStream);
        }

        public void writerJson(Block block, String pathname) throws IOException {
            block.writerJson(pathname);
        }

        public void writerJson(Block block, OutputStream outputStream) throws IOException {
            block.writerJson(outputStream);
        }

        public static Block reader(String pathname) throws IOException {
            return Block.reader(pathname);
        }

        public static Block readerUrl(String url) throws IOException {
            return Block.readerUrl(url);
        }

        public static Block reader(InputStream inputStream) throws IOException {
            return Block.reader(inputStream);
        }

        public static Block parse(String s) throws IOException {
            return Block.parse(s);
        }

        public static Block readerJson(String pathname) throws IOException {
            return Block.readerJson(pathname);
        }

        public static Block readerJsonUrl(String url) throws IOException {
            return Block.readerJsonUrl(url);
        }

        public static Block readerJson(InputStream inputStream) throws IOException {
            return Block.readerJson(inputStream);
        }

        public static Block parseJson(String json) {
            return Block.parseJson(json);
        }
    }

    public static class BlockCodeHelper {

        public void writer(BlockCode blockCode, String pathname) throws IOException {
            blockCode.writer(pathname);
        }

        public void writer(BlockCode blockCode, OutputStream outputStream) throws IOException {
            blockCode.writer(outputStream);
        }

        public void writerJson(BlockCode blockCode, String pathname) throws IOException {
            blockCode.writerJson(pathname);
        }

        public void writerJson(BlockCode blockCode, OutputStream outputStream) throws IOException {
            blockCode.writerJson(outputStream);
        }

        public static BlockCode reader(String pathname) throws IOException {
            return BlockCode.reader(pathname);
        }

        public static BlockCode readerUrl(String url) throws IOException {
            return BlockCode.readerUrl(url);
        }

        public static BlockCode reader(InputStream inputStream) throws IOException {
            return BlockCode.reader(inputStream);
        }

        public static BlockCode parse(String s) throws IOException {
            return BlockCode.parse(s);
        }

        public static BlockCode readerJson(String pathname) throws IOException {
            return BlockCode.readerJson(pathname);
        }

        public static BlockCode readerJsonUrl(String url) throws IOException {
            return BlockCode.readerJsonUrl(url);
        }

        public static BlockCode readerJson(InputStream inputStream) throws IOException {
            return BlockCode.readerJson(inputStream);
        }

        public static BlockCode parseJson(String json) {
            return BlockCode.parseJson(json);
        }
    }

    public static class BlockBase64Helper {

        public void writer(BlockBase64 blockBase64, String pathname) throws IOException {
            blockBase64.writer(pathname);
        }

        public void writer(BlockBase64 blockBase64, OutputStream outputStream) throws IOException {
            blockBase64.writer(outputStream);
        }

        public void writerJson(BlockBase64 blockBase64, String pathname) throws IOException {
            blockBase64.writerJson(pathname);
        }

        public void writerJson(BlockBase64 blockBase64, OutputStream outputStream) throws IOException {
            blockBase64.writerJson(outputStream);
        }

        public static BlockBase64 reader(String pathname) throws IOException {
            return BlockBase64.reader(pathname);
        }

        public static BlockBase64 readerUrl(String url) throws IOException {
            return BlockBase64.readerUrl(url);
        }

        public static BlockBase64 reader(InputStream inputStream) throws IOException {
            return BlockBase64.reader(inputStream);
        }

        public static BlockBase64 parse(String s) throws IOException {
            return BlockBase64.parse(s);
        }

        public static BlockBase64 readerJson(String pathname) throws IOException {
            return BlockBase64.readerJson(pathname);
        }

        public static BlockBase64 readerJsonUrl(String url) throws IOException {
            return BlockBase64.readerJsonUrl(url);
        }

        public static BlockBase64 readerJson(InputStream inputStream) throws IOException {
            return BlockBase64.readerJson(inputStream);
        }

        public static BlockBase64 parseJson(String json) {
            return BlockBase64.parseJson(json);
        }
    }
}
