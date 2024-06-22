package com.ovakn.restapi.controller;

import com.ovakn.restapi.DTOs.GameDTO;
import com.ovakn.restapi.entity.Game;
import com.ovakn.restapi.repository.GameRep;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Tag(name = "AdminController",
        description = "Контроллер для взаимодействия с БД со стороны администратора приложения")
@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController {
    private final GameRep gameRep;

    @Operation(summary = "Отображение списка игр",
            description = "Возвращает лист со всеми играми из БД")
    @GetMapping("api/games/getAll")
    public List<Game> gettingGameList() {
        return gameRep.findAll();
    }

    @Operation(summary = "Поиск игры по ID",
            description = "Проводит поиск в БД по переданному ID и возвращает сведения об игре, если она есть в БД")
    @GetMapping("api/game/getByID")
    public Game gettingGameByID(@RequestParam int id) {
        return gameRep.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Добавление игры в БД",
            description = "Получает данные об игре в виде DTO, после чего добавляет в БД")
    @PostMapping("api/games/add")
    public String addingGame(@RequestBody GameDTO gameDTO) {
        Game game = new Game(gameDTO.getName(),
                gameDTO.getPrice(),
                gameDTO.getDeveloper(),
                gameDTO.getPublisher(),
                gameDTO.getQuantity(),
                gameDTO.getReleaseYear(),
                gameDTO.getGenre(),
                gameDTO.getSeries());
        gameRep.save(game);
        return "Добавлена новая запись: " + game;
    }

    @Operation(summary = "Обновляет сведения об игре",
            description = "Получает")
    @PutMapping("api/game/update")
    public String updatingGame(@RequestBody GameDTO game) {
        if (gameRep.existsById(game.getId())) {
            return "Обновлена запись:" + gameRep.save(game);
        } else {
            return "Не найдено игры с таким ID";
        }
    }

    @Operation(summary = "",
            description = "")
    @DeleteMapping("api/game/delete")
    public String deletingGame() {

    }
}