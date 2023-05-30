package dev.dima.authservice.utils.mappers;

import dev.dima.authservice.dtos.requests.UserRequest;
import dev.dima.authservice.dtos.responses.UserResponse;
import dev.dima.authservice.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userRoles", target = "roles")
    UserResponse toResponse(UserEntity userEntity);

    @Mapping(ignore = true, target = "dateCreated")
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "userRoles")
    @Mapping(ignore = true, target = "status")
    UserEntity toEntity(UserRequest userRequest);
}
