/*
*  Copyright (c) Microsoft. All rights reserved. Licensed under the MIT license. See full license at the bottom of this file.
*/

package com.microsoft.onenoteapi.service;

import com.microsoft.onenotevos.Envelope;
import com.microsoft.onenotevos.SectionGroup;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedString;

public interface SectionGroupsService {

    /**
     * @param version
     * @param filter
     * @param order
     * @param select
     * @param top
     * @param skip
     * @param search
     * @param callback
     */
    @GET("/{version}/me/notes/sectionGroups")
    void getSectionGroups(
            @Path("version") String version,
            @Query("filter") String filter,
            @Query("orderby") String order,
            @Query("select") String select,
            @Query("top") Integer top,
            @Query("skip") Integer skip,
            @Query("search") String search,
            Callback<Envelope<SectionGroup>> callback
    );

    @GET("/{version}/me/notes/notebooks/{id}/sectionGroups")
    void getSectionGroupsForNotebook(
            @Path("version") String version,
            @Path("id") String id,
            @Query("filter") String filter,
            @Query("orderby") String order,
            @Query("select") String select,
            @Query("top") Integer top,
            @Query("skip") Integer skip,
            @Query("search") String search,
            Callback<Envelope<SectionGroup>> callback

    );

    /**
     * POST to the sectiongroups resource of a notebook
     *
     * @param contentTypeHeader
     * @param version
     * @param id
     * @param content
     * @param callback
     */
    @POST("/{version}/me/notes/notebooks/{id}/sectiongroups")
    void postSectionGroupInNotebook(
            @Path("version") String version,
            @Header("Content-type") String contentTypeHeader,
            @Path("id") String id,
            @Body TypedString content,
            Callback<Envelope<SectionGroup>> callback
    );


    /**
     * POST to the sectiongroups resource of a section group
     *
     * @param contentTypeHeader
     * @param version
     * @param id
     * @param content
     * @param callback
     */
    @POST("/{version}/me/notes/sectiongroups/{id}/sectiongroups")
    void postSectionGroupInSectionGroup(
            @Path("version") String version,
            @Header("Content-type") String contentTypeHeader,
            @Path("id") String id,
            @Body TypedString content,
            Callback<Envelope<SectionGroup>> callback
    );
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