package com.example.shinhandsspringboot.persistence;

import com.example.shinhandsspringboot.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
    public List<Board> findBoardByTitle(String title);

//    public Collection<Board> findByWriter(String writer);

    public Collection<Board> findByWriterContaining(String writer);

    public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);

    public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);

//    public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    //페이징 처리
    //list / Slice / Page 타입을 써야함
    public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);

    // 제목에 대한 검색 처리
    @Query("SELECT b from Board b where b.title like %?1% and b.bno > 0 order by b.bno desc")
    public List<Board> findByTitle(String title);

    // 내용에 대한 검색 처리
    @Query("select b from Board  b where b.content like %:content% and b.bno > 0 order by b.bno desc ")
    public List<Board> findByContent(@Param("content") String content);

    // 작성자에 대한 검색 처리
    @Query("select b from #{#entityName}  b where b.writer like  %?1%  and b.bno > 0 order by b.bno desc ")
    List<Board> findByWriter(String writer);

    @Query("select b.bno, b.title, b.writer, b.regdate" + " from Board b where b.title like %?1% and b.bno > 0 order by b.bno desc")
    public List<Object[]> findByTitle2(String title);

    //@Query 와 Paging 처리/정렬
    @Query("select b from Board b where b.bno > 0 order by b.bno asc ")
    public List<Board> findByPage(Pageable pageable);

}
