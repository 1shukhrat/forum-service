package ru.saynurdinov.demo.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.saynurdinov.demo.forum.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findAllByTopicId(Long topicId, Pageable p);
}
