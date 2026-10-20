package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditProjectCommand.EditProjectDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Description;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.testutil.ProjectBuilder;

public class EditProjectDescriptorTest {

    private static final Index FIRST_PROJECT = Index.fromOneBased(1);
    private static final Index SECOND_PROJECT = Index.fromOneBased(2);
    private static final LocalDate NEW_DEADLINE = LocalDate.of(2027, 1, 1);

    private Model model;
    private Project firstProject;
    private Project secondProject;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        firstProject = new ProjectBuilder()
            .withName("CS2103T")
            .withDeadline(LocalDate.of(2026, 11, 13))
            .withDescription("Original description")
            .build();
        secondProject = new ProjectBuilder()
            .withName("Orbital Team")
            .withDeadline(LocalDate.of(2027, 7, 1))
            .withDescription("Another project")
            .build();

        model.addProject(firstProject);
        model.addProject(secondProject);
    }

    @Test
    public void descriptorEquals() {
        EditProjectDescriptor descriptor = new EditProjectDescriptor();
        descriptor.setName(new ProjectName("Updated Project"));
        descriptor.setDeadline(new Deadline(NEW_DEADLINE));
        descriptor.setDescription(new Description("Updated description"));

        EditProjectDescriptor copy = new EditProjectDescriptor(descriptor);

        assertTrue(descriptor.equals(descriptor));
        assertTrue(descriptor.equals(copy));
        assertTrue(copy.equals(descriptor));
        assertFalse(descriptor.equals(null));
        assertFalse(descriptor.equals(1));

        EditProjectDescriptor differentName = new EditProjectDescriptor(descriptor);
        differentName.setName(new ProjectName("Another Name"));
        assertFalse(descriptor.equals(differentName));

        EditProjectDescriptor differentDeadline = new EditProjectDescriptor(descriptor);
        differentDeadline.setDeadline(new Deadline(NEW_DEADLINE.plusDays(1)));
        assertFalse(descriptor.equals(differentDeadline));

        EditProjectDescriptor differentDescription = new EditProjectDescriptor(descriptor);
        differentDescription.setDescription(new Description("Another description"));
        assertFalse(descriptor.equals(differentDescription));

        assertTrue(new EditProjectDescriptor().equals(new EditProjectDescriptor()));
        assertFalse(descriptor.equals(new EditProjectDescriptor()));
        assertFalse(new EditProjectDescriptor().equals(descriptor));
    }

    @Test
    public void descriptorCopy_originalModified_copyUnchanged() {
        EditProjectDescriptor original = new EditProjectDescriptor();
        original.setName(new ProjectName("Original Name"));
        original.setDeadline(new Deadline(NEW_DEADLINE));
        original.setDescription(new Description("Original description"));

        EditProjectDescriptor copy = new EditProjectDescriptor(original);

        original.setName(new ProjectName("Changed Name"));
        original.setDeadline(new Deadline(NEW_DEADLINE.plusDays(1)));
        original.setDescription(Description.EMPTY);

        assertEquals(new ProjectName("Original Name"), copy.getName().orElseThrow());
        assertEquals(new Deadline(NEW_DEADLINE), copy.getDeadline().orElseThrow());
        assertEquals(new Description("Original description"),
            copy.getDescription().orElseThrow());
    }

    @Test
    public void descriptor_descriptionOmittedVersusCleared_distinctStates() {
        EditProjectDescriptor omitted = new EditProjectDescriptor();
        EditProjectDescriptor cleared = new EditProjectDescriptor();
        cleared.setDescription(Description.EMPTY);

        assertFalse(omitted.isAnyFieldEdited());
        assertTrue(omitted.getName().isEmpty());
        assertTrue(omitted.getDeadline().isEmpty());
        assertTrue(omitted.getDescription().isEmpty());

        assertTrue(cleared.isAnyFieldEdited());
        assertEquals(Description.EMPTY, cleared.getDescription().orElseThrow());
        assertFalse(omitted.equals(cleared));

        EditProjectDescriptor copiedClear = new EditProjectDescriptor(cleared);
        assertTrue(copiedClear.isAnyFieldEdited());
        assertEquals(Description.EMPTY, copiedClear.getDescription().orElseThrow());
    }
}
