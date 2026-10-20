package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditProjectCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditProjectCommand object
 */
public class EditProjectCommandParser implements Parser<EditProjectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditProjectCommand
     * and returns an EditProjectCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public EditProjectCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROJECT, PREFIX_DEADLINE, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProjectCommand.MESSAGE_USAGE),
                    pe
            );
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PROJECT, PREFIX_DEADLINE, PREFIX_DESCRIPTION);

        EditProjectCommand.EditProjectDescriptor editProjectDescriptor = new EditProjectCommand.EditProjectDescriptor();

        if (argMultimap.getValue(PREFIX_PROJECT).isPresent()) {
            editProjectDescriptor.setName(ParserUtil.parseProjectName(argMultimap.getValue(PREFIX_PROJECT).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editProjectDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editProjectDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get())
            );
        }

        if (!editProjectDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditProjectCommand.MESSAGE_NOT_EDITED);
        }

        return new EditProjectCommand(index, editProjectDescriptor);
    }
}
