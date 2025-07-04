import com.sap.gateway.ip.core.customdev.util.Message
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfWriter

import java.io.ByteArrayOutputStream

Message processData(Message message) {
    // Step 1: Read the plain text input from the message body
    def text = message.getBody(String)

    // Step 2: Set up PDF generation
    ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream()
    Document document = new Document()

    // Define passwords (owner and user)
    String userPassword = "user123"
    String ownerPassword = "admin123"

    // Step 3: Set up PdfWriter with encryption
    PdfWriter writer = PdfWriter.getInstance(document, pdfOutput)
    writer.setEncryption(
        userPassword.getBytes("UTF-8"),
        ownerPassword.getBytes("UTF-8"),
        PdfWriter.ALLOW_PRINTING, // Allowed permissions
        PdfWriter.ENCRYPTION_AES_128 // Use AES encryption
    )

    // Step 4: Write content to PDF
    document.open()
    document.add(new Paragraph("Confidential Document"))
    document.add(new Paragraph("----------------------"))
    document.add(new Paragraph(text))
    document.close()

    // Step 5: Set PDF binary as message body
    message.setBody(pdfOutput.toByteArray())

    // Step 6: Optionally set headers
    def headers = message.getHeaders()
    headers.put("Content-Type", "application/pdf")
    headers.put("Content-Disposition", "attachment; filename=secured.pdf")
    message.setHeaders(headers)

    return message
}
