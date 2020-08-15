package project.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import project.model.binding.UserRegisterBindingModel;
import project.model.service.RoleServiceModel;
import project.model.service.UserServiceModel;
import project.model.view.UserViewModel;

import java.security.Principal;
import java.util.List;

public interface UserService extends UserDetailsService {

    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel updateProfile(UserRegisterBindingModel userRegisterBindingModel, Principal principal);

    List<UserServiceModel> getAllUsers();

    List<UserViewModel> getAllUsersForView();

    UserServiceModel findUserByUsername(String username);

    UserServiceModel findUserById(String userId);

    List<RoleServiceModel> getAllUserRoles(String username);

    List<UserViewModel> getAllUsersWithoutAuthAdmin();

    void delete(String id);
}