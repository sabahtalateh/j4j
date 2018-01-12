<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <document>
            <xsl:for-each select="document/entry">
                <entry field="{field}"/>
            </xsl:for-each>
        </document>
    </xsl:template>

</xsl:stylesheet>