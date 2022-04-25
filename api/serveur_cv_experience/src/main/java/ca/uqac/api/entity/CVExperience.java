package ca.uqac.api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CVExperience {

    private String id;
    private String date1;
    private String date2;
    private String lieu;
    private String titre;
    private String detail;
}
