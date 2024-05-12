package com.chat.stomp.chatting;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChattingController {

    private final SimpMessagingTemplate simpMessagingTemplate;  // (1)

    @MessageMapping("/chattings/{chattingRoomId}/messages")
    public void chat(@DestinationVariable Long chattingRoomId, ChattingRequest chattingRequest) {
        simpMessagingTemplate.convertAndSend("/subscription/chattings/" + chattingRoomId, chattingRequest.getContent());
        log.info("Message [{}] send by member: {} to chatting room: {}", chattingRequest.getContent(), chattingRequest.getSenderId(), chattingRoomId);
    }
}