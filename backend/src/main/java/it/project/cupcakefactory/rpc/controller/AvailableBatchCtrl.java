package it.project.cupcakefactory.rpc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import it.project.cupcakefactory.dto.BatchDto;
import it.project.cupcakefactory.service.BatchService;
import it.project.cupcakefactory.views.BatchView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@RequestMapping("/rpc/batches")
@Controller
public class AvailableBatchCtrl {

    @Autowired
    private BatchService batchService;

    @RequestMapping(value = "/available", method = RequestMethod.GET)
    @ResponseBody
    @JsonView(BatchView.class)
    public List<BatchDto> getAvailableBatches(@RequestParam("untilDate") Long untilDateMillis) {
        Date untilDate = new Date();
        if (untilDateMillis != null)
            untilDate = new Date(untilDateMillis);
        Date expireDate = batchService.getExpireDate(untilDate);
        return batchService.getBatches(expireDate, untilDate, false);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    @JsonView(BatchView.class)
    public List<BatchDto> getAllBatches(@RequestParam("expireTarget") Long expireTargetMillis) {
        Date expireTarget = new Date();
        if (expireTargetMillis != null)
            expireTarget = new Date(expireTargetMillis);
        return batchService.getAllBatches(expireTarget);
    }

}
