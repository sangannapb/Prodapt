package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Skeleton template for a controller test using MockMvc.
 *
 * You can use annotations from JUnit 5 such as @ParameterizedTest, @ValueSauce,
 * @CsvSource and @MethodSource for your test data.
 *
 * Example usage of mockMvc for a GET request
 * mockMvc.perform(get("/path-to-your-endpoint").param("your-query-param", param-value))
 *                 .andExpect(status().whateverStatusCodeYouExpect())
 *                 .andExpect(content().string("string-you-expect-in-response")).
 *                 .andExpect(jsonPath("$.jsonField").value("json-value-you-expect"));
 */
@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCommonWords() throws Exception {
        mockMvc.perform(get("/remove").param("word", "eloquent"))
                .andExpect(status().isOk())
                .andExpect(content().string("loquen"));

        mockMvc.perform(get("/remove").param("word", "country"))
                .andExpect(status().isOk())
                .andExpect(content().string("ountr"));

        mockMvc.perform(get("/remove").param("word", "person"))
                .andExpect(status().isOk())
                .andExpect(content().string("erso"));
    }

    @Test
    void testEdgeCases_0to3Chars() throws Exception {
        mockMvc.perform(get("/remove").param("word", ""))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/remove").param("word", "a"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/remove").param("word", "ab"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        mockMvc.perform(get("/remove").param("word", "xyz"))
                .andExpect(status().isOk())
                .andExpect(content().string("y"));
    }

    @Test
    void testSpecialCharacters() throws Exception {
        mockMvc.perform(get("/remove").param("word", "123%qwerty+"))
                .andExpect(status().isOk())
                .andExpect(content().string("23%qwerty"));

        mockMvc.perform(get("/remove").param("word", "!@Hello#"))
                .andExpect(status().isOk())
                .andExpect(content().string("@Hello"));

        mockMvc.perform(get("/remove").param("word", "99!a@"))
                .andExpect(status().isOk())
                .andExpect(content().string("9!a"));
    }

}
