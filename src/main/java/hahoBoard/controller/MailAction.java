package hahoBoard.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class MailAction {
	@Autowired
	private JavaMailSender mailSender;
	public void sendMail(String reciver, String memberNick) throws MessagingException{
		MimeMessage msg = mailSender.createMimeMessage();
		SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMddHHmmss");
		String num = dateForm.format(new Date());
		String content = "<html><body>"
				       + "안녕하세요 '" + memberNick +"'님 가입을 환영합니다.<br />"
				       
				       + "<a href='http://localhost:9090/"
				       + "memberMail?num="+num+"&reciver="+reciver+"&memberNick="+memberNick+"'>"
				       + "<strong><b>"
				       + "메일인증을 완료하시려면 여기를 눌러주세요.</a>"
				       + "</b></strong>"
					   + "</body></html>";
		String subject = "가입환영인사";

		msg.setHeader("content-type", "text/html; charset=UTF-8");
		msg.setContent(content, "text/html; charset=UTF-8");
		msg.setSubject(subject);
		msg.setRecipient(MimeMessage.RecipientType.TO , new InternetAddress(reciver));
		mailSender.send(msg);
		
	}
}
