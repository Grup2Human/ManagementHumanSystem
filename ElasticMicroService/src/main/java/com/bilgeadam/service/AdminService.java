package com.bilgeadam.service;

import com.bilgeadam.dto.request.AddAdminRequestDto;
import com.bilgeadam.dto.request.BaseRequestDto;
import com.bilgeadam.mapper.IAdminMapper;
import com.bilgeadam.repository.IAdminRepository;
import com.bilgeadam.repository.ICompanyManagerRepository;
import com.bilgeadam.repository.ICompanyRepository;
import com.bilgeadam.repository.IPersonnelRepository;
import com.bilgeadam.repository.entity.Admin;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends ServiceManager<Admin,String> {
    private final IAdminRepository repository;
    private final ICompanyRepository companyRepository;
    private final ICompanyManagerRepository companyManagerRepository;
    private final IPersonnelRepository personnelRepository;
    private final JwtTokenManager tokenManager;
    private final CompanyManagerService companyManagerService;
    private final CompanyService companyService;
    private final PersonnelService personnelService;

    public AdminService(IAdminRepository repository, ICompanyRepository companyRepository, ICompanyManagerRepository companyManagerRepository, IPersonnelRepository personnelRepository, JwtTokenManager tokenManager, CompanyManagerService companyManagerService, CompanyService companyService, PersonnelService personnelService) {
        super(repository);
        this.repository = repository;
        this.companyRepository = companyRepository;
        this.companyManagerRepository = companyManagerRepository;
        this.personnelRepository = personnelRepository;
        this.tokenManager = tokenManager;
        this.companyManagerService = companyManagerService;
        this.companyService = companyService;
        this.personnelService = personnelService;
    }

    public void saveDto(AddAdminRequestDto dto) {
        /**
         * Eğer userid daha önceden kayıt edilmiş ise kaydetme işlemi yapma
         */
        if(!repository.existsByAdminId(dto.getId()))
            save(IAdminMapper.INSTANCE.toAdmin(dto));
    }

    public Page<Admin> findAll(BaseRequestDto dto) {
        /**
         * Sıralama ve Sayfalama için bize nesneler ve ayarlamalar gerekli.
         */
        Pageable pageable = null;
        Sort sort = null;
        /**
         * Eğer kişi sıralama istediği alanı yazmamış ise sıralama yapmak istemiyordur.
         */
        if(dto.getSortParameter()!=null) {
            String direction = dto.getDirection()==null ? "ASC" : dto.getDirection();
            sort = Sort.by(Sort.Direction.fromString(direction), dto.getSortParameter());
        }
        /**
         * 1. durum -> sıralama yapmak ister ve sayfalama yapmak ister
         * 2. durum -> sıralama istemiyor ve sayfalama yapmak istiyor.
         * 3. durum -> sıralama istemiyor ve sayfalama yapmak istemiyor.
         */
        Integer pageSize = dto.getPageSize() == null ? 10 :
                dto.getPageSize() < 1 ? 10 : dto.getPageSize();
        if(sort!=null && dto.getCurrentPage()!=null) {
            pageable = PageRequest.of(dto.getCurrentPage(), pageSize, sort);
        } else if (sort==null && dto.getCurrentPage()!=null) {
            pageable = PageRequest.of(dto.getCurrentPage(), pageSize);
        } else {
            pageable = PageRequest.of(0,pageSize);
        }
        return repository.findAll(pageable);
    }
}
