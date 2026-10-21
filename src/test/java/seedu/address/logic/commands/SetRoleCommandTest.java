package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.membership.ProjectMembership;
import seedu.address.model.membership.Role;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Description;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

public class SetRoleCommandTest {

    private final Project project = new Project(new ProjectName("CS2103T Team Project"),
            new Deadline(LocalDate.of(2026, 11, 13)), Description.EMPTY);

    @Test
    public void execute_existingMembership_updatesRole() throws Exception {
        Model model = new ModelManager();
        model.addPerson(ALICE);
        model.addProject(project);
        ProjectMembership membership = new ProjectMembership(ALICE.getPersonId(), project.getProjectId());
        model.addProjectMembership(membership);

        CommandResult result = new SetRoleCommand(Index.fromOneBased(1), Index.fromOneBased(1),
                new Role("Implement parser")).execute(model);

        assertEquals(new Role("Implement parser"), model.getAddressBook().getProjectMembershipList().get(0).getRole());
        assertEquals("Set role for \"Alice Pauline\" in project \"CS2103T Team Project\": Implement parser",
                result.getFeedbackToUser());
    }

    @Test
    public void execute_unassociatedContact_throwsCommandException() {
        Model model = new ModelManager();
        model.addPerson(ALICE);
        model.addProject(project);

        assertThrows(CommandException.class, () -> new SetRoleCommand(Index.fromOneBased(1), Index.fromOneBased(1),
                new Role("Implement parser")).execute(model));
    }
}
