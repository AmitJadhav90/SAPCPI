import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.StreamingMarkupBuilder
import groovy.xml.XmlUtil

def Message processData(Message message) {
    def body = message.getBody(java.lang.String) as String
    def xml = new XmlSlurper().parseText(body)

    // New root node name
    def newRootNodeName = 'NewRootNode'    //Add new root node name here

    // Build the new XML structure with the new root node
    def builder = new StreamingMarkupBuilder()
    def newXml = builder.bind {
        "${newRootNodeName}" {
            xml.children().each { child ->
                mkp.yield child
            }
        }
    }.toString()

    // Set the new XML back to the message body
    message.setBody(newXml)

    return message
}