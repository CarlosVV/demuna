package pe.gob.dmn.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUploadBean {
 
	private String txtId2;	
    private CommonsMultipartFile file;
 
    public CommonsMultipartFile getFile() {
        return file;
    }
 
    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

	public String getTxtId2() {
		return txtId2;
	}

	public void setTxtId2(String txtId2) {
		this.txtId2 = txtId2;
	}
    
}