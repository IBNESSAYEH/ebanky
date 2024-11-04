package com.YouCode.ebanky.entities.mapper;

import com.YouCode.ebanky.entities.User;
import com.YouCode.ebanky.shared.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {



        CarMapper INSTANCE = Mappers.getMapper( CarMapper.class ); 3

        //@Mapping(source = "numberOfSeats", target = "seatCount")
        UserDto userToUserDto(User user);
    }
}
