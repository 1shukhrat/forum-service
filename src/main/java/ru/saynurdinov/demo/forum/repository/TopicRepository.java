package ru.saynurdinov.demo.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.saynurdinov.demo.forum.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Page<Topic> findAll(Pageable p);
}
