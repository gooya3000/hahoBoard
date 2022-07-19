package hahoBoard.service.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import hahoBoard.domain.board.BoardDTO;
import hahoBoard.mapper.board.BoardMapper;

@Component
@Service
public class BoardListService {
	
	@Autowired
	BoardMapper mapper;

	public void selectList(Model model) {
		// TODO Auto-generated method stub
		List<BoardDTO> list = new ArrayList();
		list = mapper.selectList();
		model.addAttribute("boardList", list);
	}

}
