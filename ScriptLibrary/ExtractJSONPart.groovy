import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.JsonSlurper;
import groovy.json.JsonBuilder

def Message processData(Message message) {
                
        def body = message.getBody(String);
        def map = new JsonSlurper();
        def object = map.parseText(body);
        def parsedJson = new JsonBuilder(object.PAYROLLHDR);  //replace your JSON nodename with "PAYROLLHDR"
        message.setBody(parsedJson.toString());
        return message;
}