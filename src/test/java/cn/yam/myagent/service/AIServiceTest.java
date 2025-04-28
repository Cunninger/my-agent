package cn.yam.myagent.service;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AIServiceTest {

    @Resource
    private static AIService aiService;
    public static void main(String[] args) {
        aiService.doChatStreamResponse("hello", "testChatId").blockLast();
    }
}