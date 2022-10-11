package com.github.lalifeier.mall.account.mapstruct;

import com.github.lalifeier.mall.account.domain.dto.CarDto;
import com.github.lalifeier.mall.account.mapstruct.convert.CarMapper;
import com.github.lalifeier.mall.account.mapstruct.entity.Car;

import com.github.lalifeier.mall.account.mapstruct.entity.CarType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class MapStructTests {

    @Test
    public void shouldMapCarToDto() {
        Car car = new Car( "Morris", 5, CarType.SEDAN);

        CarDto carDto = CarMapper.INSTANCE.carToCarDto( car );

        log.info("{}", carDto);
    }
}
