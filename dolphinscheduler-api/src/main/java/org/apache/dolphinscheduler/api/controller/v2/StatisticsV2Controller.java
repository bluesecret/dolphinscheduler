/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler.api.controller.v2;

import static org.apache.dolphinscheduler.api.enums.Status.COUNT_WORKFLOW_DEFINITION_USER_ERROR;
import static org.apache.dolphinscheduler.api.enums.Status.QUERY_ONE_TASK_STATES_COUNT_ERROR;
import static org.apache.dolphinscheduler.api.enums.Status.QUERY_ONE_WORKFLOW_STATE_COUNT_ERROR;
import static org.apache.dolphinscheduler.api.enums.Status.QUERY_TASK_STATES_COUNT_ERROR;
import static org.apache.dolphinscheduler.api.enums.Status.QUERY_WORKFLOW_STATES_COUNT_ERROR;

import org.apache.dolphinscheduler.api.controller.BaseController;
import org.apache.dolphinscheduler.api.dto.DefineUserDto;
import org.apache.dolphinscheduler.api.dto.TaskCountDto;
import org.apache.dolphinscheduler.api.dto.project.StatisticsStateRequest;
import org.apache.dolphinscheduler.api.exceptions.ApiException;
import org.apache.dolphinscheduler.api.service.DataAnalysisService;
import org.apache.dolphinscheduler.api.utils.Result;
import org.apache.dolphinscheduler.common.constants.Constants;
import org.apache.dolphinscheduler.dao.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
/**
 * StatisticsV2 controller
 */
@Tag(name = "STATISTICS_V2")
@RestController
@RequestMapping("/v2/statistics")
public class StatisticsV2Controller extends BaseController {

    @Autowired
    private DataAnalysisService dataAnalysisService;

    /**
     * query all workflow states count
     *
     * @param loginUser              login user
     * @param statisticsStateRequest statisticsStateRequest
     * @return workflow states count
     */
    @Operation(summary = "queryAllWorkflowStatesCount", description = "QUERY_ALL_WORKFLOW_STATES_COUNT")
    @GetMapping(value = "/workflows/states/count")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(QUERY_WORKFLOW_STATES_COUNT_ERROR)
    public Result<TaskCountDto> queryWorkflowStatesCounts(@Parameter(hidden = true) @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                                          @RequestBody(required = false) StatisticsStateRequest statisticsStateRequest) {
        TaskCountDto taskCountDto = dataAnalysisService.countWorkflowStates(loginUser, statisticsStateRequest);
        return Result.success(taskCountDto);
    }

    /**
     * query one workflow states count
     *
     * @param loginUser    login user
     * @param workflowCode workflowCode
     * @return workflow states count
     */
    @Operation(summary = "queryOneWorkflowStatesCount", description = "QUERY_One_WORKFLOW_STATES_COUNT")
    @GetMapping(value = "/{workflowCode}/states/count")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(QUERY_ONE_WORKFLOW_STATE_COUNT_ERROR)
    public Result<TaskCountDto> queryOneWorkflowStates(@Parameter(hidden = true) @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                                       @PathVariable("workflowCode") Long workflowCode) {
        TaskCountDto taskCountDto = dataAnalysisService.countOneWorkflowStates(loginUser, workflowCode);
        return Result.success(taskCountDto);
    }

    /**
     * query all task states count
     *
     * @param loginUser              login user
     * @param statisticsStateRequest statisticsStateRequest
     * @return tasks states count
     */
    @Operation(summary = "queryAllTaskStatesCount", description = "QUERY_ALL_TASK_STATES_COUNT")
    @GetMapping(value = "/tasks/states/count")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(QUERY_TASK_STATES_COUNT_ERROR)
    public Result<TaskCountDto> queryTaskStatesCounts(@Parameter(hidden = true) @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                                      @RequestBody(required = false) StatisticsStateRequest statisticsStateRequest) {
        TaskCountDto taskCountDto = dataAnalysisService.countTaskStates(loginUser, statisticsStateRequest);
        return Result.success(taskCountDto);
    }

    /**
     * query one task states count
     *
     * @param loginUser login user
     * @param taskCode  taskCode
     * @return tasks states count
     */
    @Operation(summary = "queryOneTaskStatesCount", description = "QUERY_ONE_TASK_STATES_COUNT")
    @GetMapping(value = "/tasks/{taskCode}/states/count")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(QUERY_ONE_TASK_STATES_COUNT_ERROR)
    public Result<TaskCountDto> queryOneTaskStatesCounts(@Parameter(hidden = true) @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                                         @PathVariable("taskCode") Long taskCode) {
        TaskCountDto taskCountDto = dataAnalysisService.countOneTaskStates(loginUser, taskCode);
        return Result.success(taskCountDto);
    }

    /**
     * statistics the workflow quantities of certain user
     *
     * @param loginUser              login user
     * @param statisticsStateRequest statisticsStateRequest
     * @return workflow count in project code
     */
    @Operation(summary = "countDefinitionV2ByUserId", description = "COUNT_WORKFLOW_DEFINITION_V2_BY_USERID_NOTES")
    @GetMapping(value = "/workflows/users/count")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(COUNT_WORKFLOW_DEFINITION_USER_ERROR)
    public Result<DefineUserDto> countDefinitionByUser(@Parameter(hidden = true) @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                                       @RequestBody(required = false) StatisticsStateRequest statisticsStateRequest) {
        // todo: directly use StatisticsStateRequest
        throw new UnsupportedOperationException("not supported");
    }

    /**
     * statistics the workflow quantities of certain userId
     *
     * @param loginUser login user
     * @param userId    userId
     * @return workflow count in project code
     */
    @Operation(summary = "countDefinitionV2ByUser", description = "COUNT_WORKFLOW_DEFINITION_V2_BY_USER_NOTES")
    @GetMapping(value = "/workflows/users/{userId}/count")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(COUNT_WORKFLOW_DEFINITION_USER_ERROR)
    public Result<DefineUserDto> countDefinitionByUserId(@Parameter(hidden = true) @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                                         @PathVariable("userId") Integer userId) {
        DefineUserDto defineUserDto = dataAnalysisService.countDefinitionByUserV2(loginUser, userId, null);
        return Result.success(defineUserDto);
    }

    /**
     * statistics the workflow quantities of certain userId and releaseState
     *
     * @param loginUser    login user
     * @param userId       userId
     * @param releaseState releaseState
     * @return workflow count in project code
     */
    @Operation(summary = "countDefinitionV2ByUser", description = "COUNT_WORKFLOW_DEFINITION_V2_BY_USER_NOTES")
    @GetMapping(value = "/workflows/users/{userId}/{releaseState}/count")
    @ResponseStatus(HttpStatus.OK)
    @ApiException(COUNT_WORKFLOW_DEFINITION_USER_ERROR)
    public Result<DefineUserDto> countDefinitionByUserState(@Parameter(hidden = true) @RequestAttribute(value = Constants.SESSION_USER) User loginUser,
                                                            @PathVariable("userId") Integer userId,
                                                            @PathVariable("releaseState") Integer releaseState) {
        DefineUserDto defineUserDto =
                dataAnalysisService.countDefinitionByUserV2(loginUser, userId, releaseState);
        return Result.success(defineUserDto);
    }
}
