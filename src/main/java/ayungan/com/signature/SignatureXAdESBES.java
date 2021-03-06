/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ayungan.com.signature;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;

import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.XAdESSchemas;
import es.mityc.javasign.EnumFormatoFirma;
import es.mityc.javasign.xml.refs.InternObjectToSign;
import es.mityc.javasign.xml.refs.ObjectToSign;

/**
 *
 * @author Alex
 */
public class SignatureXAdESBES extends SignatureXML {

	private byte[] dataOriginal;

	public SignatureXAdESBES(byte[] dataOriginal) {
		this.dataOriginal = dataOriginal;
	}

	public static byte[] firmarByteData(byte[] xmlOriginal, InputStream pathSignature, String passSignature)
			throws CertificateException, IOException {
		SignatureXAdESBES signature = new SignatureXAdESBES(xmlOriginal);
		signature.setPassSignature(passSignature);
		signature.setPathSignature(pathSignature);
		return signature.execute();
	}

	@Override
	protected DataToSign createDataToSign() throws IOException {
		Document docToSign = null;
		DataToSign datosAFirmar = null;
		try {
			datosAFirmar=new DataToSign();
			datosAFirmar.setXadesFormat(EnumFormatoFirma.XAdES_BES);

			datosAFirmar.setEsquema(XAdESSchemas.XAdES_132);
			datosAFirmar.setXMLEncoding("UTF-8");
			datosAFirmar.setEnveloped(true);
			datosAFirmar.addObject(new ObjectToSign(new InternObjectToSign("comprobante"), "contenido comprobante",
					null, "text/xml", null));
			datosAFirmar.setParentSignNode("comprobante");

			docToSign = getDocumentFromByte(this.dataOriginal);
		} catch (IOException ex) {
			Logger.getLogger(SignatureXAdESBES.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("error en DataToSign->" + ex.getMessage());
			
		}
		datosAFirmar.setDocument(docToSign);

		return datosAFirmar;
	}
}
