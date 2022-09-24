package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "root";

    private static Connection con;
    private static Statement statement;

    public static void main(String[] args) throws Exception{
        String query = "";
        con = DriverManager.getConnection(url, user, password);
        statement = con.createStatement();


        Scanner scanner = new Scanner(System.in);
        allCommands();
        int command = scanner.nextInt();

        while (command!=0){
            allCommands();
            switch (command){
                case 1:
                    System.out.println("Enter username");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    String lasName = scanner.nextLine();
                    int age = scanner.nextInt();
                    int sum = scanner.nextInt();
                    String sql = setUser(name, lasName, age, sum);
                    statement.executeUpdate(sql);
                    break;
                case 2:
                    scanner.nextLine();
                    String managerName = scanner.nextLine();
                    int dept = scanner.nextInt();
                    String sql1 = setManger(managerName, dept);
                    statement.executeUpdate(sql1);
                    break;
                case 3:
                    System.out.println(getAllUsersEntities());

            }
            command = scanner.nextInt();
        }
    }
    public static void allCommands(){
        System.out.println("type 0 to close program");
        System.out.println("type 1 to add user");
        System.out.println("type 2 to add  manager");
    }

    public static String setUser(String name, String lastName, int age, int sum){
        String sql = "insert into users1 (username, lastname, age, sum) values " +
                "('"+name+"', '"+lastName+"', '"+age+"', '"+sum+"')";
        System.out.println(sql);
        return sql;
    }

    public static String setManger(String name, int dept){
        String sql = "insert into managers (manager_name, dept, amount) values " +
                "('"+name+"', '"+dept+"', '"+0+"')";
        System.out.println(sql);
        return sql;
    }

    private static String getAllManagers(){
        String sql = "select * from managers";
        return sql;
    }

    private static String getAllUsers(){
        String sql = "select * from users1";
        return sql;
    }

    private static List<User> getAllUsersEntities() throws Exception{
        String sql = getAllUsers();
        ResultSet rs = statement.executeQuery(sql);
        List<User> list = new ArrayList();
        while (rs.next()){
            User user1 = new User();
            user1.setName(rs.getString(2));
            user1.setLastName(rs.getString(3));
            user1.setAge(Integer.parseInt(rs.getString(4)));
            list.add(user1);
        }
        return list;
    }
}