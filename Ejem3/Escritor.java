import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

// Recuerda incluir esta dependencia en Project Structure si haces uso de IndentingXMLStreamWriter
import javanet.staxutils.IndentingXMLStreamWriter;

public class Escritor {
    public void generarParkingXML() {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
        FileOutputStream fileOutputStream = null;
        XMLStreamWriter xmlStreamWriter = null;

        try {
            fileOutputStream = new FileOutputStream("D:\\Cloud\\Mega\\UFV\\DIS\\src\\parking.xml");
            xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(fileOutputStream);

            // Mostar el XML con la indentación de líneas y sangrados (no es obligatorio)
            xmlStreamWriter = new IndentingXMLStreamWriter(xmlStreamWriter);

            // Encoding y versión de XML

            xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
            //Incluimos la DTD para validar el documento. La DTD debe estar junto al XML y preferiblemente en fichero independiente
            xmlStreamWriter.writeDTD("<!DOCTYPE parking SYSTEM \"parking.dtd\">");

            // Comentario XML
            xmlStreamWriter.writeComment(" Generado mediante StAX ");

            // Abrimos <parking> (elemento root)
            xmlStreamWriter.writeStartElement("parking");

            // Abrimos <plaza>
            xmlStreamWriter.writeStartElement("plaza");
            xmlStreamWriter.writeAttribute("id", "1"); // El valor se puede pedir  mediante la lectura desde consola

            xmlStreamWriter.writeStartElement("matricula");
            xmlStreamWriter.writeCharacters("AAA-1111"); // El valor se puede pedir  mediante la lectura desde consola
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("propietario");
            xmlStreamWriter.writeCharacters("Juan Sánchez"); // El valor se puede pedir  mediante la lectura desde consola
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("telefono");
            xmlStreamWriter.writeCharacters("111-88-88");  // El valor se puede pedir  mediante la lectura desde consola
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("vencimiento");
            xmlStreamWriter.writeCharacters("04-12-2014");  // El valor se puede pedir  mediante la lectura desde consola
            xmlStreamWriter.writeEndElement();

            // Cerramos <plaza>
            xmlStreamWriter.writeEndElement();

            // Cerramos <parking> (elemento root)
            xmlStreamWriter.writeEndDocument();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } finally {
            try {
                xmlStreamWriter.flush();
                xmlStreamWriter.close();
                fileOutputStream.close();
            } catch (XMLStreamException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Escritor escritor = new Escritor();
        escritor.generarParkingXML();
    }
}
