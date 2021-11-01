package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class IssueManager {

    private final IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void addIssue(long id, String author, String assignee, Set<String> labels) {
        Issue issue = new Issue(id, author, assignee, labels, true);
        repository.add(issue);
    }

    public void openIssue(long id) {
        Issue issue = repository.getById(id);
        if (issue.isOpen()) {
            throw new IllegalStateException("Issue already open");
        }
        issue.setOpen(true);
    }

    public void closeIssue(long id) {
        Issue issue = repository.getById(id);
        if (!issue.isOpen()) {
            throw new IllegalStateException("Issue already close");
        }
        issue.setOpen(false);
    }

    public List<Issue> getIssueByLabels(Set<String> labels) {
        List<Issue> all = repository.getAll();
        List<Issue> result = new LinkedList<>();
        for (Issue issue : all) {
            if (issue.getLabels().equals(labels)) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> getIssuesByAuthor(String author) {
        List<Issue> result = new LinkedList<>();
        List<Issue> all = repository.getAll();
        for (Issue issue : all) {
            if (issue.getAuthor().equals(author)) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> getIssuesByAssignee(String assignee) {
        List<Issue> all = repository.getAll();
        List<Issue> result = new LinkedList<>();
        for (Issue issue : all) {
            if (issue.getAssignee().equals(assignee)) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> getOpenIssues() {
        return getIssuesByStatus(true);
    }

    public List<Issue> getClosedIssues() {
        return getIssuesByStatus(false);
    }

    private List<Issue> getIssuesByStatus(boolean status) {
        List<Issue> all = repository.getAll();
        List<Issue> result = new LinkedList<>();
        for (Issue issue : all) {
            if (issue.isOpen() == status) {
                result.add(issue);
            }
        }
        return result;
    }
}
