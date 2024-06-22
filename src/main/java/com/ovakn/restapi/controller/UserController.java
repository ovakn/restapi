package com.ovakn.restapi.controller;

import com.ovakn.restapi.entity.Game;
import com.ovakn.restapi.repository.GameRep;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "UserController",
        description = "Контроллер для взаимодействия с БД со стороны пользователя приложения")
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final GameRep gameRep;

    @Operation(summary = "Поиск игры по статусу покупки",
            description = "Проводит поиск в БД по статусу покупки и возвращает сведения об игре, если она есть в БД")
    @PostMapping("api/game/getByAvailability")
    public List<Game> gettingGameListByIsBought(@RequestParam boolean isBought) {
        return gameRep.findByIsBought(isBought);
    }
}