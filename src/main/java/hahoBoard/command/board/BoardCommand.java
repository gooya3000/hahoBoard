package hahoBoard.command.board;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardCommand {
	
	@NotBlank(message="*제목을 입력해주세요.")
	String boardSubj;
	String boardCont;
	MultipartFile boardFile[];
	@NotBlank(message="*글 비밀번호를 입력해주세요.")
	String boardPass;
	
	int boardNum;
	
	int boardDel[];

}
