package hahoBoard.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hahoBoard.command.board.BoardCommand;
import hahoBoard.command.board.CommentCommand;
import hahoBoard.command.login.LoginCommand;
import hahoBoard.command.member.MemberCommand;
import hahoBoard.service.board.BoardDelService;
import hahoBoard.service.board.BoardDetailService;
import hahoBoard.service.board.BoardInsertService;
import hahoBoard.service.board.BoardListService;
import hahoBoard.service.board.CommentDelService;
import hahoBoard.service.board.CommentInsertService;
import hahoBoard.service.board.CommentListService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardInsertService boardInsertService;
	@Autowired
	BoardListService boardListService;
	@Autowired
	BoardDetailService boardDetailService;
	@Autowired
	CommentInsertService commentInsertService;
	@Autowired
	CommentListService commentListService;
	@Autowired
	CommentDelService commentDelService;
	@Autowired
	BoardDelService boardDelService;
	
	@RequestMapping(value = "/list")
	public String list(Model model) {	
		boardListService.selectList(model);
		return "thymeleaf/hahoBoard/board_list";
	}
	
	@RequestMapping(value = "/write")
	public String write(Model model) {	
		BoardCommand boardCommand = new BoardCommand();
		model.addAttribute("boardCommand", boardCommand);
		return "thymeleaf/hahoBoard/board_write";
	}
	
	@RequestMapping(value = "/writeAction")
	public String writeAction(@Validated BoardCommand command,BindingResult result, HttpServletRequest request, Model model) {	
		
		if (result.hasErrors()) {
			return "thymeleaf/hahoBoard/board_write";
		}
		
		String location = boardInsertService.insertBoard(command, request, model);	
		return location;
		
	}
	
	@RequestMapping(value = "/post")
	public String post( @RequestParam(value="boardNum") int boardNum, Model model, HttpSession session) {	
		boardDetailService.selectOne(boardNum, model, session);
		commentListService.selectList(boardNum, model, session);
		return "thymeleaf/hahoBoard/board_detail";
	}
	
	@RequestMapping(value = "/comment")
	public String comment(@Validated CommentCommand command, BindingResult result, 
			HttpServletRequest request) {	
		commentInsertService.insertComment(command,request);
		return "redirect:/board/post?boardNum="+command.getBoardNum();
	}
	@RequestMapping(value = "/commentDel")
	public String commentDel(@RequestParam(value="commentNum") int commentNum, @RequestParam(value="boardNum") int boardNum) {	
		commentDelService.delComment(commentNum);
		return "redirect:/board/post?boardNum="+boardNum;
	}
	
	@RequestMapping(value = "/postDel")
	public String postDel( @RequestParam(value="boardNum") int boardNum, Model model, HttpSession session) {	
		boardDetailService.selectOne(boardNum, model, session);
		commentListService.selectList(boardNum, model, session);
				
		model.addAttribute("boardNum", boardNum);
		return "thymeleaf/hahoBoard/board_detail_del";
	}
	@RequestMapping(value = "/postDelAction")
	public String postDelAction( BoardCommand command, Model model) {
		String location="";
		location = boardDelService.delPost(command,model);
		return location;
	}

}
