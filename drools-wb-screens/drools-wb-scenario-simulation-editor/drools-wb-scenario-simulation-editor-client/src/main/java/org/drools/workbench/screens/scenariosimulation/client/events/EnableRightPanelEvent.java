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
package org.drools.workbench.screens.scenariosimulation.client.events;

import com.google.gwt.event.shared.GwtEvent;
import org.drools.workbench.screens.scenariosimulation.client.handlers.EnableRightPanelEventHandler;

/**
 * <code>GwtEvent</code> to <b>enable</b> the <code>RightPanelView</code>
 */
public class EnableRightPanelEvent extends GwtEvent<EnableRightPanelEventHandler> {

    public static Type<EnableRightPanelEventHandler> TYPE = new Type<>();

    /**
     * The string to use for filtering in right panel
     */
    private final String filterTerm;

    /**
     * flag to decide which kind of filter (<b>equals</b> or <b>not euals</b>) is to be applied.
     * Default to false (= <b>equals</b> filter)
     */
    private final boolean notEqualsSearch;

    /**
     * The string to <b>eventually</b> use to select the prooperty in the right panel
     */
    private final String propertyName;

    /**
     * Fire this event to show all the first-level data models <b>enabled</b> (i.e. <b>double-clickable</b> to map to an <i>instance</i> header/column)
     * and their properties <b>disabled</b> (i.e. <b>not double-clickable</b>)
     */
    public EnableRightPanelEvent() {
        filterTerm = null;
        notEqualsSearch = false;
        propertyName = null;
    }

    /**
     * Fire this event to show only the data model with the given name, <b>disabled</b> (i.e. <b>not double-clickable</b>)
     * and their properties <b>enabled</b> (i.e. <b>double-clickable</b> to map to a <i>property</i> header/column below the belonging data model instance one).
     * It show only results <b>equals</b> to filterTerm
     * @param filterTerm
     */
    public EnableRightPanelEvent(String filterTerm) {
        this.filterTerm = filterTerm;
        notEqualsSearch = false;
        propertyName = null;
    }

    /**
     * Fire this event to show only the data model with the given name, <b>disabled</b> (i.e. <b>not double-clickable</b>) and to highlight the given property
     * and their properties <b>enabled</b> (i.e. <b>double-clickable</b> to map to a <i>property</i> header/column below the belonging data model instance one).
     * It show only results <b>equals</b> to filterTerm
     * @param filterTerm
     * @param propertyName
     */
    public EnableRightPanelEvent(String filterTerm, String propertyName) {
        this.filterTerm = filterTerm;
        notEqualsSearch = false;
        this.propertyName = propertyName;
    }

    /**
     * Fire this event to filter the data model with the given name, <b>disabled</b> (i.e. <b>not double-clickable</b>)
     * and their properties <b>enabled</b> (i.e. <b>double-clickable</b> to map to a <i>property</i> header/column below the belonging data model instance one)
     * @param
     * @param notEqualsSearch set to <code>true</code> to perform a <b>not</b> filter, i.e. to show only results <b>different</b> than filterTerm
     */
    public EnableRightPanelEvent(String filterTerm, boolean notEqualsSearch) {
        this.filterTerm = filterTerm;
        this.notEqualsSearch = notEqualsSearch;
        propertyName = null;
    }

    @Override
    public Type<EnableRightPanelEventHandler> getAssociatedType() {
        return TYPE;
    }

    public String getFilterTerm() {
        return filterTerm;
    }

    public boolean isNotEqualsSearch() {
        return notEqualsSearch;
    }

    public String getPropertyName() {
        return propertyName;
    }

    @Override
    protected void dispatch(EnableRightPanelEventHandler handler) {
        handler.onEvent(this);
    }
}
