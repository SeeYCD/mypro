package myproject.frame.util;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import myproject.frame.entity.MailSenderInfo;

import com.sun.mail.util.MailSSLSocketFactory;
/**
 * 发送邮件demo，需要开启from用户的授权
 * @author user
 *
 */
public class MailUtil {
	
	public static void sendMail(MailSenderInfo mailInfo) throws Exception{
			final Properties props = new Properties();
	        // 表示SMTP发送邮件，需要进行身份验证
	        props.put("mail.smtp.auth", "true");
	        //指定发件服务器主机地址
	        props.put("mail.smtp.host", mailInfo.getMailServerHost());
	        // 发件服务器验证账号，注意是登陆用的账号而不是邮箱名
	        props.put("mail.user", mailInfo.getUserName());
	        //发件服务器验证密码
	        props.put("mail.password", mailInfo.getPassword());
	        props.setProperty("mail.transport.protocol", "smtp");
	        if(mailInfo.getMailServerHost().equals("smtp.qq.com")){
	        	//指定端口为165，默认为25
	        	props.put("mail.smtp.port", 465);
	        	MailSSLSocketFactory sf = new MailSSLSocketFactory();
	 	        sf.setTrustAllHosts(true);
	 	        props.put("mail.smtp.ssl.enable", "true");
	 	        props.put("mail.smtp.ssl.socketFactory", sf);
	        }
	        
	        Authenticator  authenticator=new Authenticator(){
	        	 protected PasswordAuthentication getPasswordAuthentication() {
	                 // 用户名、密码
	                 String userName = props.getProperty("mail.user");
	                 String password = props.getProperty("mail.password");
	                 return new PasswordAuthentication(userName, password);
	             }
	        };
	        
	        Session session =Session.getInstance(props, authenticator);
	        session.setDebug(true);
	      
	        Message msg = new MimeMessage(session);
	        //设置发件人
	        msg.setFrom(new InternetAddress(mailInfo.getFromAddress()));
	        //设置主题
	        msg.setSubject(mailInfo.getSubject());
	        //设置收件人
	        msg.setRecipients(RecipientType.TO, InternetAddress.parse(mailInfo.getToAddress()));
	        if(!MiscUtil.isNullOrEmpty(mailInfo.getCcAddress())){
	        	 //设置抄送人
		        msg.setRecipients(RecipientType.CC, InternetAddress.parse(mailInfo.getCcAddress()));
	        }
	        MimeMultipart mp = new MimeMultipart();
	        BodyPart bpText = new MimeBodyPart();
	        //设置正文
	        bpText.setContent(mailInfo.getContent(),"text/html;charset=UTF-8"); 
	        mp.addBodyPart(bpText);
	        //设置附件
	        if(!MiscUtil.isNullOrEmpty(mailInfo.getAttachIS())){
	        	//for(int i = 0; i < mailInfo.getAttachFileNames().length; i++){
	        		BodyPart bpAtt = new MimeBodyPart();
	        		/*FileDataSource fds = new FileDataSource(mailInfo.getAttachFileNames()[i]);
	        		bpAtt.setDataHandler(new DataHandler(fds)); 
	        		bpAtt.setFileName(MimeUtility.encodeText(fds.getName(), "UTF-8", "B"));*/
	        		DataSource ds=new ByteArrayDataSource(mailInfo.getAttachIS(), "application/vnd.ms-excel");
	        		bpAtt.setDataHandler(new DataHandler(ds));
//	        		bpAtt.setFileName(MimeUtility.encodeText(ds.getName(),"UTF-8", "B"));
	        		bpAtt.setFileName(MimeUtility.encodeText("单统计.xls","UTF-8", "B"));
	        		mp.addBodyPart(bpAtt);
	        	//}
	        }
	        msg.setContent(mp);
	        Transport.send(msg);
	}
	
	public static void main(String[] args){
//		StringBuffer content = new StringBuffer();
//		content.append("发送邮件成功,demo");
//		//配置发送邮件参数
//		MailSenderInfo  info = new MailSenderInfo();
//		info.setMailServerHost("smtp.qq.com");
//		info.setFromAddress(" @qq.com");
//		info.setUserName(" @qq.com");
//		info.setPassword("3333");
//		
//		info.setContent(content.toString());
//		info.setAttachFileNames(new String[]{"E:/Users/Desktop/heck.xlsx"});
//		info.setSubject("demo不是垃圾邮件");
//		info.setToAddress("ff@163.com,ff@qq.com");
//		try {
//			sendMail(info);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {
			demo();
		} catch (GeneralSecurityException e) {
 			e.printStackTrace();
		}
	}
	
	public static void demo() throws GeneralSecurityException{
		 //收件人电子邮箱
	    String to = "@126.com";
	    //发件人电子邮箱
	    final String from = "@qq.com";
	    // 指定发送邮件的主机为 smtp.qq.com（(由于我使用的qq邮箱，所以这里服务器使用的是qq服务器）
	    String host = "smtp.qq.com";  //QQ 邮件服务器
	    // 获取系统属性
	    Properties properties = System.getProperties();
	    // 设置邮件服务器
	    properties.setProperty("mail.smtp.host", host);
	    properties.put("mail.smtp.auth", "true");
	    MailSSLSocketFactory sf = new MailSSLSocketFactory();
	    sf.setTrustAllHosts(true);
	    properties.put("mail.smtp.ssl.enable", "true");
	    properties.put("mail.smtp.ssl.socketFactory", sf);
	    // 获取默认session对象
	    Session session = Session.getDefaultInstance(properties,new Authenticator(){
	        public PasswordAuthentication getPasswordAuthentication()
	        {
	            return new PasswordAuthentication(from, "*****"); //发件人邮件用户名、第三方登录授权码
	        }
	    });
 	    try{
	        System.out.println("Sent message begin....");
 	        // 创建默认的 MimeMessage 对象
	        MimeMessage message = new MimeMessage(session);
 	        // Set From: 头部头字段
	        message.setFrom(new InternetAddress(from));
 	        // Set To: 头部头字段
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
 	        // Set Subject: 头部头字段
	        message.setSubject("This is the test mail!");
 	        // 设置消息体
	        message.setText("Hello java");
 	        // 发送消息
	        Transport.send(message);
	        System.out.println("Sent message successfully....");
	    }catch (MessagingException mex) {
	        mex.printStackTrace();
	    }
	}
 	
}
