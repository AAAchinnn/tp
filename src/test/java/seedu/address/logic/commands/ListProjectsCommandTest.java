package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Description;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

public class ListProjectsCommandTest {

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ListProjectsCommand().execute(null));
    }

    @Test
    public void execute_emptyProjectList_returnsEmptyMessage() {
        Model model = new ModelManager();

        CommandResult result = new ListProjectsCommand().execute(model);

        assertEquals(ListProjectsCommand.MESSAGE_EMPTY, result.getFeedbackToUser());
        assertEquals(CommandResult.ListViewTarget.PROJECTS, result.getListViewTarget());
    }

    @Test
    public void execute_projects_returnsProjectsInListOrder() {
        Model model = new ModelManager();
        Project first = createProject("CS2103T", Description.EMPTY);
        Project second = createProject("Orbital", new Description("Build an app"));
        model.addProject(first);
        model.addProject(second);

        CommandResult result = new ListProjectsCommand().execute(model);

        assertEquals("Listed 2 project(s):\n"
                        + "1. CS2103T | Deadline: 2026-11-13 | Description: No description provided\n"
                        + "2. Orbital | Deadline: 2026-11-13 | Description: Build an app",
                result.getFeedbackToUser());
    }

    private Project createProject(String name, Description description) {
        return new Project(new ProjectName(name),
                new Deadline(LocalDate.of(2026, 11, 13)), description);
    }
}
