package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class RemarkCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemark_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withRemark(VALID_REMARK).build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        RemarkCommand command = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK));
        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, Messages.format(editedPerson));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_removeRemark_success() {
        Person personWithoutRemark = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personWithRemark = new PersonBuilder(personWithoutRemark).withRemark(VALID_REMARK).build();
        model.setPerson(personWithoutRemark, personWithRemark);

        Person editedPerson = new PersonBuilder(personWithRemark).withRemark("").build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personWithRemark, editedPerson);

        RemarkCommand command = new RemarkCommand(INDEX_FIRST_PERSON, Remark.EMPTY);
        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS,
                Messages.format(editedPerson));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personToEdit).withRemark(VALID_REMARK).build();
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        RemarkCommand command = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK));
        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, Messages.format(editedPerson));

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getAddressBook().getPersonList().size(), model.getFilteredPersonList().size());
    }

    @Test
    public void execute_invalidIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RemarkCommand command = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexInFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        RemarkCommand command = new RemarkCommand(outOfBoundIndex, new Remark(VALID_REMARK));

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        RemarkCommand command = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK));
        RemarkCommand equalCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK));

        assertTrue(command.equals(equalCommand));
        assertTrue(command.equals(command));
        assertFalse(command.equals(null));
        assertFalse(command.equals(new ClearCommand()));
        assertFalse(command.equals(new RemarkCommand(INDEX_SECOND_PERSON, new Remark(VALID_REMARK))));
        assertFalse(command.equals(new RemarkCommand(INDEX_FIRST_PERSON, new Remark("Enjoys swimming"))));
    }

    @Test
    public void toStringMethod() {
        Remark remark = new Remark(VALID_REMARK);
        RemarkCommand command = new RemarkCommand(INDEX_FIRST_PERSON, remark);
        String expected = RemarkCommand.class.getCanonicalName() + "{index=" + INDEX_FIRST_PERSON + ", remark="
                + remark + "}";

        assertEquals(expected, command.toString());
    }

}
