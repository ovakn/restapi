package com.ovakn.restapi.repository;

import com.ovakn.restapi.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GameRep extends JpaRepository<Game, Integer> {
    Game findByName(String name);
    List<Game> findByDeveloper(String developer);
    List<Game> findByPublisher(String publisher);
    List<Game> findByGenre(String genre);
    List<Game> findBySeries(String series);
    List<Game> findByAvailability(String availability);
    List<Game> findByReleaseYear(int releaseYear);
    List<Game> findByIsBought(boolean isBought);
}