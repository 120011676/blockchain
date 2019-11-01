package com.github.qq120011676.vine;

import com.github.qq120011676.vine.entity.Chunk;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ChunkTest {
    @Test
    void test() throws IOException {
        String chunkFileName = "/tmp/test.chunk";
        String jsonFileName = "/tmp/test.json";

        Chunk chunk = new Chunk();
        chunk.setContent("中午、中文");
        chunk.header("a", "a1");

        chunk.writer(chunkFileName);
        chunk.writerJson(jsonFileName);

        Chunk chunkRead = Chunk.reader(chunkFileName);
        System.out.println(chunkRead);
        Assertions.assertNotNull(chunkRead);

        Chunk chunkJsonRead = Chunk.readerJson(jsonFileName);
        System.out.println(chunkJsonRead);
        Assertions.assertNotNull(chunkJsonRead);
    }
}
