package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Task type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Tasks")
@Index(name = "TeamNames", fields = {"teamId"})
public final class Task implements Model {
  public static final QueryField ID = field("Task", "id");
  public static final QueryField TITLE = field("Task", "title");
  public static final QueryField BODY = field("Task", "body");
  public static final QueryField STATE = field("Task", "state");
  public static final QueryField UPLOADED_FILE = field("Task", "uploadedFile");
  public static final QueryField TEAM_ID = field("Task", "teamId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String") String body;
  private final @ModelField(targetType="String", isRequired = true) String state;
  private final @ModelField(targetType="String") String uploadedFile;
  private final @ModelField(targetType="ID", isRequired = true) String teamId;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getBody() {
      return body;
  }
  
  public String getState() {
      return state;
  }
  
  public String getUploadedFile() {
      return uploadedFile;
  }
  
  public String getTeamId() {
      return teamId;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Task(String id, String title, String body, String state, String uploadedFile, String teamId) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.state = state;
    this.uploadedFile = uploadedFile;
    this.teamId = teamId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Task task = (Task) obj;
      return ObjectsCompat.equals(getId(), task.getId()) &&
              ObjectsCompat.equals(getTitle(), task.getTitle()) &&
              ObjectsCompat.equals(getBody(), task.getBody()) &&
              ObjectsCompat.equals(getState(), task.getState()) &&
              ObjectsCompat.equals(getUploadedFile(), task.getUploadedFile()) &&
              ObjectsCompat.equals(getTeamId(), task.getTeamId()) &&
              ObjectsCompat.equals(getCreatedAt(), task.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), task.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTitle())
      .append(getBody())
      .append(getState())
      .append(getUploadedFile())
      .append(getTeamId())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Task {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("body=" + String.valueOf(getBody()) + ", ")
      .append("state=" + String.valueOf(getState()) + ", ")
      .append("uploadedFile=" + String.valueOf(getUploadedFile()) + ", ")
      .append("teamId=" + String.valueOf(getTeamId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TitleStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Task justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Task(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      title,
      body,
      state,
      uploadedFile,
      teamId);
  }
  public interface TitleStep {
    StateStep title(String title);
  }
  

  public interface StateStep {
    TeamIdStep state(String state);
  }
  

  public interface TeamIdStep {
    BuildStep teamId(String teamId);
  }
  

  public interface BuildStep {
    Task build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep body(String body);
    BuildStep uploadedFile(String uploadedFile);
  }
  

  public static class Builder implements TitleStep, StateStep, TeamIdStep, BuildStep {
    private String id;
    private String title;
    private String state;
    private String teamId;
    private String body;
    private String uploadedFile;
    @Override
     public Task build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Task(
          id,
          title,
          body,
          state,
          uploadedFile,
          teamId);
    }
    
    @Override
     public StateStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public TeamIdStep state(String state) {
        Objects.requireNonNull(state);
        this.state = state;
        return this;
    }
    
    @Override
     public BuildStep teamId(String teamId) {
        Objects.requireNonNull(teamId);
        this.teamId = teamId;
        return this;
    }
    
    @Override
     public BuildStep body(String body) {
        this.body = body;
        return this;
    }
    
    @Override
     public BuildStep uploadedFile(String uploadedFile) {
        this.uploadedFile = uploadedFile;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String title, String body, String state, String uploadedFile, String teamId) {
      super.id(id);
      super.title(title)
        .state(state)
        .teamId(teamId)
        .body(body)
        .uploadedFile(uploadedFile);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder state(String state) {
      return (CopyOfBuilder) super.state(state);
    }
    
    @Override
     public CopyOfBuilder teamId(String teamId) {
      return (CopyOfBuilder) super.teamId(teamId);
    }
    
    @Override
     public CopyOfBuilder body(String body) {
      return (CopyOfBuilder) super.body(body);
    }
    
    @Override
     public CopyOfBuilder uploadedFile(String uploadedFile) {
      return (CopyOfBuilder) super.uploadedFile(uploadedFile);
    }
  }
  
}
