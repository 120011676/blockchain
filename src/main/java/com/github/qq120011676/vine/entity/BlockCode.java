package com.github.qq120011676.vine.entity;

public class BlockCode extends Block {
    private static final String ENCODE_TYPE_NAME = "Encode-type";

    public BlockCode(String content, String encodeType, String previousSign) {
        super(content, previousSign);
        super.header(ENCODE_TYPE_NAME, encodeType);
    }

    public String encodeType() {
        return super.header(ENCODE_TYPE_NAME);
    }

}
