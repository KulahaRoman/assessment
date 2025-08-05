package kulaha.assessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class Repository {
    private String name;
    private String owner;
    private List<Branch> branches;
}
