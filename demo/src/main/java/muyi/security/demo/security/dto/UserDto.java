package muyi.security.demo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

}
