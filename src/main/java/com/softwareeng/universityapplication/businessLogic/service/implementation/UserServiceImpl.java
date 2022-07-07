package com.softwareeng.universityapplication.businessLogic.service.implementation;

import com.softwareeng.universityapplication.businessLogic.dtos.FriendshipDTO;
import com.softwareeng.universityapplication.businessLogic.dtos.UserDTO;
import com.softwareeng.universityapplication.businessLogic.exceptions.EmailViolationException;
import com.softwareeng.universityapplication.businessLogic.exceptions.UserNotFoundException;
import com.softwareeng.universityapplication.businessLogic.service.FriendshipService;
import com.softwareeng.universityapplication.businessLogic.service.UserService;
import com.softwareeng.universityapplication.businessLogic.service.base.AbstractJpaService;
import com.softwareeng.universityapplication.entities.User;
import com.softwareeng.universityapplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractJpaService<UserDTO, User, Long> implements UserService {

    @Autowired
    private FriendshipService friendshipService;

    public UserServiceImpl() {
        super(User.class, UserDTO.class);
    }

    @Override
    public UserDTO findByEmail(String email) throws UserNotFoundException {
        UserDTO userDTO = findByEmailOrNull(email);
        if(userDTO != null) {
            return userDTO;
        }
        throw new UserNotFoundException();
    }

    @Override
    public List<UserDTO> getUsersEnrolledInACourse(Long idCourse, Long idLoggedUser) {
        return mapEntityListToDTO(getUserRepository().findAllByCoursesOfAUser_idAndIdIsNot(idCourse, idLoggedUser));
    }

    @Override
    public List<UserDTO> getFriendsOfAUser(Long idUser) {
        List<FriendshipDTO> friendshipDTOList = this.friendshipService.getFriendshipsOfAUser(idUser);
        return getUserDTOS(idUser, friendshipDTOList);
    }

    @Override
    public List<UserDTO> getFriendsOfAUser(Long idUser, int pageNumber) {
        List<FriendshipDTO> friendshipDTOList = this.friendshipService.getFriendshipsOfAUser(idUser, pageNumber);
        return getUserDTOS(idUser, friendshipDTOList);
    }

    private List<UserDTO> getUserDTOS(Long idUser, List<FriendshipDTO> friendshipDTOList) {
        List<UserDTO> friends = new ArrayList<>();
        friendshipDTOList.forEach((friendshipDTO -> {
            if(friendshipDTO.getRequestedBy().getId().equals(idUser)) {
                friends.add(friendshipDTO.getRequestedTo());
            }
            if(friendshipDTO.getRequestedTo().getId().equals(idUser)) {
                friends.add(friendshipDTO.getRequestedBy());
            }
        }));
        return friends;
    }

    @Override
    public UserDTO save(UserDTO dto) {
        if(dto.getId() == null) {
            UserDTO existingUser = this.findByEmailOrNull(dto.getEmail());
            if(existingUser != null) {
                throw new EmailViolationException();
            }
        }
        return super.save(dto);
    }

    private UserDTO findByEmailOrNull(String email) {
        Optional<User> optionalUser = getUserRepository().findByEmail(email);
        return optionalUser.map(this::mapFromEntity).orElse(null);
    }

    private UserRepository getUserRepository() {
        return (UserRepository) repo;
    }
}
