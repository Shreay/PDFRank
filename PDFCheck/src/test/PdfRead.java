package test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;


public class PdfRead {

	public static void main(String[] args) {
	    InputStream is = null;
	    final String folder_path = "Files/";
        try {
            is = new BufferedInputStream(new FileInputStream(new File(folder_path+"online_collaboration.pdf")));

            AutoDetectParser parser = new AutoDetectParser();
            ContentHandler handler = new BodyContentHandler(-1);
            Metadata metadata = new Metadata();
            int i =0;
            parser.parse(is, handler, metadata);
            for(String line : handler.toString().split("\n")){
            	if(line != null){
            		// System.out.println("Line is  " + line);
            		for(String word : line.split(" ")){
            			if(word != null && word.toLowerCase().indexOf("client")>-1){
        					System.out.println(word);
        					i++;
            			}
            		}
            		
            	}
            }
            System.out.println("total word hits: "+String.valueOf(i));
            /*for (String name : metadata.names()) {
                String value = metadata.get(name);

                if (value != null) {
                    System.out.println("Metadata Name:  " + name);
                    System.out.println("Metadata Value: " + value);
                }
            }*/
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }

	}
	

	

}
