package project.model.service;

public class AuthorServiceModel extends BaseServiceModel{
    private String name;
    private String biography;

    public AuthorServiceModel() {
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
