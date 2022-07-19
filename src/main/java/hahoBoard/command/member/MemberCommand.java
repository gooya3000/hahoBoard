package hahoBoard.command.member;



import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MemberCommand {
	
	@NotBlank(message="*아이디를 입력해주세요.")
	@Email(message="*아이디는 이메일 형식이어야 합니다.")
	String memberId;
	@NotBlank(message="*닉네임을 입력해주세요.")
	String memberNick;
	@NotBlank(message="*비밀번호를 입력해주세요.")
	//@Min(value=10, message="*비밀번호는 최소 10자입니다."  )
	String memberPass;
	@NotBlank(message="*비밀번호를 다시 입력해주세요.")
	String memberPassCon;
	
	
	public boolean isUserPwEqualToUserPwCon() {
		if(memberPass.equals(memberPassCon)) {
			return true;
		}
		return false;
	}
	
	String memberDel[];
	
	
	

}
