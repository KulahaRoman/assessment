package kulaha.assessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private List<Repository> repositories;
}
