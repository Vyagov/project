package project.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AuthorBindingModel {
    private String name;
    private String biography;

    public AuthorBindingModel() {
    }

    @NotNull
    @Length(min = 2, max = 70, message = "Name length must be between 2 and 35 characters!")
    @Pattern(regexp = "^[a-zA-Z0-9\\s+,.!\"-_\\p{IsCyrillic}]+$", message = "Is not a text!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Length(min = 10, message = "Biography length must be more than 10 characters!")
    @Pattern(regexp = "^[a-zA-Z0-9\\s+,.!\"-_\\p{IsCyrillic}]+$", message = "Is not a text!")
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
