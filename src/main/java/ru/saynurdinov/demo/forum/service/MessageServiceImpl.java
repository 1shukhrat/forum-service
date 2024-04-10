package ru.saynurdinov.demo.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.saynurdinov.demo.forum.DTO.EditMessageDTO;
import ru.saynurdinov.demo.forum.DTO.NewMessageDTO;
import ru.saynurdinov.demo.forum.entity.Message;
import ru.saynurdinov.demo.forum.entity.Topic;
import ru.saynurdinov.demo.forum.repository.MessageRepository;
import ru.saynurdinov.demo.forum.repository.TopicRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {


    private final MessageRepository messageRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, TopicRepository topicRepository) {
        this.messageRepository = messageRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAllByTopic(int page, long topicId) throws NoSuchElementException {
        if (topicRepository.findById(topicId).isEmpty()) {
            throw new NoSuchElementException("The topic is not found");
        }
        return Collections.unmodifiableList(messageRepository.findAllByTopicId(topicId, PageRequest.of(page, 10)).getContent());
    }

    @Override
    @Transactional
    public Message add(NewMessageDTO newMessage, long topicId) throws NoSuchElementException {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty()) {
            throw new NoSuchElementException("The topic is not found");
        }
        Topic topic = optionalTopic.get();
        Message message = new Message();
        message.setText(newMessage.getText());
        message.setUser(UserService.getAuthenticated().getUser());
        message.setDate(LocalDateTime.now());
        message.setTopic(topic);
        messageRepository.save(message);
        topic.getMessageList().add(message);
        return message;

    }

    @Override
    @Transactional
    public Message edit(EditMessageDTO editedMessage, long messageId) throws NoSuchElementException, AccessDeniedException{
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isEmpty()) {
            throw new NoSuchElementException("The message is not found");
        } else {
            Message message = optionalMessage.get();
            if (message.getUser().getUsername().equals(UserService.getAuthenticated().getUsername()) || UserService.isAdmin()) {
                message.setText(editedMessage.getText());
                return messageRepository.save(message);
            } else {
                throw new AccessDeniedException("Access to the message denied");
            }

        }

    }

    @Override
    @Transactional
    public void delete(long messageId) throws NoSuchElementException, AccessDeniedException {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isEmpty()) {
            throw new NoSuchElementException("The message is not found");
        } else {
            Message message = optionalMessage.get();
            if (!UserService.isAdmin() && !message.getUser().getUsername().equals(UserService.getAuthenticated().getUsername())) {
                throw new AccessDeniedException("Access to message denied");
            }
            if (message.getUser().getUsername().equals(UserService.getAuthenticated().getUsername()) || UserService.isAdmin()) {
                message.getTopic().getMessageList().remove(message);
                messageRepository.delete(message);
                if (message.getTopic().getMessageList().isEmpty()) {
                    topicRepository.delete(message.getTopic());
                }
            } else {
                throw new AccessDeniedException("Access to the message denied");
            }
        }
    }
}
