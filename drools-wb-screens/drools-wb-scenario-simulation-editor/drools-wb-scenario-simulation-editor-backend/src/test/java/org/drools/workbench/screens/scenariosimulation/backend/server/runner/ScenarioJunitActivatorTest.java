/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.scenariosimulation.backend.server.runner;

import java.util.List;
import java.util.stream.Stream;

import org.drools.workbench.screens.scenariosimulation.backend.server.ScenarioSimulationXMLPersistence;
import org.drools.workbench.screens.scenariosimulation.backend.server.runner.model.SimulationWithFileName;
import org.drools.workbench.screens.scenariosimulation.backend.server.util.ResourceHelper;
import org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModel;
import org.drools.workbench.screens.scenariosimulation.model.Simulation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.kie.api.runtime.KieContainer;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioJunitActivatorTest {

    @Mock
    private ScenarioSimulationXMLPersistence xmlReaderMock;

    @Mock
    private KieContainer kieContainerMock;

    @Mock
    private AbstractScenarioRunner runnerMock;

    @Mock
    private Simulation simulationMock;

    @Mock
    private SimulationWithFileName simulationWithFileNameMock;

    @Mock
    private ScenarioSimulationModel scenarioSimulationModelMock;

    @Mock
    private RunNotifier runNotifierMock;

    @Before
    public void setup() {
        when(xmlReaderMock.unmarshal(any())).thenReturn(scenarioSimulationModelMock);
        when(scenarioSimulationModelMock.getSimulation()).thenReturn(simulationMock);
    }

    @Test
    public void getChildrenTest() throws InitializationError {
        List<SimulationWithFileName> children = getScenarioJunitActivator().getChildren();
        Assert.assertEquals(1, children.size());
    }

    @Test
    public void runChildTest() throws InitializationError {
        getScenarioJunitActivator().runChild(simulationWithFileNameMock, runNotifierMock);
        verify(runnerMock, times(1)).run(runNotifierMock);
    }

    private ScenarioJunitActivator getScenarioJunitActivator() throws InitializationError {
        return new ScenarioJunitActivator(ScenarioJunitActivator.class) {
            @Override
            ScenarioSimulationXMLPersistence getXmlReader() {
                return xmlReaderMock;
            }

            @Override
            Stream<String> getResources() {
                return ResourceHelper.getResourcesByExtension("txt");
            }

            @Override
            KieContainer getKieContainer() {
                return kieContainerMock;
            }

            @Override
            AbstractScenarioRunner newRunner(KieContainer kieContainer, Simulation simulation, String path) {
                return runnerMock;
            }
        };
    }
}