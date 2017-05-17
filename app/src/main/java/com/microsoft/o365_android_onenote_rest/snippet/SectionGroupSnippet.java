/*
*  Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license. See full license at the bottom of this file.
*/

package com.microsoft.o365_android_onenote_rest.snippet;

import com.google.gson.JsonObject;
import com.microsoft.o365_android_onenote_rest.SnippetDetailFragment;
import com.microsoft.onenoteapi.service.SectionGroupsService;
import com.microsoft.onenotevos.Envelope;
import com.microsoft.onenotevos.Notebook;
import com.microsoft.onenotevos.SectionGroup;

import java.util.HashMap;
import java.util.Map;

import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedString;

import static com.microsoft.o365_android_onenote_rest.R.array.create_sectiongroup_in_notebook;
import static com.microsoft.o365_android_onenote_rest.R.array.create_sectiongroup_in_sectiongroup;
import static com.microsoft.o365_android_onenote_rest.R.array.list_sectiongroups;
import static com.microsoft.o365_android_onenote_rest.R.array.list_sectiongroups_in_a_notebook;

public abstract class SectionGroupSnippet<Result>
        extends AbstractSnippet<SectionGroupsService, Result> {

    public SectionGroupSnippet(Integer descriptionArray) {
        super(SnippetCategory.sectionGroupsSnippetCategory, descriptionArray);
    }

    public SectionGroupSnippet(Integer descriptionArray, Input input) {
        super(SnippetCategory.sectionGroupsSnippetCategory, descriptionArray, input);
    }

    static SectionGroupSnippet[] getSectionGroupsSnippets() {
        return new SectionGroupSnippet[]{
                // Marker element
                new SectionGroupSnippet(null) {
                    @Override
                    public void request(SectionGroupsService service, Callback callback) {
                        // Not implemented
                    }
                },

                /*
                 * Gets all of the Section groups for all of a user's notebooks
                 * @see http://dev.onenote.com/docs#/reference/get-sectiongroups/v10menotessectiongroupsfilterorderbyselectexpandtopskipcount
                 */
                new SectionGroupSnippet<Envelope<SectionGroup>>(list_sectiongroups) {


                    @Override
                    public void request(SectionGroupsService service,
                                        Callback<Envelope<SectionGroup>> callback) {
                        service.getSectionGroups(
                                getVersion(),
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                callback);

                    }
                },

                /*
                 * Gets all section groups for a given notebook
                 * @see http://dev.onenote.com/docs#/reference/get-sectiongroups/v10menotesnotebooksidsectiongroupsfilterorderbyselectexpandtopskipcount
                 */
                new SectionGroupSnippet<Envelope<SectionGroup>>(
                        list_sectiongroups_in_a_notebook, Input.Spinner) {

                    Map<String, Notebook> notebookMap = new HashMap<>();

                    @Override
                    public void setUp(Services services, final retrofit.Callback<String[]> callback) {
                        fillNotebookSpinner(services, callback, notebookMap);
                    }

                    @Override
                    public void request(SectionGroupsService service, Callback<Envelope<SectionGroup>> callback) {
                        Notebook notebook = notebookMap.get(
                                callback.getParams().get(
                                        SnippetDetailFragment.ARG_SPINNER_SELECTION));
                        service.getSectionGroupsForNotebook(getVersion(),
                                notebook.id,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                callback);
                    }
                },

                /*
                 * Creates a new section group with a name in a notebook specified by id
                 */
                new SectionGroupSnippet<Envelope<SectionGroup>>(create_sectiongroup_in_notebook, Input.Both) {

                    Map<String, Notebook> notebookMap = new HashMap<>();

                    @Override
                    public void setUp(
                            Services services,
                            final retrofit.Callback<String[]> callback) {
                        fillNotebookSpinner(services, callback, notebookMap);
                    }

                    //Create the JSON body of a new section request.
                    //The body sets the section name
                    TypedString createSectionGroup(String sectionName) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("displayName", sectionName);
                        return new TypedString(jsonObject.toString()) {
                            @Override
                            public String mimeType() {
                                return "application/json";
                            }
                        };
                    }

                    @Override
                    public void request(SectionGroupsService service, Callback<Envelope<SectionGroup>> callback) {

                        Notebook notebook = notebookMap.get(callback
                                .getParams()
                                .get(SnippetDetailFragment.ARG_SPINNER_SELECTION));

                        service.postSectionGroupInNotebook(
                                getVersion(),
                                "application/json",
                                notebook.id,
                                createSectionGroup(callback
                                        .getParams()
                                        .get(SnippetDetailFragment.ARG_TEXT_INPUT)),
                                callback
                        );
                    }
                },

                /*
                 * Creates a new section with a title in a notebook specified by id
                 */
                new SectionGroupSnippet<Envelope<SectionGroup>>(create_sectiongroup_in_sectiongroup, Input.Both) {

                    Map<String, SectionGroup> sectionGroupMap = new HashMap<>();

                    @Override
                    public void setUp(
                            Services services,
                            final retrofit.Callback<String[]> callback) {
                        fillSectionGroupSpinner(services, callback, sectionGroupMap);
                    }

                    //Create the JSON body of a new section request.
                    //The body sets the section name
                    TypedString createSectionGroupInSectionGroup(String sectionName) {
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty("displayName", sectionName);
                        return new TypedString(jsonObject.toString()) {
                            @Override
                            public String mimeType() {
                                return "application/json";
                            }
                        };
                    }

                    @Override
                    public void request(SectionGroupsService service,
                                        Callback<Envelope<SectionGroup>> callback) {

                        SectionGroup sectionGroup = sectionGroupMap.get(callback
                                .getParams()
                                .get(SnippetDetailFragment.ARG_SPINNER_SELECTION));

                        service.postSectionGroupInSectionGroup(
                                getVersion(),
                                "application/json",
                                sectionGroup.id,
                                createSectionGroupInSectionGroup(callback
                                        .getParams()
                                        .get(SnippetDetailFragment.ARG_TEXT_INPUT)),
                                callback
                        );
                    }
                }
        };
    }

    @Override
    public abstract void request(SectionGroupsService service, Callback<Result> callback);

    protected void fillNotebookSpinner(
            Services services,
            final retrofit.Callback<String[]> callback,
            final Map<String, Notebook> notebookMap) {
        services.mNotebooksService.getNotebooks(getVersion(),
                null,
                null,
                null,
                null,
                null,
                null,
                new Callback<Envelope<Notebook>>() {

                    @Override
                    public void success(Envelope<Notebook> notebookEnvelope, Response response) {
                        Notebook[] notebooks = notebookEnvelope.value;
                        String[] bookNames = new String[notebooks.length];
                        for (int i = 0; i < notebooks.length; i++) {
                            bookNames[i] = notebooks[i].displayName;
                            notebookMap.put(notebooks[i].displayName, notebooks[i]);
                        }
                        callback.success(bookNames, response);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }

                    @Override
                    public Map<String, String> getParams() {
                        return null;
                    }
                });
    }

    protected void fillSectionGroupSpinner(
            Services services,
            final retrofit.Callback<String[]> callback,
            final Map<String, SectionGroup> sectionGroupMap) {
        services.mSectionGroupsService.getSectionGroups(getVersion(),
                null,
                null,
                null,
                null,
                null,
                null,
                new Callback<Envelope<SectionGroup>>() {

                    @Override
                    public void success(Envelope<SectionGroup> sectionGroupEnvelope, Response response) {
                        SectionGroup[] sectionGroups = sectionGroupEnvelope.value;
                        String[] sectionGroupNames = new String[sectionGroups.length];
                        for (int i = 0; i < sectionGroups.length; i++) {
                            sectionGroupNames[i] = sectionGroups[i].displayName;
                            sectionGroupMap.put(sectionGroups[i].displayName, sectionGroups[i]);
                        }
                        callback.success(sectionGroupNames, response);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }

                    @Override
                    public Map<String, String> getParams() {
                        return null;
                    }
                });
    }

}
// *********************************************************
//
// MsGraph_Android_REST_API_Explorer, https://github.com/OneNoteDev/MsGraph_Android_REST_API_Explorer
//
// Copyright (c) Microsoft Corporation
// All rights reserved.
//
// MIT License:
// Permission is hereby granted, free of charge, to any person obtaining
// a copy of this software and associated documentation files (the
// "Software"), to deal in the Software without restriction, including
// without limitation the rights to use, copy, modify, merge, publish,
// distribute, sublicense, and/or sell copies of the Software, and to
// permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
// LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
// OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
// WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
// *********************************************************