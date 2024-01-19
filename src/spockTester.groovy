import com.sap.gateway.ip.core.customdev.processor.MessageImpl
import com.sap.gateway.ip.core.customdev.util.Message
import spock.lang.Shared

class spockTester extends spock.lang.Specification {

    @Shared GroovyShell shell = new GroovyShell()
    @Shared Script script
    private Message msg

    def setupSpec() {
        // Load Groovy Script
        script = shell.parse(new File("src\\process.groovy"))
    }

    def setup() {
        this.msg = new MessageImpl()
    }

    def "message body and header changed"() {

        given: "msg input with body and header data"
        this.msg.setBody("BaseData\n")
        this.msg.setHeader("header1", "oldHeaderData")

        when: "executing the Groovy script's processData method"
        script.processData(this.msg)

        then: "body text is enriched with more text, header value is changed"
        this.msg.getBody() == "BaseData\nAdded body text from script"
        this.msg.getHeaders()["header1"] == "newHeaderData"
    }
}
