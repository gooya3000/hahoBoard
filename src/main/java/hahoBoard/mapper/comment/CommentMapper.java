package hahoBoard.mapper.comment;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import hahoBoard.domain.board.CommentDTO;

@Component
@Repository
public interface CommentMapper {

	void insertComment(CommentDTO dto);

	List<CommentDTO> selectComments(int boardNum);

	void delComment(int commentNum);

}
