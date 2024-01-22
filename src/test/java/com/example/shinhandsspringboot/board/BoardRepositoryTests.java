package com.example.shinhandsspringboot.board;

import com.example.shinhandsspringboot.domain.Board;
import com.example.shinhandsspringboot.persistence.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.stream.Stream;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void inspect() {
        Class<?> clz = boardRepository.getClass();

        System.out.println(clz.getName());

        Class<?>[] interfaces = clz.getInterfaces();

        Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));

        Class<?> superClasses = clz.getSuperclass();

        System.out.println(superClasses.getName());
    }

    @Test
    public void testInsert() {
        Board board = new Board();

        board.setTitle("게시물의 제목");
        board.setContent("게시물 내용 넣기 ...");
        board.setWriter("user00");

        boardRepository.save(board);
    }

    @Test
    public void testRead() {

        boardRepository.findById(1L).ifPresent((board) -> {
            System.out.println(board);
        });
    }

    /*@Test
    public void testUpdate(){
        System.out.println("Read First.................................");
        Board board = boardRepository.findOne(1L);

        System.out.println("Update Title ..............................");
        board.setTitle("수정된 제목");

        System.out.println("Call Save() ...............................");
        boardRepository.save(board);

    }*/

    @Test
    public void testDelete() {
        System.out.println("DELETE Entity");

        boardRepository.deleteAll();
    }

    @Test
    public void testInsert200() {
        for (int i = 1; i <= 200; i++) {
            Board board = new Board();

            board.setTitle("제목.." + i);
            board.setContent("내용 .." + i + " 채우기");
            board.setWriter("user0" + (i % 10));
            boardRepository.save(board);
        }
    }

    @Test
    public void testByTitle() throws Exception{
        //given

        boardRepository.findBoardByTitle("제목..177")
                .forEach(board -> System.out.println(board));

    }

    @Test
    public void testByWriter() throws Exception{
        //given
        Collection<Board> results = boardRepository.findByWriter("user00");

        results.forEach(
                board -> System.out.println(board)
        );

    }

    @Test
    public void testByWriterContaining() throws Exception{
        //given
        Collection<Board> results = boardRepository.findByWriterContaining("05");
        results.forEach(board -> System.out.println(board));

    }

    @Test
    public void testByTitleAndBno() throws Exception{
        //given
        Collection<Board> results = boardRepository.findByTitleContainingAndBnoGreaterThan("5", 326L);
        results.forEach(board -> System.out.println(board));

    }

    @Test
    public void testBnoOrderBy() throws Exception{
        //given
        Collection<Board> results = boardRepository.findByBnoGreaterThanOrderByBnoDesc(300L);
        results.forEach(board -> System.out.println(board));

    }

}
