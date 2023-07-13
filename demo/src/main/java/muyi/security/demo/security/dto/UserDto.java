package muyi.security.demo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;



@Data
@AllArgsConstructor
public class UserDto implements Serializable {
    private static final Long serialVersionUID = 1L;
    private String username;
    private String password;

}
