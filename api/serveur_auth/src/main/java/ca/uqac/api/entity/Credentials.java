package ca.uqac.api.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credentials {
    private String id;
    private String email;
    private String password;
}
