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

package org.apache.dolphinscheduler.dao.entity;

import org.apache.dolphinscheduler.common.utils.JSONUtils;
import org.apache.dolphinscheduler.plugin.task.api.model.DependentItem;
import org.apache.dolphinscheduler.plugin.task.api.model.DependentTaskModel;
import org.apache.dolphinscheduler.plugin.task.api.parameters.DependentParameters;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TaskInstanceTest {

    /**
     * test for TaskInstance.getDependence
     */
    @Test
    public void testTaskInstanceGetDependence() {
        TaskInstance taskInstance = new TaskInstance();
        taskInstance.setTaskParams(JSONUtils.toJsonString(getDependentParameters()));
    }

    /**
     *
     * @return
     */
    private DependentParameters getDependentParameters() {
        DependentParameters dependentParameters = new DependentParameters();
        List<DependentTaskModel> dependTaskList = new ArrayList<>();
        List<DependentItem> dependentItems = new ArrayList<>();
        DependentItem dependentItem = new DependentItem();
        dependentItem.setDepTaskCode(111L);
        dependentItem.setDefinitionCode(222L);
        dependentItem.setCycle("today");
        dependentItems.add(dependentItem);
        return dependentParameters;
    }
}
