package com.softwareeng.universityapplication.businessLogic.service.implementation;

import com.softwareeng.universityapplication.businessLogic.service.CommentService;
import com.softwareeng.universityapplication.businessLogic.service.LikeService;
import com.softwareeng.universityapplication.businessLogic.service.UserService;
import com.softwareeng.universityapplication.businessLogic.service.base.AbstractJpaService;
import com.softwareeng.universityapplication.businessLogic.dtos.UserDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.common.CommentableAndLikeableDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.userInteractions.CommentDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.userInteractions.LikeDTO;
import com.softwareeng.universityapplication.businessLogic.service.CommentableAndLikeableService;
import com.softwareeng.universityapplication.entities.userInteractions.Like;
import com.softwareeng.universityapplication.repositories.userInteraction.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl extends AbstractJpaService<LikeDTO, Like, Long> implements LikeService {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentableAndLikeableService commentableAndLikeableService;

    public LikeServiceImpl() {
        super(Like.class, LikeDTO.class);
    }

    @Override
    public void toggleLikeAComment(Long idComment, Long idUser) {
        LikeDTO likeDTO = this.getLikeIfCommentIsAlreadyLiked(idComment, idUser);
        if (likeDTO != null) {
            this.deleteById(likeDTO.getId());
        } else {
            CommentDTO commentDTO = this.commentService.findById(idComment);
            UserDTO userLogged = this.userService.findById(idUser);
            likeDTO = new LikeDTO();
            likeDTO.setCommentLiked(commentDTO);
            likeDTO.setInteractedBy(userLogged);
            this.save(likeDTO);
        }
    }

    @Override
    public LikeDTO getLikeIfCommentIsAlreadyLiked(Long idComment, Long idUser) {
        Optional<Like> optionalLike = getLikeRepository().findLikeByCommentLiked_IdAndInteractedBy_Id(idComment, idUser);
        return optionalLike.map(this::mapFromEntity).orElse(null);
    }

    @Override
    public void toggleLikeContent(Long idCommentableAndLikeable, Long idUser) {
        LikeDTO likeDTO = this.getLikeIfContentIsAlreadyLiked(idCommentableAndLikeable, idUser);
        if (likeDTO != null) {
            this.deleteById(likeDTO.getId());
        } else {
            CommentableAndLikeableDTO commentableAndLikeableDTO = this.commentableAndLikeableService.findById(idCommentableAndLikeable);
            UserDTO userLogged = this.userService.findById(idUser);
            likeDTO = new LikeDTO();
            likeDTO.setLikedContent(commentableAndLikeableDTO);
            likeDTO.setInteractedBy(userLogged);
            this.save(likeDTO);
        }
    }

    @Override
    public LikeDTO getLikeIfContentIsAlreadyLiked(Long idCommentableAndLikeable, Long idUser) {
        Optional<Like> optionalLike = getLikeRepository().findLikeByLikedContent_IdAndInteractedBy_Id(idCommentableAndLikeable, idUser);
        return optionalLike.map(this::mapFromEntity).orElse(null);
    }

    @Override
    public List<LikeDTO> getLikesOfAComment(Long idComment) {
        return mapEntityListToDTO(getLikeRepository().findAllByCommentLiked_Id(idComment));
    }

    @Override
    public List<LikeDTO> getLikesOfAContent(Long idContent) {
        return mapEntityListToDTO(this.getLikeRepository().findAllByLikedContent_Id(idContent));
    }

    private LikeRepository getLikeRepository() {
        return (LikeRepository) repo;
    }
}
