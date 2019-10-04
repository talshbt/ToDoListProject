import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



//import com.fasterxml.jackson.databind.ObjectMapper.disable(DeserializationFeature feature);
public class ToDoUIController {
    String serverUrl ="http://localhost:5555/";
    
    private String read(InputStream is) throws IOException {
        BufferedReader in = null;
        String inputLine;
        StringBuilder body;
        try {
            in = new BufferedReader(new InputStreamReader(is));

            body = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                body.append(inputLine);
            }
            in.close();

            return body.toString();
        } catch(IOException ioe) {
            throw ioe;
        } finally {
            this.closeQuietly(in);
        }
    }
    

    protected void closeQuietly(Closeable closeable) {
        try {
            if( closeable != null ) {
                closeable.close();
            }
        } catch(IOException ex) {

        }
    }
    
    public String post(String postUrl, String data) throws IOException {
        URL url = new URL(postUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        con.setDoOutput(true);

        this.sendData(con, data);

        return this.read(con.getInputStream());
    }
    
    protected void sendData(HttpURLConnection con, String data) throws IOException {
        DataOutputStream wr = null;
        try {
            wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();
        } catch(IOException exception) {
            throw exception;
        } finally {
            this.closeQuietly(wr);
        }
    }
    
    
    void addNote(String req, String note) {
    	String url = serverUrl+req;
		try {
            String body = post(url, note);
            
            System.out.println(body);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
   
    
	public Object getResponse(String request) throws IOException {
		
		URL url = new URL(serverUrl + request);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int status = con.getResponseCode();
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		
		return content;
	}
        
        
        String[] getList(){
          Object json = null;
          try {
             json = getResponse("getDb");
            
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
          
            Note[] arr  = convertJsonToArr(json);
            String[] res = new String[arr.length];
            
            for(int i = 0; i < arr.length; ++i){
                res[i] = arr[i].getMsg();
                System.out.println(res[i]);

            }
           

            return res;
    
        }
        
        void deleteItem(String req, String id){
        	String url = serverUrl+req;
    		try {
                String body = post(url, id);
                
                System.out.println(body);
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
	
	public Note[] convertJsonToArr(Object json) {
		
	Note[] pp1 = null;
		try {
			
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			pp1 = mapper.readValue(json.toString(), Note[].class);
			
		
		}
		catch(Exception e)
		{
			System.err.println(e.toString());
		}
		
		return pp1;
	}
	
	String[] getArrayOfNotes(String json){
		json = json.replace("[", ""); 
		json = json.replace("]", ""); 
		json = json.replace("\"", ""); 
		json = json.replace("\\", ""); 

		return json.split(",");    
	}
    
  
    
	public static void main(String[] args) throws IOException {
		String note1 = "NoteMessage=task1";
	

		String req = "addNote";
                
		ToDoUIController hce = new ToDoUIController();
		
		hce.deleteItem(req, "1");
                
        hce.getList();
		
		
//		hce.addNote(req, note1);


	}

}

	