package hahoBoard.service.board;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import hahoBoard.command.board.BoardCommand;
import hahoBoard.domain.board.BoardDTO;
import hahoBoard.mapper.board.BoardMapper;

@Component
@Service
public class BoardDelService {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	BoardMapper mapper;

	public String delPost(BoardCommand command, Model model) {
		// TODO Auto-generated method stub

		String location = "";
		BoardDTO dto = mapper.selectOne(command.getBoardNum());

		if (encoder.matches(command.getBoardPass(), dto.getBoardPass())) {

			if (dto.getBoardFileStr() != null) {

				File file = null;
				String filePath = "D:\\project\\STAM\\hahoBoard\\src\\main\\resources\\static\\uploadFile";

				String storeFileName[] = dto.getBoardFileStr().split("`");
				System.out.println(storeFileName);

				for (String str : storeFileName) {
					file = new File(filePath + "/" + str);
					if (file.exists())
						file.delete();

				}
			}

			mapper.delPost(command.getBoardNum());
			location = "redirect:/board/list";

		} else {
			model.addAttribute("valid_pass", "*비밀번호가 일치하지 않습니다.");
			location = "redirect:/board/postDel?boardNum=" + dto.getBoardNum();
		}

		return location;

	}

	public void delPost_admin(BoardCommand command) {
		// TODO Auto-generated method stub
		
		System.out.println(command.getBoardDel()[0]);

		BoardDTO dto = new BoardDTO();

		for (int num : command.getBoardDel()) {
			
			dto = mapper.selectOne(num);
			
			System.out.println(dto);
			if (dto.getBoardFileStr() != null) {

				File file = null;
				String filePath = "D:\\project\\STAM\\hahoBoard\\src\\main\\resources\\static\\uploadFile";

				String storeFileName[] = dto.getBoardFileStr().split("`");
				System.out.println(storeFileName);

				for (String str : storeFileName) {
					file = new File(filePath + "/" + str);
					if (file.exists())
						file.delete();

				}
			}
			mapper.delPost(num);

		}

	}

}
