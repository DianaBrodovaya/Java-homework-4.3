package ru.netology.repository;

import ru.netology.domain.Issue;
import ru.netology.exception.NotFoundException;

import java.util.LinkedList;
import java.util.List;

public class IssueRepository {

    private final List<Issue> issueList = new LinkedList<>();

    public void add(Issue issue){
        issueList.add(issue);
    }

    public Issue getById(long id){
        for (Issue issue : issueList){
            if (issue.getId() == id){
                return issue;
            }
        }
        throw new NotFoundException("cant find exception with id " + id);
    }

    public void removeById(long id){
        issueList.removeIf(issue -> issue.getId() == id);
    }

    public List<Issue> getAll(){
        return issueList;
    }
}
