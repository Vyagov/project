package project.model.view;

public class AuthorViewModel extends BaseViewModel {
    private String name;
    private String biography;

    public AuthorViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
