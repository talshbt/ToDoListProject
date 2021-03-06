import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToDoUIController {
	String serverUrl = "http://localhost:5555/";

	public Object getResponse(String request) throws IOException {

		URL url = new URL(serverUrl + request);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		@SuppressWarnings("unused")
		int status = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}

		return content;
	}

	void removeAll() {
		try {
			getResponse("truncate");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	String[] getDoneList() {
		Object json = null;
		try {
			json = getResponse("getDoneNotes");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		Note[] arr = convertJsonToArr(json);
		String[] res = new String[arr.length];

		for (int i = 0; i < arr.length; ++i) {
			res[i] = arr[i].getMsg();
			System.out.println(res[i]);

		}

		return res;

	}

	String[] getWaitedList() {
		Object json = null;
		try {
			json = getResponse("getWaitedNotes");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		Note[] arr = convertJsonToArr(json);
		String[] res = new String[arr.length];

		for (int i = 0; i < arr.length; ++i) {
			res[i] = arr[i].getMsg();
			System.out.println(res[i]);

		}

		return res;

	}

	Note[] getNoteList() {
		Object json = null;
		try {
			json = getResponse("getDb");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		Note[] arr = convertJsonToArr(json);

		for (int i = 0; i < arr.length; ++i) {

			System.out.println(arr[i].toString());

		}

		return arr;

	}

	public Note[] convertJsonToArr(Object json) {

		Note[] pp1 = null;
		try {

			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			pp1 = mapper.readValue(json.toString(), Note[].class);

		} catch (Exception e) {
			System.err.println(e.toString());
		}

		return pp1;
	}

	List<Object> postData(String req, Map<String, String> params) throws IOException {
		List<Object> list = new ArrayList<>();
		URL url = new URL(serverUrl + req);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, String> param : params.entrySet()) {
			if (postData.length() != 0) {
				postData.append('&');
			}
			postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		list.add(connection);
		list.add(postData);

		return list;
	}

	void writePostData(String req, Map<String, String> params) throws IOException {
		List<Object> list = postData(req, params);
		HttpURLConnection connection = (HttpURLConnection) list.get(0);
		StringBuilder postData = (StringBuilder) list.get(1);

		byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		connection.setDoOutput(true);

		try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
			writer.write(postDataBytes);
			writer.flush();
			writer.close();

			StringBuilder content;

			try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String line;
				content = new StringBuilder();
				while ((line = in.readLine()) != null) {
					content.append(line);
					content.append(System.lineSeparator());
				}
			}
			System.out.println(content.toString());
		} finally {
			connection.disconnect();
		}

	}

	void addNewNote(String note) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("NoteMessage", note);
		try {
			writePostData("addNote", params);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void deleteNote(Integer id) {

		Note[] noteList = getNoteList();
		id = noteList[id].getId();
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id.toString());
		try {
			writePostData("deleteNote", params);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void updateNote(Integer id, String msg) {

		Note[] noteList = getNoteList();
		id = noteList[id].getId();
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id.toString());
		params.put("NoteMessage", msg);

		try {
			writePostData("updateNote", params);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	boolean isDone(int id) {
		Note[] noteList = getNoteList();
		int isComplete = noteList[id].getIsDone();
		System.out.println(noteList[id].getId());
		System.out.println("is complete ? " + (isComplete == 1));
		return (isComplete == 1);

	}

	void toggleIsDone(Integer id) {

		Note[] noteList = getNoteList();
		id = noteList[id].getId();
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id.toString());
		try {
			writePostData("toggleIsDone", params);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

//		ToDoUIController hce = new ToDoUIController();
//		hce.toggleIsDone(0);
//
//		hce.isDone(0);
////		hce.isDone(0);
//		
////		System.out.println(hce.isDone(1));
////		hce.updateNote(1, "tal");
////		hce.removeAll();
////		hce.getNoteList();
//		
////	    Map<String, String> params = new HashMap<String, String>();
//////	    params.put("id", "1");
////	    params.put("NoteMessage", "mynewNote");
////	    hce.writePostData("addNote",  params);
//

	}

}
