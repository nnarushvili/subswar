/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nika.pewdsvstseries;

import java.io.IOException;
import javax.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Nika.Narushvili
 */

public class Worker {

    private final String pewdsURL = "https://www.youtube.com/channel/UC-lHJZR3Gqxm24_Vd_AJ5Yw";
    private final String tSeriesURL = "https://www.youtube.com/channel/UCq-Fj5jknLsUf-MWSy4_brA";
    private Document pewdsDoc;
    private Document tSeriesDoc;

    private String pewdsSubCount;
    private String tSeriesSubCount;

    public String[] getSubCounts() throws IOException {
        retrieveSubs();
        return new String[]{pewdsSubCount, tSeriesSubCount};
    }

    private Worker() {
        
    }
    
    private static Worker instance = new Worker();
    
    public static Worker getInstance(){
        return instance;
    }


    private void retrieveSubs() throws IOException {
        pewdsDoc = Jsoup.connect(pewdsURL).execute().parse();
        tSeriesDoc = Jsoup.connect(tSeriesURL).execute().parse();
        pewdsSubCount = pewdsDoc.getElementsByClass("yt-subscription-button-subscriber-count-branded-horizontal subscribed yt-uix-tooltip").first().html();
        tSeriesSubCount = tSeriesDoc.getElementsByClass("yt-subscription-button-subscriber-count-branded-horizontal subscribed yt-uix-tooltip").first().html();
    }
}
