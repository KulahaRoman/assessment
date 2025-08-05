package kulaha.assessment.controller;

import kulaha.assessment.dto.Branch;
import kulaha.assessment.dto.Repository;
import kulaha.assessment.dto.User;
import kulaha.assessment.exception.ResourceNotFoundException;
import kulaha.assessment.service.UserService;

import java.util.List;

public class MockUserService implements UserService {
    @Override
    public User getUser(String id) throws Exception {
        if(!id.equals("test_user")) {
            throw new ResourceNotFoundException("user not found");
        }

        var branch = new Branch();
        branch.setName("master");
        branch.setHash("somehash");

        var repository = new Repository();
        repository.setOwner("test_user");
        repository.setName("test_repo");
        repository.setBranches(List.of(branch));

        var user = new User();
        user.setRepositories(List.of(repository));

        return user;
    }
}
