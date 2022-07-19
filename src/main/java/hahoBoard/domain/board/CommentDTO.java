package hahoBoard.domain.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
	
	
	
	int commentNum;
	String commentCont;
	String commentId;
	String commentRegi;
	String commentIpAddr;
	
	int boardNum;
	
	String memberNick;
	
	int memberLv;

}
