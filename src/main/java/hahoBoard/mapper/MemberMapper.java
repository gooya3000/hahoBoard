package hahoBoard.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import hahoBoard.domain.member.MemberDTO;


@Component
@Repository(value="hahoBoard.mapper")
public interface MemberMapper {

	Integer insertMember(MemberDTO memberDTO) throws Exception;

	int updateJoin(MemberDTO memberDTO);

	MemberDTO selectMember(MemberDTO dto);

	List<MemberDTO> selectLoginMember(MemberDTO member);

	List<MemberDTO> selectMembers();

	void deleteMember(String memberId);

}
