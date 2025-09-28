package com.github.guilhermeVRF.Controllers;

import java.util.List;
import java.util.Map;

import com.github.guilhermeVRF.Models.ApiResponse;
import com.github.guilhermeVRF.Models.User;
import com.github.guilhermeVRF.Services.UserService;

import io.javalin.http.Context;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void createUser(Context ctx) {
        User newUser = ctx.bodyAsClass(User.class);

        User createdUser = userService.createUser(newUser.getName(), newUser.getEmail());

        ctx.status(201).json(new ApiResponse<User>("success", "Usuário criado com sucesso!", createdUser));
    }

    public void getUser(Context ctx) {
        String userId = ctx.pathParam("id");

        User user = userService.getUser(userId);
        if (user == null) {
            ctx.status(404).json(new ApiResponse<User>("error", "Usuário não encontrado", null));
            return;
        }

        ctx.status(200).json(new ApiResponse<User>("success", "Usuário encontrado com sucesso!", user));
    }

    public void getAllUsers(Context ctx) {
        ctx.status(200).json(new ApiResponse<List<User>>("success", "Usuários encontrados com sucesso!", userService.getAllUsers()));
    }

    public void updateUser(Context ctx) {
        String userId = ctx.pathParam("id");
        User updatedUser = ctx.bodyAsClass(User.class);

        User user = userService.updateUser(userId, updatedUser.getName(), updatedUser.getEmail());
        if (user == null) {
            ctx.status(404).json("Usuário não encontrado");
            return;
        }

        ctx.status(200).json(new ApiResponse<User>("success", "Usuário atualizado com sucesso!", user));
    }

    public void deleteUser(Context ctx) {
        String userId = ctx.pathParam("id");
        userService.deleteUser(userId);
        ctx.status(204);
    }
}
