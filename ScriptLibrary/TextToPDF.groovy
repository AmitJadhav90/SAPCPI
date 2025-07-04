import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfWriter

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

import com.sap.gateway.ip.core.customdev.util.Message

Message processData(Message message) {
    // Get incoming plain text
    def body = message.getBody(String)  // assuming plain text

    // Prepare PDF in memory
    ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream()

    Document document = new Document()
    PdfWriter.getInstance(document, pdfOutput)

    document.open()
    document.add(new Paragraph(body))  // Simple paragraph with text
    document.close()

    // Set PDF as message body
    message.setBody(pdfOutput.toByteArray())

    // Optional: Set header for PDF download or MIME type
    def headers = message.getHeaders()
    headers.put("Content-Type", "application/pdf")
    headers.put("Content-Disposition", "attachment; filename=output.pdf")
    message.setHeaders(headers)

    return message
}
