package com.novatronic.pscabas.gt.webcore.services.implement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.novatronic.pscabas.gt.webcore.domains.esquema.Empresa;
import com.novatronic.pscabas.gt.webcore.services.interfaces.AplicacionMigradorService;

@Service
public class AplicacionMidradorServiceImpl implements AplicacionMigradorService {

	@Override
	public Empresa convertirAplicacion() {
		try {
			// System.out.println(FileServiceImpl.archivo.getAbsolutePath());

			/*
			 * Serializer serializer = new Persister(); Empresa empresa =
			 * serializer.read(Empresa.class, FileServiceImpl.archivo);
			 * System.out.println(empresa.toString());
			 */

			/*
			 * XMLInputFactory xif = XMLInputFactory.newFactory(); StreamSource xml = new
			 * StreamSource(
			 * "C:\\Users\\jtrujillo\\Desktop\\templates\\templates\\Template-empresa-nova.xml"
			 * ); XMLStreamReader xsr = xif.createXMLStreamReader(xml); xsr.nextTag();
			 * while(!xsr.getLocalName().equals("return")) { xsr.nextTag(); }
			 * 
			 * JAXBContext jc = JAXBContext.newInstance(Empresa.class); Unmarshaller
			 * unmarshaller = jc.createUnmarshaller(); JAXBElement<Empresa> jb =
			 * unmarshaller.unmarshal(xsr, Empresa.class); xsr.close();
			 * 
			 * Empresa customer = jb.getValue(); System.out.println(customer.nombre);
			 * System.out.println(customer.mnemotico); System.out.println(customer.cifrado);
			 */

			/*
			 * JAXBContext context = JAXBContext.newInstance(Empresa.class); return
			 * (Empresa) context.createUnmarshaller() .unmarshal(new FileReader(
			 * "C:\\Users\\jtrujillo\\Desktop\\templates\\templates\\Template-empresa-nova.xml"
			 * ));
			 */

			JAXBContext jc = JAXBContext.newInstance(Empresa.class);

			Unmarshaller unmarshaller = jc.createUnmarshaller();
			Empresa customer = (Empresa) unmarshaller.unmarshal(FileServiceImpl.archivo);

			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(customer, System.out);
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
			return null;
		}
	}
}
