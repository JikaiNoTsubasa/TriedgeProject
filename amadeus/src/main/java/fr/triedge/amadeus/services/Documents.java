package fr.triedge.amadeus.services;

import fr.triedge.amadeus.model.Document;
import fr.triedge.amadeus.model.Folder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Documents {

    private static Logger log = LoggerFactory.getLogger(Documents.class);

    public static Document getDocument(int id) throws SQLException, IOException {
        Document doc = DB.getInstance().getDocument(id);
        doc.setContent(getDocumentContent(doc.getPath(), doc.getName()));
        return doc;
    }

    public static ArrayList<Folder> getDirectSubfolders(Folder root) throws SQLException {
        return getDirectSubfolders(root.getId());
    }

    public static ArrayList<Folder> getDirectSubfolders(int rootId) throws SQLException {
        return DB.getInstance().getSubFodlers(rootId);
    }

    private static String getDocumentContent(String path, String name) throws IOException {
        File file = new File(path, name);
        if (!file.exists())
            throw new RuntimeException("File not found while reading content: "+file.getAbsolutePath());
        StringBuilder tmp = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null){
            tmp.append(line);
        }
        br.close();
        return tmp.toString();
    }

    private static void saveDocumentContent(String path, String name, String content) throws IOException {
        File folder = new File(path);
        if (!folder.exists())
            folder.mkdirs();
        File file = new File(path, name);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(content);
        bw.flush();
        bw.close();
    }

    public static void createDocument(String name, String path, String content){
        try {
            saveDocumentContent(path, name, content);
            DB.getInstance().insertDocument(name, path);
        } catch (IOException e) {
            log.error("Could not save document to disk");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Document> getDocumentsInFolder(int id) throws SQLException {
        return DB.getInstance().getDocumentsInFolder(id);
    }
}
