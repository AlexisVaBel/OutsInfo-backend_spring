package com.promauto.wes.services;

import com.promauto.wes.exceptions.CMainNotFoundException;
import com.promauto.wes.models.CMain;
import com.promauto.wes.models.CModule;
import com.promauto.wes.repository.CMainRepository;
import com.promauto.wes.requests.CMainRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CMainService {
    private final CMainRepository mainRepository;
    private final CModuleService  moduleRepository;


    public CMainService(CMainRepository repository, CModuleService moduleRepository){
        this.mainRepository         = repository;
        this.moduleRepository    = moduleRepository;
    }

    @Transactional
    public CMain update(CMain mmain){
        return this.mainRepository.save(mmain);
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

    public List<CMain> findByName(String name){
        return Arrays.asList(this.mainRepository.findByModuleName(name));
    }

    public List<CMain> findByNameStartingWith(String name){
        return null;
    }

    public CMain findOne(String name) throws CMainNotFoundException {
        return this.mainRepository.findByModuleName(name);
//        final CMain [] arr=new CMain[1];
//        arr[0]=null;
//        findAll().stream().forEach(x -> {if(name.compareTo(x.getName()) == 0){
//            arr[0]=x;
//        }});
//
//
//        if(arr[0] != null){
//            return arr[0];
//        }else{
//            throw new CMainNotFoundException(name);
//        }
    }

}