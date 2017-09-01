package cn.tendata.crawler.web.controller;

import cn.tendata.crawler.webmagic.core.Crawler;
import cn.xinbee.rcs.data.domain.MailAgentDomain;
import cn.xinbee.rcs.service.MailAgentDomainQualityMonitoringService;
import cn.xinbee.rcs.service.MailAgentDomainService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/quality_monitor")
public class MailAgentDomainQualityMonitoringController {

    private final MailAgentDomainQualityMonitoringService qualityMonitoringService;
    private final MailAgentDomainService mailAgentDomainService;
    private final Crawler crawler;

    @Autowired
    public MailAgentDomainQualityMonitoringController(MailAgentDomainQualityMonitoringService qualityMonitoringService, MailAgentDomainService mailAgentDomainService, Crawler crawler) {
        this.qualityMonitoringService = qualityMonitoringService;
        this.mailAgentDomainService = mailAgentDomainService;
        this.crawler = crawler;
    }

    @GetMapping("/statistics_details")
    public ResponseEntity<Map<String, Map<String, Integer>>> details(@RequestParam("channelCode") Integer channelCode,
                                                                     @RequestParam("start") DateTime start,
                                                                     @RequestParam("end") DateTime end) {
        MailAgentDomain mailAgentDomain = mailAgentDomainService.getByChannelId(channelCode);
        Map<String, Map<String, Integer>> result = qualityMonitoringService.crawlDataStatisticsDetails(mailAgentDomain,
                start, end);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/crawl")
    public ResponseEntity<Void> crawl(@RequestParam("channelCode") Integer channelCode) {
        MailAgentDomain mailAgentDomain = mailAgentDomainService.getByChannelId(channelCode);
        crawler.crawl(mailAgentDomain);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
