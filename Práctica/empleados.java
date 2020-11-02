package jaxp.stax;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

class StaxExample {
    private static final String SALARIO = "salario";
    private static final String NOMBRE = "nombre";
    public static <E> void main(String[] args) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {

        XMLInputFactory xmlif = XMLInputFactory.newInstance();
//Leemos el documento xml
        XMLStreamReader xmlsr = xmlif.createXMLStreamReader(new FileReader("src/empleados.xml"));
        ArrayList<String> nombres = new ArrayList<String>();
        String tag = null;
        String nombre = null;
        String salario = null;
        int eventType;
        System.out.println("Iniciando el documento");
        while (xmlsr.hasNext()){
            eventType = xmlsr.next();
//Creamos un switch para controlar el inicio y fin del documento
            switch(eventType){
                case XMLEvent.START_ELEMENT:
//obtenemos las etiquetas de nombre y salario
                    tag = xmlsr.getLocalName();
                    if(tag.equals(NOMBRE)){
                        nombre = xmlsr.getElementText();
                    }else if(tag.equals(SALARIO)){
                        salario = xmlsr.getElementText();
                        //comprobamos si el salario supera los 30000
                        if(Integer.parseInt(salario) >= 30000)
                            nombres.add(nombre);
                    }
                    break;
                    //finalizamos la lectura y lo sacamos por pantalla
                case XMLEvent.END_DOCUMENT:
                    System.out.println("Fin del documento");
                    break;
            }
        }
        System.out.println("Empleados con salario mayor a 30000: "+nombres);
    }
}
