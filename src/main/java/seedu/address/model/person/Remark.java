package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents an optional remark about a person in the address book.
 * Guarantees: immutable and non-null; an empty value represents no remark.
 */
public class Remark {

    public static final Remark EMPTY = new Remark("");

    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A remark, which may be empty.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Remark otherRemark)) {
            return false;
        }

        return value.equals(otherRemark.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
