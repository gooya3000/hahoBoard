package hahoBoard.command.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthInfo {
	
	String id;
	String nick;
	int lv;

}
