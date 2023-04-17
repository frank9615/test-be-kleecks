package org.example;

import org.example.dao.VenditeDao;
import org.example.database.MySqlConnection;
import org.example.model.VenditeVariazione;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection mySqlConnection = MySqlConnection.getConnection();
        VenditeDao venditeDao = new VenditeDao(mySqlConnection);
        venditeDao.createTable();
        venditeDao.insertData();
        List<VenditeVariazione> venditeVariazioneList = venditeDao.selectData();
        venditeVariazioneList.forEach(System.out::println);

    }
}