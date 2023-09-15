package com.marktoledo.todolistapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "todo_app_todo")
public class Todo extends BaseEntity{

    @Column(length = 64, nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private Date dueDate;

    @Column()
    @Builder.Default
    private Boolean isCompleted = false;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
