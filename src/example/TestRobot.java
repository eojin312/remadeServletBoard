package example;

import dao.DetailDao;

import java.sql.SQLException;

public class TestRobot {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Robot robot1 = new Robot();
        System.out.println(robot1.getStatus());

        DetailDao detailDao = new DetailDao();

        detailDao.selectDetail(1);
    }
}
