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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ContractAuditTrailDTO>> getAllContractAuditTrail() {

        logger.info("Retrieving all contracts audit trail from DB");
        return new ResponseEntity<>(contractAuditTrailService.getAllContractAuditTrail(), HttpStatus.OK);
    }


    @ApiOperation(value = "Collect contracts autdit trail filter by criteria",
            notes = "根据指定参数获取契约监控记录列表",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<ContractAuditTrailDTO>> getContractAuditTrailByCriteria(
            @ApiParam("契约ID") @RequestParam(value = "contractID", required = false) String contractID,
            @ApiParam("契约修改动作") @RequestParam(value = "lastModifiedAction", required = false) String lastModifiedAction,
            @ApiParam("契约修改人") @RequestParam(value = "lastModifiedBy", required = false) String lastModifiedBy) {

        logger.info("Retrieving contracts audit trail info from DB.");
        return new ResponseEntity<>(
                contractAuditTrailService.getContractAuditTrailByCriteria(
                        contractID, lastModifiedAction, lastModifiedBy),
                HttpStatus.OK);
    }
}
