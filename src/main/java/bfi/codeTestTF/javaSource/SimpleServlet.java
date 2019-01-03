package bfi.codeTestTF.javaSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import info.debatty.java.stringsimilarity.Levenshtein;

@WebServlet(description = "Web Servlet Test F", urlPatterns = { "/GetResult" }) 
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		String search = request.getParameter("txt-search");
		writer.println("City Name: " + search + "<br>");

		JSONParser parseJSON = new JSONParser();
		
		try {
			URL citiesJSON = new URL("https://raw.githubusercontent.com/lutangar/cities.json/master/cities.json");
			URLConnection connection = citiesJSON.openConnection();
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder strBuilder = new StringBuilder();
			
			String inputLine;
			
			//Get cities.json file as String
			while ((inputLine = bufReader.readLine()) != null) {
				strBuilder.append(inputLine.trim());
			}
			String jsonFile = strBuilder.toString();
			System.out.println(jsonFile);
			bufReader.close();
			
			//Parse cities.json to json array
			JSONArray jsonArray = (JSONArray) parseJSON.parse(jsonFile);
			
			//Search similalrities of String
			writer.println("<br/><br/>Cities Found :");
			for (int i = 0; i < jsonArray.size(); i++) {
		        JSONObject cityObj = (JSONObject) jsonArray.get(i);
		        String countryStr = cityObj.get("country").toString();
		        
		        if (countryStr.equals("ID")) {
		        	System.out.println("success!");
		        	 String cityStr = cityObj.get("name").toString();	        	 
		        	 
		        	 Levenshtein similarStr = new Levenshtein();
		        	 double distance = similarStr.distance(cityStr.toLowerCase(), search.toLowerCase());
		        	 
		        	 
		        	 if(distance < 3) {
		        		 writer.println(cityStr + "<br/>");
		        	 } else if (search.contains(cityStr.toLowerCase())) {
						writer.println(cityStr + "<br/>");
					}
		        } else {
		        	System.out.println("Country is not ID");
		        } 
		        response.setCharacterEncoding("UTF-8");
		        writer.flush();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.println("<br/><br/><a href=\"index.jsp\">BACK</a>");
	}

}
