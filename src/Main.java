import dao.*;

import dao.impl.*;
import model.*;


import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
//        CourseDao courseDao = new CourseDaoImpl();
        SelectedDao selectedDao = new SelectedDaoImpl();
//        List<Course> courses = courseDao.getCourseAllByDesc();

//        for (Course course: courses){
//            System.out.println(course.getId() + " " + course.getName());
//        }
        List<Map<String, Object>> list = selectedDao.getSelectedListByUserId(1);
        for (Map<String, Object> map:list){
            System.out.println(map.get("userId") + " " + map.get("courseId"));
        }
//        System.out.println(selectedDao.getUserNumberByCourse(2));

    }

}
