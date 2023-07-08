package muyi.security.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorityInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;

    private String authority;

}
