package com.bdb.oplbacthdiarios.schudeler.load;

import com.bdb.opaloshare.controller.service.implement.CruceSalpg;
import com.bdb.opaloshare.controller.service.interfaces.CruceSalpgDTO;
import com.bdb.opaloshare.controller.service.interfaces.FTPService;
import com.bdb.opaloshare.persistence.entity.ParametersConnectionFTPS;
import com.bdb.opaloshare.persistence.repository.RepositorySalPgdigitalDown;
import lombok.extern.apachecommons.CommonsLog;
import org.modelmapper.ModelMapper;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CommonsLog
public class ReporteCuentasContablesTasklet implements Tasklet {

    @Autowired
    @Qualifier("serviceFTPS")
    FTPService serviceFTP;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RepositorySalPgdigitalDown repositorySalPgdigitalDown;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        List<CruceSalpgDTO> listCruceSalpgDTO = repositorySalPgdigitalDown.loadInfoGFromSalPgwithTransPg();

        List<CruceSalpg> list = listCruceSalpgDTO.stream().map( cruceSalpgDTO -> modelMapper.map(cruceSalpgDTO, CruceSalpg.class)).collect(Collectors.toList());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOutputStream);

        List<Long> nroOficinasSorted = list.stream().map(x -> x.getNroOficina()).distinct().sorted().collect(Collectors.toList());
        List<CruceSalpg> dataSorted = list.stream().sorted(Comparator.comparing(CruceSalpg::getNroOficina)).collect(Collectors.toList());

        String dateNow = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy"));

        String nombre = "Listado_CDTS_desmaterializados_cancelados_abono_cta_contable_ddMMYYYY.txt"
                .replace("ddMMYYYY", LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")));

        for (Long nroOficina : nroOficinasSorted) {
            try {
                List<CruceSalpg> crucesByOficina = dataSorted.stream().filter(x -> x.getNroOficina().equals(nroOficina)).collect(Collectors.toList());

                String nomOficina = crucesByOficina.stream().map(x -> x.getNomOficina()).findFirst().get();
                Integer numPag = 1;
                out.write(headers(String.format("%,04d", nroOficina), numPag, dateNow, nomOficina).getBytes());
                for (CruceSalpg cruceByOficina : crucesByOficina) {
                    try {
                        out.write(cruceByOficina.strucData().concat("\n").getBytes());
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                out.write("\n\n\n\n".getBytes());
                numPag++;
            } catch (Exception e) {
                log.error("Error en ReporteCuentasContablesTasklet: {0}", e);
                throw new UnexpectedJobExecutionException("Error en ReporteCuentasContablesTasklet");
            }
        }

        if (byteArrayOutputStream.size() > 0){
            ParametersConnectionFTPS parameters = serviceFTP.parametersConnection();
            serviceFTP.connectToFTP(parameters.getHostname(), parameters.getPuerto(), parameters.getUsername(), parameters.getPassword());
            String fechaActual = serviceFTP.obtenerFechaActual();
            serviceFTP.makeFile(byteArrayOutputStream, serviceFTP.rutaEspecifica("%OUTPUT%", fechaActual), nombre);
        }

        return RepeatStatus.FINISHED;
    }

    public String headers(String nroOficina, Integer numPag, String fechaActual, String nomOficina){
        StringBuilder headers = new StringBuilder();
        headers.append("OPALO/CDTS DESMATERIALIZADOS CANCELADOS CON ABONO A CUENTA CONTABLE     *OP01CDTC*     PAGINA " + numPag.toString() + " \n");
        headers.append("BANCO DE BOGOTA " + nroOficina + "                                                    FECHA PROCESO  " + fechaActual + "\n");
        headers.append("CDTS DESMATERIALIZADOS DE OFICINA Y DIGITAL\n\n\n");
        headers.append("OFICINA        " + nroOficina + "       " + nomOficina + "\n");
        headers.append("-------------- ------------ ------ --------------- --------------------------------------------- ------------------------- --------- \n");
        headers.append(String.format("%15s", "NUMERO CDT|") + String.format("%13s", "NUMERO ISIN|") + String.format("%7s", "TIP ID|")
                + String.format("%16s", "No. IDENT|") + String.format("%46s", "NOMBRE TITULAR CDT|") + String.format("%26s", "INTERES BRUTO|")
                + String.format("%26s", "RETE FUENTE|") + String.format("%26s", "INTERES NETO|") + String.format("%26s", "CAPITAL A PAGAR|")
                + "TIP CTA|" + String.format("%11s", "NUM DE CTA|") + String.format("%27s", "TOTAL A PAGAR|\n"));
        return headers.toString();
    }

}
