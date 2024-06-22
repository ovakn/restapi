package com.ovakn.restapi.controller;

import com.ovakn.restapi.entity.Game;
import com.ovakn.restapi.repository.GameRep;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "GeneralController",
        description = "Контроллер для общих методов взаимодействия с БД")
@Slf4j
@RestController
@RequiredArgsConstructor
public class GeneralController {
    private final GameRep gameRep;

    @Operation(summary = "Поиск игры по названию",
            description = "Проводит поиск в БД по названию и возвращает сведения об игре, если она есть в БД")
    @GetMapping("api/game/getByName")
    public Game gettingGameByName(@RequestParam String name) {
        return gameRep.findByName(name);
    }

    @Operation(summary = "Поиск игры по разработчику",
            description = "Проводит поиск в БД по разработчику и возвращает сведения об игре, если она есть в БД")
    @GetMapping("api/game/getByDeveloper")
    public List<Game> gettingGameListByDeveloper(@RequestParam String developer) {
        return gameRep.findByDeveloper(developer);
    }

    @Operation(summary = "Поиск игры по издателю",
            description = "Проводит поиск в БД по издателю и возвращает сведения об игре, если она есть в БД")
    @GetMapping("api/game/getByPublisher")
    public List<Game> gettingGameListByPublisher(@RequestParam String publisher) {
        return gameRep.findByPublisher(publisher);
    }

    @Operation(summary = "Поиск игры по жанру",
            description = "Проводит поиск в БД по жанру и возвращает сведения об игре, если она есть в БД")
    @GetMapping("api/game/getByGenre")
    public List<Game> gettingGameListByGenre(@RequestParam String genre) {
        return gameRep.findByGenre(genre);
    }

    @Operation(summary = "Поиск игры по серии",
            description = "Проводит поиск в БД по серии и возвращает сведения об игре, если она есть в БД")
    @GetMapping("api/game/getBySeries")
    public List<Game> gettingGameListBySeries(@RequestParam String series) {
        return gameRep.findBySeries(series);
    }

    @Operation(summary = "Поиск игры по году выпуска",
            description = "Проводит поиск в БД по году выпуска и возвращает сведения об игре, если она есть в БД")
    @GetMapping("api/game/getByReleaseYear")
    public List<Game> gettingGameListByReleaseYear(@RequestParam int releaseYear) {
        return gameRep.findByReleaseYear(releaseYear);
    }

    @Operation(summary = "Поиск игры по наличию в продаже",
            description = "Проводит поиск в БД по наличию в продаже и возвращает сведения об игре, если она есть в БД")
    @GetMapping("api/game/getByAvailability")
    public List<Game> gettingGameListByAvailability(@RequestParam String availability) {
        return gameRep.findByAvailability(availability);
    }
}