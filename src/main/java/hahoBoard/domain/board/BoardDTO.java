package hahoBoard.domain.board;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	
	String boardSubj;
	String boardCont;
	String boardPass;
	int boardNum;
	int boardCgNum;
	String boardFileOrg;
	String boardFileStr;
	String boardId;
	Timestamp boardRegi;
	String boardIpAddr;
	
	String memberNick;
	

}
