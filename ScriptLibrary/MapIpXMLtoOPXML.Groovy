import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.MarkupBuilder

def Message processData(Message message) {
    // Get the XML input as a string
    def body = message.getBody(String)
    def inputXml = new XmlSlurper().parseText(body)

    // Initialize StringWriter to hold the new XML content
    def writer = new StringWriter()
    def xmlBuilder = new MarkupBuilder(writer)

    // Handle single records ( 'Records' elements)
    xmlBuilder.root {
        User {
            firstName(inputXml.Records.name1.text())
            lastName(inputXml.Records.name2.text())
            email(inputXml.Records.mailaccount.text())
            phone(inputXml.Records.mailaccount.text())
            recordtype("User Account")
        }
    }
    
     //Handle unbounded records (multiple 'Records' elements)
   /** xmlBuilder.root {
        inputXml.Records.each{ recData ->
            User {
                firstName(recData.name1.text())
                lastName(recData.name2.text())
                email(recData.mailaccount.text())
                phone(recData.mailaccount.text())
                recordtype("User Account")
            }
        }
    } **/
    

    // Set the generated XML as the message body
    message.setBody(writer.toString())
    return message
}