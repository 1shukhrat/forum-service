package ru.saynurdinov.demo.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.saynurdinov.demo.forum.DTO.EditTopicDTO;
import ru.saynurdinov.demo.forum.DTO.NewTopicDTO;
import ru.saynurdinov.demo.forum.entity.Topic;
import ru.saynurdinov.demo.forum.repository.TopicRepository;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService{

    private final TopicRepository topicRepository;
    private final MessageService messageService;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, MessageService messageService) {
        this.topicRepository = topicRepository;
        this.messageService = messageService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Topic> getAll(int page) {
        return Collections.unmodifiableList(topicRepository.findAll(PageRequest.of(page, 10)).getContent());
    }

    @Override
    @Transactional
    public Topic add(NewTopicDTO newTopic) {
        Topic topic = new Topic();
        topic.setTitle(newTopic.getTitle());
        topicRepository.save(topic);
        messageService.add(newTopic.getMessage(), topic.getId());
        return topic;
    }

    @Override
    @Transactional
    public Topic edit(EditTopicDTO editedTopic, long topicId) throws NoSuchElementException, AccessDeniedException{
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty()) {
            throw new NoSuchElementException("The topic is not found");
        }
        Topic topic = optionalTopic.get();
        if (!UserService.isAdmin()) {
            throw new AccessDeniedException("Access to the topic denied");
        }
        topic.setTitle(editedTopic.getTitle());
        return topicRepository.save(topic);
    }

    @Override
    public void delete(long topicId) throws NoSuchElementException, AccessDeniedException{
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty()) {
            throw new NoSuchElementException("The topic is not found");
        }
        Topic topic = optionalTopic.get();
        if (!UserService.isAdmin()) {
            throw new AccessDeniedException("Access to the topic denied");
        }
        topicRepository.delete(topic);
    }
}
