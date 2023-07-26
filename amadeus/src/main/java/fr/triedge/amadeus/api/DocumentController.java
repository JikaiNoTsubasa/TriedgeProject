package fr.triedge.amadeus.api;

import fr.triedge.amadeus.model.Document;
import fr.triedge.amadeus.model.Folder;
import fr.triedge.amadeus.services.Documents;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class DocumentController {

    @GetMapping("/document")
    public ModelAndView document(@RequestParam(value = "id")int id){
        ModelAndView model = new ModelAndView("document.html");
        try {
            Document doc = Documents.getDocument(id);
            model.addObject("doc", doc);
            model.addObject("content",doc.getContent());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return model;
    }

    @GetMapping("/folder")
    public ModelAndView getFolder(@RequestParam(value = "id")int id){
        ModelAndView model = new ModelAndView("fragments::folder-content");
        try {
            ArrayList<Folder> subs = Documents.getDirectSubfolders(id);
            model.addObject("folders", subs);
            ArrayList<Document> docs = Documents.getDocumentsInFolder(id);
            model.addObject("documents", docs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return model;
    }

    @GetMapping("/folders")
    public ModelAndView folders(){
        ModelAndView model = new ModelAndView("folders.html");
        return model;
    }

}
