package com.example.shinhandsspringboot.persistence;

import com.example.shinhandsspringboot.domain.Board;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {
    public List<Board> findBoardByTitle(String title);

    public Collection<Board> findByWriter(String writer);

    public Collection<Board> findByWriterContaining(String writer);

    public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);

    public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);

    public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);
}
