package com.erkmen.dto;

import com.erkmen.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private List<OrderDTO> orderList;

}
