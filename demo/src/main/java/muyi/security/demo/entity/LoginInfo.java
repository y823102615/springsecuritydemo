package muyi.security.demo.entity;

import lombok.Data;

@Data
public class LoginInfo {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String password;
}
