package ru.saynurdinov.demo.forum.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topic", schema = "forum")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long id;

    @NotBlank(message = "topic's title must be not blank")
    @Size(min=3, message = "min length topic's title must be 3")
    private String title;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Message> messageList = new ArrayList<>();
}
