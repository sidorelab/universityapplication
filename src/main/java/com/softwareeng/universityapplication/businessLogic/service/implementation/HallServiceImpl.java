package com.softwareeng.universityapplication.businessLogic.service.implementation;

import com.softwareeng.universityapplication.businessLogic.dtos.HallDTO;
import com.softwareeng.universityapplication.businessLogic.service.HallService;
import com.softwareeng.universityapplication.businessLogic.service.base.AbstractJpaService;
import com.softwareeng.universityapplication.entities.Hall;
import org.springframework.stereotype.Service;

@Service
public class HallServiceImpl extends AbstractJpaService<HallDTO, Hall, Long> implements HallService {
    public HallServiceImpl() {
        super(Hall.class, HallDTO.class);
    }
}
