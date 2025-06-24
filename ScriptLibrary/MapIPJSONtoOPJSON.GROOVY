import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper
import groovy.json.JsonBuilder

def Message processData(Message message) {
    // Get the JSON input as a string
    def body = message.getBody(String)
    def inputJson = new JsonSlurper().parseText(body)

    // Initialize a map to build the new JSON content
    //Handle single record 
  /*  def jsonContent = [
        record: [
            firstName : inputJson.root.Records.name1,       //if input json is an error, even if it has one record, then define like this "inputJson.root.Records[0].name1"
            lastName : inputJson.root.Records.name2,
            email : inputJson.root.Records.mailaccount,
            phone : inputJson.root.Records.mailaccount,
            recordtype : "User Account"
        ]
    ]*/
    
    //Handle muliple records (multiple 'Records' elements)
    // Create a list to hold the JSON records
    def jsonRecords = []
    
    // Iterate over each <Records> element and build JSON objects
    inputJson.root.Records.each { record ->
        def jsonRecord = [
            firstName: record.name1,
            lastName: record.name2,
            email: record.mailaccount,
            phone: record.contactnumber,
            recordtype : "User Account"
        ]
        jsonRecords << jsonRecord
    }
    def jsonBuilder = new JsonBuilder(jsonRecords)

    // Set the generated JSON as the message body
    message.setBody(jsonBuilder.toPrettyString())
    return message
}