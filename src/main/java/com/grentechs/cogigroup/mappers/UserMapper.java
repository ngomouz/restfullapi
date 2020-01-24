package com.grentechs.cogigroup.mappers;

import com.grentechs.cogigroup.dtos.UserMsDto;
import com.grentechs.cogigroup.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // User to UserMsDto
    @Mappings({
        @Mapping(source = "email", target = "emailadress"),
        @Mapping(source = "role", target = "rolename"),
        @Mapping(source = "orders", target = "userOrders")
    })
    UserMsDto userToUserMsDto(User user);

    // List<User> To List<UserMsDto>
    List<UserMsDto> usersToUserMsDtos(List<User> users);
}
