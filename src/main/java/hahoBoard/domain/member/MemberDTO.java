package hahoBoard.domain.member;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	

	String memberId;

	String memberNick;

	String memberPass;

	Timestamp memberRegi;
	
	int memberLv;
	
	String memberJoin;

}
