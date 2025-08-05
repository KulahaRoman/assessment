package kulaha.assessment.service;

import kulaha.assessment.dto.Branch;
import kulaha.assessment.dto.Repository;
import kulaha.assessment.dto.User;
import kulaha.assessment.exception.ResourceNotFoundException;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final GitHub gitHub;

    public UserServiceImpl(GitHub gitHub) {
        this.gitHub = gitHub;
    }

    public User getUser(String id) throws IOException {
        GHUser ghUser;
        try {
            ghUser = gitHub.getUser(id);
        } catch (IOException e) {
            throw new ResourceNotFoundException("user not found");
        }

        List<Repository> repositories = ghUser.listRepositories().toList().stream()
                .filter(repo -> !repo.isFork())
                .map(repo -> {
                    try {
                        var branches = repo.getBranches().entrySet().stream()
                                .map(b -> {
                                    var branch = new Branch();
                                    branch.setName(b.getKey());
                                    branch.setHash(b.getValue().getSHA1());

                                    return branch;
                                }).toList();

                        var repository = new Repository();
                        repository.setName(repo.getName());
                        repository.setOwner(repo.getOwner().getLogin());
                        repository.setBranches(branches);

                        return repository;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();

        User user = new User();
        user.setRepositories(repositories);

        return user;
    }
}
