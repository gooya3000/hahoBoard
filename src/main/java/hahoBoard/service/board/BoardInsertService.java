package hahoBoard.service.board;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import hahoBoard.command.board.BoardCommand;
import hahoBoard.command.member.AuthInfo;
import hahoBoard.domain.board.BoardDTO;
import hahoBoard.mapper.board.BoardMapper;

@Component
@Service
public class BoardInsertService {
	
	AuthInfo authInfo;	
	@Autowired
	PasswordEncoder encoder;	
	@Autowired
	BoardMapper boardMapper;

	public String insertBoard(BoardCommand command, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		
		String location = "";
		
		if (command.getBoardFile().length>3) {
			model.addAttribute("file_info", "첨부파일은 3개까지 업로드 가능합니다.");
			location = "thymeleaf/hahoBoard/board_write";
		}else {
			
			String originalTotal = "";
			String storeTotal = "";
		    //String fileSizeTotal = "";
			String filePath = "D:\\project\\STAM\\hahoBoard\\src\\main\\resources\\static\\uploadFile";
			
			System.out.println(command.getBoardFile());
			
			if (command.getBoardFile()!=null) {
					
				for(MultipartFile mf : command.getBoardFile()) {
								
					String original = mf.getOriginalFilename(); // 전송된 파일명 
									
					String originalFileExtension = // 전송된 파일명으로 부터 확장자만 잘라옴   							
											original.substring(original.lastIndexOf("."));
									
					String store = UUID.randomUUID().toString().replace("-", "")
											+ originalFileExtension; // 임의의 파일명 + 확장자
									
					//String fileSize = Long.toString(mf.getSize());
									
					originalTotal += original + "`";
					storeTotal += store + "`";
					//fileSizeTotal += fileSize + "`";
									
									
					// 파일을저장하기 위해서 파일 객체 생성 
					File file = new File(filePath + "/" + store);
					try {
						mf.transferTo(file);
					}catch(Exception e) {
						location = "thymeleaf/hahoBoard/board_write";
						e.printStackTrace();
					}
				}
				
			}
			
			
			BoardDTO dto = new BoardDTO();
			
			HttpSession session = request.getSession();
			
			AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
			
			dto.setBoardId(authInfo.getId());
			dto.setBoardCont(command.getBoardCont());
			dto.setBoardSubj(command.getBoardSubj());
			dto.setBoardPass(encoder.encode(command.getBoardPass()));
			dto.setBoardFileOrg(originalTotal);
			dto.setBoardFileStr(storeTotal);
			dto.setBoardIpAddr(request.getRemoteAddr());
			
			boardMapper.insertBoard(dto);
			location="redirect:/board/list";
			
			
		}
		
		return location;
		

	}

}
