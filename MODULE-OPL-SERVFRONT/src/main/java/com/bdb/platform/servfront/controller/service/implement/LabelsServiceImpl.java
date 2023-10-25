package com.bdb.platform.servfront.controller.service.implement;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.bdb.opaloshare.persistence.entity.TxtMediumTbl;
import com.bdb.platform.servfront.controller.service.interfaces.LabelsService;
import com.bdb.platform.servfront.model.commons.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

@Service
public class LabelsServiceImpl implements LabelsService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TxtMediumTbl getLabel(Integer idTxt) {

        TxtMediumTbl resultJSONLabel = new TxtMediumTbl();

        resultJSONLabel = this.load(idTxt);

        return resultJSONLabel;
    }

    @Override
    public TxtMediumTbl setLabel(TxtMediumTbl report) {

        TxtMediumTbl resultJSONLabel = new TxtMediumTbl();

        resultJSONLabel = this.save(report);

        resultJSONLabel.setTxtDesc(Constants.LABEL_OK);

        return resultJSONLabel;
    }


    public TxtMediumTbl save(TxtMediumTbl report) {
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(Constants.ACC_PAR_TXT_MEDIUM_TBL_insert);
                ps.setInt(1, report.getIdTxt());
                Reader reader = new StringReader(report.getTxtDesc());
                ps.setClob(2, reader);

                return ps;
            }
        });
        return this.load(report.getIdTxt());
    }

    public TxtMediumTbl load(Integer id) {
        List<TxtMediumTbl> reporte = jdbcTemplate.query(Constants.ACC_PAR_TXT_MEDIUM_TBL_selectById,
                new Object[]{id}, (resultSet, i) -> {
                    return toReport(resultSet);
                });
        if (reporte.size() == 1) {
            return reporte.get(0);
        }
        throw new RuntimeException("No item found for id: " + id);
    }

    private TxtMediumTbl toReport(ResultSet resultSet) throws SQLException {
        TxtMediumTbl report = new TxtMediumTbl();
        report.setIdTxt(resultSet.getInt("ID_TXT"));

        InputStream contentStream = resultSet.getClob("TXT_DESC").getAsciiStream();
        String content = new Scanner(contentStream, "UTF-8").useDelimiter("\\A").next();
        report.setTxtDesc(content);

        return report;
    }

}
