package webservice.aladin.domain.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import webservice.aladin.domain.entity.Address;
import webservice.aladin.domain.entity.User;

@Data
public class CreateUserRequest {

    @NotNull
    private String loginId;
    @NotNull
    private String password;
    private String name;
    private String email;
    private String city;
    private String street;
    private String zipcode;

    public Address addressEntity() {
        return Address.addressBuilder()
                .city(city)
                .street(street)
                .zipcode(zipcode)
                .build();
    }

    public User userEntity() {
        return User.userBuilder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .email(email)
                .address(addressEntity())
                .build();
    }
}
