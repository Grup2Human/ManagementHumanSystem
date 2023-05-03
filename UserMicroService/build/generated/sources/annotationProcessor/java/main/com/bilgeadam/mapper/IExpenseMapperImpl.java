package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateExpenseRequestDto;
import com.bilgeadam.repository.entity.Expense;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-03T20:26:15+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IExpenseMapperImpl implements IExpenseMapper {

    @Override
    public Expense toExpense(CreateExpenseRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Expense.ExpenseBuilder<?, ?> expense = Expense.builder();

        expense.companyManagerId( dto.getCompanyManagerId() );
        expense.expenseType( dto.getExpenseType() );
        expense.expenseAmount( dto.getExpenseAmount() );
        expense.currency( dto.getCurrency() );
        expense.demandDate( dto.getDemandDate() );
        expense.document( dto.getDocument() );

        return expense.build();
    }
}
