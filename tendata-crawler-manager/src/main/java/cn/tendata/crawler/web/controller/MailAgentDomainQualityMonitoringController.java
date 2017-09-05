package cn.tendata.crawler.web.controller;

import cn.xinbee.rcs.core.Crawler;
import cn.xinbee.rcs.service.MailAgentDomainQualityMonitoringService;
import cn.xinbee.rcs.service.MailAgentDomainService;
import java.util.Map;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/quality_monitor")
public class MailAgentDomainQualityMonitoringController {

    private final MailAgentDomainQualityMonitoringService qualityMonitoringService;

    @Autowired
    public MailAgentDomainQualityMonitoringController(MailAgentDomainQualityMonitoringService qualityMonitoringService, MailAgentDomainService mailAgentDomainService, Crawler crawler) {
        this.qualityMonitoringService = qualityMonitoringService;
    }

    @GetMapping("/statistics_details")
    public ResponseEntity<Map<String, Map<String, Integer>>> details(@RequestParam("channelCode") Integer channelCode,
                                                                     @RequestParam("start") DateTime start,
                                                                     @RequestParam("end") DateTime end) {
        Map<String, Map<String, Integer>> result = qualityMonitoringService.crawlDataStatisticsDetails(channelCode, start, end);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/crawl")
    public ResponseEntity<Void> crawl(@RequestParam("channelCode") Integer channelCode) {
        qualityMonitoringService.crawl(channelCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
