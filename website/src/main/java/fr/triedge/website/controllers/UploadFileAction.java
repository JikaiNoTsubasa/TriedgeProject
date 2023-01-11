package fr.triedge.website.controllers;

import fr.triedge.website.utils.Config;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class UploadFileAction {

    private File strutsUploadedFile;
    private String strutsUploadedFileFileName;
    private String strutsAction;

    public String execute(){
        System.out.println("Action: UploadFileAction");
        if (strutsAction != null){
            System.out.println("Struts Action: "+strutsAction);
            if (strutsAction.equals("upload")){
                if (strutsUploadedFile != null){
                    System.out.println("Start uploading...");
                    File localFile = new File(Config.DEFAULT_IMAGE_PATH, strutsUploadedFileFileName);
                    System.out.println("File path set to "+localFile.getAbsolutePath());
                    try {
                        FileUtils.copyFile(strutsUploadedFile, localFile);
                        System.out.println("File uploaded to "+localFile.getAbsolutePath());
                        return "uploaded";
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    System.out.println("File is null");
                }

            }
        }
        System.out.println("End action");
        return "success";
    }

    public String getStrutsAction() {
        return strutsAction;
    }

    public void setStrutsAction(String strutsAction) {
        this.strutsAction = strutsAction;
    }

    public File getStrutsUploadedFile() {
        return strutsUploadedFile;
    }

    public void setStrutsUploadedFile(File strutsUploadedFile) {
        this.strutsUploadedFile = strutsUploadedFile;
    }

    public String getStrutsUploadedFileFileName() {
        return strutsUploadedFileFileName;
    }

    public void setStrutsUploadedFileFileName(String strutsUploadedFileFileName) {
        this.strutsUploadedFileFileName = strutsUploadedFileFileName;
    }
}
