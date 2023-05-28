package edu.whu.newspace.util;

import org.springframework.stereotype.Component;
import toolgood.words.StringSearch;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Newspace
 * @version 1.0
 * @description 敏感词检测工具类
 * @date 2023/5/24 16:58
 */
@Component
public class SensitiveFilterUtils {
    private static final List<String> sensitiveWords = new ArrayList<>();
    private static final StringSearch stringSearch = new StringSearch();


    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        String path = Thread.currentThread().getContextClassLoader().getResource("sensi_words.txt").getPath();
        path = URLDecoder.decode(path, "utf-8");
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine()) != null) {
                sensitiveWords.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stringSearch.SetKeywords(sensitiveWords);
     }

     public static boolean toolgoodWords(String content) {
         return stringSearch.ContainsAny(content);
     }
}
