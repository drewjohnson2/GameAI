
package TicTacToe;

/**
 *
 * @author david
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class Methods {
    
    private static int turnNumber = 1;
    
    public static void GetMethod(char[] arr){
          try {
                String urlString = "http://tictactoetestserver.azurewebsites.net/api/tictactoe";
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "text/xml");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
                
                parseXML(arr, conn);
                
		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  } 
    }
    
        public static void PutMethod(int pos){
    
      try {

		String urlString = "http://tictactoetestserver.azurewebsites.net/api/tictactoe/";
                urlString += pos;
                URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-Type", "text/xml");

		String input = "<TicTacToeViewModel xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://schemas.datacontract.org/2004/07/TicTacToe_Server.Models\"><Token>@</Token></TicTacToeViewModel>";
                input = input.replace(input.charAt(input.indexOf("@")), selectToken());
                
		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes());
		os.flush();

                
                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		} else {
                    turnNumber++;
                }

		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	 }
    }
        
        private static void parseXML(char[] arr, HttpURLConnection conn) throws IOException {
            try {
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                InputSource input = new InputSource();
                input.setCharacterStream(new StringReader(br.readLine()));
                try {
                    Document xmlDocument = documentBuilder.parse(input);
                    //System.out.println(xmlDocument.getDocumentElement().getTextContent());
                    int spotCount = 0;
                    while (spotCount < arr.length) {
                        int spotCheck = 0;
                        while (spotCount != Character.getNumericValue(xmlDocument.getDocumentElement().getTextContent().charAt(spotCheck/2))) {
                            spotCheck+=2;    
                        }
                            arr[spotCount] = xmlDocument.getDocumentElement().getTextContent().charAt(spotCheck/2+1);
                            spotCount++;
                    }
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
        

        
        private static char selectToken() {
            if (turnNumber % 2 == 1)
                return 'X';
            else
                return 'O';
        }
}

