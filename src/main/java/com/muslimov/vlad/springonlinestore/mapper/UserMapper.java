package com.muslimov.vlad.springonlinestore.mapper;

import com.muslimov.vlad.springonlinestore.dto.UserDto;
import com.muslimov.vlad.springonlinestore.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
