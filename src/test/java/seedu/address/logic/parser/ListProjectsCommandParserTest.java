package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListProjectsCommand;

public class ListProjectsCommandParserTest {

    @Test
    public void parseCommand_listProjects_returnsListProjectsCommand() throws Exception {
        assertInstanceOf(ListProjectsCommand.class,
                new AddressBookParser().parseCommand(ListProjectsCommand.COMMAND_WORD));
    }
}
