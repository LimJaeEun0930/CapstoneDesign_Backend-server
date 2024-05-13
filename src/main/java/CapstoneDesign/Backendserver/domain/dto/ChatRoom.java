package CapstoneDesign.Backendserver.domain.dto;

import CapstoneDesign.Backendserver.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session); //add client
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨슴");  //welcome message
        }
        sendMessage(chatMessage, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService) {// 결국 메시지전송은 서비스계층에서 되는듯?
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
