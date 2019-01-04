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
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<StreamContractDTO> getAllStreamContracts() {
        logger.info("Retrieving all streamContracts info from DB");
        return streamContractService.getAllStreamContract();
    }

    @ApiOperation(value = "Collect contracts filter by provider info in streamContract format for plugin",
            notes = "获取指定生产者相关的契约总列表",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/provider", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<StreamContractDTO> getStreamContractsByProviderInfo(
            @ApiParam(example = "provider-system") @RequestParam(value = "providerSystem", required = false) String providerSystem,
            @ApiParam(example = "provider") @RequestParam(value = "providerName", required = false) String providerName) {

        logger.info("Retrieving contracts info from DB filtering by provider info: {}/{}", providerSystem, providerName);
        return streamContractService.getStreamContractByProviderInfo(providerSystem,providerName);
    }

    @ApiOperation(value = "Collect contracts filter by consumer info in streamContract format for plugin",
            notes = "获取指定消费者相关的契约总列表",
            response = ContractDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功")})
    @RequestMapping(value = "/consumer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<StreamContractDTO> getStreamContractsByConsumerInfo(
            @ApiParam(example = "consumer-system") @RequestParam(value = "consumerSystem", required = false) String consumerSystem,
            @ApiParam(example = "consumer") @RequestParam(value = "consumerName", required = false) String consumerName) {

        logger.info("Retrieving contracts info from DB filtering by provider info: {}/{}", consumerSystem, consumerName);
        return streamContractService.getStreamContractByProviderInfo(consumerSystem,consumerName);
    }
}
