package com.example.weatherchallenge.repositories;

import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByUser (User user);
    Board findBoardByUserAndName (User user, String name);
    boolean existsBoardByNameAndUser (String boardName, User user);
    void deleteAllByUser(User user);
}