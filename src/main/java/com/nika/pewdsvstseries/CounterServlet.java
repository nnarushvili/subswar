/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nika.pewdsvstseries;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nika
 */
@WebServlet(name = "SubCounter", urlPatterns = {""}, loadOnStartup = 1)
public class CounterServlet extends HttpServlet {

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

    private static Worker subCounter;

    private static SubDTO last;

    @PostConstruct
    @Override
    public void init() {
        subCounter = Worker.getInstance();
        Timer timer = new Timer();
        timer.schedule(new subGetter(), 0, 1);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        try(PrintWriter writer = response.getWriter()){
            StringBuilder HTML = new StringBuilder(String.format("<!DOCTYPE html><html><head><meta charset=\"utf-8\"><title>Subscriber war</title></head><body>"
                +"<img src=%s>&nbsp;&nbsp;&nbsp;<img src=%s><br><h1 style=\"display:inline\">PewDiePie subs : %s</h1>"
                + "&nbsp;&nbsp;&nbsp;<h1 style=\"display:inline\">T-Series subs : %s"
                + "</h1>","images/pewd.png", "images/tseries.png", last.getPewdsSubs(), last.gettSeriesSubs()));
            int subDiff = last.getSubDifference();
            String favored = subDiff > 0 ? "PewDiePie" : "T-Series";
            HTML.append(String.format("<h1>%s is leading with %d subs</h1></body></html>", favored, Math.abs(subDiff)));
            writer.write(HTML.toString());
        }catch (Exception ex) {
            response.getWriter().write("fetching, please refresh...");
        }
    }

}
