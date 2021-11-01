package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Issue {
    private long id;
    private String author;
    private String assignee;
    private Set<String> labels;
    private boolean isOpen;
}
