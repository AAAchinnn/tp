package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.project.Project;

/** Jackson representation of a project. */
public class JsonAdaptedProject {
    private final int id;
    private final String name;

    @JsonCreator
    public JsonAdaptedProject(@JsonProperty("id") int id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public JsonAdaptedProject(Project source) {
        this(source.getId(), source.getName());
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public Project toModelType() {
        return new Project(id, name);
    }
}
