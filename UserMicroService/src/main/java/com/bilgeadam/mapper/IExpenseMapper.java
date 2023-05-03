package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateExpenseRequestDto;
import com.bilgeadam.dto.request.CreateLeaveRequestDto;
import com.bilgeadam.dto.response.LeaveDemandsResponseDto;
import com.bilgeadam.repository.entity.Expense;
import com.bilgeadam.repository.entity.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IExpenseMapper {
    IExpenseMapper INSTANCE = Mappers.getMapper(IExpenseMapper.class);
    Expense toExpense (final CreateExpenseRequestDto dto);
//    @Mapping(source = "id",target ="expenseId")
//    ExpenseDemandsResponseDto todemandsResponseDto (final Expense expense);
}
