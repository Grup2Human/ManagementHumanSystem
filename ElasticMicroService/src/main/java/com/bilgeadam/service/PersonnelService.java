package com.bilgeadam.service;

import com.bilgeadam.dto.request.AddCompanyManagerRequestDto;
import com.bilgeadam.dto.request.AddPersonnelRequestDto;
import com.bilgeadam.exception.EErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.ICompanyManagerMapper;
import com.bilgeadam.mapper.IPersonnelMapper;
import com.bilgeadam.repository.IPersonnelRepository;
import com.bilgeadam.repository.entity.Personnel;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonnelService extends ServiceManager<Personnel,String> {

    private final IPersonnelRepository iPersonnelRepository;
    private final JwtTokenManager tokenManager;

    public PersonnelService(IPersonnelRepository iPersonnelRepository, JwtTokenManager tokenManager) {
        super(iPersonnelRepository);
        this.iPersonnelRepository = iPersonnelRepository;
        this.tokenManager = tokenManager;
    }

    public void saveDto(AddPersonnelRequestDto dto) {
        /**
         * Eğer userid daha önceden kayıt edilmiş ise kaydetme işlemi yapma
         */
        if(!iPersonnelRepository.existsByPersonnelId(dto.getId()))
            save(IPersonnelMapper.INSTANCE.toPersonnel(dto));
    }
}
