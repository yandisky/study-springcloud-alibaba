package org.study.utils.md5;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Slf4j
public class MD5Hash {
    /**
     * MD5加密
     *
     * @param text 待加密字符串
     * @return 16进制加密字符串
     */
    @SneakyThrows(Exception.class)
    public static String encryptToMD5(String text) {
        return DigestUtils.md5DigestAsHex(text.getBytes(StandardCharsets.UTF_8));
    }
}
