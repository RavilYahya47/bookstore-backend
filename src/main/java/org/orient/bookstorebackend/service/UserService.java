package org.orient.bookstorebackend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.orient.bookstorebackend.model.dto.UserCreateDto;
import org.orient.bookstorebackend.model.entity.User;
import org.orient.bookstorebackend.model.entity.UserDetails;
import org.orient.bookstorebackend.model.response.UserResponse;
import org.orient.bookstorebackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    public void createUser(UserCreateDto userCreateDto) {

        var user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());

        var userDetails = new UserDetails();
        userDetails.setAge(userCreateDto.getAge());
        userDetails.setFirstName(userCreateDto.getFistName());
        userDetails.setLastName(userCreateDto.getLastName());

        user.setUserDetails(userDetails);
        userDetails.setUser(user);

        var savedUser = userRepository.save(user);

        log.info("Created user: {}", user.getId());

    }

    @Transactional
    public User getUserById(Long id) {

        var user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));

        var userResponse = new UserResponse();

        userResponse.setUsername(user.getUsername());
        userResponse.setFistName(user.getUserDetails().getFirstName());
        userResponse.setLastName(user.getUserDetails().getLastName());
        userResponse.setAge(user.getUserDetails().getAge());

        log.info(user.getUserDetails().toString());

//        user.getUserDetails().getAddresses()
//                .stream()
//                .map(address -> address.getCity()
//                        .concat(" ")
//                        .concat(address.getStreet()))
//                .forEach(address -> userResponse.getAddresses().add(address));

        log.info("User info retrieved with id: {}", user.getId());

        return user;
    }
}
