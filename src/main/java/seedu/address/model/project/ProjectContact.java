package seedu.address.model.project;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/** A persisted link between a project and a contact, identified by the contact's unique name. */
public final class ProjectContact {
    private final int projectId;
    private final String contactName;

    public ProjectContact(int projectId, String contactName) {
        requireNonNull(contactName);
        if (projectId < 0 || contactName.isBlank()) {
            throw new IllegalArgumentException("Project id must be non-negative and contact name must not be blank.");
        }
        this.projectId = projectId;
        this.contactName = contactName;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getContactName() {
        return contactName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof ProjectContact link
                && projectId == link.projectId && contactName.equals(link.contactName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, contactName);
    }
}
