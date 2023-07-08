package muyi.security.demo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
public class Authoritydto implements GrantedAuthority {
    private String authority;

}
