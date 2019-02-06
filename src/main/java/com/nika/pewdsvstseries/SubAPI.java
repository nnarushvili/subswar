/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nika.pewdsvstseries;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

/**
 *
 * @author Nika.Narushvili
 */
@Path("getsubs")
@Singleton
@Startup
public class SubAPI {

    private class subGetter extends TimerTask {

        @Override
        public void run() {
            String[] subCounts;
            try {
                subCounts = subCounter.getSubCounts();
            } catch (IOException ex) {
                return;
            }
            String pewdsSubs = subCounts[0].replace("&nbsp;", ",");
            String tSeriesSubs = subCounts[1].replace("&nbsp;", ",");
            SubDTO subDTO = new SubDTO();
            subDTO.setPewdsSubs(pewdsSubs);
            subDTO.settSeriesSubs(tSeriesSubs);
            subDTO.setSubDifference(Integer.parseInt(pewdsSubs.replace(",", "")) - Integer.parseInt(tSeriesSubs.replace(",", "")));
            last = subDTO;
        }

    }

    @PostConstruct
    private void init() {
        Timer timer = new Timer();
        timer.schedule(new subGetter(), 0, 1);
    }

    @Inject
    private PewdsSubCounter subCounter;

    private static SubDTO last;

    @GET
    public SubDTO getSubs(@Context HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET");
        return last;
    }
}
