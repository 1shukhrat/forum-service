package ru.saynurdinov.demo.forum.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import ru.saynurdinov.demo.forum.DTO.*;
import ru.saynurdinov.demo.forum.entity.Message;
import ru.saynurdinov.demo.forum.entity.Topic;
import ru.saynurdinov.demo.forum.service.MessageService;

import java.net.BindException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("topics/{topicId}/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<TopicWithMessagesDTO> getAll(@PathVariable("topicId") Long id, @RequestParam("page") int page)  throws NoSuchElementException{
        List<Message> messages = messageService.getAllByTopic(page, id);
        Topic topic = messages.getFirst().getTopic();
        return new ResponseEntity<>(new TopicWithMessagesDTO(new TopicDTO(topic.getId(),
                topic.getTitle()), messages.stream().map(message -> new MessageDTO(message.getId(), message.getText(), message.getDate(), message.getUser().getId())).toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MessageDTO> add(@Valid @RequestBody NewMessageDTO newMessageDTO, @PathVariable("topicId") Long id) throws NoSuchElementException, BindException{
        Message newMessage  = messageService.add(newMessageDTO, id);
        return new ResponseEntity<>(new MessageDTO(newMessage.getId(), newMessage.getText(), newMessage.getDate(), newMessage.getUser().getId()), HttpStatus.OK);
    }

    @PatchMapping("/{messageId}")
    public ResponseEntity<MessageDTO> edit(@PathVariable("messageId") Long messageId, @Valid @RequestBody EditMessageDTO messageDTO) throws NoSuchElementException, AccessDeniedException, BindException {
        Message editedMessage = messageService.edit(messageDTO, messageId);
        return new ResponseEntity<>(new MessageDTO(editedMessage.getId(), editedMessage.getText(), editedMessage.getDate(), editedMessage.getUser().getId()), HttpStatus.OK);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<String> delete(@PathVariable("messageId") Long id) throws NoSuchElementException, AccessDeniedException{
        messageService.delete(id);
        return new ResponseEntity<>("The message was successfully deleted", HttpStatus.NO_CONTENT);
    }


}
