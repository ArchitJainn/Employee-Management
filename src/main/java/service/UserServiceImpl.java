package service;

import entity.UserEntity;
import model.User;
import repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity=new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmailId(user.getEmailId());
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities=userRepository.findAll();
       List<User> users=userEntities.stream().map(userEntity -> new User(userEntity.getId(),userEntity.getFirstName()
        , userEntity.getLastName(), userEntity.getEmailId())).collect(Collectors.toList());
        return users;
    }

    @Override
    public User getUser(long id) {
        UserEntity userEntity=userRepository.findById(id).get();
        User user=new User();
       copyProperties(userEntity,user);
        return user;
    }
     private void copyProperties(UserEntity entity,User user)
     {
       user.setId(entity.getId());
       user.setFirstName(entity.getFirstName());
       user.setLastName(entity.getLastName());
       user.setEmailId(entity.getEmailId());
     }
    @Override
    @Transactional
    public User deleteUser(long id) {

        UserEntity userEntity=userRepository.findById(id).get();
        User user =new User();
       copyProperties(userEntity,user);
        System.out.println(user.getFirstName()+" "+user.getLastName());
        userRepository.delete(userEntity);

        return user;
    }

    @Override
    public User updateUser(long id, User user) {
        UserEntity userEntity=userRepository.findById(id).get();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmailId(user.getEmailId());
        userRepository.save(userEntity);
        return user;
    }

}
