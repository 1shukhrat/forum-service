package ru.saynurdinov.demo.forum.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import ru.saynurdinov.demo.forum.DTO.*;
import ru.saynurdinov.demo.forum.entity.Topic;
import ru.saynurdinov.demo.forum.service.TopicService;

import java.net.BindException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAll(@RequestParam("page") int page) {
        List<Topic> topicList = topicService.getAll(page);
        return new ResponseEntity<>(topicList.stream().map(topic -> new TopicDTO(topic.getId(), topic.getTitle())).toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TopicWithMessagesDTO> add(@Valid @RequestBody NewTopicDTO newTopicDTO) throws BindException{
        Topic topic = topicService.add(newTopicDTO);
        return new ResponseEntity<>(new TopicWithMessagesDTO(new TopicDTO(topic.getId(), topic.getTitle()),
                topic.getMessageList().stream().map(message -> new MessageDTO(message.getId(), message.getText(), message.getDate(),message.getUser().getId())).toList()), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TopicDTO> edit(@Valid @RequestBody EditTopicDTO editTopicDTO, @PathVariable("id") Long id) throws NoSuchElementException, AccessDeniedException, BindException {
        Topic editedTopic = topicService.edit(editTopicDTO, id);
        return new ResponseEntity<>(new TopicDTO(editedTopic.getId(), editedTopic.getTitle()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) throws NoSuchElementException, AccessDeniedException, BindException{
        topicService.delete(id);
        return new ResponseEntity<>("The topic was successfully deleted", HttpStatus.NO_CONTENT);
    }
}
