package com.github.lalifeier.mall.account.mapstruct.convert;

import com.github.lalifeier.mall.account.domain.dto.CarDto;
import com.github.lalifeier.mall.account.mapstruct.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    //@Mapping(target = "type", source = "carType.type")
    @Mapping(target = "seatCount", source = "numberOfSeats")
    CarDto carToCarDto(Car car);
}
