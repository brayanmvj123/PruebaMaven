package com.bdb.opalo.email.controller.service.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.FileSystems;

@RestController
@RequestMapping("v1")
public class ControllerLogs {

    @GetMapping("get/logs")
    public String getLogs(){
        File file = new File("/orasoft/oracle/product/12c/domains/DMN_CDTVQA/servers/SRV_kariteslxvd008/logs");
        File f = new File("/orasoft/oracle/product/12c/domains/DMN_CDTVQA/servers/SRV_kariteslxvd008/logs/SRV_kariteslxvd008.out");
        return "VALIDATE: "+FileSystems.getDefault().getPath("./").toAbsolutePath() + "\n" + FileSystems.getDefault().getPath("../").toAbsolutePath() + "\n" +
                "EXISTE: "+file.isDirectory() + "\n" + "FILE EXISTS: " + f.exists();
    }

}
