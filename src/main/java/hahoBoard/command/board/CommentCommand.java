package hahoBoard.command.board;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CommentCommand {
	@NotBlank(message="*댓글을 입력해주세요.")
	String commentCont;
	int boardNum;
}
