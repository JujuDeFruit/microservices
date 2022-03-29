package ca.uqac.api.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CVHeader {

    private String id;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String email;
}
