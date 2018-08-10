package cloudServer;

public class Upload {
	String fileName;
	String filePath;
	String lastModified;
	int id;

	public Upload(String fileName, String filePath, String lastModified) {
		this.fileName = fileName;
		this.filePath = filePath;
		this.lastModified = lastModified;
	}

	public Upload(int id, String fileName, String filePath, String lastModified) {
		this.fileName = fileName;
		this.filePath = filePath;
		this.lastModified = lastModified;
		this.id = id;

	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
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

}