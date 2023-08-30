import java.sql.*;

import static javax.swing.JOptionPane.showInputDialog;

public class Main {
    public static void main(String[] args) throws Exception {

        Connection conn = DriverManager     //connecten zur datenbank
                .getConnection("jdbc:mysql://localhost:3306/sys", "root", "rootpassword");
        int anzahlVersuche = 0;

        ResultSet resultSet;
        String sql = "SELECT * FROM kunden WHERE username = ? and passwort = ?";
        PreparedStatement statement = conn.prepareStatement(sql);

        do{
            statement.setString(1, showInputDialog("Bitte username eingeben"));
            statement.setString(2, showInputDialog("Bitte Passwort eingeben"));
            resultSet = statement.executeQuery();
            anzahlVersuche++;
            if(resultSet.next()){
                break;
            }
        } while (anzahlVersuche < 3);

        if(anzahlVersuche == 3){
            throw new HackerAlarm("Vorsicht, Hackerangriff!");
        }

        if(resultSet.next()) {
            System.out.println("User gefunden");
        } else {
            System.out.println("User nicht gefunden");
        }
    }
}