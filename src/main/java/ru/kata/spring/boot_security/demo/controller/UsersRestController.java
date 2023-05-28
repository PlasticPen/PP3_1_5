package ru.kata.spring.boot_security.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UsersRestController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersRestController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.allUsers().stream().map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") long id) {
        return convertToUserDTO(userService.findUserById(id));
    }

    @GetMapping("/signedUser")
    public UserDTO getSignedUser(@AuthenticationPrincipal User user) {
        return convertToUserDTO(userService.findUserById(user.getId()));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid UserDTO userDTO,
                                             BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            StringBuilder msg = new StringBuilder();
//
//            List<FieldError> errors = bindingResult.getFieldErrors();
//            for (FieldError error : errors) {
//                msg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
//            }
//
//            throw new RuntimeException(msg.toString());
//        }

        userService.saveUser(convertToUser(userDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid UserDTO userDTO, @PathVariable("id") long id) {
        userDTO.setId(id);
        userService.updateUser(id, (convertToUser(userDTO)));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private User convertToUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return userService.assignRoles(user, userDTO.getRoleId());
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setRoleId(user.getRoles().size());
        return userDTO;
    }
}
