/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nika.pewdsvstseries;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Nika.Narushvili
 */
@Singleton
public class Worker {

    private String pewdsURL;
    private String tSeriesURL;
    private Document pewdsDoc;
    private Document tSeriesDoc;

    private String pewdsSubCount;
    private String tSeriesSubCount;

    public String[] getSubCounts() throws IOException {
        retrieveSubs();
        return new String[]{pewdsSubCount, tSeriesSubCount};
    }

    public Worker() {

    }

    @PostConstruct
    public void init() {
        pewdsURL = "https://www.youtube.com/channel/UC-lHJZR3Gqxm24_Vd_AJ5Yw";
        tSeriesURL = "https://www.youtube.com/channel/UCq-Fj5jknLsUf-MWSy4_brA";
    }

    private void retrieveSubs() throws IOException {
        pewdsDoc = Jsoup.connect(pewdsURL).execute().parse();
        tSeriesDoc = Jsoup.connect(tSeriesURL).execute().parse();
        pewdsSubCount = pewdsDoc.getElementsByClass("yt-subscription-button-subscriber-count-branded-horizontal subscribed yt-uix-tooltip").first().html();
        tSeriesSubCount = tSeriesDoc.getElementsByClass("yt-subscription-button-subscriber-count-branded-horizontal subscribed yt-uix-tooltip").first().html();
    }
}
