package com.github.qq120011676.vine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class VineTest {
    @Test
    void test() throws IOException {
        String blockFileName = "/tmp/test.block";
        String jsonFileName = "/tmp/test.json";

        Block block = new Block("中文中文", "11");

        Vine.writer(block, blockFileName);
        Vine.writerJson(block, jsonFileName);

        Block blockRead = Vine.reader(blockFileName);
        System.out.println(blockRead);
        Assertions.assertNotNull(blockRead);

        Block blockReadJson = Vine.readerJson(jsonFileName);
        System.out.println(blockReadJson);
        Assertions.assertNotNull(blockReadJson);
    }
}
