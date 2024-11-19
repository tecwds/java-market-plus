package top.wpaint.marketplus.util;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import top.wpaint.marketplus.common.exception.AppException;
import top.wpaint.marketplus.common.Status;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

@Component
public class VerCodeUtil {

    @Resource
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * 发送邮件的方法
     *
     * @param to      接收者邮箱地址
     * @param subject 邮件主题
     * @param text    邮件内容
     * @return 发送结果
     */
    public Boolean sendSimpleMessage(String to, String text) throws AppException {
        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail, "潘的大超市");
            helper.setTo(to);
            helper.setSubject("潘的大超市 - 注册验证码");
            helper.setText(text, true); // 第二个参数true表示支持HTML格式的邮件
            mailSender.send(message);
        }
        catch (UnsupportedEncodingException | MessagingException e) {
            throw new AppException(Status.ERROR);
        }

        return true;
    }

    public static Integer genVerifyCode() {
        // TODO 完善验证码的生成
        return new Random().nextInt(900000) + 100000;
    }

}