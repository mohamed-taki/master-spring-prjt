package com.supemir.expense_manager.mapper;

import com.supemir.expense_manager.dto.users.UserResponse;
import com.supemir.expense_manager.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target= "token", ignore = true)
    UserResponse toResponse(UserEntity x);
    UserEntity toEntity(UserResponse x);
}
