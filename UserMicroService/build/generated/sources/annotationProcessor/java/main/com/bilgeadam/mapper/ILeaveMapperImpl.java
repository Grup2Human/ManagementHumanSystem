package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateLeaveRequestDto;
import com.bilgeadam.dto.response.DemandsResponseDto;
import com.bilgeadam.repository.entity.Leave;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-02T16:29:03+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class ILeaveMapperImpl implements ILeaveMapper {

    @Override
    public Leave toLeave(CreateLeaveRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Leave.LeaveBuilder<?, ?> leave = Leave.builder();

        leave.companyManagerId( dto.getCompanyManagerId() );
        leave.leaveType( dto.getLeaveType() );
        leave.demandDate( dto.getDemandDate() );
        leave.startDate( dto.getStartDate() );
        leave.endDate( dto.getEndDate() );

        return leave.build();
    }

    @Override
    public DemandsResponseDto todemandsResponseDto(Leave leave) {
        if ( leave == null ) {
            return null;
        }

        DemandsResponseDto.DemandsResponseDtoBuilder demandsResponseDto = DemandsResponseDto.builder();

        demandsResponseDto.personnelId( leave.getPersonnelId() );

        return demandsResponseDto.build();
    }
}
