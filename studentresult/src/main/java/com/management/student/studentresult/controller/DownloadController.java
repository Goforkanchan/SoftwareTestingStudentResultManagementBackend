package com.management.student.studentresult.controller;

import com.management.student.studentresult.service.MarksService;
import com.management.student.studentresult.service.PdfResultDownloadService;
import com.management.student.studentresult.utils.QueryVOMapper;
import com.management.student.studentresult.vo.QueryVO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private PdfResultDownloadService fileDownloadService;

    private static final Logger logger = LogManager.getLogger(DownloadController.class);
    @GetMapping("/result/pdf")
    public void downloadResultPdf(@RequestParam Map<String, String> requestParameters, HttpServletResponse response) throws IOException, ParseException {
        QueryVO query = QueryVOMapper.mapFromRequestParameter(requestParameters);
        response.setContentType("application/pdf");
        fileDownloadService.exportFile(response, query);
    }

}
