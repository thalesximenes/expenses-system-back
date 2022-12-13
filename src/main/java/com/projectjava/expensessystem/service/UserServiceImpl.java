package com.projectjava.expensessystem.service;

import com.projectjava.expensessystem.entity.UserEntity;
import com.projectjava.expensessystem.model.User;
import com.projectjava.expensessystem.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        boolean userEmailIdIsNull = userRepository.findByEmailId(userEntity.getEmailId()) == null;
        if (!userEmailIdIsNull) {
            return null; }
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();

        return userEntities
                    .stream()
                    .map(userEntity -> new User(
                            userEntity.getId(),
                            userEntity.getFirstName(),
                            userEntity.getLastName(),
                            userEntity.getEmailId()
                    ))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).isEmpty() ? null : userRepository.findById(id).get();
        if (userEntity == null) {return null;}
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        UserEntity user = userRepository.findById(id).isEmpty() ? null :  userRepository.findById(id).get();
        if (user == null) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    @Override
    public User updateUser(Long id, User user) {
        UserEntity userEntity = userRepository.findById(id).isEmpty() ? null :  userRepository.findById(id).get();
        if (userEntity == null) {
            return null;
        }
        userEntity.setEmailId(user.getEmailId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        userRepository.save(userEntity);
        return user;
    }

}
