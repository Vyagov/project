package project.model.view;

import project.model.service.RoleServiceModel;

import java.util.List;
import java.util.Set;

public class UserViewModel extends BaseViewModel {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Set<RoleServiceModel> authorities;
    private List<BookViewModel> books;

    public UserViewModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleServiceModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<RoleServiceModel> authorities) {
        this.authorities = authorities;
    }

    public List<BookViewModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookViewModel> books) {
        this.books = books;
    }
}
