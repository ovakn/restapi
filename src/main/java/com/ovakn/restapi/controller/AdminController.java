package com.ovakn.restapi.controller;

import com.ovakn.restapi.DTOs.GameDTO;
import com.ovakn.restapi.entity.Game;
import com.ovakn.restapi.entity.Purchase;
import com.ovakn.restapi.repository.GameRep;
import com.ovakn.restapi.repository.PurchasesRep;
import com.ovakn.restapi.service.MailSenderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminController {
    final GameRep gameRep;
    final PurchasesRep purchasesRep;
    String message;

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

    @Operation(summary = "Отображение списка всех покупок",
            description = "Возвращает все записи о совершённых покупках из БД")
    @GetMapping("api/purchases/getAll")
    public List<Purchase> gettingPurchasesList() {
        return purchasesRep.findAll();
    }

    @Operation(summary = "Добавление игры в БД",
            description = "Получает данные об игре в виде DTO, после чего добавляет в БД")
    @PostMapping("api/games/add")
    public String addingGame(@RequestBody GameDTO gameDTO) {
        Game game = new Game(
                gameDTO.getName(),
                gameDTO.getPrice(),
                gameDTO.getDeveloper(),
                gameDTO.getPublisher(),
                gameDTO.getQuantity(),
                gameDTO.getReleaseYear(),
                gameDTO.getGenre(),
                gameDTO.getSeries()
        );
        gameRep.save(game);
        message = "Добавлена новая запись: \n" + game;
        log.info(message);
        return message;
    }

    @Operation(summary = "Обновляет сведения об игре",
            description = "Получает DTO игры с обновлёнными сведениями и старое название игры в БД, после чего обновляет запись в ней")
    @PutMapping("api/game/update")
    public String updatingGame(@RequestBody GameDTO gameDTO, @RequestParam String oldName) {
        Game oldGame = new GeneralController(gameRep).gettingGameByName(oldName);
        if (oldGame != null) {
            Game newGame = new Game(
                    oldGame.getId(),
                    gameDTO.getName(),
                    gameDTO.getPrice(),
                    gameDTO.getDeveloper(),
                    gameDTO.getPublisher(),
                    oldGame.getAvailability(),
                    gameDTO.getQuantity(),
                    gameDTO.getReleaseYear(),
                    gameDTO.getGenre(),
                    gameDTO.getSeries()
            );
            message = "Предыдущая запись: \n"
                    + oldGame
                    + "\nОбновлённая запись: \n"
                    + gameRep.save(newGame);
        } else {
            message = "Не найдено записи об игре с таким названием";
        }
        log.info(message);
        return message;
    }

    @Operation(summary = "Удаляет игру",
            description = "Получает ID игры и удаляет запись с ней из БД")
    @DeleteMapping("api/game/delete")
    public String deletingGameByID(@RequestParam int id) {
        gameRep.deleteById(id);
        message = "Запись об игре успешно удалена";
        log.info(message);
        return message;
    }
}