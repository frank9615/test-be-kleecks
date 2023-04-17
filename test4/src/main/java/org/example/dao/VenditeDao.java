package org.example.dao;

import org.example.model.Vendite;
import org.example.model.VenditeVariazione;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VenditeDao {

    private Connection conn;

    public VenditeDao(Connection conn) {
        this.conn = conn;
    }

    public void createTable() throws SQLException {
        final String sql = """
                 create table if not exists vendite
                 (
                     user   varchar(1) charset utf8mb4 not null,
                     anno   int                        not null,
                     totale bigint                     not null
                 );
                 """;
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    public void insertData() throws SQLException {
        List<Vendite> venditeList = Arrays.asList(
                new Vendite("A", 2021, new BigDecimal(500)),
                new Vendite("B", 2021, new BigDecimal(1000)),
                new Vendite("C", 2022, new BigDecimal(900)),
                new Vendite("A", 2022, new BigDecimal(1200)),
                new Vendite("B", 2021, new BigDecimal(600)),
                new Vendite("A", 2022, new BigDecimal(900)),
                new Vendite("B", 2021, new BigDecimal(500)),
                new Vendite("C", 2021, new BigDecimal(1000)),
                new Vendite("C", 2022, new BigDecimal(700)),
                new Vendite("B", 2021, new BigDecimal(500))
                );
        final String sql = "insert into vendite (user, anno, totale) values (?, ?, ?)";
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        venditeList.forEach(vendite -> {
            try {
                pstmt.setString(1, vendite.getUser());
                pstmt.setInt(2, vendite.getAnno());
                pstmt.setBigDecimal(3, vendite.getTotale());
                pstmt.addBatch();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        pstmt.executeBatch();
        conn.commit();
        conn.setAutoCommit(true);
    }

    public List<VenditeVariazione> selectData() throws SQLException {
        final String sql = """
                SELECT
                            user,
                            anno,
                            SUM(totale) AS totale,
                            SUM(totale) - coalesce(
                                    (SELECT
                                         SUM(totale)
                                     FROM
                                         vendite v2
                                     WHERE
                                             v2.user = v1.user
                                       AND v2.anno = v1.anno - 1
                                    ),0) AS variazione
                FROM
                    vendite v1
                GROUP BY
                            user,
                            anno
                ORDER BY
                            user,
                            anno;
                """;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<VenditeVariazione> venditeVariazioneList = new ArrayList<>();
        while (rs.next()) {
            VenditeVariazione venditeVariazione = new VenditeVariazione();
            venditeVariazione.setUser(rs.getString("user"));
            venditeVariazione.setAnno(rs.getInt("anno"));
            venditeVariazione.setTotale(rs.getBigDecimal("totale"));
            venditeVariazione.setVariazione(rs.getBigDecimal("variazione"));
            venditeVariazioneList.add(venditeVariazione);
        }
        stmt.close();
        return venditeVariazioneList;
    }

}
