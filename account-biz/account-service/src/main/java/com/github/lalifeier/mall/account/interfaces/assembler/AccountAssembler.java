package com.github.lalifeier.mall.account.interfaces.assembler;

import org.mapstruct.factory.Mappers;

public interface AccountAssembler {

  AccountAssembler INSTANCE = Mappers.getMapper(AccountAssembler.class);

//  OrderDTO toDTO(Order order, Item item);

//  Item toEntity(ItemDTO itemDTO);
}
