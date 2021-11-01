package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Issue;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.IssueRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.netology.manager.DomainUtils.*;

@ExtendWith(MockitoExtension.class)
class IssueManagerTest {

    @Mock
    private IssueRepository repository;
    @InjectMocks
    private IssueManager issueManager;

    @Test
    public void shouldAddIssue() {
        Issue issue = new Issue(12, "asd", "asd", Sets.newSet("q", "w", "e"), true);
        issueManager.addIssue(12, "asd", "asd", Sets.newSet("q", "w", "e"));
        Mockito.verify(repository).add(issue);
    }

    @Test
    public void shouldOpenIssue() {
        Mockito.doReturn(issue7).when(repository).getById(issue7.getId());
        issueManager.openIssue(7);
    }

    @Test
    public void shouldCloseIssue() {
        Mockito.doReturn(issue1).when(repository).getById(issue1.getId());
        issueManager.closeIssue(1);
    }

    @Test
    public void shouldOpenIssueIssueNotFound() {
        Mockito.doThrow(NotFoundException.class).when(repository).getById(78);
        assertThrows(NotFoundException.class, () -> issueManager.openIssue(78));
    }

    @Test
    public void shouldCloseIssueIssueNotFound() {
        Mockito.doThrow(NotFoundException.class).when(repository).getById(78);
        assertThrows(NotFoundException.class, () -> issueManager.closeIssue(78));
    }

    @Test
    public void shouldOpenIssueWithError() {
        Mockito.doReturn(issue1).when(repository).getById(issue1.getId());
        assertThrows(IllegalStateException.class, () -> issueManager.openIssue(issue1.getId()));
    }

    @Test
    public void shouldCloseIssueWithError() {
        Mockito.doReturn(issue7).when(repository).getById(issue7.getId());
        assertThrows(IllegalStateException.class, () -> issueManager.closeIssue(issue7.getId()));
    }

    @Test
    public void getIssueByLabels() {
        Mockito.doReturn(fullList).when(repository).getAll();
        Set<String> labels1 = Sets.newSet("label1", "label2", "label3");
        Set<String> labels2 = Sets.newSet("label2", "label3");
        Set<String> labels3 = Sets.newSet("label2");
        assertIterableEquals(Sets.newSet(issue1, issue10, issue11), issueManager.getIssueByLabels(labels1));
        assertIterableEquals(Sets.newSet(issue3, issue4, issue5, issue6), issueManager.getIssueByLabels(labels2));
        assertIterableEquals(Sets.newSet(issue7, issue8, issue9), issueManager.getIssueByLabels(labels3));
    }

    @Test
    public void getIssuesByAuthor() {
        Mockito.doReturn(fullList).when(repository).getAll();
        assertIterableEquals(Sets.newSet(issue1, issue2, issue3, issue4), issueManager.getIssuesByAuthor("author1"));
        assertIterableEquals(Sets.newSet(issue5, issue6, issue7, issue8), issueManager.getIssuesByAuthor("author2"));
        assertIterableEquals(Sets.newSet(issue9, issue10, issue11), issueManager.getIssuesByAuthor("author3"));
    }

    @Test
    public void getIssuesByAssignee() {
        Mockito.doReturn(fullList).when(repository).getAll();
        assertIterableEquals(Sets.newSet(issue11, issue12, issue14, issue15), issueManager.getIssuesByAssignee("assignee1"));
        assertIterableEquals(Sets.newSet(issue1, issue2, issue3, issue4, issue9), issueManager.getIssuesByAssignee("assignee2"));
        assertIterableEquals(Sets.newSet(issue13), issueManager.getIssuesByAssignee("assignee3"));
    }

    @Test
    public void shouldGetOpenIssues() {
        Mockito.doReturn(fullList).when(repository).getAll();
        List<Issue> openIssues = issueManager.getOpenIssues();
        assertIterableEquals(DomainUtils.openIssues, openIssues);
    }

    @Test
    public void shouldGetClosedIssues() {
        Mockito.doReturn(fullList).when(repository).getAll();
        List<Issue> closedIssues = issueManager.getClosedIssues();
        assertIterableEquals(DomainUtils.closedIssues, closedIssues);
    }
}