package com.github.qq120011676.vine;

import com.github.qq120011676.vine.entity.Block;

import java.io.*;

public class Vine {
    public static void writer(Block block, String pathname) throws IOException {
        block.writer(pathname);
    }

    public static Block reader(String pathname) throws IOException {
        return Block.parse(pathname);
    }

    public static void writerJson(Block block, String pathname) throws IOException {
        block.writerJson(pathname);
    }

    public static Block readerJson(String pathname) throws IOException {
        return Block.parseJson(pathname);
    }

}
