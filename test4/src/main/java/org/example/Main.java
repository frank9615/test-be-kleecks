package org.example;

import org.example.dao.VenditeDao;
import org.example.database.MySqlConnection;
import org.example.model.VenditeVariazione;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Creo la connessione al database
        Connection mySqlConnection = MySqlConnection.getConnection();
        //Creo un oggetto VenditeDao passando la connessione al database
        VenditeDao venditeDao = new VenditeDao(mySqlConnection);
        //Creo la tabella vendite se non esiste
        venditeDao.createTable();
        //Inserisco i dati nella tabella vendite
        venditeDao.insertData();
        //Seleziono i dati dalla tabella vendite
        List<VenditeVariazione> venditeVariazioneList = venditeDao.selectData();
        //Stampo i dati
        venditeVariazioneList.forEach(System.out::println);

    }
}