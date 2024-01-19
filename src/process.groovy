import com.sap.gateway.ip.core.customdev.util.Message

Message processData(Message message) {

    def body = message.getBody()
    message.setBody(body + "Added new body text from script")

    message.setHeader("header1", "newHeaderData")

    return message
}
