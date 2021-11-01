package ru.netology.manager;

import org.mockito.internal.util.collections.Sets;
import ru.netology.domain.Issue;

import java.util.List;

public class DomainUtils {

    public static final Issue issue1 = new Issue(1, "author1", "assignee2", Sets.newSet("label1", "label2", "label3"), true);
    public static final Issue issue2 = new Issue(2, "author1", "assignee2", Sets.newSet("label1", "label3"), true);
    public static final Issue issue3 = new Issue(3, "author1", "assignee2", Sets.newSet("label2", "label3"), true);
    public static final Issue issue4 = new Issue(4, "author1", "assignee2", Sets.newSet("label2", "label3"), true);
    public static final Issue issue5 = new Issue(5, "author2", "assignee4", Sets.newSet("label2", "label3"), true);
    public static final Issue issue6 = new Issue(6, "author2", "assignee4", Sets.newSet("label2", "label3"), true);
    public static final Issue issue7 = new Issue(7, "author2", "assignee4", Sets.newSet("label2"), false);
    public static final Issue issue8 = new Issue(8, "author2", "assignee4", Sets.newSet("label2"), false);
    public static final Issue issue9 = new Issue(9, "author3", "assignee2", Sets.newSet("label2"), false);
    public static final Issue issue10 = new Issue(10, "author3", "assignee4", Sets.newSet("label1", "label2", "label3"), false);
    public static final Issue issue11 = new Issue(11, "author3", "assignee1", Sets.newSet("label1", "label2", "label3"), false);
    public static final Issue issue12 = new Issue(12, "author4", "assignee1", Sets.newSet("label3"), false);
    public static final Issue issue13 = new Issue(13, "author4", "assignee3", Sets.newSet("label3"), false);
    public static final Issue issue14 = new Issue(14, "author4", "assignee1", Sets.newSet("label3"), false);
    public static final Issue issue15 = new Issue(15, "author4", "assignee1", Sets.newSet("label3"), false);

    public static final List<Issue> fullList = List.of(issue1, issue2, issue3, issue4, issue5, issue6, issue7, issue8, issue9, issue10, issue11, issue12, issue13, issue14, issue15);
    public static final List<Issue> openIssues = List.of(issue1, issue2, issue3, issue4, issue5, issue6);
    public static final List<Issue> closedIssues = List.of(issue7, issue8, issue9, issue10, issue11, issue12, issue13, issue14, issue15);
}
