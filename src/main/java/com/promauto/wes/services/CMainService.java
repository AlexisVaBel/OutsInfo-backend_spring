package com.promauto.wes.services;

import com.promauto.wes.exceptions.CMainNotFoundException;
import com.promauto.wes.exceptions.CModuleNotFoundException;
import com.promauto.wes.models.CMain;
import com.promauto.wes.repositories.CMainRepository;
import com.promauto.wes.requests.CMainRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CMainService {
    private final CMainRepository mainRepository;
    private final CModuleService  moduleRepository;


    public CMainService(CMainRepository repository, CModuleService moduleRepository){
        this.mainRepository         = repository;
        this.moduleRepository    = moduleRepository;
    }

    @Transactional
    public CMain update(CMain main){
        return this.mainRepository.save(main);
    }

    @Transactional
    public CMain create(CMainRequest request){
        CMain mmain = new CMain();
        return  mmain; // what did you want, main creates and updates by independent server
    }

    @Transactional
    public void delete(String id){
        final Optional<CMain> mmain = this.mainRepository.findById(id);
        mmain.ifPresent(this.mainRepository::delete);
    }

    public List<CMain> findAll(){
        return this.mainRepository.findAll();
    }

    public CMain findByName(String name){
        return findByNameStartingWith(name);
    }

    public CMain findByNameStartingWith(String name){
        return this.mainRepository.findByModuleName(name);
    }

}
