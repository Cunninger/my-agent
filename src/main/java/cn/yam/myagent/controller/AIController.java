package cn.yam.myagent.controller;

import cn.yam.myagent.service.AIService;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message,@RequestParam String chatId) {
        return aiService.doChat(message,chatId);
    }


    @GetMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatResponse> chatStream(@RequestParam String message, @RequestParam String chatId) {
        Flux<ChatResponse> chatResponseFlux = aiService.doChatStreamResponse(message, chatId);
        return chatResponseFlux;
    }


    @GetMapping("/chat/history")
    public List<Message> chatHistory(@RequestParam String chatId, @RequestParam(defaultValue = "20") int lastN) {
        return aiService.getChatHistory(chatId, lastN);
    }

    // 新建对话
    @PostMapping("/chat/new")
    public ResponseEntity<String> newChat(@RequestParam String chatId) {
        aiService.createChat(chatId);
        return ResponseEntity.ok("Chat created");
    }

    // 删除对话
    @PostMapping("/chat/delete")
    public ResponseEntity<String> deleteChat(@RequestParam String chatId) {
        aiService.deleteChat(chatId);
        return ResponseEntity.ok("Chat deleted");
    }

    // 获取所有chatId
    @GetMapping("/chat/list")
    public List<String> listAllChats() {
        return aiService.listAllChatIds();
    }

}
