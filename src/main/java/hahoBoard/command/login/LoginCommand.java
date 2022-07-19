package hahoBoard.command.login;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginCommand {
	@NotBlank(message="*아이디를 입력해주세요.")
	String memberId;
	@NotBlank(message="*비밀번호를 입력해주세요.")
	String memberPass;
	
	Boolean idStore;
	Boolean autoLogin;
}
