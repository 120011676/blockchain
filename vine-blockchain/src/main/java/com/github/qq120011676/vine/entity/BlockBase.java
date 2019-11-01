package com.github.qq120011676.vine.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class BlockBase {
    private Map<String, String> headers = new HashMap<>();
    private String content;
}
