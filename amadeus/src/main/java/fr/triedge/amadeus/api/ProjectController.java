package fr.triedge.amadeus.api;

import fr.triedge.amadeus.model.Project;
import fr.triedge.amadeus.model.Task;
import fr.triedge.amadeus.services.DB;
import fr.triedge.amadeus.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class ProjectController extends AbstractController{

    static Logger log = LoggerFactory.getLogger(ProjectController.class);

    @GetMapping("/projects")
    public ModelAndView projects(){
        ModelAndView model = new ModelAndView("projects.html");
        try {
            ArrayList<Project> prjs = DB.getInstance().getAllProjects();
            model.addObject("projects", prjs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return model;
    }

    @GetMapping("/project")
    public ModelAndView project(@RequestParam(value = "id") int id){
        ModelAndView model = new ModelAndView("project.html");
        try {
            Project prj = DB.getInstance().getProject(id);
            model.addObject("project", prj);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return model;
    }

    @GetMapping("/task")
    public ModelAndView task(@RequestParam(value = "id") int id,@RequestParam(value = "projectId") int projectId){
        ModelAndView model = new ModelAndView("task.html");
        try {
            Task task = DB.getInstance().getTask(id, projectId);
            model.addObject("task", task);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return model;
    }

    @RequestMapping(path = "/createproject", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView createProject(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description){
        if (Utils.isValid(name) && Utils.isValid(description)){
            log.debug("Requested creation of project: "+name);
            try {
                DB.getInstance().createProject(name,description,getUser());
                log.debug("Project created: "+name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return new ModelAndView("redirect:/projects");
        }

        ModelAndView model = new ModelAndView("createproject.html");
        return model;
    }

    @RequestMapping(path = "/deleteproject",method = {RequestMethod.GET})
    public ModelAndView deleteProject(@RequestParam(value = "id") int id){
        try {
            DB.getInstance().deleteProject(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ModelAndView("redirect:/projects");
    }
}
