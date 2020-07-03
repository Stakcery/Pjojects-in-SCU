from email.mime.text import MIMEText
from email.header import Header
import smtplib

mail_user = "xxxx@qq.com"  # 用户名
mail_pass = "xxxxxxxx"  # 口令 在QQ邮箱设置里面
sender = 'xxxxx@qq.com'  # 发送者


# ------------------------------------------------邮件发送功能-------------------------------
def smtp(info, receivers) -> None:
    # 第三方 SMTP 服务
    mail_host = "smtp.qq.com"  # 设置服务器

    message = MIMEText(info, 'plain', 'utf-8')
    message['From'] = Header("Y4tacker", 'utf-8')
    message['To'] = Header('成功了嘻嘻嘻', 'utf-8')

    subject = '选课结果'
    message['Subject'] = Header(subject, 'utf-8')

    try:
        smtpObj = smtplib.SMTP_SSL()
        smtpObj.connect(mail_host, 465)  # 465 为 SMTP 端口号
        smtpObj.login(mail_user, mail_pass)
        smtpObj.sendmail(sender, receivers, message.as_string())
    except smtplib.SMTPException:
        pass


