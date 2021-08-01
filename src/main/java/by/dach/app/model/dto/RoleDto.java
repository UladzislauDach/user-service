package by.dach.app.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class RoleDto {
    private String name;
    private List<PermissionDto> permissionDto;
}
