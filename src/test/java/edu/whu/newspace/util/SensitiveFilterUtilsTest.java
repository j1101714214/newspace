package edu.whu.newspace.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SensitiveFilterUtilsTest {

    @Test
    public void sensitiveWordDetect() {
        assertTrue(SensitiveFilterUtils.toolgoodWords("聚商"));
        assertFalse(SensitiveFilterUtils.toolgoodWords("世界"));
    }
}