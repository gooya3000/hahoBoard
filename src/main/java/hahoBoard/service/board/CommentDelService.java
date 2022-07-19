package hahoBoard.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import hahoBoard.mapper.comment.CommentMapper;

@Component
@Service
public class CommentDelService {
	
	@Autowired
	CommentMapper mapper;

	public void delComment(int commentNum) {
		// TODO Auto-generated method stub
		
		mapper.delComment(commentNum);
		
		
	}

}
