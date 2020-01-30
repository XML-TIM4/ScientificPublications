package xmlteam4.Project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import xmlteam4.Project.businessprocess.DocumentType;
import xmlteam4.Project.exceptions.DocumentParsingFailedException;
import xmlteam4.Project.exceptions.TransformationException;
import xmlteam4.Project.model.TUser;
import xmlteam4.Project.utilities.dom.DOMParser;
import xmlteam4.Project.utilities.transformers.documentxmltransformer.DocumentXMLTransformer;
import xmlteam4.Project.utilities.transformers.xsltransformer.XSLTransformer;

import javax.mail.MessagingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NotificationService {
    @Autowired
    private EmailService emailService;

    @Autowired
    private DOMParser domParser;

    @Autowired
    private DocumentXMLTransformer documentXMLTransformer;

    @Autowired
    private XSLTransformer xslTransformer;

    @Value("${empty-notification-path}")
    private String emptyNotificationPath;

    @Value("${notification-schema-path}")
    private String notificationSchemaPath;

    @Value("${notification-html-xslt}")
    private String notificationToHTML;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public void notifyUser(TUser user, String documentId, DocumentType documentType, String content) throws SAXException, ParserConfigurationException, DocumentParsingFailedException, IOException, TransformerException, TransformationException, MessagingException {
        Document emptyNotification = domParser.buildDocumentFromFile(emptyNotificationPath, notificationSchemaPath);

        emptyNotification.getElementsByTagName("content").item(0).setTextContent(content);
        emptyNotification.getElementsByTagName("recipient-email").item(0).setTextContent(user.getEmail());
        emptyNotification.getElementsByTagName("recipient-name").item(0).setTextContent(user.getUsername());
        emptyNotification.getElementsByTagName("date").item(0).setTextContent(dateTimeFormatter.format(LocalDateTime
                .now()));
        emptyNotification.getElementsByTagName("document-id").item(0).setTextContent(documentId);
        emptyNotification.getElementsByTagName("document-collection").item(0).setTextContent(documentType.toString() +
                "s");

        String contentHTML = xslTransformer.generateXML(documentXMLTransformer.toXMLString(emptyNotification),
                notificationToHTML);

        emailService.sendMail(user.getEmail(), contentHTML);
    }
}
