package fr.triedge.website.controllers.ajax;

import fr.triedge.website.utils.Config;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AjaxListImages extends AjaxAbstractAction{
    @Override
    protected String executeAction(String action) {
        System.out.println("Execute list image");
        Set<String> files = getFileList();
        StringBuilder tmp = new StringBuilder();
        for (String s : files){
            System.out.println("Found file: "+s);
            tmp.append("<img src=\"file/").append(s).append("\" width=\"100px\" height=\"100px\" onclick=\""+action+"('file/").append(s).append("');\"/>");
        }
        return tmp.toString();
    }

    public Set<String> getFileList() {
        return Stream.of(new File(Config.DEFAULT_IMAGE_PATH).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }
}
