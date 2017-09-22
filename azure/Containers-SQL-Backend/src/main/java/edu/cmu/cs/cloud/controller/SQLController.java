package edu.cmu.cs.cloud.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.cmu.cs.cloud.model.Query;
import edu.cmu.cs.cloud.model.SelectQueryResponse;
import edu.cmu.cs.cloud.model.WriteQueryResponse;

@RestController
public class SQLController {
    @Autowired
    DataSource dataSource;

    @Autowired
    QueryRunner queryRunner;

    @RequestMapping(value={"/sql/select", "/sql/read"},
            method=RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    @ResponseBody
    public SelectQueryResponse selectQuery(@RequestBody Query query) {
        ResultSetHandler<List<Map<String, Object>>> resultSetHandler = new MapListHandler();

        try {
            List<Map<String, Object>> result = queryRunner.query(query.getQuery(), resultSetHandler);

            return new SelectQueryResponse(result);
        } catch (SQLException e) {
            return new SelectQueryResponse(e.getMessage());
        }
    }

    @RequestMapping(value={"/sql/insert", "/sql/update", "/sql/delete", "/sql/write"},
            method=RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    @ResponseBody
    public WriteQueryResponse writeQuery(@RequestBody Query query) {
        int rowCount;

        try {
            rowCount = executeUpdate(query.getQuery());
        } catch (SQLException e) {
            return new WriteQueryResponse(false, e.getMessage());
        }

        return new WriteQueryResponse(true, rowCount);
    }

    private int executeUpdate(String query) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        int rowCount;

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            rowCount = conn.createStatement()
                           .executeUpdate(query);

        } finally {
            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }

        return rowCount;
    }
}
