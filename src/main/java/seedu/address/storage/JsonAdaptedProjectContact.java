package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.project.ProjectContact;

/** Jackson representation of a project-to-contact association. */
public class JsonAdaptedProjectContact {
    private final int projectId;
    private final String contactName;

    @JsonCreator
    public JsonAdaptedProjectContact(@JsonProperty("projectId") int projectId,
            @JsonProperty("contactName") String contactName) {
        this.projectId = projectId;
        this.contactName = contactName;
    }

    public JsonAdaptedProjectContact(ProjectContact source) {
        this(source.getProjectId(), source.getContactName());
    }

    @JsonProperty("projectId")
    public int getProjectId() {
        return projectId;
    }

    @JsonProperty("contactName")
    public String getContactName() {
        return contactName;
    }

    public ProjectContact toModelType() {
        return new ProjectContact(projectId, contactName);
    }
}
