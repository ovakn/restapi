package com.ovakn.restapi.controller;

import com.ovakn.restapi.DTOs.UserDTO;
import com.ovakn.restapi.entity.Game;
import com.ovakn.restapi.entity.Purchase;
import com.ovakn.restapi.entity.User;
import com.ovakn.restapi.repository.GameRep;
import com.ovakn.restapi.repository.PurchasesRep;
import com.ovakn.restapi.repository.UserRep;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Objects;

@Tag(name = "UserController",
        description = "Контроллер для взаимодействия с БД со стороны пользователя приложения")
@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {
    final GameRep gameRep;
    final UserRep userRep;
    final PurchasesRep purchasesRep;
    String message;

    @Operation(summary = "Покупка игры",
            description = "Получает ID игры и DTO пользователя. Если эти пользователь и игра присутствуют в БД, то проводит покупку")
    @PostMapping("api/game/buy")
    public String buyingGame(@RequestParam int gameID, @RequestBody UserDTO userDTO) {
        Game game = gameRep.findById(gameID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (game != null) {
            User user = userRep.findByEmail(userDTO.getEmail());
            if (user != null) {
                if (Objects.equals(user.getName(), userDTO.getName())) {
                    Purchase purchase = new Purchase(
                            user.getName(),
                            game.getId(),
                            game.getName(),
                            user.getEmail()
                    );
                    if (user.getBalance() > game.getPrice()) {
                        user.setBalance(user.getBalance() - game.getPrice());
                        userRep.save(user);
                        purchasesRep.save(purchase);
                        message = "Данные о покупке игры сохранены:\n"
                                + purchase.toString()
                                + "\nБаланс пользователя после покупки: "
                                + user.getBalance();
                    } else {
                        message = "Средств на балансе пользователя недостаточно";
                    }
                } else {
                    message = "Не найдено такой игры и/или пользователя";
                }
            } else {
                message = "Не найдено такой игры и/или пользователя";
            }
        } else {
            message = "Не найдено такой игры и/или пользователя";
        }
        log.info(message);
        return message;
    }

    @Operation(summary = "Регистрация нового пользователя",
            description = "Получает имя и email пользователя в виде DTO и добавляет запись о нём в БД")
    @PostMapping("api/user/register")
    public String registeringUser(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getEmail());
        userRep.save(user);
        message = "Успешно зарегистрирован новый пользователь";
        log.info(message);
        return message;
    }

    @Operation(summary = "Удаление пользователя",
            description = "Ищет в БД пользователя по переданным имени и email, после чего удаляет его")
    @DeleteMapping("api/user/delete")
    public String deletingUser(@RequestBody UserDTO userDTO) {
        User user = userRep.findByEmail(userDTO.getEmail());
        if (user != null) {
            if (Objects.equals(user.getName(), userDTO.getName())) {
                userRep.deleteById(user.getId());
                message = "Пользователь успешно удалён";
            } else {
                message = "Не найдено аккаунта с таким именем пользователя и email";
            }
        } else {
            message = "Не найдено аккаунта с таким именем пользователя и email";
        }
        log.info(message);
        return message;
    }

    @Operation(summary = "Обновление сведений о пользователе",
            description = "Получает обновлённые сведения вместе со старым email, проводит по нему поиск и обновляет запись")
    @PutMapping("api/user/update")
    public String updatingUser(@RequestBody UserDTO userDTO, @RequestParam String oldEmail) {
        User oldUser = userRep.findByEmail(oldEmail);
        if (oldUser != null) {
            User updatedUser = new User(
                    oldUser.getId(),
                    userDTO.getName(),
                    userDTO.getEmail(),
                    oldUser.getBalance()
            );
            userRep.save(updatedUser);
            message = "Данные о пользователе успешно обновлены";
        } else {
            message = "Не найдено пользователя с таким email";
        }
        log.info(message);
        return message;
    }

    @Operation(summary = "Пополнение баланса пользователя",
            description = "Ищет в БД пользователя по переданным имени и email и добавляет к балансу переданное int-значение")
    @PutMapping("api/user/topUp")
    public String topUpUserBalance(@RequestBody UserDTO userDTO, @RequestParam int value) {
        User user = userRep.findByEmail(userDTO.getEmail());
        if (user.getEmail() != null) {
            if (Objects.equals(user.getName(), userDTO.getName())) {
                user.setBalance(user.getBalance() + value);
                message = "Пополнение баланса аккаунта успешно совершено";
                userRep.save(user);
            }
        } else {
            message = "Не найдено аккаунта с таким именем пользователя и email";
        }
        log.info(message);
        return message;
    }
}