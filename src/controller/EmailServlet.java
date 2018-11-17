package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EmailServlet",urlPatterns = "/EmailServlet")
public class EmailServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties prop = new Properties();
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.host", "smtp.qq.com");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.debug", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        try {
            PrintWriter printWriter=response.getWriter();
            Transport ts = session.getTransport();
            //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
            ts.connect("smtp.qq.com", "3223826042@qq.com", "wvcymrfhqdukdbcg");
            //4、创建邮件
            Message message = createSimpleMail(session,request.getParameter("email"));
            Object content=message.getContent();
            String s=content.toString();
            String code=s.substring(s.length()-6);
            printWriter.println(code);
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static MimeMessage createSimpleMail(Session session,String email)
             throws Exception {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append((int)(Math.random()*10));
        stringBuffer.append((int)(Math.random()*10));
        stringBuffer.append((int)(Math.random()*10));
        stringBuffer.append((int)(Math.random()*10));
        stringBuffer.append((int)(Math.random()*10));
        stringBuffer.append((int)(Math.random()*10));

        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("3223826042@qq.com"));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        //邮件的标题
        message.setSubject("验证码邮件");
        //邮件的文本内容
        message.setContent("你好啊！欢迎使用在线学习系统。你的验证码是"+stringBuffer, "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }


}

