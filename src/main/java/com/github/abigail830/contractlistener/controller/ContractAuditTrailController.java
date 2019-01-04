package com.github.abigail830.contractlistener.controller;

import com.github.abigail830.contractlistener.domain.ContractAuditTrailDTO;
import com.github.abigail830.contractlistener.domain.ContractDTO;
import com.github.abigail830.contractlistener.service.ContractAuditTrailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/contractAuditTrail")
public class ContractAuditTrailController {

    private static final Logger logger = LoggerFactory.getLogger(ContractAuditTrailController.class);

    @Autowired
    private ContractAuditTrailService contractAuditTrailService;


    @ApiOperation(value = "Collect full list of contracts audit trail",
            notes = "获取契约监控记录总列表",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ContractAuditTrailDTO> getAllContractAuditTrail() {
        logger.info("Retrieving all contracts audit trail from DB");
        return contractAuditTrailService.getAllContractAuditTrail();
    }

    @ApiOperation(value = "Collect contracts autdit trail filter by specified url",
            notes = "获取指定URL路径的契约监控记录总列表",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/url", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ContractAuditTrailDTO> getContractAuditTrailByURL(
            @ApiParam(example = "/inf0/name") @RequestParam(value = "url") String url) {

        logger.info("Retrieving contracts audit trail info from DB filtering by url:{}", url);
        return contractAuditTrailService.getContractDomainByUrl(url);
    }
}
