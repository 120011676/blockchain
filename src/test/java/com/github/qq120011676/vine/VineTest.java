package com.github.qq120011676.vine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class VineTest {
    @Test
    void block() throws IOException {
        String blockFileName = "/tmp/test.block";
        String jsonFileName = "/tmp/test.json";

        String content = "中文中文";
        String previousSign = "11";
        Block block = new Block(content, previousSign);
        Vine.BlockHelper.writer(block, blockFileName);
        Vine.BlockHelper.writerJson(block, jsonFileName);

        Block blockRead = Vine.BlockHelper.reader(blockFileName);
        Assertions.assertNotNull(blockRead);
        Assertions.assertTrue(blockRead.verify());
        Assertions.assertEquals(content, blockRead.content());

        Block blockReadJson = Vine.BlockHelper.readerJson(jsonFileName);
        Assertions.assertNotNull(blockReadJson);
        Assertions.assertTrue(blockReadJson.verify());
        Assertions.assertEquals(content, blockReadJson.content());
    }

    @Test
    void blockBase64() throws IOException {
        String blockFileName = "/tmp/test.block";
        String jsonFileName = "/tmp/test.json";

        byte[] content = "中文中文".getBytes();
        String previousSign = "11";
        BlockBase64 block = new BlockBase64(content, previousSign);
        Vine.BlockBase64Helper.writer(block, blockFileName);
        Vine.BlockBase64Helper.writerJson(block, jsonFileName);

        BlockBase64 blockRead = Vine.BlockBase64Helper.reader(blockFileName);
        Assertions.assertNotNull(blockRead);
        Assertions.assertTrue(blockRead.verify());
        Assertions.assertArrayEquals(content, blockRead.content());

        BlockBase64 blockReadJson = Vine.BlockBase64Helper.readerJson(jsonFileName);
        Assertions.assertNotNull(blockReadJson);
        Assertions.assertTrue(blockReadJson.verify());
        Assertions.assertArrayEquals(content, blockReadJson.content());
    }
}
