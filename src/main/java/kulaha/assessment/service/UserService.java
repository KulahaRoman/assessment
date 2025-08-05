package kulaha.assessment.service;

import kulaha.assessment.dto.User;

public interface UserService {
    User getUser(String id) throws Exception;
}
