package fr.triedge.amadeus.api;

import fr.triedge.amadeus.model.Project;
import fr.triedge.amadeus.model.Task;
import fr.triedge.amadeus.services.DB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class ProjectController {

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
}
