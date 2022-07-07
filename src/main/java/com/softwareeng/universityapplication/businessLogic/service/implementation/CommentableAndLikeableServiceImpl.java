package com.softwareeng.universityapplication.businessLogic.service.implementation;

import com.softwareeng.universityapplication.businessLogic.service.base.AbstractJpaService;
import com.softwareeng.universityapplication.businessLogic.dtos.common.CommentableAndLikeableDTO;
import com.softwareeng.universityapplication.businessLogic.service.CommentableAndLikeableService;
import com.softwareeng.universityapplication.entities.common.CommentableAndLikeable;
import org.springframework.stereotype.Service;

@Service
public class CommentableAndLikeableServiceImpl extends AbstractJpaService<CommentableAndLikeableDTO, CommentableAndLikeable, Long> implements CommentableAndLikeableService {
    public CommentableAndLikeableServiceImpl() {
        super(CommentableAndLikeable.class, CommentableAndLikeableDTO.class);
    }
}
