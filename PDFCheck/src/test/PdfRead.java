package test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;


public class PdfRead {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
	    InputStream is = null;
	    final String folder_path = "Files/";
	    final File folder = new File(folder_path);
	    AutoDetectParser parser = new AutoDetectParser();
        ContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        Map<String, Integer> map=new HashMap<String, Integer>();
        try {
        	System.out.println("Start");
        	//read each file in folder one by one
        	for(File pdfFile : folder.listFiles()){
        		is = new BufferedInputStream(new FileInputStream(pdfFile));
        		int i =0;   //word count
        		parser.parse(is, handler, metadata);   //reading the pdf file
        		//Split by each line
        		for(String line : handler.toString().split("\n")){
                	if(line != null){
                		//Split by each word
                		for(String word : line.split(" ")){
                			if(word != null && word.toLowerCase().indexOf("client")>-1){
            					//System.out.println(word);
            					i++;
                			}
                		}
                		
                	}
                }
        		
        		map.put(pdfFile.getName(),i);
        	}

            
        	List<Object> list=new ArrayList(map.entrySet());
        	
        	//Soring in reverse order
        	Collections.sort(list,new Comparator(){
        	public int compare(Object obj1, Object obj2){
        	return ((Comparable)((Map.Entry)(obj2)).getValue()).compareTo(((Map.Entry)(obj1)).getValue());
        	}
        	});
        	System.out.println("File list sorted based on occurances(File Name: num of occurances) : "+list);
            
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
