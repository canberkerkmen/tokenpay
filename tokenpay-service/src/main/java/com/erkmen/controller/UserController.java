package com.erkmen.controller;

import com.erkmen.domain.factory.ApplicationFactory;
import com.erkmen.domain.User;
import com.erkmen.dto.UserDataDTO;
import com.erkmen.dto.UserResponseDTO;
import com.erkmen.service.UserService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {

    private UserService userService;

    private ApplicationFactory applicationFactory;

    @GetMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public String login(//
                        @ApiParam("Username") @RequestParam String username, //
                        @ApiParam("Password") @RequestParam String password) {
        return userService.signin(username, password);
    }

    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String signup(@ApiParam("Signup User") @RequestBody @Valid UserDataDTO userDataDTO) {
        return userService.signup(applicationFactory.createUser(userDataDTO));
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "${UserController.delete}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String delete(@ApiParam("Username") @PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "${UserController.search}", response = UserResponseDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
        return applicationFactory.createUserResponseDTO(userService.search(username));
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @ApiOperation(value = "${UserController.me}", response = UserResponseDTO.class)
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserResponseDTO whoami(HttpServletRequest req) {
        User user = userService.whoami(req);
        return applicationFactory.createUserResponseDTO(user);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }
}
