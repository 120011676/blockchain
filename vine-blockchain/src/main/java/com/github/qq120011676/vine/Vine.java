package com.github.qq120011676.vine;

import com.github.qq120011676.vine.entity.Block;

import java.io.*;

public class Vine {
    public static void writer(Block block, String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(block.toString());
            bufferedWriter.flush();
        }
    }

    public static Block reader(String fileName) throws IOException {
        return Block.parse(new FileReader(fileName));
    }

    public static void writerJson(Block block, String fileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(block.toJson());
            bufferedWriter.flush();
        }
    }

    public static Block readerJson(String fileName) throws IOException {
        return Block.parseJson(new File(fileName));
    }

}
