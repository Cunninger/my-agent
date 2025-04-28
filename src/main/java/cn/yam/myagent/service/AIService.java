package cn.yam.myagent.service;

import cn.yam.myagent.chatmemory.FileBasedChatMemory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


import javax.validation.Valid;

import java.util.List;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@Service
@Slf4j
public class AIService {


    private final ChatClient chatClient;
    private final ChatMemory chatMemory; // 新增成员变量

    public AIService(ChatClient.Builder builder, @Value("${spring.ai.chat.memory.dir}") String chatMemoryDir) {
        this.chatMemory = new FileBasedChatMemory(chatMemoryDir); // 赋值
        this.chatClient = builder
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .build();
    }


    public String doChat(String message, String chatId) {
        ChatResponse chatResponse = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call()
                .chatResponse();
        String content = chatResponse.getResult().getOutput().getText();
        log.info("content: {}", content);
        return content;
    }


    public Flux<ChatResponse> doChatStreamResponse(String message, String chatId) {
        Flux<ChatResponse> chatResponseFlux = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .stream()
                .chatResponse()
                .doOnNext(resp -> System.out.println(resp)); // 打印每一项
        return chatResponseFlux;
    }


    public List<Message> getChatHistory(String chatId, int lastN) {

        return chatMemory.get(chatId, lastN);
    }


    public void createChat(String chatId) {
        if (chatMemory instanceof FileBasedChatMemory fileBasedChatMemory) {
            fileBasedChatMemory.create(chatId);
        }
    }

    public void deleteChat(String chatId) {
        chatMemory.clear(chatId);
    }

    /**
     * 展示所有的chatID
     * @return
     */
    public List<String> listAllChatIds() {
        if (chatMemory instanceof FileBasedChatMemory fileBasedChatMemory) {
            return fileBasedChatMemory.listAllConversations();
        }
        return List.of();
    }
}