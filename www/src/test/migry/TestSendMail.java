package test.migry;

import net.migry.common.GmailSender;

public class TestSendMail {

	public static void main(String[] args) {
		GmailSender.sendMail("migry7411@gmail.com", "migry7411@nate.com", "DJ SCREAM", "test", "메일 테스트\n메일 테스트");
	}
}
