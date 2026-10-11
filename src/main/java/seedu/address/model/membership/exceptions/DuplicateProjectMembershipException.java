package seedu.address.model.membership.exceptions;

/**
 * Signals that an operation would result in duplicate project memberships.
 */
public class DuplicateProjectMembershipException extends RuntimeException {

    public DuplicateProjectMembershipException() {
        super("Operation would result in duplicate project memberships");
    }
}
