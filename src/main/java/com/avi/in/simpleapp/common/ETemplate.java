package com.avi.in.simpleapp.common;
import com.avi.in.simpleapp.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
public class ETemplate {

	@Qualifier("gmailService")
	@Autowired
	IEmailService iEmailService;

	private static final Logger logger = LoggerFactory.getLogger(ETemplate.class);
	public static int accountActivationMail(String toUser, String firstName, String token){
		logger.info("***** ETemplate :: accountActivationMail to *****" + toUser);
		int res = 0;
		try {
			String template = ProjectConstant.htmlContent;
			String mailBody = template.replaceAll("body", "http://localhost:8080"+"?userToken="+token);
			//String mailBody = template.replaceAll("FIRST_NAME", firstName).replaceAll("ACTIVATION_URL", ExtPropertyReader.activationUrl+"?userToken="+token);
			ArrayList<String> to =  new ArrayList<>();
			to.add(toUser);
			res = GmailService.sentMail(to, "Account Activation mail", mailBody);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return res;
	}
}
