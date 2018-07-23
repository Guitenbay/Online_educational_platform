import dao.CourseDao;

import dao.impl.CourseDaoImpl;


import java.util.List;

public class Main {

    public static void main(String[] args) {
        CourseDao courseDao = new CourseDaoImpl();

//        List<Course> courses = courseDao.getCourseListByUserId(1);
//
//        for (Course h : courses) {
//        }
        courseDao.deleteCourseById(4);
//        System.out.println(h.getName() + " " + h.getDescription() + " " + h.getCreatorId());

    }

}
