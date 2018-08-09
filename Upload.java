package Project;

public class Upload {
	String fileName;
	String filePath;
	String action;
	String date;
	int id;
	
	
public Upload(String fileName, String filePath) {
	this.fileName = fileName;
	this.filePath = filePath;
}

public Upload(int id, String fileName, String filePath) {
this.fileName = fileName;
this.filePath = filePath;
this.id = id;
}

public Upload(int id, String fileName, String filePath, String date) {
this.fileName = fileName;
this.filePath = filePath;
this.id = id;
this.date = date;
}



public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getFileName() {
	return fileName;
}


public void setFileName(String fileName) {
	this.fileName = fileName;
}


public String getFilePath() {
	return filePath;
}


public void setFilePath(String filePath) {
	this.filePath = filePath;
}


public String getAction() {
	return action;
}


public void setAction(String action) {
	this.action = action;
}

public String getDate() {
	return date;
}


public void setDate(String date) {
	this.date = date;
}

}
