package com.github.abigail830.contractlistener.controller;

import com.github.abigail830.contractlistener.domain.ContractDTO;
import com.github.abigail830.contractlistener.domain.StreamContractDTO;
import com.github.abigail830.contractlistener.service.StreamContractService;
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
@RequestMapping("/v1/streamContracts")
public class StreamContractController {

    private static final Logger logger = LoggerFactory.getLogger(StreamContractController.class);

    @Autowired
    StreamContractService streamContractService;

    @ApiOperation(value = "Collect full list of contracts in streamContract format for plugin",
            notes = "为插件获取契约总列表",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<StreamContractDTO> getAllStreamContracts() {
        logger.info("Retrieving all streamContracts info from DB");
        return streamContractService.getAllStreamContract();
    }


    @ApiOperation(value = "Collect contracts filter by criteria in streamContract format for plugin",
            notes = "获取指定条件相关的契约总列表",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<StreamContractDTO> getStreamContractsByCriteria(
            @ApiParam("生产者系统") @RequestParam(value = "providerSystem", required = false) String providerSystem,
            @ApiParam("生产者ID") @RequestParam(value = "providerID", required = false) String providerID,
            @ApiParam("消费者系统") @RequestParam(value = "consumerSystem", required = false) String consumerSystem,
            @ApiParam("消费者ID") @RequestParam(value = "consumerID", required = false) String consumerID) {

        logger.info("Retrieving stream-contracts info from DB");
        return streamContractService.getStreamContractByExample(providerSystem, providerID, consumerSystem, consumerID);
    }

}
