<?xml version='1.0' ?>
<xsl:stylesheet version="1.0" xmlns:ns0="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
                              xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
                               
   <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" />

   <xsl:template match="/">
               <xsl:copy-of select="SOAP-ENV:Envelope/SOAP-ENV:Body/*" />
   </xsl:template>
</xsl:stylesheet>