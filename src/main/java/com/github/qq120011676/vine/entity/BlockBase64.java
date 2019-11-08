package com.github.qq120011676.vine.entity;

import java.util.Base64;

public class BlockBase64 {
    private BlockCode blockCode;
    private static final String ENCODE_TYPE = "Base64";

    public BlockBase64(byte[] content, String previousSign) {
        blockCode = new BlockCode(Base64.getEncoder().encodeToString(content), ENCODE_TYPE, previousSign);
    }

    public byte[] content() {
        return Base64.getDecoder().decode(this.blockCode.content());
    }
}
