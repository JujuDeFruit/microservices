package ca.uqac.api.entity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CVCompetence {

    private String id;
    private List<String> competence;
}
