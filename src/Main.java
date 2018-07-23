import dao.*;

import dao.impl.*;
import model.*;


import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        HomeworkDao homeworkDao = new HomeworkDaoImpl();

//        answerDao.update(new Answer(3, "context-1", 0, new Date(), 1, 1));

//        homeworkDao.update(new Homework(6, "home-1", "", new Date(), 1));
//        List<Homework> homeworks = homeworkDao.getHomeworkListByCourseId(1);
//
//        for (Homework answer: homeworks){
//            System.out.println(answer.getName()+ " " + answer.getDeadLine());
//        }
        homeworkDao.deleteHomeworkById(6);

    }

}
