package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.getErrorMessageForDuplicatePrefixes;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.person.Remark;

public class RemarkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

    private final RemarkCommandParser parser = new RemarkCommandParser();

    @Test
    public void parse_validRemark_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_REMARK + "Likes baseball";
        RemarkCommand expectedCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark("Likes baseball"));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyRemark_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_REMARK;
        RemarkCommand expectedCommand = new RemarkCommand(INDEX_FIRST_PERSON, Remark.EMPTY);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "0 " + PREFIX_REMARK + "Likes baseball", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 invalid " + PREFIX_REMARK + "Likes baseball", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_duplicateRemarkPrefix_failure() {
        String userInput = "1 " + PREFIX_REMARK + "First remark " + PREFIX_REMARK + "Second remark";

        assertParseFailure(parser, userInput, getErrorMessageForDuplicatePrefixes(PREFIX_REMARK));
    }

}
