package com.github.qq120011676.vine;

import com.github.qq120011676.vine.entity.Block;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class VineTest {
    @Test
    void test() throws IOException {
        String blockFileName = "/tmp/test.block";
        String jsonFileName = "/tmp/test.json";

        Block block = new Block("aaa", "11");

        Vine.writer(block, blockFileName);
        Vine.writerJson(block, jsonFileName);

        Vine.reader(blockFileName);
        Vine.readerJson(jsonFileName);
    }
}
