
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = Note.class)
public class Note {
	
//	private Integer id;
	private String msg;

	@Override
	public String toString() {
		return String.format("id:" +this.id + " msg: "+this.msg +" "+ this.isDone); 
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	Integer isDone;

	public Integer getIsDone() {
		return isDone;
	}

	public void setIsDone(Integer isDone) {
		this.isDone = isDone;
	}
	
	


	
}
