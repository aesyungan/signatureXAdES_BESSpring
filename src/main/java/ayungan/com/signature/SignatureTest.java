/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ayungan.com.signature;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author Alex
 */
public class SignatureTest {

	public static void main(String[] args) {
		String xmlPath = "D:\\development\\App Java\\NetBeansProjects\\firmarSobreXMl\\factura.xml";
		String pathSignature = "D:\\development\\App Java\\NetBeansProjects\\firmarSobreXMl\\mycert.p12";
		String passSignature = "12345678";

		System.out.println("Ruta del XML de entrada: " + xmlPath);
		System.out.println("Ruta Certificado: " + pathSignature);
		System.out.println("Clave del Certificado: " + passSignature);
		File initialFile = new File(pathSignature);
		try {
			InputStream targetStream = new FileInputStream(initialFile);

			byte[] bxmlOriginal = ConvertFile.readBytesFromFile(xmlPath);
			// ConvertFile.saveBytes(bxmlOriginal, "E:\\dacturasaveByte.xml");
			byte[] data2 = SignatureXAdESBES.firmarByteData(bxmlOriginal, targetStream, passSignature);
			ConvertFile.saveBytes(data2, "E:\\fatura_sign12.xml");
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}
