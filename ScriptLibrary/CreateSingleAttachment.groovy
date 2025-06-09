import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.impl.DefaultAttachment
import javax.mail.util.ByteArrayDataSource

def Message processData(Message message) {
    def body = message.getBody(InputStream.class)
    def headers = message.getHeaders()
    def properties = message.getProperties()
    
    def ediPayload = properties.get("SourcePayload");   //Property "SourcePayload" should be initialised in flow

    def fileName = headers.get("fileName")    //Header "fileName" should be initialised in flow
    def fileContentType = headers.get("fileContentType")   //Header "fileContentType" should be initialised in flow

    if(fileName == "" || fileName == null){
        fileName = "Payload"
    }
    if(fileContentType == "" || fileContentType == null){
        fileContentType = "application/xml"
    }

    // Set MIME type
    def dataSource = new ByteArrayDataSource(ediPayload, fileContentType)    
    // Construct a DefaultAttachment object
    def attachment = new DefaultAttachment(dataSource)
    message.addAttachmentObject(fileName, attachment)
    return message
}