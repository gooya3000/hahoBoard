package hahoBoard.mapper.board;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import hahoBoard.domain.board.BoardDTO;

@Component
@Repository(value="hahoBoard.mapper.board")
public interface BoardMapper {

	void insertBoard(BoardDTO dto);

	List<BoardDTO> selectList();

	BoardDTO selectOne(int boardNum);

	void delPost(int boardNum);

}
